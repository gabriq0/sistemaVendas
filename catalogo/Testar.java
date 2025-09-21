package catalogo;

public class Testar {
    public static void main(String[] args) {
        Catalogo c = new Catalogo();

        c.adicionarProduto(new Produto("Perfume Homem", "P12", 59.99), 24);
        c.adicionarProduto(new Produto("Hidratante Amor",  "P11", 20), 10);
        c.adicionarProduto(new Produto("Creme de Barbear",  "P13", 9.99), 12);
        c.adicionarProduto(new Produto("Perfume Homem",  "P12", 59.99), 31);
        c.adicionarProduto(new Produto("Hidratante Amor",  "P11", 20), 12);
        c.adicionarProduto(new Produto("Perfume Mulher",  "P15", 69.99), 20);

        c.mostrarCatalogo();
        System.out.println("======================================");

        c.excluirProduto(new Produto(null, "P13", 0)); //produto exemplo: creme de barbear. 
        c.excluirProduto(new Produto(null, "P15", 0)); //produto exemplo: perfume mulher.
        
        c.mostrarCatalogo();
        System.out.println("======================================");
        
        
        c.reduzirQuantidadeDoProduto(new Produto("Hidratante Amor",  "P11", 20), 10);
        c.mostrarCatalogo();
    }
}
