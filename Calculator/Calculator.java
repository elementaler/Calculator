package Calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private final Map<String, Integer> ROMAN_NUMERALS;
    public Calculator() {
        ROMAN_NUMERALS = new HashMap<>();
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("X", 10);
        ROMAN_NUMERALS.put("XL", 40);
        ROMAN_NUMERALS.put("L", 50);
        ROMAN_NUMERALS.put("XC", 90);
        ROMAN_NUMERALS.put("C", 100);
        ROMAN_NUMERALS.put("CD", 400);
        ROMAN_NUMERALS.put("D", 500);
        ROMAN_NUMERALS.put("CM", 900);
        ROMAN_NUMERALS.put("M", 1000);
    }
    private boolean isRomanNumeral(String s) {
        return s.matches("^[IVXLCDM]+$");
    }
    public int toArabic(String s) {
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1 && ROMAN_NUMERALS.containsKey(s.substring(i, i + 2))) {
                result += ROMAN_NUMERALS.get(s.substring(i, i + 2));
                i += 2;
            } else {
                result += ROMAN_NUMERALS.get(s.substring(i, i + 1));
                i++;
            }
        }
        return result;
    }
    public String toRoman(int n) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ROMAN_NUMERALS.entrySet()) {
            while (n >= entry.getValue()) {
                result.append(entry.getKey());
                n -= entry.getValue();
            }
        }
        return result.toString();
    }
    public String calculate(String expression) throws Exception {
        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            throw new Exception("Строка не является математической операцией");
        }
        String a = parts[0];
        String operator = parts[1];
        String b = parts[2];
        boolean isArabic = a.matches("^[0-9]+$") && b.matches("^[0-9]+$");
        boolean isRoman = isRomanNumeral(a) && isRomanNumeral(b);
        if (!isArabic && !isRoman) {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (isArabic) {
            int numA = Integer.parseInt(a);
            int numB = Integer.parseInt(b);
            if (numA < 1 || numA > 10 || numB < 1 || numB > 10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }
            int result;
            switch (operator) {
                case "+":
                    result = numA + numB;
                    break;
                case "-":
                    result = numA - numB;
                    break;
                case "*":
                    result = numA * numB;
                    break;
                case "/":
                    result = numA / numB;
                    break;
                default:
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            return Integer.toString(result);
        } else {
            int numA = toArabic(a);
            int numB = toArabic(b);
            if (numA < 1 || numA > 10 || numB < 1 || numB > 10) {
                throw new Exception("Числа должны быть от I до X");
            }
            int result;
            switch (operator) {
                case "+":
                    result = numA + numB;
                    break;
                case "-":
                    result = numA - numB;
                    break;
                case "*":
                    result = numA * numB;
                    break;
                case "/":
                    result = numA / numB;
                    break;
                default:
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (result <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            return toRoman(result);
        }
    }

}