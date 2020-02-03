package uk.grivell.primes;

import org.springframework.stereotype.Component;
import uk.grivell.primes.dto.PrimesResult;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrimesGenerator {

    public PrimesResult generate(int initial) {
        List<Integer> results = new ArrayList<>();
        for(int l = 2; l <= initial; l++) {
            if (isPrime(l)) {
                results.add(l);
            }
        }
        return new PrimesResult(initial, results);
    }

    private boolean isPrime(int primeCandidate) {
        for(int i = 2; i < primeCandidate; i++ ) {
            if (primeCandidate % i == 0) {
                return false;
            }
        }
        return true;
    }
}
