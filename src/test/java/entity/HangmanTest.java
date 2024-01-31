package entity;

import org.example.entity.Hangman;
import org.example.entity.WordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HangmanTest {

    @Mock
    private WordGenerator wordGenerator;
    private Hangman hangman;


    @Test
    void whenAAAIsTestedMaskShouldBeThreeUnderscores(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("AAA");
        hangman = new Hangman(wordGenerator);
        String result = hangman.generateMask();
        Assertions.assertEquals("___",result);
    }

    @Test
    void whenAIsTestedMaskShouldBeOneUnderScoreOneAThreeUnderscoresOneAOneUnderscore(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("VAUGHAN");
        hangman = new Hangman(wordGenerator);
        hangman.play('A');
        String result = hangman.generateMask();
        Assertions.assertEquals("_A___A_",result);
    }

    @Test
    void whenAIsTestedTwoTimesSecondResultShouldBeFalse(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("PAGE");
        hangman = new Hangman(wordGenerator);
        hangman.play('A');
        boolean result = hangman.play('A');
        Assertions.assertFalse(result);
    }

    @Test
    void whenCharATestedItShouldBeFalse(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("HENDRIX");
        hangman = new Hangman(wordGenerator);
        boolean result = hangman.play('A');
        Assertions.assertFalse(result);
    }

    @Test
    void whenCharATestedItShouldBeTrue(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("PAGE");
        hangman = new Hangman(wordGenerator);
        boolean result = hangman.play('A');
        Assertions.assertTrue(result);
    }

    @Test
    void whenIsWonIsTestedItShouldBeFalse(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("PAGE");
        hangman = new Hangman(wordGenerator);
        hangman.play('A');
        boolean result = hangman.isWon();
        Assertions.assertFalse(result);
    }

    @Test
    void whenIsWonIsTestedItShouldBeTrue(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("A");
        hangman = new Hangman(wordGenerator);
        hangman.play('A');
        boolean result = hangman.isWon();
        Assertions.assertTrue(result);
    }

    @Test
    void whenAIsTestedThenRemainingTriesShouldBe9(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("HENDRIX");
        hangman = new Hangman(wordGenerator);
        hangman.play('A');
        int result = hangman.getRemainingTries();
        Assertions.assertEquals(9,result);
    }

    @Test
    void whenAIsTestedThenRemainingTriesShouldBe10(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("PAGE");
        hangman = new Hangman(wordGenerator);
        hangman.play('A');
        int result = hangman.getRemainingTries();
        Assertions.assertEquals(10,result);
    }

    @Test
    void whenRemainingTriesSetTo0ThenCanPlayShouldBeFalse(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("PAGE");
        hangman = new Hangman(wordGenerator);
        hangman.setRemainingTries(0);
        boolean result = hangman.canPlay();
        Assertions.assertFalse(result);
    }

    @Test
    void whenNoTriesThenCanPlayShouldBeTrue(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("PAGE");
        hangman = new Hangman(wordGenerator);
        boolean result = hangman.canPlay();
        Assertions.assertTrue(result);
    }

    @Test
    void whenRemainingTriesSetTo0AndAIsPlayedThenIsWonShouldBeFalse(){
        Mockito.when(wordGenerator.generateRandomWord()).thenReturn("A");
        hangman = new Hangman(wordGenerator);
        hangman.setRemainingTries(0);
        hangman.play('A');
        boolean result = hangman.isWon();
        Assertions.assertFalse(result);
    }


}
