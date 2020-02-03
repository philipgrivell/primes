package uk.grivell.primes.dto;

import java.util.List;

public class PrimeResult {
    Integer initial;
    List<Integer> primes;

    public PrimeResult(int initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
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
