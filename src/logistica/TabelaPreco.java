package logistica;

import listas.*;
import catalogo.*;

public class TabelaPreco {
    private Lista<ItemTabelaPreco> tabela = new Lista<ItemTabelaPreco>();

    public void definirPreco(Produto produto, double precoProduto) {
        ItemTabelaPreco novoItem = new ItemTabelaPreco(produto, precoProduto);
        ItemTabelaPreco itemExistente = tabela.compararItens(novoItem);

        if (itemExistente != null) itemExistente.setPrecoProduto(precoProduto);
        else tabela.insereFinal(novoItem);
    }

    public void removerPreco(Produto produto) {
        ItemTabelaPreco itemBusca = new ItemTabelaPreco(produto, 0);
        this.tabela.excluirItem(itemBusca);
    }

    public Double getPreco(Produto produto) {
        ItemTabelaPreco itemBusca = new ItemTabelaPreco(produto, 0); 
        ItemTabelaPreco itemEncontrado = tabela.compararItens(itemBusca);

        if (itemEncontrado != null) return itemEncontrado.getPrecoProduto();
        else return null;
    }

    public void mostrarTabela(){
        if(this.tabela.listaVazia()) System.out.println("não tem produtos, não tem preços?!!");
        System.out.println(this.tabela.toString());
    }

    @Override
    public String toString() {
        return this.tabela.toString();
    }
}
