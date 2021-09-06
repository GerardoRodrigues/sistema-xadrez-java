package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		iniciarPartida();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}

	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoDeOrigem){
		Posicao posicao = posicaoDeOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaXadrez executarPecaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = mover(origem, destino);
		proximoTurno();
		return (PecaXadrez) pecaCapturada;
	}
	
	private Peca mover(Posicao origem, Posicao destino) {
		Peca peca = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(peca, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.temPeca(posicao)) {
			throw new ExecaoXadrez("Nao existe peca na posicao de origem.");
		}
		if(jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new ExecaoXadrez("Essa peca nao eh sua.");
		}
		if(!tabuleiro.peca(posicao).umPossivelMovimento()) {
			throw new ExecaoXadrez("Nao existe movimento possivel para a peca escolhida.");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new ExecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino.");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private void novoLugarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}

	private void iniciarPartida() {
		novoLugarPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		novoLugarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		novoLugarPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		novoLugarPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
