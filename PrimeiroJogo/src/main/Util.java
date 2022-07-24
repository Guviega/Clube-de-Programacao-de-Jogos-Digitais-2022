package main;

import java.awt.event.KeyEvent;

public class Util {
	
	public static boolean up, down, right, left;
	
	public static void setaTecla(int tecla, boolean pressionada) {
		switch (tecla) {
		case KeyEvent.VK_UP:
			up = pressionada;
			break;
		
		case KeyEvent.VK_DOWN:
			down = pressionada;
			break;
			
		case KeyEvent.VK_LEFT:
			left = pressionada;
			break;
			
		case KeyEvent.VK_RIGHT:
			right = pressionada;
			break;

		default:
			break;
		}
	}
	
	//MÉTODO PARA VERIFICAR COLISÕES DE EIXOS HORIZONTAIS
	public static boolean colide(Elemento a, Elemento b) {
		if (a.getPx() + a.getLargura() >= b.getPx() && a.getPx() <= b.getPx() + b.getLargura()) {
			return true;
		}

		return false;
	}

}
