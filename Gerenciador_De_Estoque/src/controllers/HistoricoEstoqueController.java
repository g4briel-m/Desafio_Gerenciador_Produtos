package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HistoricoEstoqueController {
    
        private List<String> historico;

    public HistoricoEstoqueController() {
        this.historico = new ArrayList<>();
    }

    public void adicionarRegistro(String registro) {
        // Adiciona um registro com a data e hora atual
        String dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        historico.add(dataHora + " - " + registro);
    }

    public List<String> getHistorico() {
        return new ArrayList<>(historico);
    }

    public String obterHistoricoFormatado() {
        StringBuilder formatoTabela = new StringBuilder();
        formatoTabela.append("Data/Hora\t\tMovimentação\n");

        for (String registro : historico) {
            formatoTabela.append(registro).append("\n");
        }

        return formatoTabela.toString();
    }
}
