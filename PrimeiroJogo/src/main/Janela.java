package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	//CLASSE JANELA EXTENDE JFRAME 
public class Janela extends JFrame{
	
	private boolean jogando = true;
	
	//TAXA DE ATUALIZAÇÕES POR SEGUNDO
	private int FPS = 20;
	//DURAÇÃO DE CADA ATUALIZAÇÃO (1 SEGUNDO DIVIDIDO PELA TAXA DE FRAMES)
	private int ms = 1000 / FPS;
	
	private int LARGURA = 480, ALTURA = 640;
	private JPanel tela;
	private Elemento player;
	
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
		
		player = new Elemento(0, 0, 50, 50, 5);
		
		tela = new JPanel() {
		
			public void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
				
				g.setColor(Color.GREEN);
				g.fillRect(player.getPx(), player.getPy(), player.getLargura(), player.getAltura());
			}
		};
		
		getContentPane().add(tela);
		
		//MÉTODOS DE JFRAME, DEFINE TAMANHO DA JANELA E REDIMENSIONAMENTO
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() / 2 - player.getAltura() / 2); 
	}
	
	//METODO PARA ATUALIZAR A TELA E REALIZAR A ANIMAÇÃO
	public void inicia() {
		//VARIÁVEL LONG PARA CONTROLAR A PRÓXIMA(PRX) ATUALIZAÇÃO(ATT)
		long prxAtt = 0;
		
		while (jogando) {
			
			//SE O TEMPO DE SISTEMA ULTRAPASSAR prxAtt, REALIZA-SE A ATUALIZAÇÃO DA TELA
			if (System.currentTimeMillis() >= prxAtt) {
				atualiza();
				tela.repaint();
				//DEFINE A PRÓXIMA ATUALIZAÇÃO PARA O TEMPO ATUAL + O TEMPO DE ATUALIZAÇÃO
				prxAtt = System.currentTimeMillis() + ms;
			}
		}
	}
	
	//MÉTODO SEPARADO QUE IRÁ REALIZAR AS ATUALIZAÇÕES DO JOGO
	public void atualiza() {
		if (Util.up) 
			player.incPy(player.getVelocidade()*-1);
		if (Util.down) 
			player.incPy(player.getVelocidade());
		if (Util.right) 
			player.incPx(player.getVelocidade());
		if (Util.left)
			player.incPx(player.getVelocidade()*-1);
	}

	public static void main(String[] args) {
		new Janela().inicia();
	}

}
