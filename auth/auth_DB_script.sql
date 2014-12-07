CREATE DATABASE IF NOT EXISTS egcdb;
USE egcdb;
CREATE TABLE IF NOT EXISTS USERS(
	u_id INT AUTO_INCREMENT,
	username 	VARCHAR(30) NOT NULL,
	password 	VARCHAR(32) NOT NULL,
	email 	VARCHAR(30) NOT NULL,
	genre 	ENUM('Femenino','Masculino') NOT NULL,
	autonomous_community 	ENUM('Andalucia','Murcia','Extremadura','Castilla la Mancha','Comunidad Valenciana','Madrid','Castilla y Leon','Aragon','Cataluña','La Rioja','Galicia','Asturias','Cantabria','Pais Vasco','Navarra')NOT NULL,
	age 	TINYINT NOT NULL,
	PRIMARY KEY(U_ID));

INSERT INTO USERS VALUE(NULL, 'danayaher','7a1b688bc2bb3cc02e0d55c4e0188fb0','danayaher@alum.us.es','Masculino','Andalucia','22');
INSERT INTO USERS VALUE(NULL, 'dandelea','9cf23ad866a1953b3dd93c80f595ea11','dandelea@alum.us.es','Masculino','Andalucia','22');
INSERT INTO USERS VALUE(NULL, 'fidmazdel','b746ac06bca08e9c60f1e67f9a978253','fidmazdel@alum.us.es','Masculino','Andalucia','24');
INSERT INTO USERS VALUE(NULL, 'juarolsal','9f1644a43dbfbaf05fda6ec642430b4d','juarolsal@alum.us.es','Masculino','Andalucia','22');
INSERT INTO USERS VALUE(NULL, 'alesanmed','2c678f01c9222350776420037a69a1db','alesanmed@alum.us.es','Masculino','Andalucia','22');
INSERT INTO USERS VALUE(NULL, 'juacaslop','f8e70dcaaf443f4fadd34959adaca9d2','juacaslop@alum.us.es','Masculino','Andalucia','27');
COMMIT;
