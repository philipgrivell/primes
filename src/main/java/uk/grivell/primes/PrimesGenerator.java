package uk.grivell.primes;

import org.springframework.stereotype.Component;
import uk.grivell.primes.dto.PrimesResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrimesGenerator {
    protected List<Integer> primesCache = new ArrayList<>(Collections.singletonList(2));

    public PrimesResult generate(int initial) {
        // Use the cache to get primes generated previously
        List<Integer> results =  primesCache.stream().filter(i -> i <= initial).collect(Collectors.toList());

        // Get the last prime value in the cache and start checking at the next odd number
        int start = primesCache.size() == 1 ? 3 : primesCache.get(primesCache.size() - 1) + 2;

        // Work any remaining primes.
        for(int i = start; i <= initial; i += 2) {
            if (isPrime(i)) {
                results.add(i);
                primesCache.add(i);
            }
        }
        return new PrimesResult(initial, results);
    }

    private boolean isPrime(int primeCandidate) {
        long checkUpTo = Math.round(Math.sqrt(Double.valueOf(primeCandidate)));
        for(int i = 3; i <= checkUpTo; i += 2 ) {
            if (primeCandidate % i == 0) {
                return false;
            }
        }
        return true;
    }
}
