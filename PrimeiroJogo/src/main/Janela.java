package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	//CLASSE JANELA EXTENDE JFRAME 
public class Janela extends JFrame{
	
	//VARIÁVEIS PARA ALTURA E LARGURA DA JANELA
	private int LARGURA = 480, ALTURA = 640;
	
	public Janela() {
		
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
