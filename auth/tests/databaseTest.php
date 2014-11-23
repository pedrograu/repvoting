<?php
	include_once "../database.php";
	include_once "../auth.php";

	
	class databaseTest extends PHPUnit_Framework_TestCase{

		public function test_getUser(){
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


		public function test_getAllUsers(){
			$emails = array("danayaher", "dandelea", "fidmazdel", "juarolsal", "alesanmed", "juacaslop");
			$users = getAllUsers();
			foreach ($users as $user){
				$this->assertContains($user['username'], $emails);
			}
		}

		public function test_createUser(){
			$username = "nombredeusuario";
			$password = "password";
			$email = "direcciondecorreo@alum.us.es";
			createUser($username, $password, $email);
			$found_user = getUser($username);
			$this->assertNotNull($found_user);
		}	
		
		/**
 		* This test will succeed !!!
 		* @expectedException PHPUnit_Framework_ExpectationFailedException
 		*/
		public function testNegative_createUsers(){
			$username = NULL;
			$password = NULL;
			$email = NULL;
			createUser($username, $password, $email);
			$found_user = getUser($username);
			$this->assertNotNull($found_user);
		}

		/**
 		* This test will succeed !!!
 		* @expectedException PHPUnit_Framework_ExpectationFailedException
 		*/
		public function testNegative_getUser(){
			$username = "incorrect_value";
			$user = getUser($username);
			$this->assertEquals($username, $user['USERNAME']);
		}

		
			
	}
	
?>