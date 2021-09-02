package tabuleiro;

public class Execao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public Execao(String msg) {
		super(msg);
	}
}
