import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LogConsole {

    ListaDeLogs logs = new ListaDeLogs(); // chamando metodo
    DateTimeFormatter formatada;

    void exibirConsole() {
        formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // metodo para formatar a data e hora

        List<String> tamanhoLista = logs.lista();
        // Timer para exibir os logs
        Timer timer = new Timer(); // o objeto Timer faz com que uma tarefa seja executada a cada periodo de tempo, como setInterval do JS
        timer.scheduleAtFixedRate(new TimerTask() { //scheduleAtFixedRate faz essa repetição dentro de um tempo especifico
            int index = 0; // indice para iterar a lista de logs

            public void run() { // run pe chamado pelo Timer quando o scheduleAtFixedRate ativa - 3 segundos
                if (index < tamanhoLista.size()) {
                    LocalDateTime horarioAtual = LocalDateTime.now();
                    String formatadaNow = horarioAtual.format(formatada);

                    System.out.println("-=-=-=-=-=-=-=-=-=-=-\n" + formatadaNow + " - " + tamanhoLista.get(index) + "\n" + "-=-=-=-=-=-=-=-=-=-=-\n");
                    index++;
                } else {
                    timer.cancel(); // quando o index for maior que o logs ele para, cancela
                }
            }
        }, 0, 2000); // aqui coloca o tempo de repetição do scheduleAtFixedRate

    }
}