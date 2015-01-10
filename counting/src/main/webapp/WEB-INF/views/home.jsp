<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>

	Grupo encargado del "Recuento"  
	
</h1>

<P> 
${serverTime} </br>


Realizará el recuento de una votación determinada. Para realizar el recuento tendrá que 
pedir los votos al almacenamiento de votos y deberá lanzar la tarea de recuento 
sincronizando las diferentes autoridades. </P>

Prueba de funcionamiento de la api rest: ${Id}
</body>
</html>
