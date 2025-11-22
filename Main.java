import catalogo.*;
import clientes.*;
import listas.*;
import vendas.*;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();
        
        Catalogo catalogoVendedor = sistema.getCatalogoVendedor();
        TabelaPreco tabelaVendedor = sistema.getTabelaVendedor();
        
        // 1. cria os produtos, define o preço na tabela.
        Produto perfume = new Produto("perfume de banana", "p01");
        Produto sabonete = new Produto("sabonete hahaha", "p02");
        tabelaVendedor.definirPreco(perfume, 10.00);
        tabelaVendedor.definirPreco(sabonete, 5.00);

        // 2. adiciona o produto no estoque, finja que o vendedor comprou ele ou algo do tipo.
        catalogoVendedor.adicionarProduto(perfume, 20);
        catalogoVendedor.adicionarProduto(sabonete, 5);
        
        System.out.println("estoque:\n" + catalogoVendedor);
        System.out.println("=============================================================\n");

        // 3. venda.........................
        Cliente cliente1 = new Cliente("gabri", "gabri@yahoo.com", "eeeeeeeee");
        Lista<ItemVenda> carrinho1 = new Lista<ItemVenda>();

        Venda venda1 = new Venda(cliente1, catalogoVendedor, carrinho1, tabelaVendedor);
        
        venda1.adicionarItem(perfume, 2);
        venda1.adicionarItem(sabonete, 3);

        boolean sucesso1 = venda1.finalizarVenda("dinheiro");

        if(sucesso1) System.out.println("sucesso");
        else System.out.println("falha");
        
        System.out.println("\nchecando estoque pós venda:\n" + catalogoVendedor);
        System.out.println("=============================================================\n");
    }
}