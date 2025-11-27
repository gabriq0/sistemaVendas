package clientes;
import listas.Lista;

public class ListaClientes {
    private Lista<Cliente> listac = new Lista<Cliente>();

    public Cliente adicionar(String nome, String email, String endereco){
        Cliente busca = new Cliente(null, email, null);
        Cliente existe = this.listac.compararItens(busca);

        if(existe == null) {
            Cliente novo = new Cliente(nome, email, endereco);
            this.listac.insereFinal(novo);
            return novo;
        } 
        else return existe;
        
    }

    public void remover(String email){
        Cliente clienteAlvo = new Cliente(null, email, null);
        this.listac.excluirItem(clienteAlvo);

    }

    public Cliente buscar(String email){
        Cliente busca = new Cliente(null, email, null);
        Cliente existe = this.listac.compararItens(busca);

        if(existe != null) return existe;
        return null;
    }

    public void mostrarLista(){
        if(this.listac.listaVazia()) System.out.println("lista de clientes vazia!!");
        System.out.println(this.listac.toString());
    }

    @Override
    public String toString() { return this.listac.toString(); }
}
