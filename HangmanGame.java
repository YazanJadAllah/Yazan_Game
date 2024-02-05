import java.util.Scanner;
import java.util.Random;

public class HangmanGame {

    private static String[] wordList = {"java", "programming", "hangman", "computer", "coding", "developer"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String selectedWord = wordList[random.nextInt(wordList.length)];
        char[] guessedWord = new char[selectedWord.length()];
        boolean[] guessedLetters = new boolean[26];
        int attemptsLeft = 6;

        // Initialize guessedWord with underscores
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        while (attemptsLeft > 0) {
            System.out.println("Current Word: " + String.valueOf(guessedWord));
            System.out.println("Attempts Left: " + attemptsLeft);

            // Display guessed letters
            System.out.print("Guessed Letters: ");
            for (int i = 0; i < guessedLetters.length; i++) {
                if (guessedLetters[i]) {
                    System.out.print((char) ('a' + i) + " ");
                }
            }
            System.out.println();

            // Get player guess
            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);

            // Check if the letter has already been guessed
            if (guessedLetters[guess - 'a']) {
                System.out.println("You already guessed that letter. Try again.");
                continue;
            }

            guessedLetters[guess - 'a'] = true;

            // Check if the guessed letter is in the word
            boolean found = false;
            for (int i = 0; i < selectedWord.length(); i++) {
                if (selectedWord.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    found = true;
                }
            }

            // Reduce attempts if the guessed letter is not in the word
            if (!found) {
                attemptsLeft--;
            }

            // Check if the player has guessed the entire word
            if (String.valueOf(guessedWord).equals(selectedWord)) {
                System.out.println("Congratulations! You guessed the word: " + selectedWord);
                break;
            }
        }

        // Display the result
        if (attemptsLeft == 0) {
            System.out.println("Sorry, you ran out of attempts. The word was: " + selectedWord);
        }

        scanner.close();
    }
}