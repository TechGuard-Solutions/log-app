import java.util.ArrayList;
import java.util.List;

public class ListaDeLogs {
    List<String> lista(){
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
        return logs;
    }
}
