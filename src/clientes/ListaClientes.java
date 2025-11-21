package clientes;
import listas.Lista;

//minha intenção é melhorar a relação / uso da classe cliente no futuro...

public class ListaClientes {
    private Lista<Cliente> listadeClientes = new Lista<Cliente>();

    public void adicionarNovoCliente(String nome, String email, String endereco){
        Cliente novoCliente = new Cliente(nome, email, endereco);
        Cliente checarClienteRecorrente = this.listadeClientes.compararItens(novoCliente);

        if(checarClienteRecorrente == null){
            this.listadeClientes.insereFinal(novoCliente);
        }
    }

    public void removerClienteDaLista(String email){
        Cliente clienteAlvo = new Cliente(null, email, null);
        this.listadeClientes.excluirItem(clienteAlvo);

    }

    @Override
    public String toString() { return this.listadeClientes.toString(); }
}
