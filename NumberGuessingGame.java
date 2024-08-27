import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int totalScore = 0;
        
        while (playAgain) {
            int attempts = 0;
            int maxAttempts = 10;
            Random random = new Random();
            int numberToGuess = random.nextInt(100) + 1;
            boolean hasGuessedCorrectly = false;
            
            System.out.println("\nI've generated a number between 1 and 100. You have 10 attempts to guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                
                int guess = scanner.nextInt();
                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    totalScore += (maxAttempts - attempts + 1);  // Higher score for fewer attempts
                    hasGuessedCorrectly = true;
                    break;
                }
            }
            
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all attempts! The correct number was " + numberToGuess);
            }

            System.out.println("Your current score: " + totalScore);

            System.out.print("Would you like to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }
        
        System.out.println("Thanks for playing! Your final score is: " + totalScore);
        scanner.close();
    }
}
