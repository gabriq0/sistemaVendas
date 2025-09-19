package clientes;
public class Cliente{
    private String nome;
    private String telefone;
    private String endereco;

    public Cliente(String nome, String telefone, String endereco){
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome(){
        return this.nome;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public String getEndereco(){
        return this.endereco;
    }

    @Override
    public String toString(){
        return "Nome: " + this.nome + " - " + this.telefone + "\n" + "Endereço: " + this.endereco + "\n";
    }
    
    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Cliente clienteBusca = (Cliente) objeto;
        return this.telefone.equals(clienteBusca.telefone);
    }
}