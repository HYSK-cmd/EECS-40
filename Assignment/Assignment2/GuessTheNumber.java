import java.util.Random;
import java.util.Scanner;
/**
 * This is a program where user tries to guess a number between 1 and 100.
 * The program tells a user whether the guessed number is higher/lower/out
 * of range until the user guesses it right.
 */
public class GuessTheNumber {
    /**
     * Generates a random number in the range between 1 and 100
     * It starts the program (calls the game start function)
     * @param args this is a command-line arguments (not used in this program)
     */
    public static void main(String[] args){
        // Create a random number generator
        Random random = new Random();

        // Generate a random number between 1 and 100
        int targetNumber = random.nextInt(100) + 1; // random number between 1–100

        playGame(targetNumber);
    }

    /**
     * Runs a simple interactive i/o game
     * The program does not accept non-integer input and out-of-range guesses
     * If the user inputs those values then the program prompts an error message and does not count the attempt
     * @param targetNumber the secret number the player must guess; expected to be in 1–100
     */
    public static void playGame(int targetNumber) {
        // TODO: Implement the method
        System.out.println("Welcome to 'Guess the Number'!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        while (true){
            System.out.print("Enter your guess: ");
            if (!sc.hasNextInt()) { // if user inputs non-negative value
                sc.next();
                System.out.println("Error: Invalid input. Please enter a number between 1 and 100.");
                continue;
            }
            int guess_num = sc.nextInt();   // if user inputs value out of the range
            if (guess_num > 100 || guess_num < 1){
                System.out.println("Error: Invalid input. Please enter a number between 1 and 100.");
                continue;
            }
            attempts++;
            if (guess_num == targetNumber) {    // exit the program
                System.out.println("Congratulations! You guessed it!");
                System.out.printf("The number was %d.\n", targetNumber);
                System.out.printf("You took %d attempts.", attempts);
                break;
            } else if (guess_num > targetNumber){
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }
        }
    }
}
