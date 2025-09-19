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

    public boolean excluirItem(T item){
        if(listaVazia()) return false; //não tem nada na lista, retorna falso, pois não excluiu nada.
        
        if (inicio.getItem().equals(item)){
            inicio = inicio.getProx();
            return true; //esse if é para caso o elemento a excluir, seja o inicio.
        }

        Bloco<T> itemAnteriorAoAlvo = inicio;
        Bloco<T> itemAlvo = inicio.getProx();

        while(itemAlvo != null){
            if(itemAlvo.getItem().equals(item)){
                itemAnteriorAoAlvo.setProx(itemAlvo.getProx());
                return true; 
                //nesse caso, ele exclui. ele manda coloca o ponteiro "prox" do itemAnteriorAoAlvo no ponteiro "prox" do itemAlvo.
            }
            itemAnteriorAoAlvo = itemAlvo;
            itemAlvo = itemAlvo.getProx();
        }

        return false;
        //eu não preciso "nullificar" o alvo da exclusão, pois o java já faz isso através do garbage collector. 
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