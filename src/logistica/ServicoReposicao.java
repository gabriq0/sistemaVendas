package logistica;

import catalogo.Produto;
import logistica.TabelaPreco;

public class ServicoReposicao {
    private Catalogo cProdutor;
    private Catalogo cVendedor;
    private TabelaPreco tabProdutor;
    private TabelaPreco tabVendedor;

    public ServicoReposicao(Catalogo cProdutor, Catalogo cVendedor, TabelaPreco tabProdutor, TabelaPreco tabVendedor){
        this.cProdutor = cProdutor;
        this.cVendedor = cVendedor;
        this.tabProdutor = tabProdutor;
        this.tabVendedor = tabVendedor;
    }

    public boolean comprardoProdutor(Produto produto, int quantidade){
        System.out.println("tentativa de reposição iniciada...\n");

        int faltanoProdutor = this.cProdutor.verificarFalta(produto, quantidade);
        if(faltanoProdutor > 0) {
            System.out.println("erro! produtor não tem este produto em estoque...");
            return false;
        }

        double precoProdutor = this.tabProdutor.getPreco(produto);
        //isso aqui provavelmente só vai ser usado no futuro.

        this.cProdutor.reduzirParaVenda(produto, quantidade);
        this.cVendedor.adicionarProduto(produto, quantidade);

        double precoVendedor = this.tabProdutor.getPreco(produto) + 10; //isso é um teste mal-implementado! 

        this.tabVendedor.definirPreco(produto, precoVendedor);

        System.out.println("sucesso! vendedor abasteceu o seu estoque com o produto");
        return true;
    }
}
