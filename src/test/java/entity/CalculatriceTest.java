package entity;

import org.example.entity.Calculatrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatriceTest {
    @Test
    void testAddition(){
        // Arrange
        Calculatrice calculatrice = new Calculatrice();
        // Act
        double result = calculatrice.addition(10D,45D);
        // Assert
        Assertions.assertEquals(55D,result);
    }

    @Test
    void testDivision(){
        // Arrange
        Calculatrice calculatrice = new Calculatrice();
        // Act
        double result = calculatrice.division(4D,2D);
        // Assert
        Assertions.assertEquals(2D,result);
    }

    @Test
    void testWrongDivision(){
        // Arrange
        Calculatrice calculatrice = new Calculatrice();
        // Act and assert
        Assertions.assertThrowsExactly(RuntimeException.class, () -> {
            // Act
            calculatrice.division(10D,0D);
        });
    }
}
