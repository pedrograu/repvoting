<?php
	include_once "../auth.php";
	
	class tokenTest extends PHPUnit_Framework_TestCase{

		protected function setUp() {
			$con = connect();
			$stmt = $con->query('
				DROP TABLE IF EXISTS USERS;
				CREATE TABLE USERS (
				U_ID INT AUTO_INCREMENT,
				USERNAME VARCHAR(40) UNIQUE,
				PASSWORD VARCHAR(40),
				EMAIL VARCHAR(100) UNIQUE,
				PRIMARY KEY(U_ID)
				);
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUE("danayaher","7a1b688bc2bb3cc02e0d55c4e0188fb0","danayaher@alum.us.es");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUE("dandelea","9cf23ad866a1953b3dd93c80f595ea11","dandelea@alum.us.es");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUE("fidmazdel","b746ac06bca08e9c60f1e67f9a978253","fidmazdel@alum.us.es");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUE("juarolsal","9f1644a43dbfbaf05fda6ec642430b4d","juarolsal@alum.us.es");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUE("alesanmed","2c678f01c9222350776420037a69a1db","alesanmed@alum.us.es");
				INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUE("juacaslop","f8e70dcaaf443f4fadd34959adaca9d2","juacaslop@alum.us.es");
				');
		}

		function tearDown(){
			$con = connect();
			$stmt = $con->query('
				DROP TABLE USERS;
				');
		}

		public function test_checkToken(){
			$username = "dandelea";
			$password = getUser($username)['PASSWORD'];
			$token = getToken($username, $password);
			$this->assertTrue(tokenIsCorrect($token));
		}

		public function test_checkTokenUser(){
			$username = "dandelea";
			$password = getUser($username)['PASSWORD'];
			$token = getToken($username, $password);
			$this->assertTrue(checkUserToken($token, $username));
		}

		/**
 		* This test will succeed !!!
 		* @expectedException PHPUnit_Framework_ExpectationFailedException
 		*/
		public function testNegative_checkToken(){
			$username = "dandelea";
			$password = "incorrect_password";
			$token = getToken($username, $password);
			$this->assertTrue(tokenIsCorrect($token));
		}

		/**
 		* This test will succeed !!!
 		* @expectedException PHPUnit_Framework_ExpectationFailedException
 		*/
		public function testNegative_checkTokenUser(){
			$username = "dandelea";
			$password = "incorrect_password";
			$token = getToken($username, $password);
			$this->assertTrue(checkUserToken($token, $username));
		}
	}
?>