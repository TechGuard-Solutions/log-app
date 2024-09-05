import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main implements ActionListener {

    JButton button;
    JTextArea textArea;
    JPanel panel;
    Font fonteTamanhoNovo;
    JLabel titulo;

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

        panel.setBorder(BorderFactory.createEmptyBorder(100, 130, 10, 130)); // adicionando margins na tela
        panel.setLayout(new GridLayout(0, 1)); // tipo flex box

        // adicionando botoes e textos na tela

        panel.add(titulo);
        panel.add(button);
        panel.add(textArea);

        panel.setBackground(Color.black); // background preto

        frame.add(panel); // adiciona todo o painel na tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quando eu fechar a tela a aplicação acaba junto
        frame.setTitle("Our GUI"); // titulo da pagina
        frame.pack();
        frame.setVisible(true); // faz com que seja visivel
        frame.setSize(800, 900); // tamanho da tela

    }

    static DateTimeFormatter formatada;
    static Integer tempo;
    static Integer tempoMs;

    public static void main(String[] args) {

        new Main();
        formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        tempo = 0;
        tempoMs = 0;

        List<String> logs = new ArrayList<>();
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


        for (String log : logs) {
            tempo = ThreadLocalRandom.current().nextInt(1, 4);
            tempoMs = tempo * 1000;

            LocalDateTime now = LocalDateTime.now();
            String formatadaNow = now.format(formatada);

            System.out.println("-=-=-=-=-=-=-=-=-=-=-\n"+formatadaNow + " - " + log + "\n"+"-=-=-=-=-=-=-=-=-=-=-\n");

            try {
                Thread.sleep(tempoMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // sobrescreve uma coisa ja existe
    public void actionPerformed(ActionEvent e) { // quando clica no botão, vem para cá
        panel.remove(button);
        panel.remove(titulo);

        fonteTamanhoNovo = new Font("Arial Black", Font.BOLD, 18);

        textArea.setVisible(true);
        textArea.setForeground(Color.white);
        textArea.setFont(fonteTamanhoNovo);
        textArea.setRows(17);
        textArea.setBackground(Color.black);
        textArea.setSize(600, 850);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        panel.add(scrollPane);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        List<String> logs = new ArrayList<>();
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

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // Cria um scheduler para tarefas assíncronas
        int[] index = {0}; // Índice inicial para iterar os logs

// Agendar uma tarefa que será executada a cada intervalo
        scheduler.scheduleAtFixedRate(() -> {
            if (index[0] < logs.size()) {
                // Obtém o horário atual e formata a string
                LocalDateTime now = LocalDateTime.now();
                String formatadaNow = now.format(formatada);

                // Atualiza o JTextArea com o próximo log
                textArea.append("-=-=-=-=-=-=-=-=-=-=-\n"+formatadaNow + " - " + logs.get(index[0]) + "\n"+"-=-=-=-=-=-=-=-=-=-=-\n");

                // Avança o índice para o próximo log
                index[0]++;
            } else {
                // Para o scheduler quando todos os logs forem exibidos
                scheduler.shutdown();
            }
        }, 0, 3, TimeUnit.SECONDS); // Executa inicialmente sem delay, e depois a cada 3 segundos
   }
}


