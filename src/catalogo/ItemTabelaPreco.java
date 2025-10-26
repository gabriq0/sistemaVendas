package catalogo;

public class ItemTabelaPreco {
    private Produto produto;
    private double precoProduto;

    public ItemTabelaPreco(Produto produto, double precoProduto) {
        this.produto = produto;
        this.precoProduto = precoProduto;
    }

    public void setPrecoProduto(double precoProduto) { this.precoProduto = precoProduto; }
    public double getPrecoProduto() { return precoProduto; }
    public Produto getProduto() { return produto; }

    @Override
    public String toString() {
        return this.produto.getNome() + " ("+ this.produto.getIdProduto() + "): R$ " + this.getPrecoProduto();
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemTabelaPreco itemBusca = (ItemTabelaPreco) objeto;
        return this.produto.equals(itemBusca.produto);
    }
}
