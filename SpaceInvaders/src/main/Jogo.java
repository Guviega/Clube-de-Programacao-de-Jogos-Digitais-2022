package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Player;
import util.KeyListener;

public class Jogo extends JFrame {

	// CONFIGURAÇÃO DO JOGO
	private boolean jogando;
	private int FPS = 30;
	private int LARGURA = 540, ALTURA = 480;
	private int espacamento = 8; // ESPACAMENTO PADRÃO DO JOGO

	// ELEMENTOS DO JOGO
	private JPanel tela;
	private Player player;

	public Jogo() {
		this.addKeyListener(new KeyListener());

		tela = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());

				// PINTANDO ELEMENTO PLAYER
				player.paint(g);

			}
		};

		// CONFIGURAÇÃO DA JANELA
		getContentPane().add(tela);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
