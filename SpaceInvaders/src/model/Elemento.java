package model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Elemento implements Desenhavel {

	// ATRIBUTOS COMUNS ENTRE ELEMENTOS
	private int px, py, largura, altura, velocidade;
	private boolean ativo;
	private Color cor;

	// CONSTRUTOR VAZIO
	public Elemento() {
	}

	// CONSTRUTOR COM PARAMETROS
	public Elemento(int px, int py, int largura, int altura) {
		super();
		this.px = px;
		this.py = py;
		this.largura = largura;
		this.altura = altura;
	}

	public Elemento(int px, int py, int largura, int altura, int velocidade) {
		super();
		this.px = px;
		this.py = py;
		this.largura = largura;
		this.altura = altura;
		this.velocidade = velocidade;
	}

	// MÉTODOS GETTER E SETTER
	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {	
		this.py = py;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	// MÉTODOS DE INCREMENTO
	public void incPx(int px) {
		this.px += px;
	}

	public void incPy(int py) {
		this.py += py;
	}

	//MÉTODO QUE PINTA O ELEMENTO SE ELE ESTIVER ATIVO
	public void paintIfAtivo(Graphics g) {
		if (this.ativo)
			this.paint(g);
	}

}
