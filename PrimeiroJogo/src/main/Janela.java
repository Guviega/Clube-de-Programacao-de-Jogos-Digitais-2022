package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	//CLASSE JANELA EXTENDE JFRAME 
public class Janela extends JFrame{
	
	//VARIÁVEIS PARA ALTURA E LARGURA DA JANELA
	private int LARGURA = 480, ALTURA = 640;
	
	//JPanel, "TELA DE PINTURA" DA JANELA
	private JPanel tela;
	
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
			
				g.setColor(Color.GREEN);
				g.fillRect(0, tela.getHeight() - 100, tela.getWidth(), 100);
				
				//CORES PODEM SER CRIADAS USANDO A CLASSE Color E CÓDIGO RGB
				Color marrom = new Color(150, 75, 0);
				
				g.setColor(marrom);
				g.fillRect(tela.getWidth() - 125, tela.getHeight() - 125, 50, 50);
				
				g.setColor(Color.RED);
				g.fillPolygon(new int[]{tela.getWidth() - 125, tela.getWidth() - 100, tela.getWidth() - 75}, new int[]{tela.getHeight() - 125, tela.getHeight() - 175, tela.getHeight() - 125}, 3);
			
				g.setColor(Color.YELLOW);
				g.fillOval(100, 100, 100, 100);
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

	public static void main(String[] args) {
		new Janela();
	}

}
