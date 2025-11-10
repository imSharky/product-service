package dev.naman.productservice.security;

import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidator {
	private RestTemplateBuilder restTemplateBuilder;

	public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplateBuilder = restTemplateBuilder;
	}

	//call userservice to validate the token, if token is not valid optional is empty else optional contains
	// all of the data in payload
//	public Optional<JwtObject> validateToken(String token){
//		RestTemplate restTemplate = restTemplateBuilder.build();
//
//		return Optional.empty();
//	}

	public boolean validateToken(String token) {
		RestTemplate restTemplate = restTemplateBuilder.build();

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, token); // just token, no Bearer

		HttpEntity<Void> request = new HttpEntity<>(headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(
					"http://localhost:3100/auth/validate",
					HttpMethod.POST,
					request,
					String.class // get response as plain text
			);

			// ✅ Only "ACTIVE" means valid
			return "ACTIVE".equalsIgnoreCase(response.getBody());

		} catch (Exception e) {
			return false;
		}
	}

}