package clientes;

public class teste {
    public static void main(String[] args) {
        ListaClientes li = new ListaClientes();

        li.adicionarNovoCliente("amanda", "(12) 99999-1234", "beira do rio"); 
        li.adicionarNovoCliente("carol", "(84) 99991-4321", "brasil");
        li.adicionarNovoCliente("scooby-doo", "(12) 98765-4321", "beira do rio");
        li.adicionarNovoCliente("carol", "(99) 12123-4545", "posto de gasolina");
        li.adicionarNovoCliente("amanda", "(12) 99999-1234", "beira do rio");
        
        System.out.println(li.toString());

        System.out.println("=========================================\n");

        li.removerClienteDaLista("(12) 99999-1234");

        System.out.println(li.toString());
    }
}
