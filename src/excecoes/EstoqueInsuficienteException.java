package excecoes;

import catalogo.Produto;

public class EstoqueInsuficienteException extends Exception {
    
    // Construtor que recebe o produto e a quantidade que falta
    public EstoqueInsuficienteException(Produto produto, int quantidadeFaltante) {
        super("Estoque insuficiente para o produto: " + produto.getNome() + ". Faltam " + quantidadeFaltante + " unidades.");
    }
}