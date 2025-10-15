import catalogo.*;
import clientes.Cliente;
import listas.Lista;
import logistica.*; 
import producao.*;
import vendas.*;

public class exemplo {
    public static void main(String[] args) {
        // 1. produtor / fornecedor:
        EstoqueMaterial estoqueMaterial = new EstoqueMaterial();
        Catalogo catalogoProdutor = new Catalogo();
        ServicoProducao servicoProducao = new ServicoProducao(estoqueMaterial, catalogoProdutor);
 
        // 2. vendedor:
        Catalogo catalogoVendedor = new Catalogo();
        
        // 3. reposição para o vendedor / carrinho para o cliente:
        ServicoReposicao servicoReposicao = new ServicoReposicao(catalogoProdutor, catalogoVendedor);
        Lista<ItemVenda> carrinhoCompras = new Lista<ItemVenda>();

        // =================================================================================

        // 4. definindo a receita do produto, e então, criando ele:
        Material alcool = new Material("i01", "álcool legal", "ml");
        Material essencia = new Material("i02", "essência de lavanda", "ml");
        
        Receita receitaPerfume = new Receita();
        receitaPerfume.adicionarIngredienteReceita(alcool, 70.0);
        receitaPerfume.adicionarIngredienteReceita(essencia, 15.0);

        Produto perfumeLavanda = new Produto("perfume lavanda", "pr01", 12.50, receitaPerfume);

        // =================================================================================

        // 5. tentando criar um produto sem ter materiais para isso:
        System.out.println("teste para checar se é possível criar um produto sem material suficiente: \n");
        boolean teste = servicoProducao.produzir(perfumeLavanda, 5);
        
        System.out.println("\nqtd de material em estoque: " + estoqueMaterial.getTamanhoEstoque());

        if (!teste) System.out.println("produção bloqueada por falta de material."); 
        else System.out.println("falha, produto feito de literalmente nada");
        System.out.println("\n=============================================================");
        
        // =================================================================================

        // 6. tentando criar da maneira correta. com materiais suficientes:
        estoqueMaterial.adicionarMaterial(alcool, 1000.0);
        estoqueMaterial.adicionarMaterial(essencia, 500.0);
        System.out.println(estoqueMaterial);

        boolean correto = servicoProducao.produzir(perfumeLavanda, 10);

        if (correto) System.out.println("produto adicionado ao catalogo do produtor com sucesso!");
        else System.out.println("falha, erro de código/lógica");
        
        System.out.println("\n=============================================================");
        System.out.println("catalogo do produtor: \n" + catalogoProdutor);
        
        // =================================================================================
        
        // 7. reposição do catalogo do VENDEDOR:
        System.out.println("tentativa de reposição do catalogo do VENDEDOR: \n");
        servicoReposicao.comprardoProdutor(perfumeLavanda, 4);
        
        System.out.println("estoque do produtor (deve ter 6 restantes): \n" + catalogoProdutor);
        System.out.println("estoque do vendedor (deve ter 4): " + catalogoVendedor);
        System.out.println("\n=============================================================");

        // =================================================================================

        // 8. venda do produto ao cliente: 
        System.out.println("final: venda básica para algum cliente: ");
        
        Cliente cliente = new Cliente("gabriel", "gabri@yahoo.com", "beira do rio");
        
        Venda venda = new Venda(cliente, catalogoVendedor, carrinhoCompras, servicoReposicao);
        venda.adicionarItem(perfumeLavanda, 3);
        venda.finalizarVenda("pix");
        
        System.out.println("\ndepois da venda: ");
        System.out.println("catalogo do vendedor (deve ter 1 restante): \n" + catalogoVendedor);
    }
}