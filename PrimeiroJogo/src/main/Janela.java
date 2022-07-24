package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
public class Janela extends JFrame{
	
	private boolean jogando = true;
	private int FPS = 20;
	private int ms = 1000 / FPS;
	
	private int LARGURA = 480, ALTURA = 640;
	private JPanel tela;
	private int linhaLimite;
	
	//PONTUA플O
	private int pontos = 0;
	
	private Elemento player;
	
	private Elemento inimigos[];
	private int inimigosQTD = 7, inimigosSPC =15;
	
	private Elemento tiro;
	
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
		
		inimigos = new Elemento[inimigosQTD];
		for (int i = 0; i < inimigos.length; i++) {
			int espaco = i * 50 + inimigosSPC * (i + 1);
			inimigos[i] = new Elemento(espaco, 0, 50, 50, 1);
		}
		
		tiro = new Elemento(0, 0, 1, 0, 0);
		
		tela = new JPanel() {
		
			public void paintComponent(Graphics g) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
				
				g.setColor(Color.BLUE);
				g.fillRect(tiro.getPx(), tiro.getPy(), tiro.getLargura(), tela.getHeight());
				
				g.setColor(Color.GREEN);
				g.fillRect(player.getPx(), player.getPy(), player.getLargura(), player.getAltura());
				
				g.setColor(Color.RED);
				for (Elemento inimigo : inimigos) {
					g.fillRect(inimigo.getPx(), inimigo.getPy(), inimigo.getLargura(), inimigo.getAltura());
				}
				
				g.setColor(Color.WHITE);
				g.fillRect(0, linhaLimite, tela.getWidth(), 2);
				
				//DESENHA PONTUA플O NO CANTO DA TELA (10, 20)
				g.drawString("Pontos: "+String.valueOf(pontos), 10, 20);
			}
		};
		
		getContentPane().add(tela);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		linhaLimite = tela.getHeight() - 50;
		
		player.setPx(tela.getWidth() / 2 - player.getLargura() / 2);
		player.setPy(tela.getHeight() - player.getAltura()); 
	}
	
	public void inicia() {
		long prxAtt = 0;
		
		while (jogando) {
			
			if (System.currentTimeMillis() >= prxAtt) {
				atualiza();
				tela.repaint();

				prxAtt = System.currentTimeMillis() + ms;
			}
		}
	}
	
	public void atualiza() {
		if (Util.right) 
			player.incPx(player.getVelocidade());
		if (Util.left)
			player.incPx(player.getVelocidade()*-1);
		
		if (player.getPx() > tela.getWidth())
			player.setPx(0);
		if (player.getPx() < 0)
			player.setPx(tela.getWidth() - player.getLargura());
	
		tiro.setPx(player.getPx() + player.getLargura() / 2);
	
		for (Elemento inimigo : inimigos) {

			if (inimigo.getPy() > linhaLimite - inimigo.getAltura()) {
				jogando = false;
				break;
			}

			if (Util.colide(inimigo, tiro) && inimigo.getPy() > 0) {
				inimigo.incPy(inimigo.getVelocidade() *-2);
				tiro.setPy(inimigo.getPy());
			}
			else
				inimigo.incPy(inimigo.getVelocidade());
		}
		
		//A CADA ATUALIZA플O, A PONTUA플O AUMENTA EM 1 (PONTOS POR SEGUNDO = FPS)
		pontos += 1;
		
	}
	
	public static void main(String[] args) {
		new Janela().inicia();
	}

}
