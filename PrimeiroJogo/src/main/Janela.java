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
	
	//SOL A PARTIR DA CLASSE ELEMENTO
	private Elemento sol;
	
	public Janela() {
		
		tela = new JPanel() {
		
			public void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
				
				g.setColor(Color.YELLOW);
				//DESENHANDO O SOL USANDO PARAMETROS DO ELEMENTO
				g.fillOval(sol.getPx(), sol.getPy(), sol.getLargura(), sol.getAltura());
				
				g.setColor(Color.GREEN);
				g.fillRect(0, tela.getHeight() - 100, tela.getWidth(), 100);
				
				Color marrom = new Color(150, 75, 0);
				
				g.setColor(marrom);
				g.fillRect(tela.getWidth() - 125, tela.getHeight() - 125, 50, 50);
				
				g.setColor(Color.RED);
				g.fillPolygon(new int[]{tela.getWidth() - 125, tela.getWidth() - 100, tela.getWidth() - 75}, new int[]{tela.getHeight() - 125, tela.getHeight() - 175, tela.getHeight() - 125}, 3);
			
				g.setColor(Color.WHITE);
				g.drawString("Sol Y: "+sol.getPy(), tela.getWidth() / 2, 30);
			}
		};
		
		getContentPane().add(tela);
		
		//M�TODOS DE JFRAME, DEFINE TAMANHO DA JANELA E REDIMENSIONAMENTO
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//INICIALIZANDO SOL PASSANDO PARAMETROS
		//POSI��O (150, 150)
		//TAMANHO 100px x 100px
		//VELOCIDADE 3
		sol = new Elemento(150, 150, 100, 100, 3);
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
				//POSI��O Y DO SOL AUMENTA CONFORME A VELOCIDADE
				sol.incPy(sol.getVelocidade());
				
				//M�TODO QUE ATUALIZA A TELA, PINTANDO NOVAMENTE OS OBJETOS
				tela.repaint();
				
				//DEFINE A PR�XIMA ATUALIZA��O PARA O TEMPO ATUAL + O TEMPO DE ATUALIZA��O
				prxAtt = System.currentTimeMillis() + ms;
			}
			
			//SE O SOL ULTRAPASSAR O CH�O(SE P�R) ENT�O RETORNA PARA O TOPO DA TELA
			if (sol.getPy() >= tela.getHeight() - 100) {
				sol.setPy(-100);;
			}
		}
		
	}

	public static void main(String[] args) {
		new Janela().animacao();
	}

}
