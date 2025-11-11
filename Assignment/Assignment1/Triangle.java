import java.util.Scanner;

public class Triangle {
    public static void main(String[] args){
        // Your code starts from here
        Scanner keyboard = new Scanner(System.in); // Create a Scanner named 'keyboard'
        int num = keyboard.nextInt();
        // Nested loops
        for(int i = 1; i <= num; i++){
            for(int j = 1; j <= i; j++){
                System.out.print("*");
            }
            System.out.print("\n");
        }
        keyboard.close(); // close the Scanner
        // Your code ends here
    }
}
