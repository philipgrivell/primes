## Prime Number Generator Rest Service

Can be run locally using PrimeApplication.main

Can deployed to Cloud Foundry using the following command:

`cf push your-appname -p target/primes-0.0.1-SNAPSHOT.jar -b https://github.com/cloudfoundry/java-buildpack.git
`

### Usage
Service will generate a list of primes up to a the value specified in a GET request: http://host/primes/value

You can add the http header "Accept" with value "application/xml" to return xml.

You can also add the param algorithm using either "standard" (check primes using division) or "sieve" (using the Sieve of Eratosthenes algorithm). If not specified the standard algorithm is used.

### Limitations
Accept http header does not work when deployed to CloudFoundry.
