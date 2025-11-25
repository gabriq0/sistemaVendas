import catalogo.Catalogo;
import catalogo.TabelaPreco;
import clientes.ListaClientes;

public class Sistema {
    private static Sistema instancia1;

    //todas as instâncias necessárias para o funcionamento do sistema:
    private final Catalogo catalogo;
    private final TabelaPreco tabelaPreco;
    private final ListaClientes listaClientes;

    private Sistema(){
        catalogo = new Catalogo();
        tabelaPreco = new TabelaPreco();
        listaClientes = new ListaClientes();
    }

    public static Sistema getInstance() {
        if (instancia1 == null) instancia1 = new Sistema();
        return instancia1;
    }

    public Catalogo getCatalogoVendedor() { return catalogo; }
    public TabelaPreco getTabelaVendedor() { return tabelaPreco; }
    public ListaClientes getListaClientes() { return listaClientes; }
}
