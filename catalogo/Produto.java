package catalogo;

public class Produto {
    private String nome;
    private String idProduto;
    private double valordeVenda;

    public Produto(String nome, String idProduto, double valordeVenda){
        this.nome = nome;
        this.idProduto = idProduto;
        this.valordeVenda = valordeVenda;
    }
    
    public String getNome(){
        return this.nome;
    }
    public String getIdProduto(){
        return this.idProduto;
    }
    public double getValordeVenda(){
        return this.valordeVenda;
    }

    public String toString(){
        return this.nome;
    }

    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Produto produtoBusca = (Produto) objeto;
        return this.idProduto.equals(produtoBusca.idProduto);
    }
}

