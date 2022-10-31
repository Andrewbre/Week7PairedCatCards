package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	private final String API_BASE_URL = "https://cat-data.netlify.app/api/facts/random";
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatFact getFact() {

		try {
			CatFact myFact = restTemplate.getForObject(API_BASE_URL, CatFact.class);
			return myFact;
		} catch (RestClientResponseException e) {
			System.out.println(e.getRawStatusCode());
			System.out.println(e.getMessage());
		} catch (ResourceAccessException e) {
			System.out.println(e.getMessage());
		}
		return new CatFact();
	}

}
