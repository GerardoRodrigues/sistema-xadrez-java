package aplicacao;

import tabuleiro.Posicao;
import xadrez.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partida = new PartidaXadrez();
		UI.printTabuleiro(partida.getPecas());
	}

}
