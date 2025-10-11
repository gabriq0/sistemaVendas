package clientes;
import listas.Lista;

public class ListaClientes {
    private Lista<Cliente> listadeClientes = new Lista<Cliente>();

    public void adicionarNovoCliente(String nome, String telefone, String endereco){
        Cliente novoCliente = new Cliente(nome, telefone, endereco);
        Cliente checarClienteRecorrente = this.listadeClientes.compararItens(novoCliente);

        if(checarClienteRecorrente == null){
            this.listadeClientes.insereFinal(novoCliente);
        }
    }

    public void removerClienteDaLista(String telefoneAlvo){
        Cliente clienteAlvo = new Cliente(null, telefoneAlvo, null);
        this.listadeClientes.excluirItem(clienteAlvo);

    }

    @Override
    public String toString() {
        return this.listadeClientes.toString();
    }
}
