import catalogo.*;
import clientes.*;
import listas.*;
import logistica.*;
import producao.*;
import vendas.*;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();

        EstoqueMaterial estoqueMaterial = sistema.getEstoqueMaterial();
        Catalogo catalogoProdutor = sistema.getCatalogoProdutor();
        Catalogo catalogoVendedor = sistema.getCatalogoVendedor();
        TabelaPreco tabelaVendedor = sistema.getTabelaVendedor();

        ServicoProducao servicoProducao = sistema.getServicoProducao();
        ServicoReposicao servicoReposicao = sistema.getServicoReposicao();

        // 1. definindo a receita do produto, e então, criando ele:
        Material alcool = new Material("i01", "álcool legal", "ml");
        Material essencia = new Material("i02", "essência de lavanda", "ml");
        
        Receita receitaPerfume = new Receita();
        receitaPerfume.adicionarIngredienteReceita(alcool, 70.0);
        receitaPerfume.adicionarIngredienteReceita(essencia, 15.0);

        Produto perfumeLavanda = new Produto("perfume lavanda", "pr01", receitaPerfume);

        // 2. tentando criar um produto sem ter materiais para isso:
        System.out.println("teste para checar se é possível criar um produto sem material suficiente: \n");
        boolean teste = servicoProducao.produzir(perfumeLavanda, 5, 25);
        
        System.out.println("\nqtd de material em estoque: " + estoqueMaterial.getTamanhoEstoque());

        if (!teste) System.out.println("produção bloqueada por falta de material."); 
        else System.out.println("falha, produto feito de literalmente nada");
        System.out.println("\n=============================================================");
        
        // 3. tentando criar da maneira correta. com materiais suficientes:
        estoqueMaterial.adicionarMaterial(alcool, 1000.0);
        estoqueMaterial.adicionarMaterial(essencia, 500.0);
        System.out.println(estoqueMaterial);

        boolean correto = servicoProducao.produzir(perfumeLavanda, 10, 25);

        if (correto) System.out.println("produto adicionado ao catalogo do produtor com sucesso!");
        else System.out.println("falha, erro de código/lógica");
        
        System.out.println("\n=============================================================");
        System.out.println("catalogo do produtor: \n" + catalogoProdutor);
        
        // 4. reposição do catalogo do VENDEDOR:
        System.out.println("tentativa de reposição do catalogo do VENDEDOR: \n");
        servicoReposicao.comprardoProdutor(perfumeLavanda, 4);
        
        System.out.println("estoque do produtor (deve ter 6 restantes): \n" + catalogoProdutor);
        System.out.println("estoque do vendedor (deve ter 4): " + catalogoVendedor);
        System.out.println("\n=============================================================");

        // 5. venda do produto ao cliente: 
        System.out.println("final: venda básica para algum cliente: ");
        
        Cliente cliente = new Cliente("gabriel", "gabri@yahoo.com", "beira do rio");
        Lista<ItemVenda> carrinhoCompras = new Lista<ItemVenda>();
        
        Venda venda = new Venda(cliente, catalogoVendedor, carrinhoCompras, servicoReposicao, tabelaVendedor);
        venda.adicionarItem(perfumeLavanda, 3);
        venda.finalizarVenda("pix");
        
        System.out.println("\ndepois da venda: ");
        System.out.println("catalogo do vendedor (deve ter 1 restante): \n" + catalogoVendedor);
    }
}
