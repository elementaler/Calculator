package Calculator;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        try {
            System.out.println(calculator.calculate("3 + 2")); // 5
            System.out.println(calculator.calculate("5 - 2")); // 3
            System.out.println(calculator.calculate("4 * 6")); // 24
            System.out.println(calculator.calculate("10 / 2")); // 5
            System.out.println(calculator.calculate("X + V")); // XV
            System.out.println(calculator.calculate("IX - II")); // VII
            System.out.println(calculator.calculate("IV * VI")); // XXIV
            System.out.println(calculator.calculate("X / V")); // II

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }    }
}