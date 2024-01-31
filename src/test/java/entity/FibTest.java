package entity;

import org.example.entity.Fib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

public class FibTest {

    private Fib fib;


    @Test
    void getFibSeriesWhenRangeOneTheSeriesShoulNotBeEmpty() {
        // Arrange
        fib = new Fib(1);
        // Act
        boolean result = fib.getFibSeries().isEmpty();
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void getFibSeriesWhenRangeOneTheSeriesShouldBe0() {
        // Arrange
        fib = new Fib(1);
        // Act
        List<Integer> result = fib.getFibSeries();
        // Assert
        Assertions.assertEquals(Arrays.asList(0), result);
    }


    @Test
    void getFibSeriesWhenRangeSixTheSeriesShouldContainThree() {
        // Arrange
        fib = new Fib(6);
        // Act
        boolean result = fib.getFibSeries().contains(3);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void getFibSeriesWhenRangeSixTheSeriesSizeShouldBe6() {
        // Arrange
        fib = new Fib(6);
        // Act
        int result = fib.getFibSeries().size();
        // Assert
        Assertions.assertEquals(6, result);
    }

    @Test
    void getFibSeriesWhenRangeSixTheSeriesShouldNotContainFour() {
        // Arrange
        fib = new Fib(6);
        // Act
        boolean result = fib.getFibSeries().contains(4);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void getFibSeriesWhenRangeSixTheSeriesShouldBe0_1_1_2_3_5() {
        // Arrange
        fib = new Fib(6);
        // Act
        List<Integer> result = fib.getFibSeries();
        // Assert
        Assertions.assertTrue(result.containsAll(Arrays.asList(0, 1, 1, 2, 3, 5)));
    }


    @Test
    void getFibSeriesWhenRangeSixTheSeriesShouldBeSortedAscending() {
        // Arrange
        fib = new Fib(6);
        // Act
        List<Integer> result = fib.getFibSeries();
        // Assert
        Assertions.assertEquals(Arrays.asList(0, 1, 1, 2, 3, 5), result);
    }

}