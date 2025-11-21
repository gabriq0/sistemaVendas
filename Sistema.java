import logistica.*;
import producao.*;


public class Sistema {
    private static Sistema instancia1;

    //todas as instâncias necessárias para o funcionamento do sistema:
    private final EstoqueMaterial estoqueMaterial;
    private final Catalogo catalogoProdutor;
    private final TabelaPreco tabelaProdutor;
    private final Catalogo catalogoVendedor;
    private final TabelaPreco tabelaVendedor;

    private final ServicoProducao servicoProducao;
    private final ServicoReposicao servicoReposicao;

    private Sistema(){
        estoqueMaterial = new EstoqueMaterial();
        catalogoProdutor = new Catalogo();
        tabelaProdutor = new TabelaPreco();

        catalogoVendedor = new Catalogo();
        tabelaVendedor = new TabelaPreco();

        servicoProducao = new ServicoProducao(estoqueMaterial, catalogoProdutor, tabelaProdutor);
        servicoReposicao = new ServicoReposicao(catalogoProdutor, catalogoVendedor, tabelaProdutor, tabelaVendedor);
    }

    public static Sistema getInstance() {
        if (instancia1 == null) instancia1 = new Sistema();
        return instancia1;
    }

    public EstoqueMaterial getEstoqueMaterial() { return estoqueMaterial; }
    public Catalogo getCatalogoProdutor() { return catalogoProdutor; }
    public Catalogo getCatalogoVendedor() { return catalogoVendedor; }
    public TabelaPreco getTabelaProdutor() { return tabelaProdutor; }
    public TabelaPreco getTabelaVendedor() { return tabelaVendedor; }
    public ServicoProducao getServicoProducao() { return servicoProducao; }
    public ServicoReposicao getServicoReposicao() { return servicoReposicao; }
}
