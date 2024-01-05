public class Card {
    String suit;
    String rank;

    Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

}

