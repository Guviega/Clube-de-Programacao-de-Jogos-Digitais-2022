package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame {

	private boolean jogando = true;
	private int FPS = 20;
	private int ms = 1000 / FPS;

	private int LARGURA = 480, ALTURA = 640;
	private JPanel tela;
	private int linhaLimite;

	// PONTUAÇÃO
	private int pontos = 0;

	private Elemento player;

	private Elemento inimigos[];
	private int inimigosQTD = 7, inimigosSPC = 15;

	private Elemento tiro;

	public Janela() {

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Util.setaTecla(e.getKeyCode(), false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Util.setaTecla(e.getKeyCode(), true);
			}
		});

		player = new Elemento(0, 0, 50, 50, 5, "/img/player.png");

		inimigos = new Elemento[inimigosQTD];
		for (int i = 0; i < inimigos.length; i++) {
			int espaco = i * 50 + inimigosSPC * (i + 1);
			inimigos[i] = new Elemento(espaco, 0, 50, 50, 1, "/img/inimigo.png");
		}

		tiro = new Elemento(0, 0, 4, 0, 0, "");

		tela = new JPanel() {

			public void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());

				g.setColor(Color.RED);
				g.fillRect(tiro.getPx(), tiro.getPy(), tiro.getLargura(), tela.getHeight());

				// AO INVÉS DE DESENHARMOS FORMAS NATIVAS, USAMOS O MÉTODO
				// drawImage PARA DESENHAR IMAGENS PARA CADA ELEMENTO
				g.drawImage(player.getImage(), player.getPx(), player.getPy(), player.getLargura(), player.getAltura(),
						null);

				for (Elemento inimigo : inimigos) {
					g.drawImage(inimigo.getImage(), inimigo.getPx(), inimigo.getPy(), inimigo.getLargura(),
							inimigo.getAltura(), null);
				}
				//////////////////////////////////////////////////////////

				g.setColor(Color.WHITE);
				g.fillRect(0, linhaLimite, tela.getWidth(), 2);

				// DESENHA PONTUAÇÃO NO CANTO DA TELA
				g.drawString("Pontos: " + String.valueOf(pontos), 10, 20);
				g.drawString("Ultima: " + PontuacaoManage.getUltima(), 10, 35);
				g.drawString("Recorde: " + PontuacaoManage.getRecorde(), 10, 50);

			}
		};

		getContentPane().add(tela);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		linhaLimite = tela.getHeight() - 50;

		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() - player.getAltura());
	}

	public void inicia() {
		long prxAtt = 0;

		while (jogando) {

			if (System.currentTimeMillis() >= prxAtt) {
				atualiza();
				tela.repaint();

				prxAtt = System.currentTimeMillis() + ms;
			}
		}

		// QUANDO JOGANDO = FALSE, REALIZA A ATUALIZAÇÃO DAS PONTUAÇÕES E DO ARQUIVO
		if (pontos > PontuacaoManage.getRecorde())
			PontuacaoManage.setRecorde(pontos);
		PontuacaoManage.setUltima(pontos);
		PontuacaoManage.escrever();

	}

	public void atualiza() {

		if (Util.right)
			player.incPx(player.getVelocidade());
		if (Util.left)
			player.incPx(player.getVelocidade() * -1);

		if (player.getPx() > tela.getWidth())
			player.setPx(0);
		if (player.getPx() < 0)
			player.setPx(tela.getWidth() - player.getLargura());

		tiro.setPx(player.getPx() + player.getLargura() / 2 - tiro.getLargura() / 2);

		for (Elemento inimigo : inimigos) {

			if (inimigo.getPy() > linhaLimite - inimigo.getAltura()) {
				jogando = false;
				break;
			}

			if (Util.colide(inimigo, tiro) && inimigo.getPy() > 0) {
				inimigo.incPy(inimigo.getVelocidade() * -2);
				tiro.setPy(inimigo.getPy() + inimigo.getAltura() / 2);
			} else
				inimigo.incPy(inimigo.getVelocidade());
		}

		// A CADA ATUALIZAÇÃO, A PONTUAÇÃO AUMENTA EM 1 (PONTOS POR SEGUNDO = FPS)
		pontos += 1;

	}

	public static void main(String[] args) {
		// É EXTREMAMENTE NECESSÁRIO LER O ARQUIVO ANTES DE INICIAR O JOGO
		PontuacaoManage.ler();

		new Janela().inicia();
	}

}
