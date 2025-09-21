package vendas;
import java.time.*;
import java.time.format.DateTimeFormatter;
import catalogo.*;

public class Venda {
    private String idVenda;
    private String dataTransacao;

    public String getDataTransacao() {
        LocalDateTime horadeVenda = LocalDateTime.now();
        DateTimeFormatter estiloBrasil = DateTimeFormatter.ofPattern("hh:mm - dd/MM/yy");
        dataTransacao = horadeVenda.format(estiloBrasil);
        return dataTransacao; //essa formatação está aí, pois o formato original é bem estranho/confuso.
    }
    public String getIdVenda(){
        return this.idVenda;
    }

    
}
