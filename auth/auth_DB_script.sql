CREATE DATABASE IF NOT EXISTS egcdb;
USE egcdb;
CREATE TABLE IF NOT EXISTS user(
	username 	VARCHAR(30) NOT NULL,
	password 	VARCHAR(30) NOT NULL,
	email 	VARCHAR(30) NOT NULL,
	genero 	ENUM('Femenino','Masculino') NOT NULL,
	comunidad_autonoma 	ENUM('Andalucia','Murcia','Extremadura','Castilla la Mancha','Comunidad Valenciana','Madrid','Castilla y Leon','Aragon','Cataluña','La Rioja','Galicia','Asturias','Cantabria','Pais Vasco','Navarra')NOT NULL,
	edad 	TINYINT NOT NULL);

INSERT INTO user VALUE('danayaher','danayaher1','danayaher@alum.us.es','Masculino','Andalucia','22');
INSERT INTO user VALUE('dandelea','dandelea1','dandelea@alum.us.es','Masculino','Andalucia','22');
INSERT INTO user VALUE('fidmazdel','fidmazdel1','fidmazdel@alum.us.es','Masculino','Andalucia','24');
INSERT INTO user VALUE('juarolsal','juarolsal1','juarolsal@alum.us.es','Masculino','Andalucia','22');
INSERT INTO user VALUE('alesanmed','alesanmed1','alesanmed@alum.us.es','Masculino','Andalucia','22');
INSERT INTO user VALUE('juacaslop','juacaslop1','juacaslop@alum.us.es','Masculino','Andalucia','27');
COMMIT;
