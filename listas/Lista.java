package listas;
public class Lista<T>{
    private Bloco<T> inicio;
    private int tamanho=0;

    public int tamanhoLista(){
        return this.tamanho;
    }

    public void insereFinal(T item){
        Bloco<T> novoItem = new Bloco<T>();
        novoItem.setItem(item);

        if(listaVazia()){
            this.inicio = novoItem;
            tamanho+=1;
        }
        
        else{
            Bloco<T> auxiliar = this.inicio;

            while(!auxiliar.isUltimo()) auxiliar = auxiliar.getProx();

            auxiliar.setProx(novoItem); 
            tamanho+=1;   
            //nesse ponto aqui, auxiliar está no ultimo item da lista, então o próximo dele deve ser o novo item (o novo último item).
        }
    }

    public boolean listaVazia(){
        if(this.inicio == null) return true;
        else return false;
    }

    public T compararItens(T item){
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
            tamanho-=1;
            return true; //esse if é para caso o elemento a excluir, seja o inicio.
        }

        Bloco<T> itemAnteriorAoAlvo = inicio;
        Bloco<T> itemAlvo = inicio.getProx();

        while(itemAlvo != null){
            if(itemAlvo.getItem().equals(item)){
                itemAnteriorAoAlvo.setProx(itemAlvo.getProx());
                tamanho-=1;
                return true; 
                //nesse caso, ele exclui. ele manda coloca o ponteiro "prox" do itemAnteriorAoAlvo no ponteiro "prox" do itemAlvo.
            }
            itemAnteriorAoAlvo = itemAlvo;
            itemAlvo = itemAlvo.getProx();
        }

        return false;
        //eu não preciso "nullificar" o alvo da exclusão, pois o java já faz isso através do garbage collector. 
    }

    public T pegarBloco(int index){
        if(index < 0 || index >= this.tamanho) return null;
        
        Bloco<T> auxiliar = this.inicio;
        for(int a=0;a < index;a++){
            auxiliar = auxiliar.getProx();
        }
        return auxiliar.getItem();
        //isso vai passar pela lista e entregar um bloco. parecido com um array, ele vai ter um "index", para que
        //eu consiga passar pela lista e pegar um elemento específico sem que obrigatoriamente eu compare ele com 
        //outro, como é o caso do método "compararItens()"!
    }

    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        Bloco<T> auxiliar = this.inicio;

        while (auxiliar != null) {    
            sbuffer.append(auxiliar.toString()+"\n");
            auxiliar = auxiliar.getProx();
        }
        return sbuffer.toString();
    }
}