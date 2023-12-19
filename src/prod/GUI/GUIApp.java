package prod.GUI;

import prod.calc.AllCalculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIApp {
    private static int count = 1;

    public GUIApp() {
        AllCalculation manager = new AllCalculation();

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 150);
        frame.setLocation(520, 300);

        JPanel pnl = new JPanel();
        TextField numberFirst = new TextField();
        TextField numberSecond = new TextField();
        TextField numberThird = new TextField();
        TextField numberFourth = new TextField();

        numberFirst.setPreferredSize(new Dimension(150, 20));
        numberSecond.setPreferredSize(new Dimension(150, 20));
        numberThird.setPreferredSize(new Dimension(150, 20));
        numberFourth.setPreferredSize(new Dimension(150, 20));

        JLabel jlabel = new JLabel();
        jlabel.setText("");
        JLabel jlabel1 = new JLabel();
        jlabel1.setText("+");
        JLabel jlabel2 = new JLabel();
        jlabel2.setText("+");
        JLabel jlabel3 = new JLabel();
        jlabel3.setText("+");
        JLabel jlabel4 = new JLabel();
        jlabel4.setText("(");
        JLabel jlabel5 = new JLabel();
        jlabel5.setText(")");
        JLabel jlabel6 = new JLabel();
        jlabel6.setText("=");

        pnl.add(numberFirst);
        pnl.add(jlabel1);
        pnl.add(jlabel4);
        pnl.add(numberSecond);
        pnl.add(jlabel2);
        pnl.add(numberThird);
        pnl.add(jlabel5);
        pnl.add(jlabel3);
        pnl.add(numberFourth);
        pnl.add(jlabel6);
        pnl.add(jlabel);

        JPanel name = new JPanel();
        JLabel jlabel10 = new JLabel("Харитончик Егор Сергеевич, 3 курс 12 группа (ФПМИ ПИ)");
        name.add(jlabel10);

        JPanel pnl2 = new JPanel();
        JPanel text1 = new JPanel();
        JPanel text2 = new JPanel();

        JButton btnPlus = new JButton("+");
        JButton btnMinus = new JButton("−");
        JButton btnMulti = new JButton("*");
        JButton btnDiv = new JButton("/");
        JButton toNext = new JButton("След. знак");
        JButton toPrev = new JButton("Пред. знак");
        JButton result = new JButton("Высчитать");
        JButton math = new JButton("Мат.");
        JButton bugh = new JButton("Буг.");
        JButton usech = new JButton("Усеч.");

        pnl2.add(btnPlus);
        pnl2.add(btnMinus);
        pnl2.add(btnMulti);
        pnl2.add(btnDiv);
        pnl2.add(toNext);
        pnl2.add(toPrev);
        pnl2.add(result);
        pnl2.add(text1);
        pnl2.add(text2);
        pnl2.add(math);
        pnl2.add(bugh);
        pnl2.add(usech);



        frame.getContentPane().add(BorderLayout.NORTH, name);
        frame.getContentPane().add(BorderLayout.CENTER, pnl);
        frame.getContentPane().add(BorderLayout.SOUTH, pnl2);
        frame.setVisible(true);


        toNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        toPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prev();
            }
        });

        math.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setRounding("math");
            }
        });

        bugh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setRounding("bugh");
            }
        });

        usech.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setRounding("usech");
            }
        });

        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manager.checkNumber(manager.toNormalString(numberFirst.getText()))
                        & manager.checkNumber(manager.toNormalString(numberSecond.getText()))
                        & manager.checkNumber(manager.toNormalString(numberThird.getText()))
                        & manager.checkNumber(manager.toNormalString(numberFourth.getText()))) {
                    String result = manager.getCalculation(
                            manager.toNormalString(numberFirst.getText()),
                            manager.toNormalString(numberSecond.getText()),
                            manager.toNormalString(numberThird.getText()),
                            manager.toNormalString(numberFourth.getText()));

                    if (manager.checkNumber(result)) {
                        jlabel.setText(manager.toViewWithSpaces(result));
                    }
                }
            }
        });

        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJlabel(jlabel1, jlabel2, jlabel3).setText("+");
                setCommand(manager, "+");
            }
        });

        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJlabel(jlabel1, jlabel2, jlabel3).setText("−");
                setCommand(manager, "−");
            }
        });

        btnMulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJlabel(jlabel1, jlabel2, jlabel3).setText("*");
                setCommand(manager, "*");
            }
        });

        btnDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJlabel(jlabel1, jlabel2, jlabel3).setText("/");
                setCommand(manager, "/");
            }
        });
    }

    public static void next() {
        count++;
        if (count == 4) {
           count = 3;
        }
    }

    public static void prev() {
        count--;
        if (count == 0) {
            count = 1;
        }
    }

    public static JLabel getJlabel(JLabel num1, JLabel num2, JLabel num3) {
        switch (count) {
            case (1):
                return num1;
            case (2):
                return num2;
            case (3):
                return num3;
        }
        return null;
    }

    public static void setCommand(AllCalculation manager, String command) {
        switch (count) {
            case (1):
                manager.setCommandFirst(command);
                break;
            case (2):
                manager.setCommandSecond(command);
                break;
            case (3):
                manager.setCommandThird(command);
                break;
        }
    }
}
