package listas;
public class Lista<T>{
    private Bloco<T> inicio;

    public void insereFinal(T item){
        Bloco<T> novoItem = new Bloco<T>();
        novoItem.setItem(item);

        if(listaVazia()) this.inicio = novoItem;
        
        else{
            Bloco<T> auxiliar = this.inicio;

            while(!auxiliar.isUltimo()) auxiliar = auxiliar.getProx();

            auxiliar.setProx(novoItem); //nesse ponto aqui, auxiliar está no ultimo item da lista, 
                                    //então o próximo dele deve ser o novo item (o novo último item).
        }
    }

    public boolean listaVazia(){
        if(this.inicio == null) return true;
        else return false;
    }

    public T checarLista(T item){
        Bloco<T> busca = inicio;
        while(busca != null){
            if(busca.getItem().equals(item)) return busca.getItem();
            
            busca = busca.getProx();
        }

        return null; 
    }

    public String toString() {

        StringBuffer sb = new StringBuffer();

        Bloco<T> aux = this.inicio;

        while (aux!=null) {
            
            sb.append(aux.toString()+"\n");
            aux = aux.getProx();
        }
        return sb.toString();
    }
}