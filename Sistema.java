import catalogo.Catalogo;
import clientes.ListaClientes;

public class Sistema {
    private static Sistema instancia1;

    //todas as instâncias necessárias para o funcionamento do sistema:
    private Catalogo catalogo;
    private ListaClientes listaClientes;

    private Sistema(){
        catalogo = new Catalogo();
        listaClientes = new ListaClientes();
    }

    public static Sistema getInstance() {
        if (instancia1 == null) instancia1 = new Sistema();
        return instancia1;
    }

    public Catalogo getCatalogo() { return catalogo; }
    public ListaClientes getListaClientes() { return listaClientes; }
}
