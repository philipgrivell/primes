package uk.grivell.primes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import uk.grivell.primes.dto.PrimesResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimesApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPrimesController() {
		PrimesResult expected = new PrimesResult(15, Arrays.asList(2,3,5,7,11,13));
		PrimesResult actual =  this.restTemplate.getForObject("http://localhost:" + port + "/primes/15",
				PrimesResult.class);
		assertEquals(expected.getInitial(), actual.getInitial());
		assertEquals(expected.getPrimes(), actual.getPrimes());
	}
}