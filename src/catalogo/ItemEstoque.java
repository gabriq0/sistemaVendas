package catalogo;

public class ItemEstoque {
    private Produto produto;
    private int quantidadeEstoque;

    public ItemEstoque(Produto produto, int quantidadeEstoque){
        this.produto = produto;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public Produto getProduto() { return this.produto; }
    public int getQuantidadeEstoque() { return this.quantidadeEstoque; }

    @Override
    public String toString() {
        return this.produto.getNome() + " ("+ this.produto.getIdProduto() + ") - qtd: " +
        this.quantidadeEstoque +"\nR$ " + this.produto.getValordeVenda();
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemEstoque itemBusca = (ItemEstoque) objeto;
        return this.produto.equals(itemBusca.produto);
    }
}
