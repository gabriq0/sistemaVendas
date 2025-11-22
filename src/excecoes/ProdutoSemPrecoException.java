package excecoes;
import catalogo.Produto;

public class ProdutoSemPrecoException extends Exception {
    public ProdutoSemPrecoException(Produto produto){
        super(produto.getNome() + " não tem preço registrado na tabela");
    }
}
