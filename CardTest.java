import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest {

    @Test
    void createCard() {
        // Arrange
        String suit = "Hearts";
        String rank = "Ace";

        // Act
        Card card = new Card(suit, rank);

        // Assert
        assertNotNull(card);
        assertEquals(suit, card.suit);
        assertEquals(rank, card.rank);
        assertEquals("Ace of Hearts", card.toString());
    }

}
