package listas;
public class Bloco<T> {
    private T item;
    private Bloco<T> prox;

    public void setItem(T item){ this.item = item; }
    public void setProx(Bloco<T> prox) { this.prox = prox; }
    public T getItem() { return this.item; }
    public Bloco<T> getProx() { return this.prox; }

    public boolean isUltimo(){
        if(this.prox == null) return true;
        else return false;
    }

    @Override
    public String toString() { return this.item.toString(); }

    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Bloco<?> blocoBusca = (Bloco<?>) objeto;

        if(this.item == null) return blocoBusca.item == null;
        else return this.item.equals(blocoBusca.item);
    }
}
