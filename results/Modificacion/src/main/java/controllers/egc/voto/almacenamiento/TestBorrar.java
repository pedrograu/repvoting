/*package controllers.egc.voto.almacenamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.base.Strings;

import controllers.Respuesta;
import controllers.Votes;
import controllers.VotoCabina;

public class TestBorrar {
	private static JSONObject readJsonFromUrl(String url) throws IOException,
	JSONException, ParseException {
JSONParser jsonParser = new JSONParser();
InputStream is = new URL(url).openStream();
try {
	BufferedReader rd = new BufferedReader(new InputStreamReader(is,
			Charset.forName("UTF-8")));
	String jsonText = readAll(rd);
	JSONObject json = null;
	try {
		json = (JSONObject) jsonParser.parse(jsonText);
	} catch (org.json.simple.parser.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
} finally {
	is.close();
}
}

private static String readAll(Reader rd) throws IOException {
StringBuilder sb = new StringBuilder();
int cp;
while ((cp = rd.read()) != -1) {
	sb.append((char) cp);
}
return sb.toString();
}
	public static void main(String[] args){
		ObjectMapper mapper = new ObjectMapper();
		try {
			JSONObject json = readJsonFromUrl("http://cabinaus.herokuapp.com/prueba_id_voto/2/");
			String ses=json.toString();
			System.out.println(ses);
			VotoCabina votoCabina = mapper.readValue(ses, new TypeReference<VotoCabina>() {});
			System.out.println(votoCabina);
			
			VotoCabina votoCabina2 = new VotoCabina();
			String answer = votoCabina.getAnswers().substring(0,votoCabina.getAnswers().length()-2);
			votoCabina2.setAnswers(answer + "NO");
			List<VotoCabina> lvc= new ArrayList<>();
			lvc.add(votoCabina2);
			lvc.add(votoCabina);
			List<Respuesta> lre = new ArrayList<>();
			Respuesta r = new Respuesta();
			r.setPregunta(lvc.get(0).getAnswers());
			Integer si =0 ;
			
			Integer no = 0 ;
			for(VotoCabina vc: lvc ){
				String resp = vc.getAnswers().substring(votoCabina.getAnswers().length()-2,votoCabina.getAnswers().length());
	
				if(resp.equals("SI")){
					si +=1;
				}else if(resp.equals("NO")){
					no += 1;
				}
			}
			r.setNumerosNo(String.valueOf(no));
			r.setNumerosSi(String.valueOf(si));
			

			String ssa=mapper.writeValueAsString(r);
			System.out.println(ssa);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		}

}
*/