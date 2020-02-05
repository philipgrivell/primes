package uk.grivell.primes.generator;


import org.junit.jupiter.api.Test;
import uk.grivell.primes.dto.PrimesResult;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeGeneratorTest {

    @Test
    public void testPrimesStd() {
        PrimesGeneratorStd generatorStd = new PrimesGeneratorStd();
        testPrimes(generatorStd);
        assertEquals(15, generatorStd.primesCache.size());

    }

    @Test
    public void testPrimesSieve() {
        testPrimes(new PrimesGeneratorSieve());
    }

    private void testPrimes(PrimesGenerator primesGenerator) {
        BiConsumer<Integer, List<Integer>> test = (in, expected) -> {
            PrimesResult result = primesGenerator.generate(in);
            assertEquals(expected, result.getPrimes());
        };

        test.accept(0, Arrays.asList());
        test.accept(1, Arrays.asList());
        test.accept(2, Arrays.asList(2));
        test.accept(10, Arrays.asList(2, 3, 5, 7));
        test.accept(16, Arrays.asList(2, 3, 5, 7, 11, 13));
        test.accept(49, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47));
    }
}
