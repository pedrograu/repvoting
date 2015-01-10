/*package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import main.AuthorityImpl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;
import domain.VotosCifrados;

public class TestMain {

	public static void main(String[] args) {
		VotosCifrados votos =null;
		String jsonObject = getStringJSONFromUrl("http://php-egc.rhcloud.com/get_votes.php?votation_id=998");
		ObjectMapper mapper = new ObjectMapper();
		try {
			votos = mapper.readValue(jsonObject, new TypeReference<VotosCifrados>() {});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AuthorityImpl authorityImpl = new AuthorityImpl();
		String key = authorityImpl.getPrivateKey("998");
		for (String s: votos.getVotes()){
			BASE64Decoder decoder = new BASE64Decoder();
			String g = s.substring(0,s.length()-1);
			try {
				
				byte[] bytesDecode = decoder.decodeBuffer(g);
				boolean testss = authorityImpl.checkVote(bytesDecode, "998");
				if (testss){
					
					Cipher rsa;
					
					KeyFactory keyFactory = KeyFactory.getInstance("RSA");
					KeySpec keySpec = new PKCS8EncodedKeySpec(DatatypeConverter.parseBase64Binary(authorityImpl.getPrivateKey("998")));
					PrivateKey privKeyFromBytes = keyFactory.generatePrivate(keySpec);
					rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
					rsa.init(Cipher.DECRYPT_MODE, privKeyFromBytes);
					byte[] bytesDesencriptados = rsa.doFinal(bytesDecode);
					String finalgg = new String(bytesDesencriptados);
					//String googs = authorityImpl.decrypt("998", bytesDecode);
					//System.out.print(googs);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.print(votos);

	}
	
	public static String getStringJSONFromUrl(String urlString) {

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;

		String jsonString = null;

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
		// return JSON String
		return jsonString;
	}

}
*/