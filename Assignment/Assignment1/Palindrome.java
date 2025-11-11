import java.util.ArrayDeque;
import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args){
        checkPalindrome();
    }
    public static void checkPalindrome(){
        boolean isPal = false;
        // Your code starts from here
        Scanner input = new Scanner(System.in);
        String str = input.next();
        int s = 0;
        int n = (str.length()%2 == 0) ? str.length()/2 : str.length()/2 + 1;
        for(int i = 0; i < n; i++){
            if(str.charAt(i) != str.charAt(str.length()-i-1)){
                s = 1;
                break;
            }
        }
        isPal = s != 1;
        // Your code ends here
        System.out.println(isPal ? "true" : "false");
    }
}
