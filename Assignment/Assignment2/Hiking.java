/**
 * This is a program to find the first and second best student runners.
 * The students are registered in an 1-D array with completion times in
 * minutes in a corresponding 1-D array
 */
public class Hiking {
    /**
     * Store students' name and their results
     * It starts the program and call the following functions (findBestRunner(), findSecondBestRunner())
     * @param args this is a command-line arguments (not used in this program)
     */
    public static void main(String[] args){
        // Array of student names participating in the hiking
        String[] students = {"Mason", "Olivia", "Sophia", "Liam", "Noah", "Lucas", "Harper", "Aria", "Ethan", "Ava", "Jackson", "Charlotte", "Benjamin", "Amelia", "Oliver", "Mia"};
        // Array of corresponding completion times in minutes
        int[] times = {278, 310, 259, 289, 332, 297, 305, 264, 290, 283, 315, 275, 301, 288, 319, 276};
        
        // Create an instance of Hiking class
        Hiking h = new Hiking();
        // Find and display the fastest runner
        h.findBestRunner(students, times);
        // Find and display the second fastest runner
        h.findSecondBestRunner(students, times);
    }
    /**
     * find the best runner among participants
     * @param students the array of students participatng in hiking
     * @param times the array of corresponding completion times measured in minutes
     */
    public void findBestRunner(String[] students, int[] times) {
        // TODO: Implement the method
        int min = 99999;
        int idx = -1;
        for (int i = 0; i < times.length; i++) {
            if (times[i] < min) {
                min = times[i];
                idx = i;
            }
        }
        System.out.printf("Fastest runner: %s (%d minutes)%n", students[idx], times[idx]);
    }
    /**
     * Find the second best runner among participants
     * @param students the array of students participatng in hiking
     * @param times the array of corresponding completion times measured in minutes
     */
    public void findSecondBestRunner(String[] students, int[] times) {
        // TODO: Implement the method
        int min = 99999;
        int idx = -1;
        for (int i = 0; i < times.length; i++) {
            if (times[i] < min) {
                min = times[i];
                idx = i;
            }
        }

        int second_best_idx = -1;
        int second_min = 99999;
        for (int i = 0; i < times.length; i++) {
            if (i == idx) {
                continue;   // skip the first runner
            }
            if (times[i] < second_min){
                second_min = times[i];
                second_best_idx = i;    // the second best runner index
            }
        }
        System.out.printf("Second fastest runner: %s (%d minutes)%n", students[second_best_idx], times[second_best_idx]);
    }
}