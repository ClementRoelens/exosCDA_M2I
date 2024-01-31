package util;

import org.example.exceptions.NotFoundException;
import org.example.util.TownSeeker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TownSeekerTest {
    @Test
    void whenAThenNotFoundException(){
        Assertions.assertThrowsExactly(NotFoundException.class, () -> {
            TownSeeker.seek("a");
        });
    }

    @Test
    void whenAbThenDoesntThrowException(){
        Assertions.assertDoesNotThrow(() -> TownSeeker.seek("ab"));
    }

    @Test
    void whenVaThenValenceAndVancouver(){
        List<String> result = TownSeeker.seek("Va");
        Assertions.assertEquals(Arrays.asList("Valence", "Vancouver"), result);
    }

    @Test
    void whenLowerVaThenValenceAndVancouver(){
        List<String> result = TownSeeker.seek("va");
        Assertions.assertEquals(Arrays.asList("Valence", "Vancouver"), result);
    }

    @Test
    void whenApeThenBudapest(){
        List<String> result = TownSeeker.seek("ape");
        Assertions.assertEquals(Arrays.asList("Budapest"), result);
    }

    @Test
    void whenAsteriskThenAllTowns(){
        List<String> result = TownSeeker.seek("*");
        List<String> intendedValue = Arrays.asList(
                "Paris",
                "Budapest",
                "Skopje",
                "Rotterdam",
                "Valence",
                "Vancouver",
                "Amsterdam",
                "Vienne",
                "Sydney",
                "New York",
                "Londres",
                "Bangkok",
                "Hong Kong",
                "Dubaï",
                "Rome",
                "Istanbul");
        Assertions.assertEquals(intendedValue, result);
    }
}
