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
	
	//POSI��O Y DO SOL QUE SER� ANIMADO
	private int solY = 100;
	
	public Janela() {
		
		//INSTANCIA JPANEL, "ABRINDO" PARA REALIZAR ALTERA��ES NA INST�NCIA
		tela = new JPanel() {
		
			//METODO paintComponent USADO PARA "PINTAR" NA TELA
			public void paintComponent(Graphics g) {
				//PRIMEIRO SE ESCOLHE A COR COM A CLASSE java.awt.Color
				g.setColor(Color.BLACK);
				
				//E DESENHA-SE O OBJETO
				//AQUI ESTAMOS PINTANDO UM FUNDO PRETO COM IN�CIO EM (0, 0) - CANTO SUPERIOR ESQUERDO
				//E COM O TAMANHO DE TODA A TELA - tela.getWidth() e tela.getHeight() -
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
				// ***COORDENADAS NO JFRAME FUNCIONAM COMO NO PLANO CARTESIANO, COM EIXOS X E Y
			
				g.setColor(Color.YELLOW);
				//USAMOS A VARIAVEL solY COMO POSI��O DO SOL NO EIXO Y
				g.fillOval(100, solY, 100, 100);
				
				g.setColor(Color.GREEN);
				g.fillRect(0, tela.getHeight() - 100, tela.getWidth(), 100);
				
				//CORES PODEM SER CRIADAS USANDO A CLASSE Color E C�DIGO RGB
				Color marrom = new Color(150, 75, 0);
				
				g.setColor(marrom);
				g.fillRect(tela.getWidth() - 125, tela.getHeight() - 125, 50, 50);
				
				g.setColor(Color.RED);
				g.fillPolygon(new int[]{tela.getWidth() - 125, tela.getWidth() - 100, tela.getWidth() - 75}, new int[]{tela.getHeight() - 125, tela.getHeight() - 175, tela.getHeight() - 125}, 3);
			
				//TEXTOS(Strings) TAMB�M PODEM SER DESENHADOS NA TELA
				g.setColor(Color.WHITE);
				g.drawString("Sol Y: "+solY, tela.getWidth() / 2, 30);
				
			}
			
		};
		
		//ADICIONA O OBJETO tela AO JFRAME;
		getContentPane().add(tela);
		
		//M�TODOS DE JFRAME, DEFINE TAMANHO DA JANELA E REDIMENSIONAMENTO
		setSize(LARGURA, ALTURA);
		setResizable(false);
		
		//DEFINE O "X" DA JANELA COMO ENCERRAMENTO DO SISTEMA
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//DEFINE VISUALIZA��O DA JANELA
		setVisible(true);
	}
	
	//METODO PARA ATUALIZAR A TELA E REALIZAR A ANIMA��O
	public void animacao() {
		//VARI�VEL LONG PARA CONTROLAR A PR�XIMA(PRX) ATUALIZA��O(ATT)
		long prxAtt = 0;
		
		//VARI�VEL PARA ENCERRAR A ANIMA��O
		long encerraAnimacao = System.currentTimeMillis() + 10000;
		
		//ENQUANTO O JOGO ESTIVER RODANDO(jogando = true) REALIZA ATUALIZA��ES
		while (jogando) {
			//QUANDO O TEMPO DO SISTEMA CHEGAR A 10s ELE PARA
			if (System.currentTimeMillis() >= encerraAnimacao) {
				jogando = false;
			}
			
			//SE O TEMPO DE SISTEMA ULTRAPASSAR prxAtt, REALIZA-SE A ATUALIZA��O DA TELA
			if (System.currentTimeMillis() >= prxAtt) {
				//POSI��O Y DO SOL AUMENTA EM 3px
				solY += 3;
				
				//M�TODO QUE ATUALIZA A TELA, PINTANDO NOVAMENTE OS OBJETOS
				tela.repaint();
				
				//DEFINE A PR�XIMA ATUALIZA��O PARA O TEMPO ATUAL + O TEMPO DE ATUALIZA��O
				prxAtt = System.currentTimeMillis() + ms;
			}
			
			//SE O SOL ULTRAPASSAR O CH�O(SE P�R) ENT�O RETORNA PARA O TOPO DA TELA
			if (solY >= tela.getHeight() - 100) {
				solY = -100;
			}
		}
		
	}

	public static void main(String[] args) {
		new Janela().animacao();
	}

}
