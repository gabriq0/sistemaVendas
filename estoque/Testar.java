public class Testar {
    public static void main(String[] args) {
        Catalogo c = new Catalogo();

        c.adicionarItem(new Produto("Perfume Homem"), 24);
        c.adicionarItem(new Produto("Hidratante Amor"), 10);
        c.adicionarItem(new Produto("Creme de Barbear"), 12);
        c.adicionarItem(new Produto("Perfume Homem"), 31);
        c.adicionarItem(new Produto("Hidratante Amor"), 12);
        c.adicionarItem(new Produto("Perfume Mulher"), 20);

        System.out.println(c.toString());
    }
}
