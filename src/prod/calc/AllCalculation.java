package prod.calc;

import javax.swing.*;
import java.math.BigDecimal;

public class AllCalculation {
    private final BigDecimal PLUS_ONE_TRILLION = BigDecimal.valueOf(1000000000000.000000);
    private final BigDecimal MINUS_ONE_TRILLION = BigDecimal.valueOf(-1000000000000.000000);
    private String command = "+";

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public boolean isNumber(String number) {
        String freshNumber = replaceComma(number);
        try {
            BigDecimal BDnum = new BigDecimal(freshNumber);
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "Введите число!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean notOverflow(String number) {
        String freshNumber = replaceComma(number);
        BigDecimal BDnum = new BigDecimal(freshNumber);
        int comparisonResultPlus = PLUS_ONE_TRILLION.compareTo(BDnum);
        int comparisonResultMinus = MINUS_ONE_TRILLION.compareTo(BDnum);
        if (comparisonResultPlus < 0) {
            JOptionPane.showMessageDialog(null, "Переполнение!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (comparisonResultMinus > 0) {
            JOptionPane.showMessageDialog(null, "Переполнение!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean checkNumber(String number) {
        if (isNumber(number) & notOverflow(number) & ExponentialNotationCheck(number)) {
            return true;
        }
        return false;
    }

    public boolean ExponentialNotationCheck(String number) {
        char[] letter = number.toCharArray();
        for (int i = 0; i < letter.length; i++) {
            if (letter[i] == 'e' || letter[i] == 'E') {
                JOptionPane.showMessageDialog(null, number +" является числом в экспоненциальной нотации.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public String getCalculation(String firstNumber, String secondNumber) {
        String firstFreshNumber = replaceComma(firstNumber);
        String secondFreshNumber = replaceComma(secondNumber);
        BigDecimal BDFirstNumber = new BigDecimal(firstFreshNumber);
        BigDecimal BDSecondNumber = new BigDecimal(secondFreshNumber);
        if (checkCommand()) {
            BigDecimal resultPlus = BDFirstNumber.add(BDSecondNumber);
            return resultPlus.toString();
        }
        BigDecimal resultMinus = BDFirstNumber.subtract(BDSecondNumber);
        return resultMinus.toString();
    }

    public String replaceComma(String number) {
        return number.replace(',', '.');
    }
    public boolean checkCommand() {
        if (getCommand().equals("−")) {
            return false;
        }
        return true;
    }
}
