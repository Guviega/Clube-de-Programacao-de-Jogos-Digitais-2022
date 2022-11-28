package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Inimigo;
import model.Player;
import model.Tiro;
import util.KeyListener;
import util.Util;

public class Jogo extends JFrame {

	// CONFIGURAÇÃO DO JOGO
	private boolean jogando;
	private int FPS = 30;
	private int LARGURA = 540, ALTURA = 480;
	private int espacamento = 8; // ESPACAMENTO PADRÃO DO JOGO
	private int quantidadeInimigos = 13;
	private int inimigosAtivos;

	// ELEMENTOS DO JOGO
	private JPanel tela;
	private Player player;
	private Inimigo inimigos[];
	private Tiro tiro;

	public Jogo() {
		this.addKeyListener(new KeyListener());

		tela = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());

				// PINTANDO ELEMENTOS
				paintElementos(g);

			}
		};

		// CONFIGURAÇÃO DA JANELA
		getContentPane().add(tela);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void paintElementos(Graphics g) {
		tiro.paintIfAtivo(g);
		player.paint(g);
		for (Inimigo inimigo : inimigos)
			inimigo.paintIfAtivo(g);
	}

	private void inicia() {
		carregajogo();
		jogando = true;
		long prxAtt = 0;

		while (jogando) {
			if (System.currentTimeMillis() >= prxAtt) {
				atualiza(); //ATUALIZA A PARTE LÓGICA DO JOGO
				tela.repaint(); // MÉTODO QUE REPINTA A TELA
				prxAtt = System.currentTimeMillis() + 1000 / FPS;
			}
		}
	}

	private void carregajogo() {
		inimigosAtivos = quantidadeInimigos;
		
		player = new Player(0, 0, 40, 40, 5);
		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() - player.getAltura() - espacamento);
		
		tiro = new Tiro(0, 0, 5, 15, 15);
		tiro.setCor(Color.BLUE);
		
		inimigos = new Inimigo[quantidadeInimigos];
		for (int i = 0; i < inimigos.length; i++) {
			int espaco = i * 30 + espacamento * (i + 1);
			inimigos[i] = new Inimigo(espaco + 2, espacamento, 25, 25, 1);
			inimigos[i].setAtivo(true);
		}
	}

	private void atualiza() {
		movimentaPlayer();
		tiroPlayer();
		verificaInimigos();
	}
	
	private void tiroPlayer() {
		if (inimigosAtivos == 0)
			jogando = false;
		
		if (KeyListener.space && !tiro.isAtivo()) {
			tiro.setPx(player.getPx() + player.getLargura() / 2 - tiro.getLargura() / 2);
			tiro.setPy(player.getPy());
			tiro.setAtivo(true);
		}
		if (tiro.getPy() < 0)
			tiro.setAtivo(false);
		if (tiro.isAtivo())
			tiro.incPy(tiro.getVelocidade() * -1);
	}

	private void movimentaPlayer() {
		if (KeyListener.right)
			player.incPx(player.getVelocidade());
		if (KeyListener.left)
			player.incPx(-player.getVelocidade());
		if (player.getPx() > tela.getWidth())
			player.setPx(0);
		if (player.getPx() < 0)
			player.setPx(tela.getWidth() - player.getLargura());
	}
	
	private void verificaInimigos() {
		for (Inimigo inimigo : inimigos) {

			if (!inimigo.isAtivo())
				continue;

			if (tiro.isAtivo() && inimigo.isAtivo() && Util.colide(inimigo, tiro)) {
				inimigo.setAtivo(false);
				inimigosAtivos--;
				tiro.setAtivo(false);
			}
		}
	}

	public static void main(String[] args) {
		new Jogo().inicia();
	}

}
