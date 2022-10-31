package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	private final String API_BASE_URL = "https://cat-data.netlify.app/api/pictures/random";
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {

		try {
			CatPic myPic = restTemplate.getForObject(API_BASE_URL, CatPic.class);
			return myPic;
		} catch (RestClientResponseException e) {
			System.out.println(e.getRawStatusCode());
			System.out.println(e.getMessage());
		} catch (ResourceAccessException e) {
			System.out.println(e.getMessage());
		}

		return new CatPic();
	}

}	
