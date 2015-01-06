package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;

import com.google.gson.Gson;

import main.AuthorityImpl;
import domain.Resultado;
import domain.VotoAntiguo;
import domain.VotoAux;
import domain.VotoNuevo;
import main.*;


public class Algoritmo {
	// Suponemos que la base de datos almacenará la información de la siguiente
	// manera: "PP", "PSOE", "PP", "PODEMOS", ...
	// Recorreremos toda esa colección, de forma que obtengamos los nombres de
	// cada votación, y los almacenaremos en un hashset (para que no se repitan
	// valores)

	// La supuesta lista que nos pasan

	public static Map<String, Integer> Algoritmo1(List<String> votos) {

		// Suponemos que la coleccion "votos" es lo que hemos recuperado de la
		// base de datos

		Set<String> claves = new HashSet<String>();

		for (String s : votos) {
			claves.add(s);
		}
		// Ahora, una vez tenemos las claves,crearemos un mapa al que
		// asignaremos a cada clave su valor.
		Map<String, Integer> resultados = new HashMap<String, Integer>();

		// Iniciamos el mapa con los valores a cero
		for (String s1 : claves) {
			resultados.put(s1, 0);
		}
		// Recorremos la lista de votos y adjudicamos el número de veces
		// repetida a su
		// nombre
		for (String s2 : votos) {
			if (resultados.containsKey(s2)) {
				resultados.put(s2, resultados.get(s2) + 1);
			}
		}

		return resultados;
	}

	// En el caso de que no se nos pase una lista de strings, sino una lista de
	// votos
	// se usará este algoritmo, que hace lo mismo que el algoritmo de arriba,
	// pero sacando de la
	// entidad "VotoAntiguo" su nombre.
	public static Map<String, Integer> algoritmo2(List<VotoAntiguo> votos) {
		Set<String> claves = new HashSet<String>();
		List<String> votaciones = new ArrayList<String>();
		for (VotoAntiguo v : votos) {
			votaciones.add(v.getNombre());
		}
		for (String s : votaciones) {
			claves.add(s);
		}
		Map<String, Integer> resultados = new HashMap<String, Integer>();
		for (String s1 : claves) {
			resultados.put(s1, 0);
		}
		for (String s2 : votaciones) {
			if (resultados.containsKey(s2)) {
				resultados.put(s2, resultados.get(s2) + 1);
			}
		}
		return resultados;
	}

	public static List<Resultado> algoritmo3(String idVotacion,List<String> votos) throws BadPaddingException 
	{
		Authority auth = new AuthorityImpl();
		List<VotoNuevo> votes = new ArrayList<VotoNuevo>();
		for (String s: votos)
		{
			if (auth.checkVote(s.getBytes(), idVotacion))
			{
				String json = auth.decrypt(idVotacion, s.getBytes());
				Gson gson = new Gson();
				VotoNuevo vot = gson.fromJson(json,VotoNuevo.class);
				votes.add(vot);
			}
		}
		
		Set<String> claves = new HashSet<String>();
		for (VotoNuevo v : votes) {
			claves.addAll(v.getPreguntaRespuesta().keySet());
		}
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (String c : claves) {
			resultados.add(new Resultado(c, 0, 0));
		}
		for (VotoNuevo v : votes) {
			for (Resultado r : resultados) {
				if (v.getPreguntaRespuesta().get(r.getPregunta())!=null) {
					if (v.getPreguntaRespuesta().get(r.getPregunta())
							.equals("si")) {
						r.setNumeroSi(r.getNumeroSi() + 1);
					} else if (v.getPreguntaRespuesta().get(r.getPregunta())
							.equals("no")) {
						r.setNumeroNo(r.getNumeroNo() + 1);
					}
				}
			}
		}
		return resultados;
	}
	
	
	public static List<Resultado> algoritmo4(List<VotoAux> votos){
		List<VotoNuevo> votoNuevos = new ArrayList<VotoNuevo>();
		
		for (VotoAux v:votos)
		{
			String delim1 = "[,]";
			String delim2 = "[:]";
			Map<String,String> preguntaRespuesta = new HashMap<String,String>();
			List<String> votoRespuestas = Arrays.asList(v.getAnswer().split(delim1));
			for(int i=0;i<votoRespuestas.size();i++)
			{
				String votoRespuesta = votoRespuestas.get(i);
				List<String> voto = Arrays.asList(votoRespuesta.split(delim2));
				preguntaRespuesta.put(voto.get(0), voto.get(1));
			}
			VotoNuevo votoNuevo = new VotoNuevo();
			votoNuevo.setAge(v.getAge());
			votoNuevo.setAutonomous_community(v.getAutonomous_community());
			votoNuevo.setGenre(v.getGenre());
			votoNuevo.setId(v.getId());
			votoNuevo.setId_poll(v.getId_poll());
			votoNuevo.setPreguntaRespuesta(preguntaRespuesta);
			votoNuevos.add(votoNuevo);
		}
		
		Set<String> claves = new HashSet<String>();
		for (VotoNuevo v : votoNuevos) {
			claves.addAll(v.getPreguntaRespuesta().keySet());
		}
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (String c : claves) {
			resultados.add(new Resultado(c, 0, 0));
		}
		for (VotoNuevo v : votoNuevos) {
			for (Resultado r : resultados) {
				if (v.getPreguntaRespuesta().get(r.getPregunta())!=null) {
					if (v.getPreguntaRespuesta().get(r.getPregunta())
							.equals("si")) {
						r.setNumeroSi(r.getNumeroSi() + 1);
					} else if (v.getPreguntaRespuesta().get(r.getPregunta())
							.equals("no")) {
						r.setNumeroNo(r.getNumeroNo() + 1);
					}
				}
			}
		}
		return resultados;
	}
}