package com.egc.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import algoritmos.Algoritmo;
import domain.Voto;
import domain.VotoAntiguo;

@RestController
public class ApiTestController {

	@RequestMapping("/welcome")
	public String welcome(
			@RequestParam(value = "name", required = false, defaultValue = "people") String name) {
		String welcome = "Hello "
				+ name
				+ ", welcome to our API Test, yours can use this URL for get your name in the sentence:"
				+ " http://localhost:8080/test/welcome?name=YourName";

		return welcome;
	}

	@RequestMapping("/test")
	public VotoAntiguo test(
			@RequestParam(value = "name", required = false, defaultValue = "John Dou") String name) {
		VotoAntiguo voto = new VotoAntiguo();
		voto.setNombre(name);
		voto.setEdad(35);
		voto.setPoblacion("Sevilla");

		return voto;
	}

	@RequestMapping(value = "/t", method = RequestMethod.POST)
	public void t(@RequestParam() String name) {
		System.out.println(name);
	}

	@RequestMapping("/recuento")
	public Map<String,Integer> recuento(
			@RequestParam(value = "idVotacion", required = true) int idVotacion) 
					throws URISyntaxException, IOException  {
		 
		URI uri = new URI("https://developers.facebook.com/blog/post/616/");
		
		RestTemplate restTemplate = new RestTemplate();
		Voto votos = restTemplate.getForObject(
				"http://php-egc.rhcloud.com/get_votes.php?votation_id="
						+ idVotacion, Voto.class);

		Map<String, Integer> recuento = Algoritmo.Algoritmo(votos.getVotes());
		
		restTemplate.postForObject(uri, recuento, Map.class);
		
		return recuento;

	}

}