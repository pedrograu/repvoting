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
		username 	VARCHAR(30) NOT NULL,
		password 	VARCHAR(32) NOT NULL,
		email 	VARCHAR(30) NOT NULL,
		genero 	ENUM("Femenino","Masculino") NOT NULL,
		comunidad_autonoma 	ENUM("Andalucia","Murcia","Extremadura","Castilla la Mancha","Comunidad Valenciana","Madrid","Castilla y Leon","Aragon","Cataluña","La Rioja","Galicia","Asturias","Cantabria","Pais Vasco","Navarra")NOT NULL,
		edad 	TINYINT NOT NULL,
		PRIMARY KEY(username)
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

function getEmail($email){
	$con = connect();
	$stmt = $con->prepare("SELECT EMAIL FROM USERS WHERE EMAIL=:email");
	$stmt->bindParam(':email',$email);
	$stmt->execute();
	return $stmt->fetch();
}

function getAllUsers() {
	$con = connect();
	$stmt = $con->prepare("SELECT USERNAME, PASSWORD, EMAIL FROM USERS");
	$stmt->execute();
	return $stmt->fetchAll();
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