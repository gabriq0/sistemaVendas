import catalogo.*;
import clientes.*;
import vendas.*;
import excecoes.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== teste do sistema hahahaha: ===\n");

        // 1. iniciando o sistema
        Sistema sistema = Sistema.getInstance();
        Catalogo catalogo = sistema.getCatalogoVendedor();
        TabelaPreco tabela = sistema.getTabelaVendedor();
        ListaClientes listaClientes = sistema.getListaClientes();

        // 2. produtos no catalogo + preços na TABELA DE PREÇOS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("--- 1. cadastro dos produtos: ---");
        Produto p1 = new Produto("perfume lavanda", "P001");
        Produto p2 = new Produto("creme do mal", "P002");
        Produto p3 = new Produto("batom secreto", "P003");
        Produto pSemPreco = new Produto("coisa", "P004");

        catalogo.adicionarProduto(p1, 50); 
        catalogo.adicionarProduto(p2, 20); 
        catalogo.adicionarProduto(p3, 5);  
        catalogo.adicionarProduto(pSemPreco, 10);

        tabela.definirPreco(p1, 150.00);
        tabela.definirPreco(p2, 45.50);
        tabela.definirPreco(p3, 30.00);

        System.out.println("catálogo atual:");
        catalogo.mostrarCatalogo(); 

        System.out.println("===========================================================\n");

        // 3. cliente(s)......
        System.out.println("--- 2. gestão de cliente(s): ---");
        Cliente cliente = listaClientes.adicionar("gabri", "gabri@yahoo.com","neo quimica");
        listaClientes.adicionar("mario", "mario@yahoo.com","reino");
        listaClientes.adicionar("luigi", "luigi@yahoo.com","reino");
        
        System.out.println("lista de clientes: ");
        listaClientes.mostrarLista();

        System.out.println("===========================================================\n");

        // 4. testando uma venda normal
        System.out.println("--- 3. realizando venda com sucesso: ---");
        Venda venda1 = new Venda(cliente, catalogo, tabela);

        try {
            venda1.adicionarItem(p1, 2);
            venda1.adicionarItem(p2, 1);
            venda1.finalizarVenda("dinheiro");
            System.out.println(">> venda finalizada!");
        } catch (Exception e) {
            System.out.println("erro inesperado: " + e.getMessage());
        }

        System.out.println("\ncatalogo pós-venda:");
        catalogo.mostrarCatalogo();

        System.out.println("===========================================================\n");

        System.out.println("--- 4. teste para excessões: ---");
        
        // 5.1 testando uma venda de produto sem preço:
        System.out.println("\n[4.1] tentar vender produto sem preço:");
        Venda vendaErro1 = new Venda(cliente, catalogo, tabela);
        try {
            vendaErro1.adicionarItem(pSemPreco, 1);
        } catch (ProdutoSemPrecoException e) {
            System.out.println("sucesso!: erro -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("outro erro (não deveria acontecer): " + e.getMessage());
        }

        // 5.2 testando comprar mais do que o estoque
        System.out.println("\n[4.2] tentar vender mais do que tem:");
        Venda vendaErro2 = new Venda(cliente, catalogo, tabela);
        try {
            vendaErro2.adicionarItem(p3, 10);
            vendaErro2.finalizarVenda("dinheiro");
        } catch (EstoqueInsuficienteException e) {
            System.out.println("sucesso!: erro -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("outro erro (não deveria acontecer): " + e.getMessage());
        }

        // 5.3 qtd invalidada de itens no carrinho
        System.out.println("\n[4.3] tentar adicionar qtd negativa:");
        try {
            vendaErro2.adicionarItem(p1, -5);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("sucesso!: erro -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("outro erro (não deveria acontecer): " + e.getMessage());
        }

        System.out.println("\n=== fim!!!!!! ===");
    }
}