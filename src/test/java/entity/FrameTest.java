package entity;

import org.example.entity.Frame;
import org.example.entity.RollResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FrameTest {

    private Frame frame;
    @Mock
    private RollResult rollResult;


    @Test
    void whenRoundIsOneAndRollIsOneAndRollResultIs5ThenRoundScoreShouldIncrease(){
        Mockito.when(rollResult.getResult(10)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.startNewRound();
        int scoreBeforeRoll = frame.getCurrentRound().getScore();
        frame.roll();
        int scoreAfterRoll = frame.getCurrentRound().getScore();
        int scoreDifference = scoreAfterRoll - scoreBeforeRoll;
        Assertions.assertEquals(scoreDifference,5);
    }

    @Test
    void whenRoundIsOneAndRollIsTwoAndRollResultIs5ThenRoundScoreShouldIncrease(){
        Mockito.when(rollResult.getResult(5)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(5);
        int scoreBeforeSecondRoll = frame.getCurrentRound().getScore();
        frame.roll();
        int scoreAfterSecondRoll = frame.getRounds()[frame.getCurrentRoundNumber()-2].getScore();
        int scoreDifference = scoreAfterSecondRoll - scoreBeforeSecondRoll;
        Assertions.assertEquals(scoreDifference,5);
    }

    @Test
    void whenRoundIsOneAndRollIsOneAndPerformRollThenRollNumberShouldBeTwo(){
        Mockito.when(rollResult.getResult(10)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.startNewRound();
        frame.roll();
        int rollNumber = frame.getCurrentRound().getRollNumber();
        Assertions.assertEquals(rollNumber,2);
    }

    @Test
    void whenRoundIsOneAndRollIsOneAndRollResultIs10ThenRoundNumberShouldBeTwo(){
        Mockito.when(rollResult.getResult(10)).thenReturn(10);
        frame = new Frame(rollResult);
        frame.startNewRound();
        frame.roll();
        int roundNumber = frame.getCurrentRoundNumber();
        Assertions.assertEquals(roundNumber,2);
    }

    @Test
    void whenRoundIsOneAndRollIsTwoAndPerformRollThenRoundNumberShouldBeTwo(){
        Mockito.when(rollResult.getResult(5)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(5);
        frame.roll();
        int roundNumber = frame.getCurrentRoundNumber();
        Assertions.assertEquals(roundNumber,2);
    }

    @Test
    void whenRoundIsOneAndRollIsTwoAndRollResultIs10ThenScoreShouldBe10(){
        Mockito.when(rollResult.getResult(10)).thenReturn(10);
        frame = new Frame(rollResult);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.roll();
        int score = frame.getScore();
        Assertions.assertEquals(score,10);
    }

    @Test
    void whenRoundIsOneAndRollIsOneAndRollResultIs5ThenScoreShouldBe10(){
        Mockito.when(rollResult.getResult(5)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(5);
        frame.roll();
        int score = frame.getScore();
        Assertions.assertEquals(score,10);
    }

    @Test
    void whenRoundIs10AndRollIsOneAndRollNumberIs1AndRollResultIs10ThenRollNumberShouldBe2(){
        Mockito.when(rollResult.getResult(30)).thenReturn(10);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.roll();
        int rollNumber = frame.getCurrentRound().getRollNumber();
        Assertions.assertEquals(rollNumber,2);
    }

    @Test
    void whenRoundIsTenAndRollNumberIsTwoAndRoundScoreIs10AndRollResultIs10ThenRoundScoreShouldBe20(){
        Mockito.when(rollResult.getResult(20)).thenReturn(10);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(10);
        frame.roll();
        int score = frame.getCurrentRound().getScore();
        Assertions.assertEquals(score,20);
    }

    @Test
    void whenRoundIsTenAndRollNumberIsTwoAndRoundScoreIs10AndRollResultIs10ThenRollNumberShouldBe3(){
        Mockito.when(rollResult.getResult(20)).thenReturn(10);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(10);
        frame.roll();
        int rollNumber = frame.getCurrentRound().getRollNumber();
        Assertions.assertEquals(rollNumber,3);
    }

    @Test
    void WhenRoundIsTenAndRollNumberIsTwoAndDidASpareThenRollNumberShouldBe3(){
        Mockito.when(rollResult.getResult(25)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(5);
        frame.roll();
        int rollNumber = frame.getCurrentRound().getRollNumber();
        Assertions.assertEquals(rollNumber,3);
    }

    @Test
    void whenRoundIsTenAndRollNumberIsTwoAndDidASpareThenRoundScoreShouldBe10(){
        Mockito.when(rollResult.getResult(25)).thenReturn(5);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(5);
        frame.roll();
        int score = frame.getCurrentRound().getScore();
        Assertions.assertEquals(score,10);
    }

    @Test
    void whenRoundIsTenAndRollNumberIsTwoAndScoreIs2AndRollResultIs2ThenIsEndedShouldBeTrue(){
        Mockito.when(rollResult.getResult(28)).thenReturn(2);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(2);
        frame.getCurrentRound().setScore(2);
        frame.roll();
        boolean isEnded = frame.isEnded();
        Assertions.assertTrue(isEnded);
    }

    @Test
    void whenRoundIsTenAndRollNumberIsThreeAndScoreIs20AndRollResultIs10ThenIsEndedShouldBeTrue(){
        Mockito.when(rollResult.getResult(10)).thenReturn(10);
        frame = new Frame(rollResult);
        frame.setCurrentRoundNumber(9);
        frame.startNewRound();
        frame.getCurrentRound().setRollNumber(3);
        frame.getCurrentRound().setScore(20);
        frame.roll();
        boolean isEnded = frame.isEnded();
        Assertions.assertTrue(isEnded);
    }
}
