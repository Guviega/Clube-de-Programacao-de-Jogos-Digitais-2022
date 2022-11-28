package util;

import model.Elemento;

public class Util {

	// M�TODO PARA VERIFICAR COLIS�ES DE EIXOS HORIZONTAIS
	public static boolean colideX(Elemento a, Elemento b) {
		if (a.getPx() + a.getLargura() >= b.getPx() && a.getPx() <= b.getPx() + b.getLargura()) {
			return true;
		}
		return false;
	}

	public static boolean colide(Elemento a, Elemento b) {
		if (!a.isAtivo() || !b.isAtivo())
			return false;

		final int plA = a.getPx() + a.getLargura();
		final int plB = b.getPx() + b.getLargura();
		final int paA = a.getPy() + a.getAltura();
		final int paB = b.getPy() + b.getAltura();

		if (plA >= b.getPx() && a.getPx() <= plB && paA >= b.getPy() && a.getPy() <= paB) {
			return true;
		}

		return false;
	}

}
