import javax.swing.*;
import java.awt.*;

public class CriacaoTela {
    JScrollPane scroll;
    JButton button;
    JTextArea textArea;
    JPanel panel;
    Font fonteTamanhoNovo;
    JLabel titulo;

    Main main = new Main();

    void tela() {
        fonteTamanhoNovo = new Font("Arial Black", Font.BOLD, 40);
        JFrame frame = new JFrame();

        button = new JButton("Clique para iniciar");
        textArea = new JTextArea();
        panel = new JPanel();
        titulo = new JLabel();

        titulo.setText("TechGuard Solutions");
        titulo.setForeground(Color.green);
        titulo.setFont(fonteTamanhoNovo);

        button.addActionListener(e -> main.actionPerformed(e)); // ativa o ActionListener, quando clicar no botao alguma coisa acontece
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
        frame.setSize(800, 700); // tamanho da tela
    }

    void telaAposClique() { // quando clica no botão, vem para cá

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
    }
}