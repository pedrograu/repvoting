<?php
	include_once "../database.php";
	include_once "../auth.php";
	
	class databaseTest extends PHPUnit_Framework_TestCase{

		protected function setUp() {
			$con = connect();
			$stmt = $con->query('
				DROP TABLE IF EXISTS USERS;
				CREATE TABLE USERS (
				U_ID INT AUTO_INCREMENT,
				USERNAME VARCHAR(40) UNIQUE,
				PASSWORD VARCHAR(40),
				EMAIL VARCHAR(100) UNIQUE,
				GENRE ENUM("Femenino","Masculino") NOT NULL,
				AUTONOMOUS_COMMUNITY ENUM("Andalucia","Murcia","Extremadura","Castilla la Mancha","Comunidad Valenciana","Madrid","Castilla y Leon","Aragon","Catalu�a","La Rioja","Galicia","Asturias","Cantabria","Pais Vasco","Navarra") NOT NULL,
				AGE TINYINT NOT NULL,
				PRIMARY KEY(U_ID)
				);
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) VALUE("danayaher","7a1b688bc2bb3cc02e0d55c4e0188fb0","danayaher@alum.us.es","Masculino","Andalucia","22");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) VALUE("dandelea","9cf23ad866a1953b3dd93c80f595ea11","dandelea@alum.us.es","Masculino","Andalucia","22");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) VALUE("fidmazdel","b746ac06bca08e9c60f1e67f9a978253","fidmazdel@alum.us.es","Masculino","Andalucia","24");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) VALUE("juarolsal","9f1644a43dbfbaf05fda6ec642430b4d","juarolsal@alum.us.es","Masculino","Andalucia","22");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) VALUE("alesanmed","2c678f01c9222350776420037a69a1db","alesanmed@alum.us.es","Masculino","Andalucia","22");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) VALUE("juacaslop","f8e70dcaaf443f4fadd34959adaca9d2","juacaslop@alum.us.es","Masculino","Andalucia","27");
				');
		}

		function tearDown(){
			$con = connect();
			$stmt = $con->query('
				DROP TABLE USERS;
				');
		}

		function test_getUser(){
			$emails = [
				"danayaher" => "danayaher@alum.us.es",
				"dandelea" => "dandelea@alum.us.es",
				"fidmazdel" => "fidmazdel@alum.us.es",
				"juarolsal" => "juarolsal@alum.us.es",
				"alesanmed" => "alesanmed@alum.us.es",
				"juacaslop" => "juacaslop@alum.us.es"];

			$key = array_rand($emails);
			$user = getUser($key);
			$this->assertEquals($emails[$key], $user['EMAIL']);
		}


		function test_getAllUsers(){
			$emails = array("danayaher", "dandelea", "fidmazdel", "juarolsal", "alesanmed", "juacaslop");
			$users = getAllUsers();
			foreach ($users as $user){
				$this->assertContains($user['USERNAME'], $emails);
			}
		}

		function test_createUser(){
			$username = "nombredeusuario";
			$password = "password";
			$email = "direcciondecorreo@alum.us.es";
			$genre = "Femenino";
			$autonomous_community = "Madrid";
			$age = 50;
			createUser($username, $password, $email, $genre, $autonomous_community, $age);
			$found_user = getUser($username);
			$this->assertNotNull($found_user);
		}	

		/**
 		* This test will succeed !!!
 		* @expectedException PHPUnit_Framework_ExpectationFailedException
 		*/
		function testNegative_getUser(){
			$username = "incorrect_value";
			$user = getUser($username);
			$this->assertEquals($username, $user['USERNAME']);
		}

		
			
	}
	
?>