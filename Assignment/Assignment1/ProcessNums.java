import java.util.Random;
import java.util.Scanner;

// Define class ProcessNums to determine the percentage of even numbers
// in 10 random numbers from the range 0 to 10.
public class ProcessNums {
    public static void main(String[] args){
        GetPercentEven();
    }
    public static void GetPercentEven(){
        int seed = 0, numEven = 0;
        int TOTAL_NUMS = 10;
        double percentEven = 0;
        int i = 0;

        // Your code starts from here
        Scanner integer = new Scanner(System.in);
        seed = integer.nextInt();
        Random random_num = new Random(seed);
        for(i = 0; i < TOTAL_NUMS; i++){
            int num = random_num.nextInt(11); // ranged from 0-10
            System.out.println(num);
            if(isEven(num)){
                numEven++;
            }
        }
        percentEven = (double) numEven/TOTAL_NUMS * 100;
        System.out.printf("%.2f%n", percentEven);
        integer.close();
        // Your code ends here
    }

    // Determine if the number is odd
    public static boolean isEven(int number) {
        // Your code starts from here
        if((number%2 == 0 && number != 0) || number == 0){
            return true;
        }
        // Your code ends here
        return false;
    }
}
