package uk.grivell.primes;


import org.junit.jupiter.api.Test;
import uk.grivell.primes.dto.PrimesResult;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeGeneratorTest {
    PrimesGenerator primesGenerator = new PrimesGenerator();

    @Test
    public void testPrimes() {
        BiConsumer<Integer, List<Integer>> test = (in, expected) -> {
            PrimesResult result = primesGenerator.generate(in);
            assertEquals(expected, result.getPrimes());
        };

        test.accept(0, Arrays.asList());
        test.accept(1, Arrays.asList());
        test.accept(10, Arrays.asList(2, 3, 5, 7));
        test.accept(50, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47));
    }
}
