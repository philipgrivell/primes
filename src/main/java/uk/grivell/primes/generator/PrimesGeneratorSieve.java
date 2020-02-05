package uk.grivell.primes.generator;

import org.springframework.stereotype.Component;
import uk.grivell.primes.dto.PrimesResult;

import java.util.ArrayList;

@Component
public class PrimesGeneratorSieve implements PrimesGenerator {
    @Override
    public String getAlgorithm() {
        return "sieve";
    }

    @Override
    public PrimesResult generate(int initial) {
        boolean[] primeCandidates = new boolean[initial + 1];
        for (int i = 0; i < primeCandidates.length; i++) {
            primeCandidates[i] = true;
        }
        int primeCount = initial;
        for (int i = 2; i < Math.sqrt(initial + 1); i++) {
            if (primeCandidates[i] == true) {
                for (int j = (i * i); j <= initial; j += i) {
                    primeCandidates[j] = false;
                    primeCount--;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList<>(primeCount);
        for (int i = 2; i < primeCandidates.length; i++) {
            if (primeCandidates[i]) {
                primes.add(i);
            }
        }
        return new PrimesResult(getAlgorithm(), initial, primes);
    }
}
