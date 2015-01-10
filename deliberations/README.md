repvoting
=========

Repositorio compartido en el que se desarrollaran varios subsistemas del sistema de votado correspondiente a la asignatura EGC



Como instalar este subsistema:

PARA TODOS LOS CASOS el script DeliberationsSript.sql, NO CREA el usuario acme para no machacar los permisos hacia otras tablas
por tanto, estan comentados en el script, si los necesitas crear, descomentalos

3 maneras:

1. Con eclipse:
	1.1. Importar como proyecto maven (como en DP) <br>
	1.2. Ejecutar el script DeliberationsScript.sql <br>
	1.3. Popular la base de datos (para crear las tablas, no contiene nada)<br>
	1.4. Es necesario que census este funcionando y auth para poder acceder al subsistema.<br>
<br>
2. Con tomcat:<br>
	2.1 Ejecutar el script Deliberations.sql<br>
	2.2. Generar el war y popular la base de datos dentro de la carpeta del proyecto con "mvn clean install" esto genera el war en:<br>
		/target/Deliberations-1.80.war, se debe de cambiar el nombre Deliberations.war para que el contexto en tomcat sea /Deliberations<br>
	2.3. Copiar el war al directorio de instalación del tomcat (en linux /var/lib/tomcat7/webapps)<br>
	2.4. Reiniciar tomcat<br>
	2.5. Es necesario que census este funcionando y auth para poder acceder al subsistema.<br>
<br>
3. Modo facil, si usas linux.<br>
	3.1 ejecutar como sudo el script install.sh ( sudo ./install o sh install)<br>
	3.2 es necesario tener instalado tomcat, mysqlserver, git y maven (mvn)<br>
