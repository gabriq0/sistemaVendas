package logistica;

import catalogo.Produto;
import listas.*;
import producao.*;
import logistica.TabelaPreco;

public class ServicoProducao {
    private EstoqueMaterial estoqueMaterial;
    private Catalogo cProdutor;
    private TabelaPreco tabelaProdutor;

    public ServicoProducao(EstoqueMaterial estoqueMaterial, Catalogo cProdutor,TabelaPreco tabelaProdutor){
        this.estoqueMaterial = estoqueMaterial;
        this.cProdutor = cProdutor;
        this.tabelaProdutor = tabelaProdutor;
    }

    public boolean produzir(Produto produto, int quantidade, double precoBase) {
        Receita recdoProduto = produto.getReceita();
        
        if(recdoProduto == null){
            System.out.println(produto.getNome() + " não tem receita, não sabemos como fazê-lo!!");
            return false;
        }

        Lista<ItemReceita> itensReceita = recdoProduto.getItensdaReceita();

        for (int i = 0; i < itensReceita.tamanhoLista(); i++) {
            ItemReceita ingrediente = itensReceita.pegarBloco(i);

            double qtdNecessaria = ingrediente.getQuantidadeNecessaria() * quantidade;
            
            if (estoqueMaterial.getQuantidadeMaterial(ingrediente.getMaterial()) < qtdNecessaria) {
                System.out.println("não foi possível produzir: matéria-prima insuficiente: " + ingrediente.getMaterial().getNome());
                return false; // BLOQUEIA A PRODUÇÃO CASO NÃO TENHA MATERIAIS SUFICIENTES PARA A RECEITA...
            }
        }
        
        for (int i = 0; i < itensReceita.tamanhoLista(); i++) {
            ItemReceita ingrediente = itensReceita.pegarBloco(i);
            double qtdNecessaria = ingrediente.getQuantidadeNecessaria() * quantidade;
            estoqueMaterial.usarMaterial(ingrediente.getMaterial(), qtdNecessaria);
        }

        cProdutor.adicionarProduto(produto, quantidade);
        this.tabelaProdutor.definirPreco(produto, precoBase);

        System.out.println("produção conluída: " + quantidade + "x " + produto.getNome() + " adicionado ao estoque do produtor.");
        System.out.println("preço base: R$ " + this.tabelaProdutor.getPreco(produto));
        return true;
    }
}
