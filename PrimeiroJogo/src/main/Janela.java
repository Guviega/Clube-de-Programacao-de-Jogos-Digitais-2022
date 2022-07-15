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
		
		//MÉTODOS DE JFRAME, DEFINE TAMANHO DA JANELA E REDIMENSIONAMENTO
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//INICIALIZANDO SOL PASSANDO PARAMETROS
		//POSIÇÃO (150, 150)
		//TAMANHO 100px x 100px
		//VELOCIDADE 3
		sol = new Elemento(150, 150, 100, 100, 3);
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
				//POSIÇÃO Y DO SOL AUMENTA CONFORME A VELOCIDADE
				sol.incPy(sol.getVelocidade());
				
				//MÉTODO QUE ATUALIZA A TELA, PINTANDO NOVAMENTE OS OBJETOS
				tela.repaint();
				
				//DEFINE A PRÓXIMA ATUALIZAÇÃO PARA O TEMPO ATUAL + O TEMPO DE ATUALIZAÇÃO
				prxAtt = System.currentTimeMillis() + ms;
			}
			
			//SE O SOL ULTRAPASSAR O CHÃO(SE PÔR) ENTÃO RETORNA PARA O TOPO DA TELA
			if (sol.getPy() >= tela.getHeight() - 100) {
				sol.setPy(-100);;
			}
		}
		
	}

	public static void main(String[] args) {
		new Janela().animacao();
	}

}
