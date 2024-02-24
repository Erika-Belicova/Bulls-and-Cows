package bulls_and_cows;

import java.util.Scanner;
import java.util.Random;

public class BullsAndCows {
    public String guess;
    public int bulls;
    public int cows;
    public int i;
    public int j;
    public int k;
    public String secret;
    Scanner scanner = new Scanner(System.in);

    public String randomCode(int symbols) {
        String empty = "";
        Random rand = new Random();
        Integer random = rand.nextInt(symbols);
        if (random <= 9) {
            return random.toString();
        }
        if ((random > 9) && (random < 36)) {
            String alphabet = ("abcdefghijklmnopqrstuvwxyz");
            Character letter = alphabet.charAt(random - 10);
            return letter.toString();
        }
        return empty;
    }

    public int inputCheck() {
        if (!scanner.hasNextInt()) {
            System.out.println("Error: " + scanner.next() + " isn't a valid number.");
            System.exit(0);
        }
        return scanner.nextInt();
    }

    public String secretCode() {

        System.out.println("Input the length of the secret code: ");
        int num = inputCheck();
        String star = "*";
        if (num > 36) {
            System.out.printf("Error: can't generate a secret number " +
                    "with a length of %d because there aren't enough unique digits.", num);
            System. exit(0);
        } else if (num < 1) {
            System.out.printf("Error: can't generate a secret number " +
                    "with a length of %d. Possible length of the code is 1-36.", num);
            System. exit(0);
        }
        System.out.println("Input the number of possible symbols in the code: ");
        int symbols = inputCheck();
        if (symbols < num) {
            System.out.println("Error: it's not possible to generate a code with a length of " + num +
                    " with " + symbols + " unique symbols.");
            System.exit(0);
        }
        if (symbols < 1) {
            System.out.printf("Error: can't generate a secret number " +
                    "with the number of symbols being %d. Possible number of symbols is 1-36.", num);
            System. exit(0);
        }
        if (symbols <= 10) {
            System.out.println("The secret is prepared: " + star.repeat(num) + " (0-" + (symbols - 1) + ").");
        } else if ((symbols > 10) && (symbols <= 36)) {
            String alphabet = ("abcdefghijklmnopqrstuvwxyz");
            char letter = alphabet.charAt(symbols - 11);
            System.out.println("The secret is prepared: " + star.repeat(num) + " (0-9, a-" + letter + ").");
        } else {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System. exit(0);
        }
        Random rand = new Random();
        String bloop = randomCode(symbols);
        String result = bloop;

        if (num == 1) {
            if (result.isEmpty()) {
                bloop = randomCode(symbols);
                result = result + bloop;
            }
        } else {
            int i = 0;
            while (i < num - 1) {
                if (result.isEmpty()) {
                    bloop = randomCode(symbols);
                    result = result + bloop;
                } else {
                    bloop = randomCode(symbols);
                    while (result.contains(bloop)) {
                        bloop = randomCode(symbols);
                    }
                    result = result + bloop;
                    i++;
                }
            }
        }
        return result;
    }

    public void answerGuess(String answer) {
        int num = answer.length();
        int turns = 1;

        System.out.println("Okay, let's start a game!\n");
        System.out.println("Turn " + turns + ": ");
        guess = scanner.next();
        while (!guess.equals(answer)) {
            bulls = 0;
            cows = 0;
            for (i = 0; i < num; i++) {
                for (j = 0; j < num; j++) {
                    if ((guess.charAt(i) == answer.charAt(j)) && (i != j)) {
                        cows++;
                    }
                }
            }

            for (k = 0; k < num; k++) {
                if (guess.charAt(k) == answer.charAt(k)) {
                    bulls++;
                }
            }

            if ((cows == 0) && (bulls == 0)) {
                System.out.println("Grade: None\n");
            } else if ((cows != 0) && (bulls == 0)) {
                if (cows == 1) {
                    System.out.println("Grade: 1 cow\n");
                } else {
                    System.out.printf("Grade: %d cows\n", cows);
                }
            } else if ((cows == 0) && (bulls != 0)) {
                if (bulls == 1) {
                    System.out.println("Grade: 1 bull\n");
                } else {
                    System.out.printf("Grade: %d bulls\n\n", bulls);
                }
            } else if ((cows != 0) && (bulls != 0)) {
                if ((cows == 1) && (bulls == 1)) {
                    System.out.println("Grade: 1 bull and 1 cow\n");
                } else if (bulls == 1) {
                    System.out.printf("Grade: 1 bull and %d cows\n\n", cows);
                } else if (cows == 1) {
                    System.out.printf("Grade: %d bulls and 1 cow\n", bulls);
                } else {
                    System.out.printf("Grade: %d bulls and %d cows\n\n", bulls, cows);
                }
            }

            turns++;
            System.out.println("Turn " + turns + ": ");
            guess = scanner.next();
        }
        if (guess.equals(answer)) {
            System.out.println("Grade: " + num + " bulls ");
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            System.out.println("Error: Something went wrong. ");
        }
    }

    public static void main(String[] args) {
        BullsAndCows main = new BullsAndCows();
        String result = main.secretCode();
        main.answerGuess(result);

    }
}