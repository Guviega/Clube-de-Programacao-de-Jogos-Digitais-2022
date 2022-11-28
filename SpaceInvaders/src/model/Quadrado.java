package model;

import java.awt.Graphics;

public class Quadrado extends Elemento {

	public Quadrado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quadrado(int px, int py, int largura, int altura, int velocidade) {
		super(px, py, largura, altura, velocidade);
		// TODO Auto-generated constructor stub
	}

	public Quadrado(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(getCor());
		g.fillRect(getPx(), getPy(), getLargura(), getAltura());
	}

}
