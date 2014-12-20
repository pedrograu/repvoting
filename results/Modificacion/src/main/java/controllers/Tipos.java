package controllers;
/**
 * @param Esta
 *            clase contiene los tipos de criterio asi como las posibilidades
 *            con las que se va a modificar las votaciones.
 * 
 * */

public class Tipos {

	public enum Voto {
		SI, NO;
	}

	public enum Sexo {
		HOMBRE, MUJER;
	}

	public enum Trabajador {
		PARADO, TRABAJADOR, JUBILADO, DESEMPLEADO;
	}

	public enum Nacionalidad {
		ESPANHOL, NOESPANHOL, NEGRO;
	}

	/**
	 * @param 17 COMUNIDADES AUToNOMAS. REPARTO: El primer nombre ira con las
	 *        dos primeras comunidades establecidas (en el siguiente orden en el
	 *        que estan puestas) y el siguiente nombre con las dos siguientes
	 *        comunidades. Si te es dificil de entender es porque no has
	 *        aprobado logica informatica. ABA, DANI UTRERA, DANI JAEN, TINOGRO,
	 *        SAMU,SINUHe, TOMaS, aNGEL LLAMAS LAFUENTE, GABRIEL( Yo hago Islas
	 *        Canarias de ejemplo).
	 *        GAL,AST,CAN,PAV,NAV,LAR,ARA,CAT,MAD,CAL,EXT,CAM
	 *        ,COV,AND,REM,ISB,ISC
	 * */

	public enum Comunidad {

		// GAL,AST,CAN,PAV,NAV,LAR,ARA,CAT,MAD,CAL,EXT,CAM,COV,AND,REM,ISB,ISC

		GALICIA, ASTURIAS, CANTABRIA, PAISVASCO, NAVARRA, LARIOJA, ARAGON, CATALUNHA, MADRID, CASTILLAYLEON, EXTREMADURA, CASTILLALAMANCHA, COMUNIDADVALENCIANA, ANDALUCIA, REGIONDEMURCIA, ISLASBALEARES, ISLASCANARIAS

	}

	/*
	 * Aqui habra una serie de tipos segun provincia, nacionalidad, etc
	 */
}
