import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Main implements ActionListener {

    int counts = 0;
    JButton button;
    JLabel label;
    JLabel label2;
    JPanel panel;
    Font fonteTamanhoNovo;

    public Main() {

        JFrame frame = new JFrame();
        Font fonteTamanhoNovo = new Font("Arial Black", Font.BOLD, 24);
        button = new JButton("Clique para iniciar");
        label = new JLabel("Numero de cliques: " + counts);
        label2 = new JLabel("teste");
        panel = new JPanel();

        button.addActionListener(this); // ativa o ActionListener, quando clicar no botao alguma coisa acontece
        button.setPreferredSize(new Dimension(190, 110)); // "tamanho do botao" nao deu muito certo
        button.setFont(fonteTamanhoNovo); // fonte
        button.setBackground(Color.white); // cor do botao
        button.setBorder(BorderFactory.createLineBorder(Color.green, 4)); // borda verde
        button.setFocusPainted(false); // tira a bordinha do texto do botao

        // deixando invisivel
        label.setVisible(false);
        label2.setVisible(false);

        panel.setBorder(BorderFactory.createEmptyBorder(100, 130, 10, 130)); // adicionando margins na tela
        panel.setLayout(new GridLayout(0, 1)); // tipo flex box

        // adicionando botoes e textos na tela
        panel.add(button);
        panel.add(label);
        panel.add(label2);

        panel.setBackground(Color.black); // background preto

        frame.add(panel); // adiciona todo o painel na tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quando eu fechar a tela a aplicação acaba junto
        frame.setTitle("Our GUI"); // titulo da pagina
        frame.pack();
        frame.setVisible(true); // faz com que seja visivel
        frame.setSize(600, 600); // tamanho da tela

    }

    static DateTimeFormatter formatada;
    static Integer tempo;
    static Integer tempoMs;

    public static void main(String[] args) {

        new Main();
//        formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        tempo = 0;
//        tempoMs = 0;
//
//        String[] logs = {
//                "Iniciando Aplicação",
//                "Carregando configurações",
//                "Validando configurações",
//                "Conectando ao banco de dados...",
//                "Banco de dados conectado",
//                "\nIniciando processo da coleta de dados \nConsultando tabela de usuários",
//                "Usuários carregados com sucesso",
//                "\nConsultando tabela de bases \nBases carregados com sucesso",
//                "\nConsultando tabela de análises \nAnálises carregadas com sucesso",
//                "Processo da coleta de dados finalizado",
//                "\nIniciando análise dos dados \nAnalisando dados de usuários \nAnalisando dados de bases",
//                "Analisando dados consolidados",
//                "\nAlimentando Dashboard \nGeração de relatórios iniciada",
//                "\nRelatórios gerados com sucesso \nEnviando relatórios para o servidor \nRelatórios enviados",
//                "Iniciando processo de backup",
//                "Backup realizado com sucesso",
//                "Finalizando processos secundários",
//                "Finalizando Aplicação"
//        };
//
//
//        for (String log : logs) {
//            tempo = ThreadLocalRandom.current().nextInt(1, 4);
//            tempoMs = tempo * 1000;
//
//            LocalDateTime now = LocalDateTime.now();
//            String formatadaNow = now.format(formatada);
//
//            System.out.println(formatadaNow + " - " + log);
//
//            try {
//                Thread.sleep(tempoMs);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override // sobrescreve uma coisa ja existe
    public void actionPerformed(ActionEvent e) { // quando clica no botão, vem para cá
        button.setVisible(false);

        fonteTamanhoNovo = new Font("Arial Black", Font.BOLD, 18);

        label.setVisible(true);
        label.setForeground(Color.white);
        label.setFont(fonteTamanhoNovo);

        formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        panel.setLayout(new GridLayout(10, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 10, 20));

        String[] logs = {
                "",
                "Iniciando Aplicação",
                "Carregando configurações",
                "Validando configurações",
                "Conectando ao banco de dados...",
                "Banco de dados conectado",
                "\nIniciando processo da coleta de dados \nConsultando tabela de usuários",
                "Usuários carregados com sucesso",
                "\nConsultando tabela de bases \nBases carregadas com sucesso",
                "\nConsultando tabela de análises \nAnálises carregadas com sucesso",
                "Processo da coleta de dados finalizado",
                "\nIniciando análise dos dados \nAnalisando dados de usuários \nAnalisando dados de bases",
                "Analisando dados consolidados",
                "\nAlimentando Dashboard \nGeração de relatórios iniciada",
                "\nRelatórios gerados com sucesso \nEnviando relatórios para o servidor \nRelatórios enviados",
                "Iniciando processo de backup",
                "Backup realizado com sucesso",
                "Finalizando processos secundários",
                "Finalizando Aplicação"
        };

        Timer timer = new Timer(0, null); // Inicialmente sem delay

        ActionListener taskPerformer = new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (index < logs.length) {
                    // Atualiza o label com o próximo log
                    LocalDateTime now = LocalDateTime.now();
                    String formatadaNow = now.format(formatada);
                    String t = formatadaNow + " - " + logs[index];
                    label.setText(t);
//                    System.out.println(t);
                    // Determina um tempo de atraso aleatório
                    tempo = ThreadLocalRandom.current().nextInt(1, 4);
                    tempoMs = tempo * 1000;
                    timer.setDelay(tempoMs); // Configura o delay do próximo log

                    index++;
                } else {
                    timer.stop(); // Para o timer quando todos os logs forem processados
                }
            }
        };

        timer.addActionListener(taskPerformer);
        timer.setInitialDelay(0); // Nenhum atraso inicial
        timer.start(); // Inicia o timer
    }
}


