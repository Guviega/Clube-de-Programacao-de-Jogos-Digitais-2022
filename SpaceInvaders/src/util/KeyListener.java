package util;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
//KEYLISTENER COM TECLAS QUE SER�O UTILIZADAS NO JOGO
	public static boolean right, left, space;

	//M�TODO QUE DEFINE QUAIS TECLAS EST�O ATIVAS
	public static void setaTecla(int tecla, boolean pressionada) {
		switch (tecla) {
		case KeyEvent.VK_LEFT:
			left = pressionada;
			break;
		case KeyEvent.VK_RIGHT:
			right = pressionada;
			break;
		
		case KeyEvent.VK_A:
			left = pressionada;
			break;
		case KeyEvent.VK_D:
			right = pressionada;
			break;
			
		case KeyEvent.VK_SPACE:
			space = pressionada;
			break;

		default:
			break;
		}
	}

	//M�TODOS DA INTERFACE KEYLISTENER QUE LEEM AS TECLAS DO COMPUTADOR
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		setaTecla(e.getKeyCode(), false);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setaTecla(e.getKeyCode(), true);
	}

}