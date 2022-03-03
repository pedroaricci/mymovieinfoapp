package views;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ScreenSearch extends JFrame implements ActionListener {
    JTextField txtBuscar;
    public ScreenSearch() {
        setTitle("Digite o nome do Filme");
        //


        setLayout(new GridLayout(2,1));
        txtBuscar = new JTextField();
        add(txtBuscar);

        JButton btBuscar = new JButton("Buscar");
        add(btBuscar);
        btBuscar.setBackground(Color.cyan);
        btBuscar.addActionListener(this);
        btBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionPerformed(null);
                }
            }
        });
        JButton btlimpar =  new JButton("Limpar");
        add(btlimpar);
        btlimpar.addActionListener(this::clean);
        btlimpar.setBackground(Color.cyan);

        setLayout(new BorderLayout());
        add(txtBuscar, BorderLayout.NORTH);
        add(btlimpar, BorderLayout.WEST);
        add(btBuscar, BorderLayout.EAST);

        setSize(150, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground( Color.red );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.screenMovie.getScreenMoviePresenter().requestMovieData(txtBuscar.getText());
        Main.navegarParaMovie();
    }

    public void clean(Object o) {
        txtBuscar.setText("");
    }
}
