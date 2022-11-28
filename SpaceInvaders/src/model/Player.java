package model;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Player extends Elemento {

	public Player() {
		super();
	}

	public Player(int px, int py, int largura, int altura, int velocidade) {
		super(px, py, largura, altura, velocidade);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon(getClass().getResource("/img/player.png")).getImage(), getPx(),
				getPy(), getLargura(), getAltura(), null);
	}

}
