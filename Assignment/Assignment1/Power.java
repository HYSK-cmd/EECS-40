import java.util.Scanner;
import java.text.DecimalFormat;

// Power class to calculate the value of base to the exponent
public class Power {
    public static void main(String[] args){
        double[] ans = wrapper();
    }
    private static double[] wrapper()
    {
        double result = 0;
        // Your answers have to be stored in the result variable

        // Your code starts from here
        Scanner input = new Scanner(System.in);
        //System.out.print("Enter a base and an exponent: ");
        int base = input.nextInt();
        int exp = input.nextInt();

        result = power(base, exp); // call the power function
        // Your code ends here

        // Create a DecimalFormat object to format the number
        DecimalFormat df = new DecimalFormat("#.##");
        // note:  The pattern "#.##" will only show decimal places if they are non-zero

        // Format the number
        String formattedNumber = df.format(result);

        // Print the power result
        System.out.println(formattedNumber);

        return new double[] {result};
    }

    // Calculate the value of the base to the exponent
    public static double power(int base, int exponent) {
        // Your code starts from here
        double result = 1;
        boolean flag = false; // flag is true if exp is positive, otherwise false
        if (exponent < 0){
            flag = true;
            exponent = exponent * -1; // make exp positive
        }
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        if (flag){
            return 1.0 / result;
        }
        // Your code ends here
        return result;
    }
}
