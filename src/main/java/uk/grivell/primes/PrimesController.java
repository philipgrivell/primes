package uk.grivell.primes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.grivell.primes.dto.PrimesResult;

import javax.annotation.Resource;


@Controller
public class PrimesController {
    Logger logger = LoggerFactory.getLogger(PrimesController.class);

    @Resource
    private PrimesGenerator primesGenerator;

    @GetMapping("/primes/{initial}")
    @ResponseBody
    public PrimesResult generatePrimes(@PathVariable("initial") String initial) {
        logger.info("Processing prime request with initial: " + initial);
        int initialValue = Integer.parseInt(initial);
        return primesGenerator.generate(initialValue);
    }

    @GetMapping(value = "/primes/{initial}", headers = "Accept=" + MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    String generatePrimesXml(@PathVariable("initial") String initial) throws JsonProcessingException {
        int initialValue = Integer.parseInt(initial);
        PrimesResult primesResult = primesGenerator.generate(initialValue);
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writer().with(SerializationFeature.WRAP_ROOT_VALUE).withRootName("primes-result").writeValueAsString(primesResult);
    }
}
