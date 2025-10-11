import catalogo.*;
import clientes.Cliente;
import listas.Lista;
import logistica.*; 
import producao.*;
import vendas.*;

public class teste {
    public static void main(String[] args) {

        // --- 1. SETUP DO ECOSSISTEMA ---

        // A) AMBIENTE DO PRODUTOR (A FÁBRICA)
        EstoqueInsumos almoxarifadoFabrica = new EstoqueInsumos();
        Catalogo catalogoProdutor = new Catalogo();
        ServicoProducao servicoProducao = new ServicoProducao(almoxarifadoFabrica, catalogoProdutor);

        // B) AMBIENTE DO VENDEDOR (A LOJA)
        Catalogo catalogoVendedor = new Catalogo();
        
        // C) A PONTE ENTRE ELES
        ServicoReposicao servicoReposicao = new ServicoReposicao(catalogoProdutor, catalogoVendedor);

        // --- 2. DEFINIÇÃO DA "RECEITA" E DO PRODUTO ---
        Insumo alcool = new Insumo("I01", "Álcool de Cereais", "ml");
        Insumo essencia = new Insumo("I02", "Essência de Lavanda", "ml");
        Receita receitaSabonete = new Receita();
        receitaSabonete.adicionarIngredienteReceita(alcool, 50.0);
        receitaSabonete.adicionarIngredienteReceita(essencia, 15.0);
        Produto saboneteLavanda = new Produto("Sabonete de Lavanda", "s01", 12.50, receitaSabonete);


        // =================================================================================
        System.out.println("--- CENÁRIO 1: PROVANDO A PROTEÇÃO DO CATÁLOGO (REGRA 1) ---");
        // A linha abaixo, se fosse descomentada, causaria um ERRO DE COMPILAÇÃO.
        // O compilador Java nos impede de adicionar um produto "aleatório"
        // porque o método adicionarProduto() não é público.
        
        // ERRO: catalogoVendedor.adicionarProduto(saboneteLavanda, 100); 
        
        System.out.println("SUCESSO: O código nem compilaria se tentássemos chamar adicionarProduto() daqui.");
        System.out.println("Isso prova que a única forma de abastecer o vendedor é via ServicoReposicao.");
        // =================================================================================


        // =================================================================================
        System.out.println("\n--- CENÁRIO 2: TENTATIVA DE PRODUÇÃO SEM MATÉRIA-PRIMA (REGRA 2) ---");
        System.out.println("Tentando produzir 5 sabonetes...");
        boolean produziuSemInsumos = servicoProducao.produzir(saboneteLavanda, 5);
        
        System.out.println("\n-> RESULTADO:");
        if (!produziuSemInsumos) {
            System.out.println("SUCESSO: A produção foi corretamente BLOQUEADA por falta de insumos.");
        } else {
            System.out.println("FALHA: O sistema permitiu a produção sem matéria-prima.");
        }
        System.out.println("Estoque do Produtor: " + (catalogoProdutor.getTamanhoCatalogo() > 0 ? catalogoProdutor : "Vazio (Correto)"));
        // =================================================================================


        // =================================================================================
        System.out.println("\n--- CENÁRIO 3: PRODUÇÃO BEM-SUCEDIDA APÓS ABASTECIMENTO (REGRA 2) ---");
        System.out.println("Adicionando matéria-prima ao almoxarifado da fábrica...");
        almoxarifadoFabrica.adicionarInsumo(alcool, 1000.0);
        almoxarifadoFabrica.adicionarInsumo(essencia, 500.0);
        System.out.println(almoxarifadoFabrica);

        System.out.println("\nTentando produzir 10 sabonetes...");
        boolean produziuComInsumos = servicoProducao.produzir(saboneteLavanda, 10);

        System.out.println("\n-> RESULTADO:");
        if (produziuComInsumos) {
            System.out.println("SUCESSO: A produção foi realizada e o produto foi adicionado ao estoque do produtor.");
        } else {
            System.out.println("FALHA: O sistema bloqueou a produção indevidamente.");
        }
        System.out.println("Estoque do Produtor: \n" + catalogoProdutor);
        // =================================================================================
        

        // =================================================================================
        System.out.println("\n--- CENÁRIO 4: REPOSIÇÃO DO VENDEDOR (REGRA 1) ---");
        System.out.println("Vendedor tentando comprar 4 sabonetes do produtor...");
        servicoReposicao.comprardoProdutor(saboneteLavanda, 4);
        
        System.out.println("\n-> RESULTADO:");
        System.out.println("Estoque do Produtor (deve ter 6 restantes): \n" + catalogoProdutor);
        System.out.println("Estoque do Vendedor (deve ter 4): \n" + catalogoVendedor);
        // =================================================================================

        
        // =================================================================================
        System.out.println("\n--- CENÁRIO 5: VENDA FINAL AO CLIENTE ---");
        Cliente cliente = new Cliente("Ana", "ana@email.com", "Brasil");
        Venda venda = new Venda(cliente, catalogoVendedor, new Lista<>(), servicoReposicao);
        venda.adicionarItem(saboneteLavanda, 3); // Ana quer 3 sabonetes
        venda.finalizarVenda("Crédito");
        
        System.out.println("\n-> RESULTADO PÓS-VENDA:");
        System.out.println("Estoque Final do Vendedor (deve ter 1 restante): \n" + catalogoVendedor);
        // =================================================================================

        /* SIM, EU USEI UMA IA (gemini), PARA CRIAR ESSE TESTE! TEM MUITAS CLASSES NOVAS, EU NÃO QUERIA CRIAR UM 
        MENU PARA TESTAR CADA UM DELES... =(
         */
    }
}