package clientes;
public class Cliente{
    private String nome;
    private String email;
    private String endereco;

    public Cliente(String nome, String email, String endereco){
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getEndereco(){
        return this.endereco;
    }

    @Override
    public String toString(){
        return "Nome: " + this.nome + " - " + this.email + "\n" + "Endereço: " + this.endereco + "\n";
    }
    
    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Cliente clienteBusca = (Cliente) objeto;
        return this.email.equals(clienteBusca.email);
    }
}