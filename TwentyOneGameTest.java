import java.util.Scanner;

public class TwentyOneGameTest {

    // Method to initialize the deck of cards
    public static Card[] initializeDeck() {
        // Define the suits and ranks of the cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // Create an array to represent the deck
        Card[] deck = new Card[suits.length * ranks.length];
        int index = 0;

        // Populate the deck with cards for each suit and rank combination
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = new Card(suit, rank);
            }
        }

        return deck;
    }

    // Method to shuffle the deck of cards
    public static void shuffleDeck(Card[] deck) {
        for (int i = deck.length - 1; i > 0; i--) {
            int randIndex = (int) (Math.random() * (i + 1));

            // Swap the cards at position i and randIndex
            Card temp = deck[i];
            deck[i] = deck[randIndex];
            deck[randIndex] = temp;
        }
    }

    // Method to deal a single card to a player
    public static void dealCards(Player player, Card[] deck, int index) {
        // Ensure the player's hand has space for the card
        if (player.handSize < player.hand.length) {
            // Deal one card to the player from the specified index in the deck
            player.hand[player.handSize++] = deck[index];
        }
    }

    // Method to display a player's hand and total points
    public static void displayHand(Player player) {
        System.out.println(player.name + "'s hand:");
        for (int i = 0; i < player.handSize; i++) {
            System.out.println(player.hand[i].rank + " of " + player.hand[i].suit);
        }
        System.out.println("Total Points: " + player.calculatePoints());
        System.out.println();
    }

    // Method for a player's turn
    public static void playerTurn(Player player, Card[] deck, Scanner scanner) {
        int currentIndex = player.handSize; // Track the current index for hits
        String choice;
        do {
            System.out.print(player.name + ", do you want to hit or stand? ");
            choice = scanner.next();

            if (choice.equalsIgnoreCase("hit")) {
                // Shuffle the deck before dealing each card
                shuffleDeck(deck);

                // Deal a single card to the player
                dealCards(player, deck, currentIndex);
                // Display the updated hand and points
                displayHand(player);
                currentIndex++; // Move to the next index for the next hit
            }

        } while (choice.equalsIgnoreCase("hit") && player.calculatePoints() < 21);
    }

    // Method to play one round of the game
    public static void playRound(Player player1, Player player2, Card[] deck, Scanner scanner) {
        // Shuffle the deck for the current round
        shuffleDeck(deck);

        // Deal two cards to each player
        dealCards(player1, deck, 0);
        dealCards(player1, deck, 1); // Deal the second card
        dealCards(player2, deck, 2);
        dealCards(player2, deck, 3); // Deal the second card

        // Display initial hands and calculate points
        displayHand(player1);
        displayHand(player2);

        // Player 1's turn
        playerTurn(player1, deck, scanner);

        // Player 2's turn
        playerTurn(player2, deck, scanner);

        // Determine the winner of the round
        determineRoundWinner(player1, player2);
    }


    // Method to determine the winner of a round
    public static void determineRoundWinner(Player player1, Player player2) {
        int points1 = player1.calculatePoints();
        int points2 = player2.calculatePoints();

        System.out.println(player1.name + "'s hand: ");
        displayHand(player1);
        System.out.println(player2.name + "'s hand: ");
        displayHand(player2);

        System.out.println(player1.name + " obtains " + points1 + " points");
        System.out.println(player2.name + " obtains " + points2 + " points");

        // Check for busts and compare points to declare the round winner
        if (points1 > 21 && points2 > 21) {
            System.out.println("Both players busted. It's a tie!");
        } else if (points1 > 21) {
            System.out.println(player1.name + " busted. " + player2.name + " wins the round!");
        } else if (points2 > 21) {
            System.out.println(player2.name + " busted. " + player1.name + " wins the round!");
        } else if (points1 > points2) {
            System.out.println(player1.name + " wins the round!");
        } else if (points2 > points1) {
            System.out.println(player2.name + " wins the round!");
        } else {
            System.out.println("Both players have the same amount. It's a tie!");
        }
        System.out.println();
    }

    // Method to determine the overall winner of the game
    public static void determineOverallWinner(Player player1, Player player2) {
        int roundsWonPlayer1 = 0;
        int roundsWonPlayer2 = 0;

        // Play 3 rounds
        for (int round = 1; round <= 3; round++) {
            System.out.println("ROUND " + round + ":");
            playRound(player1, player2, initializeDeck(), new Scanner(System.in));

            // Check points and update rounds won
            int points1 = player1.calculatePoints();
            int points2 = player2.calculatePoints();

            if (points1 <= 21 && (points1 > points2 || points2 > 21)) {
                roundsWonPlayer1++;
            } else if (points2 <= 21 && (points2 > points1 || points1 > 21)) {
                roundsWonPlayer2++;
            }

            // Clear hands for the next round
            player1.handSize = 0;
            player2.handSize = 0;

            // Wait for user input to proceed to the next round
            System.out.println("Press Enter to start the next round...");
            new Scanner(System.in).nextLine();
        }

        // Display overall results and determine the overall winner
        System.out.println("Overall Results:");
        System.out.println(player1.name + " won " + roundsWonPlayer1 + " rounds.");
        System.out.println(player2.name + " won " + roundsWonPlayer2 + " rounds.");

        if (roundsWonPlayer1 > roundsWonPlayer2) {
            System.out.println(player1.name + " is the overall winner!");
        } else if (roundsWonPlayer2 > roundsWonPlayer1) {
            System.out.println(player2.name + " is the overall winner!");
        } else {
            System.out.println("It's a tie! No overall winner.");
        }
    }


    // Main method to start the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize players
        int maxHandSize = 10;
        Player player1 = new Player("Player 1", maxHandSize);
        Player player2 = new Player("Player 2", maxHandSize);

        // Initialize and shuffle deck for the entire game
        Card[] deck = initializeDeck();
        shuffleDeck(deck);

        // Play 3 rounds
        for (int round = 1; round <= 3; round++) {
            System.out.println("ROUND " + round + ":");
            playRound(player1, player2, deck, scanner);

            // Clear hands for the next round
            player1.handSize = 0;
            player2.handSize = 0;

            // Wait for user input to proceed to the next round
            System.out.println("Press Enter to start the next round...");
            scanner.nextLine();
        }

        // Determine the overall winner of the game
        determineOverallWinner(player1, player2);

        // Close the scanner
        scanner.close();
    }
}