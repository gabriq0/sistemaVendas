package catalogo;

public class Produto {
    private String nome;
    private String idProduto;

    public Produto(String nome, String idProduto){
        this.nome = nome;
        this.idProduto = idProduto;
    }
    
    public String getNome(){ return this.nome; }
    public String getIdProduto() { return this.idProduto; }

    @Override
    public String toString(){ return this.nome; }

    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Produto produtoBusca = (Produto) objeto;
        return this.idProduto.equals(produtoBusca.idProduto);
    }
}

