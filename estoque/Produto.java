public class Produto {
    private String nome;

    public Produto(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
    
    public String toString(){
        return this.nome;
    }

    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Produto produtoBusca = (Produto) objeto;
        return this.nome.equals(produtoBusca.nome);
    }
}

