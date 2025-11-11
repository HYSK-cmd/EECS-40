package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A JavaFX calculator application
 * The calculator supports basic arithmetic, exponent, reciprocal, square root,
 * factorial (1 to 10), sign toggle, natural log, and a single block memory
 * It can also clear the text field and prints the result out in the terminal
 */
public class Calculator extends Application {
    private TextField textField;
    private boolean clear = true; // true means the text field is empty
    private boolean noOps = true; // true means no operator has been entered
    private static String equation; // variable that keeps track of user's inputs
    private static double memory = 0.0; // variable in a single-register memory

    /**
     * Initializes the stage and builds the calculator
     *
     * @param stage the stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        textField = new TextField("0");
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(textField, 0, 0, 5, 1);
        displayBtn(grid);

        Scene scene = new Scene(grid, 430, 470);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }
    // display buttons & implement event handlers
    private void displayBtn(GridPane grid){
        String digits = "0123456789";
        String ops = "+-*/**";
        String[][] numbers = {
                {"MC", "MR", "M+", "M-", "AC"},
                {"7", "8", "9", "/", "1/x"},
                {"4", "5", "6", "*", "sqr"},
                {"1", "2", "3", "-", "x!"},
                {"0", ".", "+/-", "+", "="},
                {"log", "**", "txt"}
        };
        Loop:
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < numbers[i].length; j++){
                int choice = 0;
                String numStr = numbers[i][j];
                Button btn = makeButton(numStr);
                grid.add(btn, j, i + 1);

                if(digits.contains(numStr) || numStr.equals(".")){
                    choice = 1;
                } else if (ops.contains(numStr)){
                    choice = 2;
                } else if (numStr.equals("=")){
                    choice = 3;
                } else if (numStr.equals("sqr")){
                    choice = 4;
                } else if (numStr.equals("x!")){
                    choice = 5;
                } else if (numStr.equals("1/x")){
                    choice = 6;
                } else if (numStr.equals("+/-")){
                    choice = 7;
                } else if (numStr.equals("MC")){
                    choice = 8;
                } else if (numStr.equals("MR")){
                    choice = 9;
                } else if (numStr.equals("M+")){
                    choice = 10;
                } else if (numStr.equals("M-")){
                    choice = 11;
                } else if (numStr.equals("AC")){
                    choice = 12;
                } else if (numStr.equals("log")){
                    choice = 13;
                } else if (numStr.equals("txt")){
                    choice = 14;
                }

                // Append Event Handler
                switch(choice){
                    case 1:
                        btn.setOnAction(e->append(numStr)); break;
                    case 2:
                        btn.setOnAction(e->setOperator(numStr)); break;
                    case 3:
                        btn.setOnAction(e->calculate()); break;
                    case 4:
                        btn.setOnAction(e->handleSqrt()); break;
                    case 5:
                        btn.setOnAction(e->handleFactorial()); break;
                    case 6:
                        btn.setOnAction(e->handleReciprocal()); break;
                    case 7:
                        btn.setOnAction(e->handleToggleSign()); break;
                    case 8:
                        btn.setOnAction(e->memoryClear()); break;
                    case 9:
                        btn.setOnAction(e->memoryRecall()); break;
                    case 10:
                        btn.setOnAction(e->memoryAdd()); break;
                    case 11:
                        btn.setOnAction(e->memorySub()); break;
                    case 12:
                        btn.setOnAction(e->clearAll()); break;
                    case 13:
                        btn.setOnAction(e->handleLog()); break;
                    case 14:
                        if(textField.getText().isEmpty()){
                            System.out.println("TextField is empty!");
                        } else {
                            btn.setOnAction(e -> System.out.println("Result: " + textField.getText()));
                        }
                        break;
                }
            }
        }
    }
    /*
    ==================Event Handlers=====================
     */
    // Append a string to the text field
    private void append(String s) {
        // reinitialize string 'equation'
        if(equation == null) equation = "";
        // if text field is empty or just 0, clean it then append
        if (clear || "0".equals(textField.getText())){ textField.setText(""); clear = false; }
        // prevent extra dot input if it is already there
        if (".".equals(s) && textField.getText().contains(".")){ return; }
        // change to 0. if the first input is dot in an empty text field; otherwise, append it
        if (".".equals(s) && textField.getText().isEmpty()){
            textField.setText("0.");
            return;
        } else {
            textField.appendText(s);
        }
        // first operand
        if(noOps){ equation = textField.getText(); }
    }
    // set an operator
    private void setOperator(String s){
        String str = textField.getText();
        if(str == null || str.isEmpty()){ return; }
        equation = str + s; // concatenate
        clear = true;   // clear the text field
        noOps = false;  // prevent extra ops
    }
    // calculate the equation
    private void calculate(){
        //System.out.println(equation);
        if(equation == null) return;
        String expr = equation;
        if(expr.endsWith("**")){
            expr += textField.getText();
        }
        int idxOp; String op; char lastCh;
        String firstOp = "", secondOp = "";
        int powIdx = expr.indexOf("**", 1);
        if(powIdx != -1){
            op = "**";
            firstOp = expr.substring(0, powIdx);
            secondOp = expr.substring(powIdx + 2);
            System.out.println(firstOp + "   " + secondOp);
            // if base is negative at the same time as exponent is a decimal value
            if(firstOp.contains("-") && secondOp.contains(".")){ displayError(); return; }
        } else {
            lastCh = expr.charAt(expr.length()-1);
            if("+-*/".lastIndexOf(lastCh) >= 0){
                String rest = textField.getText();
                expr += rest;
            }
            // find the pos of op
            idxOp = -1;
            // start at idx 1 so it will ignore the negative sign of the first operand
            for(int i = 1; i < expr.length(); i++){
                if(expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/'){
                    idxOp = i; break;
                }
            }
            if (idxOp == -1) { displayResult(expr);}
            op = String.valueOf(expr.charAt(idxOp));
            firstOp = expr.substring(0, idxOp);
            secondOp = expr.substring(idxOp + 1);
        }

        // conversion
        double a, b, result = 0;
        a = Double.parseDouble(firstOp); b = Double.parseDouble(secondOp);
        System.out.println("a:"+ a + "b:" + b);
        switch (op){
            case "+": result = a + b;
                System.out.println(add(a, b)); break;
            case "-": result = a - b;
                System.out.println(subtract(a, b)); break;
            case "*": result = a * b;
                System.out.println(multiply(a, b)); break;
            case "/":
                if (b == 0){ displayError(); return;}
                System.out.println(divide(a, b));
                result = a / b; break;
            case "**":
                System.out.println(power(a, b));
                result = Math.pow(a, b); break;
        }
        // check if the result can be integer
        String newResult = isInteger(result) ? String.valueOf((int)result) : String.valueOf(result);
        textField.setText(newResult);
        equation = newResult;
        clear = true;
        noOps = true;
    }
    // SquareRoot
    private void handleSqrt(){
        if(equation == null) return;
        String expr = equation;
        double x = Double.parseDouble(expr);
        if (x < 0.0) { displayError(); return; }
        x = Math.sqrt(x);
        String newResult = isInteger(x) ? String.valueOf((int)x) : String.valueOf(x);
        displayResult(newResult);
        System.out.println(sqrt(Double.parseDouble(expr)));
    }
    // Log
    private void handleLog(){
        if(equation == null) return;
        String expr = equation;
        double x = Double.parseDouble(expr);
        if (x <= 0.0) { displayError(); return; }
        x = Math.log(x);
        String newResult = isInteger(x) ? String.valueOf((int)x) : String.valueOf(x);
        displayResult(newResult);
        System.out.println(log(Double.parseDouble(expr)));
    }
    // Factorial
    private void handleFactorial(){
        if(equation == null) return;
        String expr = equation;
        int x = Integer.parseInt(expr);
        if (x > 10 || x < 0) { // limited range (1 to 10)
            displayError();
            return;
        }
        x = calculateFac(x);
        String newResult = String.valueOf(x);
        displayResult(newResult);
        System.out.println(factorial(x));
    }
    // Reciprocal
    private void handleReciprocal(){
        if(equation == null) return;
        String expr = equation;
        double x = Double.parseDouble(expr);
        if (x == 0) { displayError(); return; } // undefined, hence display an error
        x = 1 / x;
        String newResult = isInteger(x) ? String.valueOf((int)x) : String.valueOf(x);
        displayResult(newResult);
        System.out.println(reciprocal(Double.parseDouble(expr)));
    }
    // Toggle Sign
    private void handleToggleSign(){
        if (equation == null) return;
        String expr = textField.getText();
        double x = Double.parseDouble(expr);
        x = (x < 0) ? Math.abs(x) : -x;
        String newResult = isInteger(x) ? String.valueOf((int)x) : String.valueOf(x);
        textField.setText(newResult);
        String temp = equation;

        // if equation ends with operator, append the toggled value
        if (!temp.isEmpty()) {
            char c = temp.charAt(temp.length() - 1);
            if (c == '+' || c == '-' || c == '*' || c == '/' || temp.endsWith("**")) {
                equation = temp + newResult;
                return;
            }
        }

        int opIdx = temp.indexOf("**", 1);
        int powLength = 2;
        if (opIdx == -1) {
            powLength = 1; // no ** exists in the equation
            for (int i = 1; i < temp.length(); i++) {
                char c = temp.charAt(i);
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    opIdx = i; break;
                }
            }
        }
        // The equation has "**"
        if (opIdx != -1) {
            equation = temp.substring(0, opIdx + powLength) + newResult;
            return;
        }
        System.out.println(toggleSign(Double.parseDouble(expr)));
        equation = newResult;
        clear = true;
        noOps = true;
    }
    // Clear everything
    private void clearAll(){
        textField.setText("0");
        equation = "";
        memoryClear();
        clear = true;
        noOps = true;
    }
    /*
    ==================Memory Methods=====================
     */
    private void memoryClear(){
        memory = 0.0;
        System.out.println("Memory has been cleared\n");
    }
    private void memoryRecall(){
        String m = (isInteger(memory)) ? String.valueOf((int)memory) : String.valueOf(memory);
        textField.setText(m);
        clear = true;
    }
    private void memoryAdd(){
        if(equation == null) return;
        double num = Double.parseDouble(textField.getText());
        memory += num;
    }
    private void memorySub(){
        if(equation == null) return;
        double num = Double.parseDouble(textField.getText());
        memory -= num;
    }
    /*
    ==================Useful Methods=====================
     */
    // make a Button
    private Button makeButton(String b){
        Button btn = new Button(b);
        btn.setPrefSize(70, 50);
        btn.setAlignment(Pos.CENTER);
        return btn;
    }
    // calculate factorial
    private int calculateFac(int x){
        if(x == 0 || x == 1){
            return 1;
        }
        return x * calculateFac(x-1);
    }
    // check if it's possible to convert to integer
    private boolean isInteger(double n){
        String s = String.valueOf(n);
        int dot = s.indexOf('.');
        return (s.length() - dot - 1) == 1 && s.charAt(s.length() - 1) == '0'; // i.e) 5.0
    }
    // Display result in the text field
    private void displayResult(String s){
        textField.setText(s);
        equation = s;
        clear = true;
        noOps = true;
    }
    // Display error in the text field
    private void displayError(){
        textField.setText("ERROR");
        clear = true; noOps = true; equation = "";
    }

    // ======== Pure operations for JUnit tests (no JavaFX dependency) ========
    public double add(double a, double b) {
        return a + b;
    }
    public double subtract(double a, double b) {
        return a - b;
    }
    public double multiply(double a, double b) {
        return a * b;
    }
    public double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("ERROR");
        return a / b;
    }
    public double power(double a, double b) {
        return Math.pow(a, b);
    }
    public double sqrt(double x) {
        if (x < 0) throw new IllegalArgumentException("ERROR");
        return Math.sqrt(x);
    }
    public double log(double x) {
        if (x <= 0) throw new IllegalArgumentException("ERROR");
        return Math.log(x); // natural log ln(x)
    }
    public int factorial(int x) {
        if (x < 0 || x > 10) throw new IllegalArgumentException("ERROR");
        x = calculateFac(x);
        return x;
    }
    public double factorial(double x) {
        int n = (int) x;
        if (n < 0 || n > 10) throw new IllegalArgumentException("ERROR");
        return (double) calculateFac(n);
    }
    public double reciprocal(double x) {
        if (x == 0) throw new ArithmeticException("ERROR");
        return 1.0 / x;
    }
    public double toggleSign(double x) {
        return (x > 0) ? -x : Math.abs(x);
    }
    /**
     * This is the main function that launches the JavaFX calculator program
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }
}