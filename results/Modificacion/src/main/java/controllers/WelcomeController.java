/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modificacion")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	private final String jsonTest = "{\"votes\": [{\"id\": \"None\",\"community\": \"Extremadura\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Extremadura\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Andalucia\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Andalucia\",\"answers\": \"¿DeseaaprobarEGC?: NO\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"}],\"msg\": 1}";

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/resultados")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "99") Integer votacionId) {
		ModelAndView result = null;
		if (votacionId!=99){
		ObjectMapper mapper = new ObjectMapper();
		try {
			JSONObject json = getJSONFromUrl("http://cabinaus.herokuapp.com/prueba_id_voto/2/");
			String ses = json.toString();

			VotoCabina votoCabina = mapper.readValue(ses,
					new TypeReference<VotoCabina>() {
					});

			VotoCabina votoCabina2 = new VotoCabina();
			String answer = votoCabina.getAnswers().substring(0,
					votoCabina.getAnswers().length() - 2);
			votoCabina2.setAnswers(answer + "NO");
			List<VotoCabina> lvc = new ArrayList<>();
			lvc.add(votoCabina2);
			lvc.add(votoCabina);
			List<Respuesta> lre = new ArrayList<>();
			Respuesta r = new Respuesta();
			lre.add(r);
			r.setPregunta(lvc.get(0).getAnswers()
					.substring(0, votoCabina.getAnswers().length() - 3));
			Integer si = 0;

			Integer no = 0;
			for (VotoCabina vc : lvc) {
				String resp = vc.getAnswers().substring(
						votoCabina.getAnswers().length() - 2,
						votoCabina.getAnswers().length());

				if (resp.equals("SI")) {
					si += 1;
				} else if (resp.equals("NO")) {
					no += 1;
				}
			}
			r.setNumerosNo(String.valueOf(no));
			r.setNumerosSi(String.valueOf(si));

			String ssa = mapper.writeValueAsString(lre);

			result = new ModelAndView("welcome/index");
			result.addObject("json", ssa);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}else{
			String json = defaultJson();
			result = new ModelAndView("welcome/index");
			result.addObject("json", json);
		}

		// result.addObject("moment", moment);

		return result;
	}

	public String defaultJson() {
		String result = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			VotosTest votosTest = mapper.readValue(jsonTest, new TypeReference<VotosTest>() {});
			
			List<Respuesta> lre = new ArrayList<>();
			Respuesta r = new Respuesta();
			lre.add(r);
			r.setPregunta(votosTest.getVotes().get(0).getAnswers()
					.substring(0, votosTest.getVotes().get(0).getAnswers().length() - 3));
			
			Integer si = 0;

			Integer no = 0;
			
			for (VotoTest vc : votosTest.getVotes()) {
				String resp = vc.getAnswers().substring(
						vc.getAnswers().length() - 2,
						vc.getAnswers().length());

				Integer mod = 1;
				switch (vc.getCommunity()) {
				case "Extremadura":
					mod = 2;
					break;

				default:
					break;
				}
				if (resp.equals("SI")) {
					si += mod;
				} else if (resp.equals("NO")) {
					no += mod;
				}
			}
			r.setNumerosNo(String.valueOf(no));
			r.setNumerosSi(String.valueOf(si));

			String ssa = mapper.writeValueAsString(lre);
			result = ssa;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return result;
	}

	public JSONObject getJSONFromUrl(String urlString) {

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;

		String jsonString = null;
		JSONObject jsonObject = null;

		try {
			// Construct the URL for the OpenWeatherMap query
			// Possible parameters are available at OWM's forecast API page, at
			// http://openweathermap.org/API#forecast
			URL url = new URL(urlString);

			// Create the request to OpenWeatherMap, and open the connection
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			// Read the input stream into a String
			InputStream inputStream = urlConnection.getInputStream();
			StringBuffer buffer = new StringBuffer();

			if (inputStream == null) {
				jsonString = null;
			}

			reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}

			if (buffer.length() == 0) {
				// Stream was empty. No point in parsing.
				jsonString = null;
			}
			jsonString = buffer.toString();
		} catch (IOException e) {

			// If the code didn't successfully get the weather data, there's no
			// point in attempting
			// to parse it.
			jsonString = null;
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
				}
			}
		}
		// try to parse the string to a JSON object
		try {
			jsonObject = new JSONObject(jsonString);
		} catch (Exception e) {
		}
		// return JSON String
		return jsonObject;
	}
}