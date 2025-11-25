package excecoes;

public class QuantidadeInvalidaException extends Exception {
    public QuantidadeInvalidaException(int quantidade){
        super("quantidade inválida: " + quantidade);
    }
}
