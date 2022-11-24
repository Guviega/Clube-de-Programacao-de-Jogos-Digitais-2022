package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JFrame {

	// CONFIGURA��O DO JOGO
	private boolean jogando;
	private int FPS = 30;
	private int LARGURA = 540, ALTURA = 480;
	private int animacao = 0; //VARI�VEL QUE CONTROLA A ANIMA��O DOS OBJETOS

	// ELEMENTOS DO JOGO
	private JPanel tela;

	public Jogo() {
		// TELA DE "PINTURA" DO JOGO
		tela = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				// PINTA FUNDO PRETO
				g.setColor(Color.BLACK); // DEFINE COR DE PINTURA
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight()); // PINTA OBJETO RETANGULAR DO TAMANHO DA TELA COMO
																		// FUNDO

				// PRIMEIRO SE DEFINE A COR, DEPOIS PINTA-SE O OBJETO
				g.setColor(Color.yellow);
				g.fillRect(78, 29, 150 + animacao, 100); // RETANGULO AMARELO

				// ATRAV�S DA CLASSE Graphics, PODE-SE PINTAR RETANGULOS(Rect), OVAIS(Oval) E
				// POLIGONOS(Polygon)
				g.setColor(Color.RED);
				g.fillOval(160, 230, 100, 100 + animacao); // C�RCULO VERMELHO

				// N�O � NECESS�RIO SETAR A COR PARA PINTAR UM OBJETO DA MESMA COR DO ANTERIOR
				g.fillOval(290, 178 + animacao, 120, 200); // OVAL VERMELHA

				// M�TODOS FILL S�O OBJETOS PREENCHIDOS, M�TODOS DRAW S�O OBJETOS DE CONTORNO
				g.setColor(Color.BLUE);
				g.drawRect(50 + animacao, 50, tela.getWidth() - 100, tela.getHeight() - 100); // MOLDURA AZUL

				g.setColor(Color.WHITE);
				g.drawString("Anima��o: " + animacao, 200, 150);
			}
		};

		// CONFIGURA��O DA JANELA
		getContentPane().add(tela); // ADICIONA JPANEL � JANELA
		setSize(LARGURA, ALTURA); // DEFINE TAMANHO DA JANELA
		setResizable(false); // DEFINE SE O USU�RIO PODER� REDIMENSIONAR A JANELA
		setDefaultCloseOperation(EXIT_ON_CLOSE); // IMPORTANTE: DEFINE 'X' DO WINDOWS COMO ENCERRADOR DO PROCESSO
		setVisible(true); // DEFINE VISIBILIDADE DA JANELA
	}

	//M�TODO QUE FAR� A ATUALIZA��O DA TELA DO JOGO
	//**UMA ANIMA��O � UMA SUCESS�O DE ATUALIZA��ES NA TELA, CONFORME O FPS
	public void inicia() {
		jogando = true;
		long prxAtt = 0;

		while (jogando) {
			if (System.currentTimeMillis() >= prxAtt) {
				animacao++;
				if (animacao >= 300)//ANIMA��O DURA 300 FRAMES
					animacao = 0;//REINICIA A ANIMA��O
				tela.repaint(); //M�TODO QUE REPINTA A TELA
				prxAtt = System.currentTimeMillis() + 1000 / FPS;
			}
		}
	}

	public static void main(String[] args) {
		new Jogo().inicia();
	}

}
