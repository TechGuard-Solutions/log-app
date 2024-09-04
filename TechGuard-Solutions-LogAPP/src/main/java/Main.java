import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Integer tempo = 0;
        Integer tempoMs = 0;

        String[] logs = {
                "Iniciando Aplicação",
                "Carregando configurações",
                "Validando configurações",
                "Conectando ao banco de dados...",
                "Banco de dados conectado",
                "Iniciando processo da coleta de dados",
                "Consultando tabela de usuários",
                "Usuários carregados com sucesso",
                "Consultando tabela de bases",
                "Bases carregados com sucesso",
                "Consultando tabela de análises",
                "Análises carregadas com sucesso",
                "Processo da coleta de dados finalizado",
                "Iniciando análise dos dados",
                "Analisando dados de usuários",
                "Analisando dados de bases",
                "Analisando dados consolidados",
                "Alimentando Dashboard",
                "Geração de relatórios iniciada",
                "Relatórios gerados com sucesso",
                "Enviando relatórios para o servidor",
                "Relatórios enviados",
                "Iniciando processo de backup",
                "Backup realizado com sucesso",
                "Finalizando processos secundários",
                "Finalizando Aplicação"
        };


        for (String log : logs) {
            tempo = ThreadLocalRandom.current().nextInt(1,7);
            tempoMs = tempo * 1000;

            LocalDateTime now = LocalDateTime.now();
            String formatadaNow = now.format(formatada);

            System.out.println(formatadaNow + " - " + log);

            try {
                Thread.sleep(tempoMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
