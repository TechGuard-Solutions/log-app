import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Logs {
    public void lerLogs() {

        Scanner opLogs = new Scanner(System.in);

        String urlNode = "logs/logsNode/";
        String urlJava = "logs/logsJava/";
        String urlBanco = "logs/logsDB/";
        String urlSistema = "logs/logsSistema/";

        String bucketName = "techguard-bucket";
        String folderPath = ""; // Caminho da pasta "logs" no S3
        Region region = Region.US_EAST_1; // Substitua pela sua região do bucket


        while (true) {
            System.out.println("""
                Quais logs deseja ler?
                0 - SAIR
                1 - Logs do Servidor Node.
                2 - Logs do Servidor Java.
                3 - Logs do Banco de Dados.
                4 - Logs do Sistema EC2.
                """);

            switch (opLogs.nextInt()) {
                case 0:
                    System.out.println("Parando Aplicação...");
                    return;
                case 1:
                    folderPath = urlNode;
                    break;
                case 2:
                    folderPath = urlJava;
                    break;
                case 3:
                    folderPath = urlBanco;
                    break;
                case 4:
                    folderPath = urlSistema;
                    break;
            }



            S3Client s3 = S3Client.builder()
                    .region(region)
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();

            // Lista os arquivos e pastas dentro da pasta "logs/"
            ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .prefix(folderPath) // Filtra os objetos com base no caminho "logs/"
                    .delimiter("/") // Trata subdiretórios
                    .build();

            while (true) {
                ListObjectsV2Response listResponse = s3.listObjectsV2(listRequest);
                List<S3Object> objects = listResponse.contents();

                // Exibe a lista de arquivos encontrados
                System.out.println("Arquivos encontrados:");
                System.out.println("0 - VOLTAR");
                for (int i = 0; i < objects.size(); i++) {
                    System.out.println(i + 1 + ": " + objects.get(i).key());
                }

                // Solicita ao usuário que escolha um arquivo
                System.out.print("\nDigite o número do arquivo que deseja ler (Digite 0 para voltar): ");
                Scanner scanner = new Scanner(System.in);
                int escolha = scanner.nextInt();

                // Verifica se a escolha é válida
                if (escolha == 0) {
                    System.out.println("Retornando...");
                    break;
                } else if (escolha < 1 || escolha > objects.size()) {
                    System.out.println("Escolha inválida!");
                    break;
                }

                // Obtém o arquivo selecionado
                String arquivoSelecionado = objects.get(escolha - 1).key();
                System.out.println("Lendo o arquivo: " + arquivoSelecionado);

                // Lê o arquivo selecionado do S3
                System.out.println("\n".repeat(5));
                System.out.println("===============================================================");
                lerArquivoS3(s3, bucketName, arquivoSelecionado);
                System.out.println("===============================================================");
                System.out.println("\n".repeat(5));
            }
            // Fecha o cliente S3
            s3.close();
        }
        }

    private void lerArquivoS3(S3Client s3, String bucketName, String key) {
        // Construção de um novo objeto para solicitar o arquivo específico de um bucket no Amazon S3.
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        // Lendo o conteúdo do arquivo de log diretamente do S3
        try (InputStream inputStream = s3.getObject(getObjectRequest);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // Imprime cada linha do arquivo .log
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            System.out.println("Operação de leitura do arquivo de log finalizada!");
        }
    }
}
