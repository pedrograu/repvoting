package controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Respuesta;
import domain.Voto;
import domain.Votos;

@RestController
public class GreetingController {

	private final String jsonTest = "{\"votes\": [{\"id\": \"None\",\"community\": \"Extremadura\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Extremadura\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Andalucia\",\"answers\": \"¿DeseaaprobarEGC?: SI\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"},{\"id\": \"None\",\"community\": \"Andalucia\",\"answers\": \"¿DeseaaprobarEGC?: NO\",\"age\": \"20\",\"id_poll\": \"2\",\"genre\": \"HOMBRE\"}],\"msg\": 1}";

    @RequestMapping("/modificacion/results")
    public List<Respuesta> resultados(@RequestParam(required = false, defaultValue = "99") Integer votacionId) {
    	
        return respuestas();
    }
    
    public List<Respuesta> respuestas(){
    	List<Respuesta> result = null;
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
			Votos votos = mapper.readValue(jsonTest, new TypeReference<Votos>() {});
			
			List<Respuesta> lre = new ArrayList<>();
			Respuesta r = new Respuesta();
			lre.add(r);
			r.setPregunta(votos.getVotes().get(0).getAnswers()
					.substring(0, votos.getVotes().get(0).getAnswers().length() - 3));
			
			Integer si = 0;

			Integer no = 0;
			
			for (Voto vc : votos.getVotes()) {
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
			r.setNumerosNo(no);
			r.setNumerosSi(si);

			Respuesta r2 = new Respuesta();
			r2.setNumerosNo(85);
			r2.setNumerosSi(12);
			r2.setPregunta("Are you crazy?");
			lre.add(r2);
			result = lre;

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
