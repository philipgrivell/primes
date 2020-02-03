package uk.grivell.primes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.grivell.primes.dto.PrimeResult;

import javax.annotation.Resource;


@Controller
public class PrimesController {
    Logger logger = LoggerFactory.getLogger(PrimesController.class);

    @Resource
    private PrimesGenerator primesGenerator;

    @GetMapping("/primes/{initial}")
    @ResponseBody
    public PrimeResult generatePrimes(@PathVariable("initial") String initial) {
        logger.info("Processing prime request with initial: " + initial);
        int initialValue = Integer.parseInt(initial);
        return primesGenerator.generate(initialValue);
    }
}
