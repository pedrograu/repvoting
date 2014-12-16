<?php
/** 
* @file
* \brief Inicio de la aplicación
* \details Pantalla de inicio de la aplicación. Añade cabeceras, muestra 
* los mensajes de error de logAttempt.php y define la estructura del layout.
* \author auth.agoraUS
*/

include_once 'variables.php';

if (isset($_REQUEST['logout'])) {
    setcookie("token", NULL, time()-3600);
    setcookie("user", NULL, time()-3600);
} else if (isset($_SESSION['user'])) {
    header('Location: ./?cont=home');
}

if (isset($_REQUEST['error'])) {
    $errorMsgs = array(
        "shortPass"=>"La constraseña es demasiado corta como para ser válida.",
        "shortUser"=>"El usuario es demasiado corto como para ser válido.",
        "wrongPass"=>"La contraseña es incorrecta",
        "connectionFailed"=>"Ha ocurrido un error con la base de datos."
        );
    $errorMsg = $errorMsgs[$_REQUEST['error']];
} else {
    $errorMsg = "";
}
?>
<!DOCTYPE html>
<html lang="es" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title><?php echo TITLE?></title>
</head>
<body>
<div id="loginWr">
    <div id="loginTitle"><?php echo TITLE?></div>
    <div id="login">
        <p> Si no estás registrado, puedes hacerlo <a href="register.php">aquí</a></p>
        <form action="logAttempt.php" method="post">
        <table>
            <tr>
                <td>
                    <label for="user"><img src="img/userSnapshot.png" alt="Nombre de usuario"></label>
                </td>
                <td>
                    <input  type="text" 
                            id="user" 
                            name="user" 
                            title="Su nombre de usuario" 
                            placeholder="Nombre de usuario" 
                            tabindex="1"/><br/>
                </td>
                <td rowspan="2" id="enter">
                    <input type="submit" value="Entrar">
                </td>
            </tr><tr>
                <td>
                    <label for="pass"><img src="img/passSnapshot.png" alt="Contraseña"></label>
                </td>
                <td>
                    <input  type="password" 
                            id="pass" 
                            name="pass" 
                            title="Su contraseña" 
                            placeholder="Contraseña" 
                            tabindex="2"/>
                </td>
            </tr>
        </table>
        </form>
    <div id="error"><?php echo $errorMsg?></div>
    </div>
</div>
</body>
</html>