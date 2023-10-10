package prod.GUI;

import prod.calc.AllCalculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIApp {
    public GUIApp() {
        AllCalculation manager = new AllCalculation();

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 100);
        frame.setLocation(520, 300);

        JPanel pnl = new JPanel();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        textField1.setPreferredSize(new Dimension(150, 20));
        textField2.setPreferredSize(new Dimension(150, 20));

        JLabel jlabel = new JLabel();
        jlabel.setText("");
        JLabel jlabel1 = new JLabel();
        jlabel1.setText("+");
        JLabel jlabel2 = new JLabel();
        jlabel2.setText("=");
        pnl.add(textField1);
        pnl.add(jlabel1);
        pnl.add(textField2);
        pnl.add(jlabel2);
        pnl.add(jlabel);

        JPanel pnl2 = new JPanel();
        JButton btn1 = new JButton("+");
        JButton btn2 = new JButton("−");
        JButton btn3 = new JButton("Высчитать");
        pnl2.add(btn1);
        pnl2.add(btn2);
        pnl2.add(btn3);

        frame.getContentPane().add(BorderLayout.CENTER, pnl);
        frame.getContentPane().add(BorderLayout.SOUTH, pnl2);
        frame.setVisible(true);


        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manager.checkNumber(textField1.getText()) & manager.checkNumber(textField2.getText())) {
                    String result = manager.getCalculation(textField1.getText(), textField2.getText());
                    if(manager.checkNumber(result)) {
                        jlabel.setText(result);
                    }
                }
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel1.setText("+");
                manager.setCommand("+");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel1.setText("−");
                manager.setCommand("−");
            }
        });
    }
}
