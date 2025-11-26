import java.time.LocalDate;

import catalogo.*;
import clientes.*;
import vendas.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== teste do sistema hahahaha: ===\n");

        // 1. iniciando o sistema
        Sistema sistema = Sistema.getInstance();
        Catalogo catalogo = sistema.getCatalogo();
        ListaClientes listaClientes = sistema.getListaClientes();
        TabelaPreco tabela = new TabelaPreco();

        // agora a tabela tem uma vigência! uau eba! o_O
        tabela.definirVigencia(LocalDate.of(2025, 04, 23),LocalDate.of(2027, 12,31));

        // 2. produtos no catalogo + preços na TABELA DE PREÇOS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("--- 1. cadastro dos produtos: ---");
        Produto p1 = new Produto("perfume lavanda", "P001");
        Produto p2 = new Produto("creme do mal", "P002");
        Produto p3 = new Produto("batom secreto", "P003");

        catalogo.adicionarProduto(p1, 50); 
        catalogo.adicionarProduto(p2, 20); 
        catalogo.adicionarProduto(p3, 5);  

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

        venda1.adicionarItem(p1, 2);
        venda1.adicionarItem(p2, 1);
        venda1.finalizarVenda("dinheiro");
        
        System.out.println("\ncatalogo pós-venda:");
        catalogo.mostrarCatalogo();

        System.out.println("===========================================================\n");

        System.out.println("--- 4. testando erro com a vigência: ---");
        
        TabelaPreco tabela2 = new TabelaPreco(); //esse período já passou alguns anos, ou seja, vencida...
        tabela2.definirVigencia(LocalDate.of(2006, 04, 23),LocalDate.of(2010, 12,31));
        
        //mesmos preços:
        tabela2.definirPreco(p1, 150.00);
        tabela2.definirPreco(p2, 45.50);
        tabela2.definirPreco(p3, 30.00);

        Venda venda2 = new Venda(cliente, catalogo, tabela2);

        venda2.adicionarItem(p1, 10);
        venda2.adicionarItem(p2, 7);
        venda2.finalizarVenda("dinheiro");

        System.out.println("\ncatalogo pós-venda:");
        catalogo.mostrarCatalogo();

        System.out.println("=== fim!!!!!! ===");

    }
}