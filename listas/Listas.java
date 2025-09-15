package listas;

public class Listas {
    private Bloco inicio;

    public void insereInicio(int valor) {

        Bloco novo = new Bloco();
        novo.setValor(valor);

        this.insereInicio(novo);

    }
    private void insereInicio(Bloco bloco) {

        bloco.setProx(this.inicio);
        this.inicio = bloco;        
    }

    //processo que insere no início ^

    public void insereFinal(int valor){
        Bloco novoBloco = new Bloco();
        novoBloco.setValor(valor);

        this.insereFinal(novoBloco);
    }
    private void insereFinal(Bloco novoBloco){
        if(listaVazia()) this.inicio = novoBloco;
        else{
            Bloco auxiliar = this.inicio;
            while(!auxiliar.checkUltimo()) auxiliar = auxiliar.getProx();
            auxiliar.setProx(novoBloco);
        }

        
    }

    //processo que insere no final da lista ^

    public boolean listaVazia(){
        if(inicio == null) return true;
        else return false;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        if(listaVazia()) return "a lista está vazia.";
        
        Bloco auxiliar = this.inicio;
        while(auxiliar != null) {
            buffer.append(auxiliar.getValor()+" ");
            auxiliar = auxiliar.getProx();
        }
        return buffer.toString();
    }
}
