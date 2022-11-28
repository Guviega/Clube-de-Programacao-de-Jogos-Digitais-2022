package model;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Inimigo extends Elemento {

	public Inimigo() {
		super();
	}

	public Inimigo(int px, int py, int largura, int altura, int velocidade) {
		super(px, py, largura, altura, velocidade);
	}

	public Inimigo(int px, int py, int largura, int altura) {
		super(px, py, largura, altura);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon(getClass().getResource("/img/inimigo.png")).getImage(), getPx(), getPy(),
				getLargura(), getAltura(), null);
	}

}
