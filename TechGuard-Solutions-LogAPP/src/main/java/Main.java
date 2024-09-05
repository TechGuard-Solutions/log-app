import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

//implements: fala que a classe main esta IMPLEMENTANDO a ActionListener
//ActionListener: faz com que as ações acontecam como quando clica no botao ele vai la para o metodo de baixo
public class Main implements ActionListener {

    // criação de objetos como "variaveis globais" do JS
    JButton button;
    JTextArea textArea;
    JPanel panel;
    Font fonteTamanhoNovo;
    JLabel titulo;
    JScrollPane scroll;

    //static significa que esses elementos pertencem à própria classe Main ali de cima, e não a cada objeto
    static List<String> logs = new ArrayList<>();
    static DateTimeFormatter formatada;
    static Integer tempo;
    static Integer tempoMs;

    public Main() {
        fonteTamanhoNovo = new Font("Arial Black", Font.BOLD, 40);

        JFrame frame = new JFrame();
        button = new JButton("Clique para iniciar");
        textArea = new JTextArea();
        panel = new JPanel();
        titulo = new JLabel();

        titulo.setText("TechGuard Solutions");
        titulo.setForeground(Color.green);
        titulo.setFont(fonteTamanhoNovo);

        button.addActionListener(this); // ativa o ActionListener, quando clicar no botao alguma coisa acontece
        button.setPreferredSize(new Dimension(190, 110)); // "tamanho do botao" nao deu muito certo
        button.setFont(fonteTamanhoNovo); // fonte
        button.setBackground(Color.white); // cor do botao
        button.setBorder(BorderFactory.createLineBorder(Color.green, 4)); // borda verde
        button.setFocusPainted(false); // tira a bordinha do texto do botao

        // deixando invisivel
        textArea.setVisible(false);

        panel.setBorder(BorderFactory.createEmptyBorder(80, 130, 60, 130)); // adicionando margins na tela
        panel.setLayout(new GridLayout(0, 1)); // tipo flex box

        // adicionando botoes e textos na tela
        panel.add(titulo);
        panel.add(button);

        panel.setBackground(Color.black); // background preto

        // Criando JScrollPane no construtor
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(panel); // adiciona todo o painel na tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quando eu fechar a tela a aplicação acaba junto
        frame.setTitle("Our GUI"); // titulo da pagina
        frame.pack();
        frame.setVisible(true); // faz com que seja visivel
        frame.setSize(800, 900); // tamanho da tela

        // Inicializando a lista de logs
        logs.add("Iniciando Aplicação");
        logs.add("Carregando configurações");
        logs.add("Validando configurações");
        logs.add("Conectando ao banco de dados...");
        logs.add("Banco de dados conectado");
        logs.add("\nIniciando processo da coleta de dados \nConsultando tabela de usuários");
        logs.add("Usuários carregados com sucesso");
        logs.add("\nConsultando tabela de bases \nBases carregadas com sucesso");
        logs.add("\nConsultando tabela de análises \nAnálises carregadas com sucesso");
        logs.add("Processo da coleta de dados finalizado");
        logs.add("\nIniciando análise dos dados \nAnalisando dados de usuários \nAnalisando dados de bases");
        logs.add("Analisando dados consolidados");
        logs.add("\nAlimentando Dashboard \nGeração de relatórios iniciada");
        logs.add("\nRelatórios gerados com sucesso \nEnviando relatórios para o servidor \nRelatórios enviados");
        logs.add("Iniciando processo de backup");
        logs.add("Backup realizado com sucesso");
        logs.add("Finalizando processos secundários");
        logs.add("Finalizando Aplicação");
    }

    public static void main(String[] args) {

        new Main(); // chamando a tela
        formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // metodo para formatar a data e hora
        tempo = 0;
        tempoMs = 0;

        for (String log : logs) { // adiciona cada indice do logs no log
            tempo = ThreadLocalRandom.current().nextInt(1, 4); // fazer o tempo ser aleatorio
            tempoMs = tempo * 1000;

            LocalDateTime horarioAtual = LocalDateTime.now(); // ve a data e o horario de agora
            String formatadaNow = horarioAtual.format(formatada);

            System.out.println("-=-=-=-=-=-=-=-=-=-=-\n"+formatadaNow + " - " + log + "\n"+"-=-=-=-=-=-=-=-=-=-=-\n");

            try {
                Thread.sleep(tempoMs); // "pausa" o for a cada tempoMs
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // override serve para sobrescrever metodos, nessa caso ele estava sendo sobrescrito pela classe main
    public void actionPerformed(ActionEvent e) { // quando clica no botão, vem para cá

        // removendo o botao e o titulo da tela
        panel.remove(button);
        panel.remove(titulo);

        fonteTamanhoNovo = new Font("Arial Black", Font.BOLD, 18);

        //formatando textArea
        textArea.setVisible(true); //deixando ela visivel
        textArea.setForeground(Color.white); // fazendo a letra ser branca
        textArea.setFont(fonteTamanhoNovo);
        textArea.setRows(17); // colocando a quantidade de linha antes de quebrar
        textArea.setBackground(Color.black);
        textArea.setSize(600, 850);


        scroll.setViewportView(textArea); // coloca o textArea na JScrollPane

        //arrumando o painel para o scroll
        panel.add(scroll);
        panel.setLayout(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // colocando padding

        // Timer para exibir os logs
        Timer timer = new Timer(); // o objeto Timer faz com que uma tarefa seja executada a cada periodo de tempo, como setInterval do JS
        timer.scheduleAtFixedRate(new TimerTask() { //scheduleAtFixedRate faz essa repetição dentro de um tempo especifico
            int index = 0; // indice para iterar a lista de logs

            @Override // override serve para sobrescrever metodos
            public void run() { // run pe chamado pelo Timer quando o scheduleAtFixedRate ativa - 3 segundos
                if (index < logs.size()) {
                    LocalDateTime horarioAtual = LocalDateTime.now();
                    String formatadaNow = horarioAtual.format(formatada);

                    textArea.append("-=-=-=-=-=-=-=-=-=-=-\n" + formatadaNow + " - " + logs.get(index) + "\n" + "-=-=-=-=-=-=-=-=-=-=-\n");
                    index++;
                } else {
                    timer.cancel(); // quando o index for maior que o logs ele para, cancela
                }
            }
        }, 0, 2000); // aqui coloca o tempo de repetição do scheduleAtFixedRate
    }
}

