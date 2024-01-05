public class Player {

    // Player's name
    String name;

    // Array to store cards in the player's hand
    Card[] hand;

    // Number of cards currently in the player's hand
    int handSize;

    // Constructor to initialize player with a name and a maximum hand size
    Player(String name, int maxHandSize) {
        this.name = name;
        this.hand = new Card[maxHandSize];
        this.handSize = 0;
    }

    // Method to calculate the total points in the player's hand
    int calculatePoints() {
        // Initialize points to zero
        int points = 0;

        // Flag to track if the player has an Ace in their hand
        boolean hasAce = false;

        // Loop through each card in the player's hand
        for (int i = 0; i < handSize; i++) {
            Card card = hand[i];

            // Switch statement to determine points based on the card's rank
            switch (card.rank) {
                case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> points += Integer.parseInt(card.rank);
                case "King", "Queen", "Jack" -> points += 10;
                case "Ace" -> {
                    points += 1;
                    hasAce = true;
                }
            }
        }

        // If there is an Ace and adding 10 points doesn't exceed 21, add 10 points
        if (hasAce && points + 10 <= 21) {
            points += 10;
        }

        // Return the calculated total points
        return points;
    }
}
