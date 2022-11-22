package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JFrame {

	// CONFIGURAÇÃO DO JOGO
	private int LARGURA = 540, ALTURA = 480;

	// ELEMENTOS DO JOGO
	private JPanel tela;

	public Jogo() {
		//TELA DE "PINTURA" DO JOGO
		tela = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				
				//PINTA FUNDO PRETO
				g.setColor(Color.BLACK); //DEFINE COR DE PINTURA
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight()); //PINTA OBJETO RETANGULAR DO TAMANHO DA TELA COMO FUNDO
				
				//PRIMEIRO SE DEFINE A COR, DEPOIS PINTA-SE O OBJETO
				g.setColor(Color.yellow);
				g.fillRect(78, 29, 150, 100); //RETANGULO AMARELO
				
				//ATRAVÉS DA CLASSE Graphics, PODE-SE PINTAR RETANGULOS(Rect), OVAIS(Oval) E POLIGONOS(Polygon)
				g.setColor(Color.RED);
				g.fillOval(160, 230, 100, 100); //CÍRCULO VERMELHO
				
				//NÃO É NECESSÁRIO SETAR A COR PARA PINTAR UM OBJETO DA MESMA COR DO ANTERIOR
				g.fillOval(290, 178, 120, 200); //OVAL VERMELHA
				
				//MÉTODOS FILL SÃO OBJETOS PREENCHIDOS, MÉTODOS DRAW SÃO OBJETOS DE CONTORNO
				g.setColor(Color.BLUE);
				g.drawRect(50, 50, tela.getWidth()-100, tela.getHeight()-100); //MOLDURA AZUL
				
				g.setColor(Color.WHITE);
				g.drawString("TAMBEM PODEMOS DESENHAR STRINGS!!!!", 200, 150);
			}
		};

		//CONFIGURAÇÃO DA JANELA
		getContentPane().add(tela); // ADICIONA JPANEL À JANELA
		setSize(LARGURA, ALTURA); // DEFINE TAMANHO DA JANELA
		setResizable(false); // DEFINE SE O USUÁRIO PODERÁ REDIMENSIONAR A JANELA
		setDefaultCloseOperation(EXIT_ON_CLOSE); // IMPORTANTE: DEFINE 'X' DO WINDOWS COMO ENCERRADOR DO PROCESSO
		setVisible(true); // DEFINE VISIBILIDADE DA JANELA
	}

	public static void main(String[] args) {
		new Jogo();
	}

}
