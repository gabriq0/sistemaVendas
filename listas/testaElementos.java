package listas;
public class testaElementos {
    public static void main(String[] args) {
        Listas lista = new Listas();
        int valores[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for(int i=0;i<valores.length;i++) lista.insereFinal(valores[i]);

        System.out.println(lista.toString());
    }
}
