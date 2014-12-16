<?php
/** 
* @file
* \brief Operación de registro
* \details Recupera la información del formulario de 
* registro, comprueba errores y crea el usuario. Devuelve las cabeceras oportunas.
* \author auth.agoraUS
*/

session_start();
include_once('database.php');
include_once('auth.php');

$error = 0;
if (isset($_SESSION['registerForm'])) {
    $registerForm = array();
    if (!isset($_REQUEST['username']) || $_REQUEST['username'] == "") {
        $registerForm['username'] = $_REQUEST['username'];
        $error += 2;
    } else if (strlen($_REQUEST['username']) < 5 ) {
        $registerForm['username'] = $_REQUEST['username'];
        $error += 4;
    } else if (!uniqueUsername($_REQUEST['username'])) {
        $registerForm['username'] = $_REQUEST['username'];
        $error += 8;
    } else {
        $registerForm['username'] = $_REQUEST['username'];
    }
    if (!isset($_REQUEST['password']) || $_REQUEST['password'] == "") {
        $registerForm['password'] = $_REQUEST['password'];
        $error += 16;
    } else if (strlen($_REQUEST['password']) < 5) {
        $registerForm['password'] = $_REQUEST['password'];
        $error += 32;
    } else if (!isset($_REQUEST['r_password']) || strcmp($_REQUEST['r_password'], $_REQUEST['password']) != 0) {
        $registerForm['password'] = $_REQUEST['password'];
        $error += 64;
    } else {
        $registerForm['password'] = $_REQUEST['password'];
    }
    if (!isset($_REQUEST['email']) || $_REQUEST['email'] == "") {
        $registerForm['email'] = $_REQUEST['email'];
        $error += 128;
    } else if (!filter_var($_REQUEST['email'], FILTER_VALIDATE_EMAIL)) {
        $registerForm['email'] = $_REQUEST['email'];
        $error += 256;
    } else if (!uniqueEmail($_REQUEST['email'])) {
        $registerForm['email'] = $_REQUEST['email'];
        $error += 512;
    } else {
        $registerForm['email'] = $_REQUEST['email'];
    }
    if (!isset($_REQUEST['genre']) || $_REQUEST['genre'] == "default") {
        $registerForm['genre'] = $_REQUEST['genre'];
        $error += 1024;
    } else {
        $genres = ["Masculino", "Femenino"];
        if (!(in_array($_REQUEST['genre'], $genres))) {
            $registerForm['genre'] = $_REQUEST['genre'];
            $error += 2048;
        }
        $registerForm['genre'] = $_REQUEST['genre'];
    }
    if (!isset($_REQUEST['autonomous_community']) || $_REQUEST['autonomous_community'] == "default") {
        $registerForm['autonomous_community'] = $_REQUEST['autonomous_community'];
        $error += 4096;
    } else {
        $autonomousCommunities = [ "Andalucia", 
                                    "Murcia", 
                                    "Extremadura", 
                                    "Castilla la Mancha", 
                                    "Comunidad Valenciana", 
                                    "Madrid", 
                                    "Castilla y Leon", 
                                    "Aragon", 
                                    "Cataluña", 
                                    "La Rioja", 
                                    "Galicia", 
                                    "Asturias", 
                                    "Cantabria", 
                                    "Pais Vasco", 
                                    "Navarra"];
        if (!(in_array($_REQUEST['autonomous_community'], $autonomousCommunities))) {
            $registerForm['autonomous_community'] = $_REQUEST['autonomous_community'];
            $error += 8192;
        }
        $registerForm['autonomous_community'] = $_REQUEST['autonomous_community'];
    }
    if (!isset($_REQUEST['age']) || $_REQUEST['age'] == "") {
        $registerForm['age'] = $_REQUEST['age'];
        $error += 16384;
    } else if (intval($_REQUEST['age']) == 0) {
        $registerForm['age'] = $_REQUEST['age'];
        $error += 23768;
    } else {
        $registerForm['age'] = $_REQUEST['age'];
    }

    if ($error == 0) {
        try{
            createUser($registerForm['username'], 
                        md5($registerForm['password']), 
                        $registerForm['email'], 
                        $registerForm['genre'], 
                        $registerForm['age'], 
                        $registerForm['autonomous_community']);
            session_unset($_SESSION['registerForm']);
            header("Location: index.php");
        }catch(Exception $e) {
            $error += 1;
            $_SESSION['registerForm'] = $registerForm;
            header("Location: register.php?error=".$error);
        }
    } else {
        $_SESSION['registerForm'] = $registerForm;
        header("Location: register.php?error=".$error);
    }
} else {
    header('Location: register.php');
}