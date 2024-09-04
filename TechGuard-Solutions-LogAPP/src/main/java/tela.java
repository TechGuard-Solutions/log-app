import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tela implements ActionListener {

    int counts = 0;
    JButton button;
    JLabel label;
    JLabel label2;
    JPanel panel;
    Font fonteTamanhoNovo;

    public tela(){

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

        panel.setBorder(BorderFactory.createEmptyBorder(100,130,10,130)); // adicionando margins na tela
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

    public static void main(String[] args){
        new tela(); // faz tudo funcionar
    }

    @Override // sobrescreve uma coisa ja existe
    public void actionPerformed(ActionEvent e) { // quando clica no botao vem para ca

        counts++;
        fonteTamanhoNovo = new Font( "Arial Black", Font.BOLD, 24);
        panel.setLayout(new GridLayout(10, 1));

        label.setText("Numero de cliques: " + counts);
        label.setVisible(true);
        label.setFont(fonteTamanhoNovo);
        label.setForeground(Color.white);

        label2.setFont(fonteTamanhoNovo);
        label2.setForeground(Color.white);

//        button.setVisible(false);
//        Timer timer = new Timer(5000, new ActionListener(){
//            label2.setVisible(true);
//        });
    }
}

