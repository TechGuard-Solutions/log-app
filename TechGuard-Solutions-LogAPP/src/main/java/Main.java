import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//implements: fala que a classe main esta IMPLEMENTANDO a ActionListener
//ActionListener: faz com que as ações acontecam como quando clica no botao ele vai la para o metodo de baixo
public class Main implements ActionListener {

    // criação de objetos como "variaveis globais" do JS
    //static significa que esses elementos pertencem à própria classe Main ali de cima, e não a cada objeto
    static CriacaoTela tela = new CriacaoTela();
    static LogConsole console = new LogConsole();
    static ListaDeLogs logs = new ListaDeLogs();

    public static void main(String[] args) {
        tela.tela(); // inicializando tela
    }

    @Override // override serve para sobrescrever metodos, nessa caso ele estava sendo sobrescrito pela classe main
    public void actionPerformed(ActionEvent e) {
        console.exibirConsole(); // chamando metodo -> apos clicar no botao sera exibido no console
        tela.telaAposClique(); // -> formata a tela apos o clique

        DateTimeFormatter formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // metodo para formatar a data e hora
        List<String> tamanhoLista = logs.lista();

        // Timer para exibir os logs
        Timer timer = new Timer(); // o objeto Timer faz com que uma tarefa seja executada a cada periodo de tempo, como setInterval do JS
        timer.scheduleAtFixedRate(new TimerTask() { //scheduleAtFixedRate faz essa repetição dentro de um tempo especifico
            int index = 0; // indice para iterar a lista de logs

            @Override // override serve para sobrescrever metodos
            public void run() { // run pe chamado pelo Timer quando o scheduleAtFixedRate ativa - 3 segundos
                if (index < tamanhoLista.size()) {
                    LocalDateTime horarioAtual = LocalDateTime.now();
                    String formatadaNow = horarioAtual.format(formatada);

                    tela.textArea.append("-=-=-=-=-=-=-=-=-=-=-\n" + formatadaNow + " - " + tamanhoLista.get(index) + "\n" + "-=-=-=-=-=-=-=-=-=-=-\n");
                    index++;
                } else {
                    timer.cancel(); // quando o index for maior que o logs ele para, cancela
                }
            }
        }, 0, 2000); // aqui coloca o tempo de repetição do scheduleAtFixedRate
    }
}