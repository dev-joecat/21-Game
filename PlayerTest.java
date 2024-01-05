import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void calculatePointsNoAce() {
        // Arrange
        Player player = new Player("TestPlayer", 5);
        player.hand[0] = new Card("Hearts", "2");
        player.hand[1] = new Card("Diamonds", "8");
        player.handSize = 2;

        // Act
        int points = player.calculatePoints();

        // Assert
        assertEquals(10, points);
    }

    @Test
    void calculatePointsWithAce() {
        // Arrange
        Player player = new Player("TestPlayer", 5);
        player.hand[0] = new Card("Hearts", "Ace");
        player.hand[1] = new Card("Diamonds", "9");
        player.handSize = 2;

        // Act
        int points = player.calculatePoints();

        // Assert
        assertEquals(20, points);
    }

    @Test
    void calculatePointsWithAceAs11() {
        // Arrange
        Player player = new Player("TestPlayer", 5);
        player.hand[0] = new Card("Hearts", "Ace");
        player.hand[1] = new Card("Diamonds", "10");
        player.handSize = 2;

        // Act
        int points = player.calculatePoints();

        // Assert
        assertEquals(21, points);
    }

}
