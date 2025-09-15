package listas;

public class Bloco{
    private int valor;
    private Bloco prox;

    public void setValor(int valor){
        this.valor = valor;
    }

    public void setProx(Bloco prox){
        this.prox = prox;
    }

    public int getValor(){
        return this.valor;
    }

    public Bloco getProx(){
        return this.prox;
    }

    public boolean checkUltimo(){
        if(getProx() == null) return true;
        else return false;
    }
}