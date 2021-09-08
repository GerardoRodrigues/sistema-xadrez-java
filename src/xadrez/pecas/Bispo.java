package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean mat[][] = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//NO
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//NE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
