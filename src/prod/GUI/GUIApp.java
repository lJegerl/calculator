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
        frame.setSize(500, 150);
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

        JPanel name = new JPanel();
        JLabel jlabel3 = new JLabel("Егор Харитончик Сергеевич, 3 курс 12 группа (ФПМИ ПИ)");
        name.add(jlabel3);

        JPanel pnl2 = new JPanel();
        JButton btnPlus = new JButton("+");
        JButton btnMinus = new JButton("−");
        JButton btnMulti = new JButton("*");
        JButton btnDiv = new JButton("/");
        JButton result = new JButton("Высчитать");

        pnl2.add(btnPlus);
        pnl2.add(btnMinus);
        pnl2.add(btnMulti);
        pnl2.add(btnDiv);
        pnl2.add(result);

        frame.getContentPane().add(BorderLayout.NORTH, name);
        frame.getContentPane().add(BorderLayout.CENTER, pnl);
        frame.getContentPane().add(BorderLayout.SOUTH, pnl2);
        frame.setVisible(true);


        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manager.checkNumber(manager.toNormalString(textField1.getText())) & manager.checkNumber(manager.toNormalString(textField2.getText()) )) {
                    String result = manager.getCalculation(manager.toNormalString(textField1.getText()), manager.toNormalString(textField2.getText()));
                    if (manager.checkNumber(result)) {
                        jlabel.setText(manager.toViewWithSpaces(result));
                    }
                }
            }
        });

        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel1.setText("+");
                manager.setCommand("+");
            }
        });

        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel1.setText("−");
                manager.setCommand("−");
            }
        });

        btnMulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel1.setText("*");
                manager.setCommand("*");
            }
        });

        btnDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel1.setText("/");
                manager.setCommand("/");
            }
        });
    }
}
