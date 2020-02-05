package uk.grivell.primes.dto;

import java.util.List;

public class PrimesResult {
    String algorithm;
    Integer initial;
    List<Integer> primes;

    public PrimesResult(String algorithm, int initial, List<Integer> primes) {
        this.algorithm = algorithm;
        this.initial = initial;
        this.primes = primes;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getInitial() {
        return initial;
    }

    public void setInitial(Integer initial) {
        this.initial = initial;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }
}
