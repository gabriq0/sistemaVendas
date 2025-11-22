import catalogo.Catalogo;
import catalogo.TabelaPreco;

public class Sistema {
    private static Sistema instancia1;

    //todas as instâncias necessárias para o funcionamento do sistema:
    private final Catalogo catalogoVendedor;
    private final TabelaPreco tabelaVendedor;

    private Sistema(){
        catalogoVendedor = new Catalogo();
        tabelaVendedor = new TabelaPreco();
    }

    public static Sistema getInstance() {
        if (instancia1 == null) instancia1 = new Sistema();
        return instancia1;
    }

    public Catalogo getCatalogoVendedor() { return catalogoVendedor; }
    public TabelaPreco getTabelaVendedor() { return tabelaVendedor; }
}
