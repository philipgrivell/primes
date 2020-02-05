package uk.grivell.primes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import uk.grivell.primes.dto.PrimesResult;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimesApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPrimesControllerJsonStd() {
        testPrimesControllerJson(null);
    }

    @Test
    public void testPrimesControllerJsonSieve() {
        testPrimesControllerJson("sieve");
    }

    private void testPrimesControllerJson(String algorithm) {
        PrimesResult expected = new PrimesResult(Optional.ofNullable(algorithm).orElse("standard"), 15, Arrays.asList(2, 3, 5, 7, 11, 13));
        PrimesResult actual = restTemplate.getForObject(getUrl(algorithm).toString(),
                PrimesResult.class);
        assertEquals(expected.getAlgorithm(), actual.getAlgorithm());
        assertEquals(expected.getInitial(), actual.getInitial());
        assertEquals(expected.getPrimes(), actual.getPrimes());
    }

    @Test
    public void testPrimesControllerXmlStd() throws JsonProcessingException {
        testPrimesControllerXml("standard");
    }

    @Test
    public void testPrimesControllerXmlSieve() throws JsonProcessingException {
        testPrimesControllerXml("sieve");
    }

    public void testPrimesControllerXml(String algorithm) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String expected = xmlMapper.writer().with(SerializationFeature.WRAP_ROOT_VALUE).withRootName("primes-result").writeValueAsString(new PrimesResult(algorithm, 15, Arrays.asList(2, 3, 5, 7, 11, 13)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(getUrl(algorithm), HttpMethod.GET, entity, String.class);
        assertEquals(expected, response.getBody());
    }

    private String getUrl(String algorithm) {
        StringBuilder url = new StringBuilder("http://localhost:" + port + "/primes/15");
        if (algorithm != null) {
            url.append("?algorithm=" + algorithm);
        }
        return url.toString();
    }
}