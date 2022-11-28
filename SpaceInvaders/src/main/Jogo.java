package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Quadrado;

public class Jogo extends JFrame {

	// CONFIGURA��O DO JOGO
	private boolean jogando;
	private int FPS = 30;
	private int LARGURA = 540, ALTURA = 480;

	// ELEMENTOS DO JOGO
	private JPanel tela;
	private Quadrado quadrado;

	public Jogo() {
		// TELA DE "PINTURA" DO JOGO
		tela = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				// PINTA FUNDO PRETO
				g.setColor(Color.BLACK); // DEFINE COR DE PINTURA
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight()); // PINTA OBJETO RETANGULAR DO TAMANHO DA TELA COMO
			
				//PINTANDO ELEMENTO QUADRADO
				quadrado.paint(g);
				
			}
		};

		// CONFIGURA��O DA JANELA
		getContentPane().add(tela); // ADICIONA JPANEL � JANELA
		setSize(LARGURA, ALTURA); // DEFINE TAMANHO DA JANELA
		setResizable(false); // DEFINE SE O USU�RIO PODER� REDIMENSIONAR A JANELA
		setDefaultCloseOperation(EXIT_ON_CLOSE); // IMPORTANTE: DEFINE 'X' DO WINDOWS COMO ENCERRADOR DO PROCESSO
		setVisible(true); // DEFINE VISIBILIDADE DA JANELA
	}

	// M�TODO QUE FAR� A ATUALIZA��O DA TELA DO JOGO
	// **UMA ANIMA��O � UMA SUCESS�O DE ATUALIZA��ES NA TELA, CONFORME O FPS
	private void inicia() {
		carregajogo();
		jogando = true;
		long prxAtt = 0;

		while (jogando) {
			if (System.currentTimeMillis() >= prxAtt) {
				quadrado.incPx(quadrado.getVelocidade()); //ANIMA��O DO QUADRADO
				tela.repaint(); // M�TODO QUE REPINTA A TELA
				prxAtt = System.currentTimeMillis() + 1000 / FPS;
			}
		}
	}

	private void carregajogo() {
		quadrado = new Quadrado(0, tela.getHeight()/2, 50, 50, 1);
		quadrado.setCor(Color.RED);
	}

	public static void main(String[] args) {
		new Jogo().inicia();
	}

}
