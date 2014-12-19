
package controllers.egc.voto.almacenamiento;

import java.util.List;

/**
 * @author Modificación
 * 
 *	Esta clase es un objeto el cual nos envía Almacenamiento 
 *y que contiene un mensaje que puede ser sí o no según "si 
 *ha salido bien la operación del GET o no " y una lista de 
 *votos cifrados los cuales tenemos que descifrar con el .jar 
 *de verificación y que, cuando lo hayamos hecho, tendremos 
 *un JSON que será el objeto Voto original. Una vez tengamos
 * los votos descifrados podremos recorrerlos, coger los datos 
 * necesarios para su modificación. A continuación tendremos
 *  que convertir los resultados obtenidos a un JSON en una RestFull.
 *
 */
public class VotoAlmacenamientoCifrado{
   	private Integer msg;
   	private List<String> votosCifrados;

 	public Integer getMsg(){
		return this.msg;
	}
	public void setMsg(Integer msg){
		this.msg = msg;
	}
 	public List<String> getVotes(){
		return this.votosCifrados;
	}
	public void setVotes(List<String> votes){
		this.votosCifrados = votes;
	}
}
