package uk.grivell.primes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.grivell.primes.dto.PrimesResult;
import uk.grivell.primes.generator.PrimesGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class PrimesController {
    Logger logger = LoggerFactory.getLogger(PrimesController.class);

    private Map<String, PrimesGenerator> primesGeneratorLookup = new HashMap<>();

    @Autowired
    private void setGeneratorLookup(List<PrimesGenerator> primesGenerators) {
        primesGenerators.stream().forEach(generator -> primesGeneratorLookup.put(generator.getAlgorithm(), generator));
    }

    @GetMapping("/primes/{initial}")
    @ResponseBody
    public PrimesResult generatePrimes(@PathVariable("initial") String initial, @RequestParam(required = false) String algorithm) {
        logger.info("Processing prime request with initial: " + initial);
        int initialValue = Integer.parseInt(initial);
        return getPrimeGenator(algorithm).generate(initialValue);
    }

    @GetMapping(value = "/primes/{initial}", headers = "Accept=" + MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    String generatePrimesXml(@PathVariable("initial") String initial, @RequestParam(required = false) String algorithm) throws JsonProcessingException {
        int initialValue = Integer.parseInt(initial);
        PrimesResult primesResult =  getPrimeGenator(algorithm).generate(initialValue);
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writer().with(SerializationFeature.WRAP_ROOT_VALUE).withRootName("primes-result").writeValueAsString(primesResult);
    }

    private PrimesGenerator getPrimeGenator(String algorithm) {
        return Optional.ofNullable(primesGeneratorLookup.get(algorithm)).orElse(primesGeneratorLookup.get("standard"));
    }
}
