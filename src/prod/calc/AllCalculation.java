package prod.calc;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class AllCalculation {
    private final BigDecimal PLUS_ONE_TRILLION = BigDecimal.valueOf(1000000000000.000000);
    private final BigDecimal MINUS_ONE_TRILLION = BigDecimal.valueOf(-1000000000000.000000);

    private String rounding = "math";
    private String commandFirst = "+";
    private String commandSecond = "+";
    private String commandThird = "+";

    public void setCommandFirst(String commandFirst) {
        this.commandFirst = commandFirst;
    }
    public void setCommandSecond(String commandSecond) {
        this.commandSecond = commandSecond;
    }
    public void setCommandThird(String commandThird) {
        this.commandThird = commandThird;
    }

    public void setRounding(String rounding) {
        this.rounding = rounding;
    }

    public String getCommandFirst() {
        return commandFirst;
    }

    public String getRounding() {
        return rounding;
    }

    public String getCommandSecond() {
        return commandSecond;
    }
    public String getCommandThird() {
        return commandThird;
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

    public boolean checkDivideZero(String secondNumber, String command) {
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

    public String getCalculation(String firstNumber, String secondNumber, String thirdNumber, String fourthNumber) {
        String firstFreshNumber = replaceComma(firstNumber);
        String secondFreshNumber = replaceComma(secondNumber);
        String thirdFreshNumber = replaceComma(thirdNumber);
        String fourthFreshNumber = replaceComma(fourthNumber);

        BigDecimal BDFirstNumber = new BigDecimal(firstFreshNumber);
        BigDecimal BDSecondNumber = new BigDecimal(secondFreshNumber);
        BigDecimal BDThirdNumber = new BigDecimal(thirdFreshNumber);
        BigDecimal BDFourthNumber = new BigDecimal(fourthFreshNumber);

        if (checkDivideZero(thirdNumber, getCommandSecond())) {
            String secondAndThird = checkCommand(BDSecondNumber, BDThirdNumber, getCommandSecond());
            BigDecimal BDSecondAndThirdNUmber = new BigDecimal(secondAndThird);

            if (getCommandFirst().equals("*") || getCommandFirst().equals("/")) {
                if (checkDivideZero(secondAndThird, getCommandFirst())) {
                    String firstAndResult = checkCommand(BDFirstNumber, BDSecondAndThirdNUmber, getCommandFirst());
                    BigDecimal BDFirstAndResultNumber = new BigDecimal(firstAndResult);
                    if (checkDivideZero(firstAndResult, getCommandThird())) {
                        String result = checkCommand(BDFirstAndResultNumber, BDFourthNumber, getCommandThird());
                        BigDecimal resultNumber = new BigDecimal(result);
                        return checkRounding(resultNumber).toString();
                    }
                }
            }
            else if (getCommandThird().equals("*") || getCommandThird().equals("/")) {
                if (checkDivideZero(fourthNumber, getCommandThird())) {
                    String thirdAndResult = checkCommand(BDSecondAndThirdNUmber, BDFourthNumber, getCommandThird());
                    BigDecimal BDThirdAndResultNumber = new BigDecimal(thirdAndResult);
                    if (checkDivideZero(thirdAndResult, getCommandFirst())) {
                        String result = checkCommand(BDFirstNumber, BDThirdAndResultNumber, getCommandFirst());
                        BigDecimal resultNumber = new BigDecimal(result);
                        return checkRounding(resultNumber).toString();
                    }
                }
            }
            else {
                if (checkDivideZero(secondAndThird, getCommandFirst())) {
                    String firstAndResult = checkCommand(BDFirstNumber, BDSecondAndThirdNUmber, getCommandFirst());
                    BigDecimal BDFirstAndResultNumber = new BigDecimal(firstAndResult);
                    if (checkDivideZero(firstAndResult, getCommandThird())) {
                        String result = checkCommand(BDFirstAndResultNumber, BDFourthNumber, getCommandThird());
                        BigDecimal resultNumber = new BigDecimal(result);
                        return checkRounding(resultNumber).toString();
                    }
                }
            }
        }
        return null;
    }

    public String replaceComma(String number) {
        return number.replace(',', '.');
    }

    public BigDecimal checkRounding(BigDecimal number) {
        switch (getRounding()) {
            case ("math"):
                return number;
            case ("bugh"):
                return number.setScale(2, RoundingMode.HALF_EVEN);
            case ("usech"):
                return number.setScale(0, RoundingMode.DOWN);
        }
        return null;
    }

    public String checkCommand(BigDecimal firstNumber, BigDecimal secondNumber, String command) {
        BigDecimal result = BigDecimal.valueOf(0);
        switch (command) {
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
        return result.toString();
    }
}
