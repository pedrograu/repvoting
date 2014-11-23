<?php
	include_once "../auth.php";
	
	class tokenTest extends PHPUnit_Framework_TestCase{

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