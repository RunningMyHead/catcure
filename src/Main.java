import java.util.ArrayList;
import java.util.Stack;

public class Main {
    static class Quadruple {
        String op;
        String arg1;
        String arg2;
        String result;

        public Quadruple(String op, String arg1, String arg2, String result) {
            this.op = op;
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.result = result;
        }

        @Override
        public String toString() {
            return "(" + op + ", " + arg1 + ", " + arg2 + ", " + result + ")";
        }
    }

    public static void main(String[] args) {
        String input = "a = (b + c) * (d - e)";
        ArrayList<Quadruple> quadruples = new ArrayList<>();

        // Remove spaces for easier processing
        input = input.replaceAll("\\s", "");

        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        int tempCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                int j = i;
                while (j < input.length() && Character.isLetterOrDigit(input.charAt(j))) {
                    j++;
                }
                operands.push(input.substring(i, j));
                i = j - 1;
            } else if (ch == '(') {
                operators.push(String.valueOf(ch));
            } else if (ch == ')') {
                while (!operators.peek().equals("(")) {
                    generateQuadruple(operators, operands, quadruples, tempCount++);
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && precedence(String.valueOf(ch)) <= precedence(operators.peek())) {
                    generateQuadruple(operators, operands, quadruples, tempCount++);
                }
                operators.push(String.valueOf(ch));
            }
        }

        while (!operators.isEmpty()) {
            generateQuadruple(operators, operands, quadruples, tempCount++);
        }

        // Output quadruples
        System.out.println("Generated Quadruples:");
        for (Quadruple quad : quadruples) {
            System.out.println(quad);
        }
    }

    private static int precedence(String op) {
        switch (op) {
            case "=":
                return 1;
            case "+":
            case "-":
                return 2;
            case "*":
            case "/":
                return 3;
            default:
                return 0;
        }
    }

    private static void generateQuadruple(Stack<String> operators, Stack<String> operands,
                                          ArrayList<Quadruple> quadruples, int tempCount) {
        String op = operators.pop();
        String arg2 = operands.pop();
        String arg1 = operands.isEmpty() ? "" : operands.pop();
        String result = (op.equals("=")) ? arg1 : "T" + tempCount;

        if (!op.equals("=")) {
            operands.push(result);
        }

        quadruples.add(new Quadruple(op, arg1, arg2, result));
    }
}