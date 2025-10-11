package logistica;

import catalogo.Produto;

public class ServicoReposicao {
    private Catalogo cProdutor;
    private Catalogo cVendedor;

    public ServicoReposicao(Catalogo cProdutor, Catalogo cVendedor){
        this.cProdutor = cProdutor;
        this.cVendedor = cVendedor;
    }

    public boolean comprardoProdutor(Produto produto, int quantidade){
        System.out.println("tentativa de reposição iniciada...\n");

        int faltanoProdutor = this.cProdutor.verificarFalta(produto, quantidade);
        if(faltanoProdutor > 0) {
            System.out.println("erro! produtor não tem este produto em estoque...");
            return false;
        }

        cProdutor.reduzirParaVenda(produto, quantidade);
        cVendedor.adicionarProduto(produto, quantidade);
        System.out.println("sucesso! vendedor abasteceu o seu estoque com o produto");
        return true;
    }
}
