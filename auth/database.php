<?php
include_once "variables.php";

function connect() {
	$con = new PDO(DB_HOST,DB_USER,DB_PASS);
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
		GENRE ENUM("Femenino","Masculino") NOT NULL,
		AUTONOMOUS_COMMUNITY ENUM("Andalucia","Murcia","Extremadura","Castilla la Mancha","Comunidad Valenciana","Madrid","Castilla y Leon","Aragon","Cataluña","La Rioja","Galicia","Asturias","Cantabria","Pais Vasco","Navarra") NOT NULL,
		AGE TINYINT NOT NULL,
		PRIMARY KEY(U_ID)
		);
		');
}

function getUser($user) {
	$con = connect();
	$stmt = $con->prepare("SELECT USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE FROM USERS WHERE USERNAME=:user");
	$stmt->bindParam(':user',$user);
	$stmt->execute();
	return $stmt->fetch();
}

function getAllUsers() {
	$con = connect();
	$stmt = $con->prepare("SELECT USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE FROM USERS");
	$stmt->execute();
	$res = null;
	foreach ($stmt as $user) {
		$res[] = array('USERNAME'=>$user['USERNAME'], 'PASSWORD'=>$user['PASSWORD'], 'EMAIL'=>$user['EMAIL'], 'GENRE'=>$user['GENRE'], 'AUTONOMOUS_COMMUNITY'=>$user['AUTONOMOUS_COMMUNITY'], 'AGE'=>$user['AGE']);
	}
	return $res;
}

function createUser($username, $password, $email) {
	$con = connect();
	$stmt = $con->prepare("INSERT INTO USERS VALUES(null, :username, :password, :email, :genre, :autonomous_community, :age");
	$stmt->bindParam(':username',$username);
	$stmt->bindParam(':password',$password);
	$stmt->bindParam(':email',$email);
	$stmt->bindParam(':genre',$genre);
	$stmt->bindParam(':autonomous_community',$autonomous_community);
	$stmt->bindParam(':age',$age);
	$stmt->execute();
}

?>