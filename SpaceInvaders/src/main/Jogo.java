package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Inimigo;
import model.Player;
import util.KeyListener;

public class Jogo extends JFrame {

	// CONFIGURAÇÃO DO JOGO
	private boolean jogando;
	private int FPS = 30;
	private int LARGURA = 540, ALTURA = 480;
	private int espacamento = 8; // ESPACAMENTO PADRÃO DO JOGO
	private int quantidadeInimigos = 13;

	// ELEMENTOS DO JOGO
	private JPanel tela;
	private Player player;
	private Inimigo inimigos[];

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
		player = new Player(0, 0, 40, 40, 5);
		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() - player.getAltura() - espacamento);
		
		inimigos = new Inimigo[quantidadeInimigos];
		for (int i = 0; i < inimigos.length; i++) {
			int espaco = i * 30 + espacamento * (i + 1);
			inimigos[i] = new Inimigo(espaco + 2, espacamento, 25, 25, 1);
			inimigos[i].setAtivo(true);
		}
	}

	private void atualiza() {
		movimentaPlayer();
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

	public static void main(String[] args) {
		new Jogo().inicia();
	}

}
