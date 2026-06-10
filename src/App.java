import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Welcome to an all in one game!");
        userLevelChoose();
        
    }
       
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void userLevelChoose() {
        Scanner myScanner = new Scanner(System.in);    
        
        System.out.print(
            """
               1. Guess a number
               2. Play the wordle
               3. Exit
            Type the number of your option in: """
            ); int choice = myScanner.nextInt();
            
            
            automatedMethodChoose(choice);
            }
        
    
        public static void automatedMethodChoose(int choice) {
        //TOdo, create the methods that i have mentioned 
         if (choice == 1) {
                guessNumber();
                userLevelChoose();
         } else if (choice == 2) {
                wordle();
                userLevelChoose();
         } else if (choice == 3){
                return;
         }  else {
                System.out.println("Please try again!"); 
                userLevelChoose();
            }
    }
    
    
    public static void guessNumber() {
        Scanner myScanner = new Scanner(System.in);
        int genNum = (int)(Math.random()*100) + 1;
        int attempts = 0;
        int guess = 0;

        while (guess != genNum) {
            System.out.print("What is your guess from 1 to 100?: ");
            if (!myScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                myScanner.next();
                continue;
            }
            guess = myScanner.nextInt();
            attempts++;

       if (guess == genNum) {
                System.out.printf("Congratulations! You got it correct in %d attempts!", attempts);
                System.out.println(""); userLevelChoose();
            } else if (guess > genNum) {
                System.out.println("Too high! Try a lower number.");
            } else {
                System.out.println("Too low! Try a higher number.");
            }
        }
        
    }

    public static void wordle() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Welcome to Wordle!");
        System.out.println("Guess the word to win, only 6 attempts");
        String[] wordBank = {"APPLE", "GRAPE", "LEMON", "GUAVA", "HOUSE", "BORED", "BORAX", "BORAC", "WORLD", "FIRST", "CLASS", "SNAKE", "BEACH"};
        String secretWord = wordBank[(int)(Math.random()* wordBank.length)];
        int maxAttempts = 6;
        boolean solved = false;
        String colorGreen = "\u001B[32m";
        String colorYellow = "\u001B[33m";
        String colorGray = "\u001B[90m";
        String colorNormal = "\u001B[0m";

        for (int i = 1; i <= maxAttempts; i++) {
            System.out.printf("Attempt %d/%d. Enter a word that is 5 letters: ", i, maxAttempts);
            String guess = myScanner.nextLine().trim().toUpperCase();

            if (guess.length() != 5 || !guess.matches("[A-Z]+")) {
                System.out.println("Please type in only 5 letters, this is wordle (wait a min, why is wordle 6 letters?)");
                i--;
                continue;
            }

            boolean[] secretMatched = new boolean[5];
            boolean[] guessMatched = new boolean[5];
            String[] coloredOutput = new String[5];

            for (int c = 0; c < 5; c++) {
                if (guess.charAt(c) == secretWord.charAt(c)) {
                    coloredOutput[c] = colorGreen + guess.charAt(c) + colorNormal;
                    secretMatched[c] = true;
                    guessMatched[c] = true;
                }
            }

            for (int c = 0; c < 5; c++) {
                if (guessMatched[c]) continue;

                boolean foundPartial = false;
                for (int j = 0; j < 5; j++) {
                    if (!secretMatched[j] && guess.charAt(c) == secretWord.charAt(j)) {
                        coloredOutput[c] = colorYellow + guess.charAt(c) + colorNormal;
                        secretMatched[j] = true;
                        guessMatched[c] = true;
                        foundPartial = true;
                        break;
                    }
                }

                if (!foundPartial) {
                    coloredOutput[c] = colorGray + guess.charAt(c) + colorNormal;
                }
            }

            System.out.print("Feedback: ");
            for (String letter : coloredOutput) {
                System.out.print(letter + " ");
            }
            System.out.println("\n");

            if (guess.equals(secretWord)) {
                System.out.printf("You solved the wordle in %d guesses%n", i);
                solved = true;
                break;
            }
        }

        if (!solved) {
            System.out.println("You ran out of guesses, the secret word was " + secretWord);
        }

    }
}

   
   
