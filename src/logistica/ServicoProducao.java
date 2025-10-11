package logistica;

import catalogo.Produto;
import listas.*;
import producao.EstoqueInsumos;
import producao.ItemReceita;
import producao.Receita;

public class ServicoProducao {
    private EstoqueInsumos estoqueInsumos;
    private Catalogo cProdutor;

    public ServicoProducao(EstoqueInsumos estoqueInsumos, Catalogo cProdutor){
        this.estoqueInsumos = estoqueInsumos;
        this.cProdutor = cProdutor;
    }

    public boolean produzir(Produto produto, int quantidade) {
        Receita recdoProduto = produto.getReceita();
        if(recdoProduto == null){
            System.out.println(produto.getNome() + " não tem receita, não sabemos como fazê-lo!!");
            return false;
        }

        Lista<ItemReceita> itensReceita = recdoProduto.getItensdaReceita();

        for (int i = 0; i < itensReceita.tamanhoLista(); i++) {
            ItemReceita ingrediente = itensReceita.pegarBloco(i);

            double qtdNecessaria = ingrediente.getQuantidadeNecessaria() * quantidade;
            
            if (estoqueInsumos.getQuantidadeInsumo(ingrediente.getInsumo()) < qtdNecessaria) {
                System.out.println("não foi possível produzir: matéria-prima insuficiente: " + ingrediente.getInsumo().getNome());
                return false; // BLOQUEIA A PRODUÇÃO CASO NÃO TENHA INSUMOS SUFICIENTES PARA A RECEITA...
            }
        }
        
        for (int i = 0; i < itensReceita.tamanhoLista(); i++) {
            ItemReceita ingrediente = itensReceita.pegarBloco(i);
            double qtdNecessaria = ingrediente.getQuantidadeNecessaria() * quantidade;
            estoqueInsumos.usarInsumos(ingrediente.getInsumo(), qtdNecessaria);
        }

        cProdutor.adicionarProduto(produto, quantidade);
        System.out.println("produção conluída: " + quantidade + "x " + produto.getNome() + " adicionado ao estoque do produtor.");
        return true;
    }
}
