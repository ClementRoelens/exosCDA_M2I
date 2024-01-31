package entity;

import org.example.entity.GradingCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GradingCalculatorTest {
    @Test
    void gradeATest() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(95);
        gradingCalculator.setAttendancePercentage(90);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('A', result);
    }

    @Test
    void gradeB85Test() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(85);
        gradingCalculator.setAttendancePercentage(90);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('B', result);
    }

    @Test
    void gradeB95Test() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(95);
        gradingCalculator.setAttendancePercentage(65);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('B', result);
    }

    @Test
    void gradeCTest() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(65);
        gradingCalculator.setAttendancePercentage(90);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('C', result);
    }

    @Test
    void gradeF95Test() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(95);
        gradingCalculator.setAttendancePercentage(55);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('F', result);
    }

    @Test
    void gradeF65Test() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(65);
        gradingCalculator.setAttendancePercentage(55);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('F', result);
    }

    @Test
    void gradeF50Test() {
        // Arrange
        GradingCalculator gradingCalculator = new GradingCalculator();
        gradingCalculator.setScore(50);
        gradingCalculator.setAttendancePercentage(90);
        // Act
        char result = gradingCalculator.getGrade();
        // Assert
        Assertions.assertEquals('F', result);
    }

}
