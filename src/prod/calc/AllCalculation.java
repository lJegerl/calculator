package prod.calc;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
        String normalString = toNormalString(number);
        if (isNumber(normalString) & notOverflow(normalString) & ExponentialNotationCheck(normalString)) {
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

    public boolean checkDivideZero(String secondNumber) {
        if (secondNumber.equals("0") & command.equals("/")) {
            JOptionPane.showMessageDialog(null, "Нельзя делить на 0!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String toNormalString(String input) {
        if (checkSpaces(input)) {
            return input.replaceAll(" ", "");
        } else {
            JOptionPane.showMessageDialog(null, "Неправильно введено число!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private boolean checkSpaces(String input) {
       if (input.indexOf(' ') == -1) {
          return true;
       } else {
           StringBuilder reversed = new StringBuilder(input).reverse();
           if (input.indexOf('.') == -1 & input.indexOf(',') == -1) {
               for (int i = 0; i < reversed.length(); i++) {
                   if (reversed.charAt(i) == ' ' & i % 4 != 3) {
                       return false;
                   }
               }
           } else {
               int index = 0;
               for (int i = 0; i < reversed.length(); i++) {
                   if (reversed.charAt(i) == ',' || reversed.charAt(i) == '.') {
                       index = i;
                       break;
                   }
                   if (reversed.charAt(i) == ' ') {
                       return false;
                   }
               }
               int buff = 0;
               for (int i = index + 1; i < reversed.length(); i++, buff++) {
                   if (reversed.charAt(i) == ' ' & buff % 4 != 3) {
                       return false;
                   }
               }
           }
       }
       return true;
    }

    public String toViewWithSpaces(String number) {
        try {
            BigDecimal BDnumber = new BigDecimal(number);

            DecimalFormat df = new DecimalFormat("#,###.######");
            df.setGroupingUsed(true);
            df.setGroupingSize(3);

            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator('.');
            decimalFormatSymbols.setGroupingSeparator(' ');

            df.setDecimalFormatSymbols(decimalFormatSymbols);
            return df.format(BDnumber);
        } catch (NumberFormatException e) {
            return number;
        }
    }

    public String getCalculation(String firstNumber, String secondNumber) {
        String firstFreshNumber = replaceComma(firstNumber);
        String secondFreshNumber = replaceComma(secondNumber);
        BigDecimal BDFirstNumber = new BigDecimal(firstFreshNumber);
        BigDecimal BDSecondNumber = new BigDecimal(secondFreshNumber);
        if (checkDivideZero(secondNumber)) {
            return checkCommand(BDFirstNumber, BDSecondNumber);
        }
        return null;
    }

    public String replaceComma(String number) {
        return number.replace(',', '.');
    }

    public String checkCommand(BigDecimal firstNumber, BigDecimal secondNumber) {
        BigDecimal result = BigDecimal.valueOf(0);
        switch (getCommand()) {
            case ("+"):
                result = firstNumber.add(secondNumber).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros();
                break;
            case ("−"):
                result = firstNumber.subtract(secondNumber).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros();
                break;
            case ("*"):
                result = firstNumber.multiply(secondNumber).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros();
                break;
            case ("/"):
                result = firstNumber.divide(secondNumber,6, RoundingMode.HALF_UP).stripTrailingZeros();
                break;
        }
        return BigDecimal.valueOf(Double.parseDouble(result.toString())).toPlainString();
    }
}
