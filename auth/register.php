<?php
/** 
* @file
* \brief Registro en la aplicación
* \details Pantalla de registro en la aplicación. Añade cabeceras, muestra 
* los mensajes de error de action_register.php y define la estructura del layout.
* \author auth.agoraUS
*/



include_once("database.php");
session_start();
?>
<!DOCTYPE html>
<html lang="es" xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta charset="utf-8">
   <link rel="stylesheet" href="layout.css" />
   <script src="lib/jquery-2.1.1.min.js"></script>
   <title><?php echo TITLE?></title>
   <script type="text/javascript">
    function validateEmail(email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"
                ))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-z
                A-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function form_process(){
        var errores = false;
        $('#error').html("");
        if ($('#username').val() == undefined || $('#username').val() == "") {
            errores = true;
            $('#error').html($('#error').html() + "-Debe elegir un nombre de usuario<br>");
        } else if ($('#username').val().length < 5) {
            errores = true;
            $('#error').html($('#error').html() + 
            "-El nombre de usuario es demasiado corto (mínimo 5 caracteres)<br>");
        }
        if ($('#password').val() == undefined || $('#password').val() == "") {
            errores = true;
            $('#error').html($('#error').html() + "-Debe elegir una contraseña<br>");
        } else if ($('#password').val().length < 5) {
            errores = true;
            $('#error').html($('#error').html() + "-La contraseña es demasiado corta (mínimo 5 caracteres)<br>");
        } else if ( $('#r_password').val() == undefined ||
                    $('#r_password').val() == "" ||
                    $('#password').val() != $('#r_password').val()) {
            errores = true;
            $('#error').html($('#error').html() + "-Las contraseñas no coinciden<br>");
        }
        if ($('#email').val() == undefined || $('#email').val() == "") {
            errores = true;
            $('#error').html($('#error').html() + "-Debe indicar una dirección de correo electrónico.<br>");
        } else if (!validateEmail($('#email').val())) {
            errores = true;
            $('#error').html($('#error').html() + "-La dirección de correo electrónico no es válida<br>");
        }
        if ($('#genre').val() == undefined || $('#genre').val() == "" || $('#genre').val() == "default" ) {
            errores = true;
            $('#error').html($('#error').html() + "-Debe elegir un género<br>");
        }
        if ($('#age').val() == undefined || $('#age').val() == "") {
            errores = true;
            $('#error').html($('#error').html() + "-Debe elegir una edad<br>");
        } else if ($('#age').val() < 1) {
            error = true;
            $('#error').html($('#error').html() + "-La edad no es válida<br>");
        }
        if ($('#autonomous_community').val() == undefined ||
            $('#autonomous_community').val() == "" || 
            $('#autonomous_community').val() == "default" ){
            errores = true;
            $('#error').html($('#error').html() + "-Debe elegir una comunidad autónoma<br>");
        }
        return !errores;
    }
</script>
<?php
    if (!isset($_SESSION['registerForm'])) {
        $registerForm['username'] = "";
        $registerForm['password'] = "";
        $registerForm['email'] = "";
        $registerForm['age'] = "";
    } else {
        $registerForm = $_SESSION['registerForm'];
    }

    $_SESSION['registerForm'] = $registerForm;
    ?>
</head>
<body>
   <div class="wrapper">
   <div class="logo">Logo y título de la aplicación</div>
   <div class="menu">Menú de selección de opciones</div>
   <h1>Registro</h1>
   <div id="error">
        <?php
            if (isset($_REQUEST['error'])) {
                $error = $_REQUEST['error'];
                if ($error % 2 != 0) {
                    echo "-Error al insertar en la base de datos.<br>";
                    $error--;
                }
                if ($error >= 23768) {
                    echo "-La edad no es válida.<br>";
                    $error -= 23768;
                }
                if ($error >= 16384) {
                    echo "-Debe introducir una edad.<br>";
                    $error -= 16384;
                }
                if ($error >= 8192) {
                    echo "-La comunidad autónoma no es válida.<br>";
                    $error -= 8192;
                }
                if ($error >= 4096) {
                    echo "-Debe elegir una comunidad autónoma.<br>";
                    $error-=4096;
                }
                if ($error >= 2048) {
                    echo "-El género no es válido.<br>";
                    $error -= 2048;
                }
                if ($error >= 1024) {
                    echo "-Debe elegir un género.<br>";
                    $error -= 1024;
                }
                if ($error >= 512) {
                    echo "-El email ya está registrado.<br>";
                    $error -= 512;
                }
                if ($error >= 256) {
                    echo "-La dirección de correo electrónico no es válida.<br>";
                    $error -= 256;
                }
                if ($error >= 128) {
                    echo "-Debe indicar una dirección de correo electrónico.<br>";
                    $error -= 128;
                }
                if ($error >= 64) {
                    echo "-Las contraseñas no coinciden.<br>";
                    $error -= 64;
                }
                if ($error >= 32) {
                    echo "-La contraseña es demasiado corta (mínimo 5 caracteres).<br>";
                    $error -= 32;
                }
                if ($error >= 16) {
                    echo "-Debe elegir una contraseña.<br>";
                    $error -= 16;
                }
                if ($error >= 8) {
                    echo "-Ese nombre de usuario ya existe.<br>";
                    $error -= 8;
                }
                if ($error >= 4) {
                    echo "-El nombre de usuario es demasiado corto (mínimo 5 caracteres).<br>";
                    $error -= 4;
                }
                if ($error >= 2) {
                    echo "-Debe elegir un nombre de usuario.<br>";
                    $error -= 2;
                }
            }
        ?>
    </div>
    <form id="registerForm" onsubmit="return form_process()" method="POST" action="action_register.php">
        <table>
            <tr>
                <td>
                    <label for="username">Nombre de usuario:</label>
                </td>
                <td>
                    <input  type="text" 
                            id="username" 
                            name="username" 
                            value=<?php echo htmlentities($registerForm['username']) ?>>
                </td>
                <td>
                    <label for="email">Correo electrónico</label>
                </td>
                <td>
                    <input  type="text" 
                            id="email" 
                            name="email" 
                            value=<?php echo htmlentities($registerForm['email']) ?>>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Contraseña</label>
                </td>
                <td>
                    <input  type="password" 
                            id="password" 
                            name="password">
                </td>
                <td>
                    <label for="r_password">Repetir contraseña</label>
                </td>
                <td>
                    <input  type="password" 
                            id="r_password" 
                            name="r_password">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="genre">Género</label>
                </td>
                <td>
                    <select id="genre" name="genre">
                        <option value="default">----------</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Femenino">Femenino</option>
                    </select>
                </td>
                <td>
                    <label for="age">Edad</label>
                </td>
                <td>
                    <input  type="number" 
                            id="age" 
                            name="age" 
                            min="1" 
                            value=<?php echo htmlentities($registerForm['age'])?>>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="autonomous_community">Comunidad autónoma</label>
                </td>
                <td>
                    <select name="autonomous_community" id="autonomous_community">
                        <option value="default" selected="true">----------</option>
                        <option value="Andalucia">Andalucia</option>
                        <option value="Murcia">Murcia</option>
                        <option value="Extremadura">Extremadura</option>
                        <option value="Castilla la Mancha">Castilla la Mancha</option>
                        <option value="Comunidad Valenciana">Comunidad Valenciana</option>
                        <option value="Madrid">Madrid</option>
                        <option value="Castilla y Leon">Castilla y Leon</option>
                        <option value="Aragon">Aragon</option>
                        <option value="Cataluña">Cataluña</option>
                        <option value="La Rioja">La Rioja</option>
                        <option value="Galicia">Galicia</option>
                        <option value="Asturias">Asturias</option>
                        <option value="Cantabria">Cantabria</option>
                        <option value="Pais Vasco">Pais Vasco</option>
                        <option value="Navarra">Navarra</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input  type="submit" 
                            id="submit" 
                            value ="Enviar" 
                            class="contact"/>
                </td>
            </tr>
        <table>
    </form>
    <div class="push"></div>
    </div>
    <div class="footer">
        <p>Copyright</p>
    </div>
</body>
</html>