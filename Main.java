import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== teste do sistema hahahaha: ===\n");

        // 1. iniciando o sistema
        Sistema sistema = Sistema.getInstance();
        
        // agora a tabela tem uma vigência! uau eba! o_O
        sistema.criarTabela(LocalDate.of(2025, 04, 23),LocalDate.of(2027, 12,31));
        
        // 2. produtos no catalogo + preços na TABELA DE PREÇOS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("--- 1. cadastro dos produtos: ---");
        sistema.cadastrarProduto("perfume lavanda", "P001", 50);
        sistema.cadastrarProduto("creme do mal", "P002", 20);
        sistema.cadastrarProduto("batom secreto", "P003", 5);

        sistema.definirPreco("P001", 150.0);
        sistema.definirPreco("P002", 20.0);
        sistema.definirPreco("P003", 50.0);

        System.out.println("\ncatálogo atual:");
        sistema.getCatalogo().mostrarCatalogo(); 

        System.out.println("===========================================================\n");

        // 3. cliente(s)......
        System.out.println("--- 2. gestão de cliente(s): ---");
        
        sistema.cadastrarCliente("gabri", "gabri@yahoo.com","neo quimica");
        sistema.cadastrarCliente("mario", "mario@yahoo.com","reino");
        sistema.cadastrarCliente("luigi", "luigi@yahoo.com","reino");
        
        System.out.println("\nlista de clientes: ");
        sistema.getListaClientes().mostrarLista();

        System.out.println("===========================================================\n");

        // 4. testando uma venda normal
        System.out.println("--- 3. realizando venda com sucesso: ---");
        
        sistema.iniciarVenda("gabri@yahoo.com");
        sistema.addItemVenda("P001", 5);
        sistema.addItemVenda("P002", 5);
        sistema.fecharVenda("dinheiro");
        
        System.out.println("\ncatalogo pós-venda:");
        sistema.getCatalogo().mostrarCatalogo();

        System.out.println("=== fim!!!!!! ===");

    }
}