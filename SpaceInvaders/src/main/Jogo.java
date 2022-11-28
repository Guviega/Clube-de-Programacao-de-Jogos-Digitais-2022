package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Quadrado;

public class Jogo extends JFrame {

	// CONFIGURAÇÃO DO JOGO
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

		// CONFIGURAÇÃO DA JANELA
		getContentPane().add(tela); // ADICIONA JPANEL À JANELA
		setSize(LARGURA, ALTURA); // DEFINE TAMANHO DA JANELA
		setResizable(false); // DEFINE SE O USUÁRIO PODERÁ REDIMENSIONAR A JANELA
		setDefaultCloseOperation(EXIT_ON_CLOSE); // IMPORTANTE: DEFINE 'X' DO WINDOWS COMO ENCERRADOR DO PROCESSO
		setVisible(true); // DEFINE VISIBILIDADE DA JANELA
	}

	// MÉTODO QUE FARÁ A ATUALIZAÇÃO DA TELA DO JOGO
	// **UMA ANIMAÇÃO É UMA SUCESSÃO DE ATUALIZAÇÕES NA TELA, CONFORME O FPS
	private void inicia() {
		carregajogo();
		jogando = true;
		long prxAtt = 0;

		while (jogando) {
			if (System.currentTimeMillis() >= prxAtt) {
				quadrado.incPx(quadrado.getVelocidade()); //ANIMAÇÃO DO QUADRADO
				tela.repaint(); // MÉTODO QUE REPINTA A TELA
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
