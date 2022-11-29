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
	private int vidas = 5;
	private int inimigosAtivos;

	// ELEMENTOS DO JOGO
	private int pontos = 0;
	private JPanel tela;
	private Player player;
	private Inimigo inimigos[];
	private Tiro tiro, tiroInimigo;
	private int inimigoDir = 1;
	private boolean moveInimigos;
	private boolean novaLinha;

	public Jogo() {
		this.addKeyListener(new KeyListener());

		tela = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());

				// PINTANDO ELEMENTOS
				paintElementos(g);
				paintCabecalho(g);

			}
		};

		// CONFIGURAÇÃO DA JANELA
		getContentPane().add(tela);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void paintCabecalho(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawLine(0, 60, tela.getWidth(), 60);
		g.drawString("Pontos: " + pontos, tela.getWidth() / 2 + 50, 35);
		g.drawString("Vidas: " + vidas, tela.getWidth() / 2 - 80, 35);
	}

	private void paintElementos(Graphics g) {
		tiro.paintIfAtivo(g);
		tiroInimigo.paintIfAtivo(g);
		player.paint(g);
		for (Inimigo inimigo : inimigos)
			inimigo.paintIfAtivo(g);
	}

	private void inicia() {
		carregajogo();
		jogando = true;
		long prxAtt = 0;
		int contadorMoveInimigos = 0;

		while (jogando) {
			if (System.currentTimeMillis() >= prxAtt) {
				if (contadorMoveInimigos > FPS) {
					moveInimigos = true;
					contadorMoveInimigos = 0;
				}
				atualiza(); // ATUALIZA A PARTE LÓGICA DO JOGO
				tela.repaint(); // MÉTODO QUE REPINTA A TELA
				contadorMoveInimigos++;
				prxAtt = System.currentTimeMillis() + 1000 / FPS;
			}
		}
	}

	private void carregajogo() {
		inimigoDir = 1;
		moveInimigos = false;
		novaLinha = false;
		inimigosAtivos = quantidadeInimigos;

		player = new Player(0, 0, 40, 40, 5);
		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() - player.getAltura() - espacamento);
		player.setAtivo(true);

		tiro = new Tiro(0, 0, 5, 15, 15);
		tiro.setCor(Color.BLUE);

		inimigos = new Inimigo[quantidadeInimigos];
		for (int i = 0; i < inimigos.length; i++) {
			int espaco = i * 30 + espacamento * (i + 1);
			inimigos[i] = new Inimigo(espaco + 2, 60 + espacamento, 25, 25, 1);
			inimigos[i].setAtivo(true);
		}
		tiroInimigo = new Tiro(0, 0, 5, 10, 10);
		tiroInimigo.setCor(Color.RED);
	}

	private void atualiza() {
		if (vidas == 0 || inimigosAtivos == 0)
			jogando = false;

		movimentaPlayer();
		tiroPlayer();
		verificaInimigos();
		movimentaTiroInimigo();
		tiroInimigoOnPlayer();
	}

	private void tiroPlayer() {
		if (KeyListener.space && !tiro.isAtivo()) {
			tiro.setPx(player.getPx() + player.getLargura() / 2 - tiro.getLargura() / 2);
			tiro.setPy(player.getPy());
			tiro.setAtivo(true);
		}
		if (tiro.getPy() < 60)
			tiro.setAtivo(false);
		if (tiro.isAtivo())
			tiro.incPy(tiro.getVelocidade() * -1);
	}

	private void tiroInimigoOnPlayer() {
		if (Util.colide(player, tiroInimigo)) {
			vidas--;
			tiroInimigo.setAtivo(false);
		}
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
		boolean colideBordas = false;

		for (Inimigo inimigo : inimigos) {

			if (!inimigo.isAtivo())
				continue;

			if (inimigo.getPy() >= player.getPy())
				jogando = false;

			if (tiro.isAtivo() && inimigo.isAtivo() && Util.colide(inimigo, tiro)) {
				inimigo.setAtivo(false);
				inimigosAtivos--;
				pontos += 10;
				tiro.setAtivo(false);
			}

			if (moveInimigos) {
				if (novaLinha)
					inimigo.incPy(inimigo.getAltura() + espacamento);
				else
					inimigo.incPx(espacamento * inimigoDir);
				if (!novaLinha && !colideBordas) {
					int pxEsq = inimigo.getPx() - espacamento;
					int pxDir = inimigo.getPx() + inimigo.getLargura() + espacamento;
					if (pxEsq <= 0 || pxDir >= tela.getWidth())
						colideBordas = true;
				}

				if (!tiroInimigo.isAtivo() && inimigo.getPx() > player.getPx()
						&& inimigo.getPx() < player.getPx() + player.getLargura()) {
					tiroInimigo.setPx(inimigo.getPx() + inimigo.getLargura() / 2 - tiroInimigo.getLargura() / 2);
					tiroInimigo.setPy(inimigo.getPy());
					tiroInimigo.setAtivo(true);
				}
			}
		}
		if (moveInimigos && novaLinha) {
			inimigoDir *= -1;
			novaLinha = false;
		} else if (moveInimigos && colideBordas) {
			novaLinha = true;
		}
		moveInimigos = false;
	}

	private void movimentaTiroInimigo() {
		if (tiroInimigo.getPy() > tela.getHeight())
			tiroInimigo.setAtivo(false);
		if (tiroInimigo.isAtivo())
			tiroInimigo.incPy(tiroInimigo.getVelocidade());
	}

	public static void main(String[] args) {
		new Jogo().inicia();
	}

}
