<?php
/** 
* @file
* \brief Operación de registro
* \details Recupera la información del formulario de 
* registro, comprueba errores y crea el usuario. Devuelve las cabeceras oportunas.
* \author auth.agora@us
*/

session_start();
include_once('database.php');
include_once('auth.php');

$error = 0;
if(isset($_SESSION['register_form'])){
	$register_form = array();
	if(!isset($_REQUEST['username']) || $_REQUEST['username'] == ""){
		$register_form['username'] = $_REQUEST['username'];
		$error += 2;
	}else if(strlen($_REQUEST['username']) < 5 ){
		$register_form['username'] = $_REQUEST['username'];
		$error += 4;
	}else if(!uniqueUsername($_REQUEST['username'])){
		$register_form['username'] = $_REQUEST['username'];
		$error += 8;
	}else{
		$register_form['username'] = $_REQUEST['username'];
	}
	if(!isset($_REQUEST['password']) || $_REQUEST['password'] == ""){
		$register_form['password'] = $_REQUEST['password'];
		$error += 16;
	}else if(strlen($_REQUEST['password']) < 5){
		$register_form['password'] = $_REQUEST['password'];
		$error += 32;
	}else if(!isset($_REQUEST['r_password']) || strcmp($_REQUEST['r_password'], $_REQUEST['password']) != 0){
		$register_form['password'] = $_REQUEST['password'];
		$error += 64;
	}else{
		$register_form['password'] = $_REQUEST['password'];
	}
	if(!isset($_REQUEST['email']) || $_REQUEST['email'] == ""){
		$register_form['email'] = $_REQUEST['email'];
		$error += 128;
	}else if(!filter_var($_REQUEST['email'], FILTER_VALIDATE_EMAIL)){
		$register_form['email'] = $_REQUEST['email'];
		$error += 256;
	}else if(!uniqueEmail($_REQUEST['email'])){
		$register_form['email'] = $_REQUEST['email'];
		$error += 512;
	}else{
		$register_form['email'] = $_REQUEST['email'];
	}
	if(!isset($_REQUEST['genre']) || $_REQUEST['genre'] == "default"){
		$register_form['genre'] = $_REQUEST['genre'];
		$error += 1024;
	}else{
		$genres = ["Masculino", "Femenino"];
		if(!(in_array($_REQUEST['genre'], $genres))){
			$register_form['genre'] = $_REQUEST['genre'];
			$error += 2048;
		}
		$register_form['genre'] = $_REQUEST['genre'];
	}
	if(!isset($_REQUEST['autonomous_community']) || $_REQUEST['autonomous_community'] == "default"){
		$register_form['autonomous_community'] = $_REQUEST['autonomous_community'];
		$error += 4096;
	}else{
		$autonomous_communities = ["Andalucia", "Murcia", "Extremadura", "Castilla la Mancha", "Comunidad Valenciana", "Madrid", "Castilla y Leon", "Aragon", "Cataluña", "La Rioja", "Galicia", "Asturias", "Cantabria", "Pais Vasco", "Navarra"];
		if(!(in_array($_REQUEST['autonomous_community'], $autonomous_communities))){
			$register_form['autonomous_community'] = $_REQUEST['autonomous_community'];
			$error += 8192;
		}
		$register_form['autonomous_community'] = $_REQUEST['autonomous_community'];
	}
	if(!isset($_REQUEST['age']) || $_REQUEST['age'] == ""){
		$register_form['age'] = $_REQUEST['age'];
		$error += 16384;
	}else if(intval($_REQUEST['age']) == 0){
		$register_form['age'] = $_REQUEST['age'];
		$error += 23768;
	}else{
		$register_form['age'] = $_REQUEST['age'];
	}

	if($error == 0){
		try{
			createUser($register_form['username'], md5($register_form['password']), $register_form['email'], $register_form['genre'], $register_form['age'], $register_form['autonomous_community']);
			session_unset($_SESSION['register_form']);
			header("Location: index.php");
		}catch(Exception $e){
			$error += 1;
			$_SESSION['register_form'] = $register_form;
			header("Location: register.php?error=".$error);
		}
	}else{
		$_SESSION['register_form'] = $register_form;
		header("Location: register.php?error=".$error);
	}
}else{
	header('Location: register.php');
}
?>