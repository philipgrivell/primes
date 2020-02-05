package uk.grivell.primes.generator;

import uk.grivell.primes.dto.PrimesResult;

public interface PrimesGenerator {
     String getAlgorithm();

     PrimesResult generate(int initial);
}
