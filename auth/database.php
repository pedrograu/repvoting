<?php
include_once "variables.php";

function connect() {
	$con = new PDO(DB_HOST,DB_USER,DB_PASS);
	$con ->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	return $con;
}

function setUp() {
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
		');
}

function getUser($user) {
	$con = connect();
	$stmt = $con->prepare("SELECT USERNAME, PASSWORD, EMAIL FROM USERS WHERE USERNAME=:user");
	$stmt->bindParam(':user',$user);
	$stmt->execute();
	return $stmt->fetch();
}

function getAllUsers() {
	$con = connect();
	$stmt = $con->prepare("SELECT USERNAME, PASSWORD, EMAIL FROM USERS");
	$stmt->execute();
	$res = null;
	foreach ($stmt as $user) {
		$res[] = array('username'=>$user['USERNAME'], 'password'=>$user['PASSWORD'], 'email'=>$user['EMAIL']);
	}
	return $res;
}

function createUser($username, $password, $email, $genre, $age, $aut_comm) {
	$con = connect();
	$stmt = $con->prepare("INSERT INTO USERS VALUES(:username, :password, :email, :genre, :aut_comm, :age)");
	$stmt->bindParam(':username',$username);
	$stmt->bindParam(':password',$password);
	$stmt->bindParam(':email',$email);
	$stmt->bindParam(':genre',$genre);
	$stmt->bindParam(':aut_comm',$aut_comm);
	$stmt->bindParam(':age',$age);
	$stmt->execute();
}

?>