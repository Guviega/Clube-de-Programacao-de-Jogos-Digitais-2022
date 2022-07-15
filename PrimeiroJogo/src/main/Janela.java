package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	//CLASSE JANELA EXTENDE JFRAME 
public class Janela extends JFrame{
	
	//BOOLEAN USADA PARA CONTROLAR O FLUXO DO JOGO
	private boolean jogando = true;
	
	//TAXA DE ATUALIZAÇÕES POR SEGUNDO
	private int FPS = 20;
	
	//DURAÇÃO DE CADA ATUALIZAÇÃO (1 SEGUNDO DIVIDIDO PELA TAXA DE FRAMES)
	private int ms = 1000 / FPS;
	
	//VARIÁVEIS PARA ALTURA E LARGURA DA JANELA
	private int LARGURA = 480, ALTURA = 640;
	
	//JPanel, "TELA DE PINTURA" DA JANELA
	private JPanel tela;
	
	//POSIÇÃO Y DO SOL QUE SERÁ ANIMADO
	private int solY = 100;
	
	public Janela() {
		
		//INSTANCIA JPANEL, "ABRINDO" PARA REALIZAR ALTERAÇÕES NA INSTÂNCIA
		tela = new JPanel() {
		
			//METODO paintComponent USADO PARA "PINTAR" NA TELA
			public void paintComponent(Graphics g) {
				//PRIMEIRO SE ESCOLHE A COR COM A CLASSE java.awt.Color
				g.setColor(Color.BLACK);
				
				//E DESENHA-SE O OBJETO
				//AQUI ESTAMOS PINTANDO UM FUNDO PRETO COM INÍCIO EM (0, 0) - CANTO SUPERIOR ESQUERDO
				//E COM O TAMANHO DE TODA A TELA - tela.getWidth() e tela.getHeight() -
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
				// ***COORDENADAS NO JFRAME FUNCIONAM COMO NO PLANO CARTESIANO, COM EIXOS X E Y
			
				g.setColor(Color.YELLOW);
				//USAMOS A VARIAVEL solY COMO POSIÇÃO DO SOL NO EIXO Y
				g.fillOval(100, solY, 100, 100);
				
				g.setColor(Color.GREEN);
				g.fillRect(0, tela.getHeight() - 100, tela.getWidth(), 100);
				
				//CORES PODEM SER CRIADAS USANDO A CLASSE Color E CÓDIGO RGB
				Color marrom = new Color(150, 75, 0);
				
				g.setColor(marrom);
				g.fillRect(tela.getWidth() - 125, tela.getHeight() - 125, 50, 50);
				
				g.setColor(Color.RED);
				g.fillPolygon(new int[]{tela.getWidth() - 125, tela.getWidth() - 100, tela.getWidth() - 75}, new int[]{tela.getHeight() - 125, tela.getHeight() - 175, tela.getHeight() - 125}, 3);
			
				//TEXTOS(Strings) TAMBÉM PODEM SER DESENHADOS NA TELA
				g.setColor(Color.WHITE);
				g.drawString("Sol Y: "+solY, tela.getWidth() / 2, 30);
				
			}
			
		};
		
		//ADICIONA O OBJETO tela AO JFRAME;
		getContentPane().add(tela);
		
		//MÉTODOS DE JFRAME, DEFINE TAMANHO DA JANELA E REDIMENSIONAMENTO
		setSize(LARGURA, ALTURA);
		setResizable(false);
		
		//DEFINE O "X" DA JANELA COMO ENCERRAMENTO DO SISTEMA
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//DEFINE VISUALIZAÇÃO DA JANELA
		setVisible(true);
	}
	
	//METODO PARA ATUALIZAR A TELA E REALIZAR A ANIMAÇÃO
	public void animacao() {
		//VARIÁVEL LONG PARA CONTROLAR A PRÓXIMA(PRX) ATUALIZAÇÃO(ATT)
		long prxAtt = 0;
		
		//VARIÁVEL PARA ENCERRAR A ANIMAÇÃO
		long encerraAnimacao = System.currentTimeMillis() + 10000;
		
		//ENQUANTO O JOGO ESTIVER RODANDO(jogando = true) REALIZA ATUALIZAÇÕES
		while (jogando) {
			//QUANDO O TEMPO DO SISTEMA CHEGAR A 10s ELE PARA
			if (System.currentTimeMillis() >= encerraAnimacao) {
				jogando = false;
			}
			
			//SE O TEMPO DE SISTEMA ULTRAPASSAR prxAtt, REALIZA-SE A ATUALIZAÇÃO DA TELA
			if (System.currentTimeMillis() >= prxAtt) {
				//POSIÇÃO Y DO SOL AUMENTA EM 3px
				solY += 3;
				
				//MÉTODO QUE ATUALIZA A TELA, PINTANDO NOVAMENTE OS OBJETOS
				tela.repaint();
				
				//DEFINE A PRÓXIMA ATUALIZAÇÃO PARA O TEMPO ATUAL + O TEMPO DE ATUALIZAÇÃO
				prxAtt = System.currentTimeMillis() + ms;
			}
			
			//SE O SOL ULTRAPASSAR O CHÃO(SE PÔR) ENTÃO RETORNA PARA O TOPO DA TELA
			if (solY >= tela.getHeight() - 100) {
				solY = -100;
			}
		}
		
	}

	public static void main(String[] args) {
		new Janela().animacao();
	}

}
