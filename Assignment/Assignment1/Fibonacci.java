import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args){
        int ans = wrapper();
    }
    private static int wrapper(){
        int result = 0;
        // Your code starts from here
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        result = fib(n);
        // Your code ends here
        System.out.println(result);
        return result;
    }

    public static int fib(int n){
        // Your code starts from here
        if (n == 0){
            return 0;
        } else if (n == 1){
            return 1;
        } else {
            return fib(n-1) + fib(n-2); // fibonacci sequence formula
        }
        // Your code ends here
        //return 0;
    }
}
