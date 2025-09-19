public class NovoItem {
    private Produto produto;
    private float qtd;

    public NovoItem(Produto produto, float qtd){
        this.produto = produto;
        this.qtd = qtd;
    }

    public Produto getProduto(){
        return this.produto;
    }
    public float getQtd(){
        return this.qtd;
    }

    public void setQtd(float qtd){
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "Nome do Produto: " + this.produto.toString() + "\nQuantidade: " + this.qtd;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        NovoItem itemBusca = (NovoItem) objeto;
        return this.produto.equals(itemBusca.produto);
}
}
