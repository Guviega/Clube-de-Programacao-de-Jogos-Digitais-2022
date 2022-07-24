package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	//CLASSE JANELA EXTENDE JFRAME 
public class Janela extends JFrame{
	
	//BOOLEAN USADA PARA CONTROLAR O FLUXO DO JOGO
	private boolean jogando = true;
	
	//TAXA DE ATUALIZA��ES POR SEGUNDO
	private int FPS = 20;
	
	//DURA��O DE CADA ATUALIZA��O (1 SEGUNDO DIVIDIDO PELA TAXA DE FRAMES)
	private int ms = 1000 / FPS;
	
	//VARI�VEIS PARA ALTURA E LARGURA DA JANELA
	private int LARGURA = 480, ALTURA = 640;
	
	//JPanel, "TELA DE PINTURA" DA JANELA
	private JPanel tela;
	
	//PLAYER A PARTIR DA CLASSE ELEMENTO
	private Elemento player;
	
	public Janela() {
		
		player = new Elemento(0, 0, 30, 30, 3);
		
		tela = new JPanel() {
		
			public void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
				
				g.setColor(Color.GREEN);
				g.fillRect(player.getPx(), player.getPy(), player.getLargura(), player.getAltura());
			}
		};
		
		getContentPane().add(tela);
		
		//M�TODOS DE JFRAME, DEFINE TAMANHO DA JANELA E REDIMENSIONAMENTO
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() / 2 - player.getAltura() / 2); 
	}
	
	//METODO PARA ATUALIZAR A TELA E REALIZAR A ANIMA��O
	public void animacao() {
		//VARI�VEL LONG PARA CONTROLAR A PR�XIMA(PRX) ATUALIZA��O(ATT)
		long prxAtt = 0;
		
		while (jogando) {
			
			//SE O TEMPO DE SISTEMA ULTRAPASSAR prxAtt, REALIZA-SE A ATUALIZA��O DA TELA
			if (System.currentTimeMillis() >= prxAtt) {
				tela.repaint();
				
				//DEFINE A PR�XIMA ATUALIZA��O PARA O TEMPO ATUAL + O TEMPO DE ATUALIZA��O
				prxAtt = System.currentTimeMillis() + ms;
			}
		}
		
	}

	public static void main(String[] args) {
		new Janela().animacao();
	}

}
