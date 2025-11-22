package excecoes;

public class CarrinhoVazioException extends Exception {
    public CarrinhoVazioException(){
        super("carrinho vazio! impossível continuar venda");
    }
}
