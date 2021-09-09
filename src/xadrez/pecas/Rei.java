package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	private PartidaXadrez partida;
	
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testeTorreRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean mat[][] = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//Acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//NO
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//NE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Movimento Especial Roque
		if(getContadorMovimento() == 0 && !partida.getCheck()) {
			//"Roque pequeno"
			Posicao pT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if(testeTorreRoque(pT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			//"Roque grande"
			Posicao pT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if(testeTorreRoque(pT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return mat;
	}
}
