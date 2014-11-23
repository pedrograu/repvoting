<?php
include_once("database.php");
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
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function form_process(){
        var errores = false;
        $('#error').html("");
        if($('#username').val() == undefined || $('#username').val() == ""){
            errores = true;
            $('#error').html($('#error').html() + "<br>Debe elegir un nombre de usuario");
        }else if($('#username').val().length < 5){
            errores = true;
            $('#error').html($('#error').html() + "<br>El nombre de usuario es demasiado corto (mínimo 5 caracteres)");
        }
        if($('#password').val() == undefined || $('#password').val() == ""){
            errores = true;
            $('#error').html($('#error').html() + "<br>Debe elegir una contraseña");
        }else if($('#password').val().length < 5){
            errores = true;
            $('#error').html($('#error').html() + "<br>La contraseña es demasiado corta (mínimo 5 caracteres)");
        }
        if($('#email').val() == undefined || $('#email').val() == ""){
            errores = true;
            $('#error').html($('#error').html() + "<br>Debe elegir un email");
        }else if(!validateEmail($('#email').val())){
            errores = true;
            $('#error').html($('#error').html() + "<br>El email no es válido");
        }
        if($('#genre').val() == undefined || $('#genre').val() == "" || $('#genre').val() == "default" ){
            errores = true;
            $('#error').html($('#error').html() + "<br>Debe elegir un género");
        }
        if($('#age').val() == undefined || $('#age').val() == ""){
            errores = true;
            $('#error').html($('#error').html() + "<br>Debe elegir una edad");
        }else if($('#age').val() < 1){
            error = true;
            $('#error').html($('#error').html() + "<br>La edad no es válida");
        }
        if($('#autonomous_community').val() == undefined || $('#autonomous_community').val() == "" || $('#autonomous_community').val() == "default" ){
            errores = true;
            $('#error').html($('#error').html() + "<br>Debe elegir una comunidad autónoma");
        }
        return !errores;
    }
</script>
<?php
    if(!isset($_SESSION['register_form'])){
        $register_form['username'] = "";
        $register_form['password'] = "";
        $register_form['email'] = "";
        $register_form['age'] = "";
    }else{
        $register_form = $_SESSION['register_form'];
    }

    $_SESSION['register_form'] = $register_form;
    ?>
</head>
<body>
   <div class="wrapper">
   <div class="logo">Logo y título de la aplicación</div>
   <div class="menu">Menú de selección de opciones</div>
   <h1>Registro</h1>
   <div id="error">
        <?php
            if(isset($_REQUEST['error'])){
                $error = $_REQUEST['error'];
                switch ($error) {
                    case '1':
                        echo "Parece que algo ha ido mal...";
                        break;

                    case '2':
                        echo "Creo que ya ha mandado suficientes mensajes por hoy. Contestaré lo antes posible.";
                        break;
                }
            }
        ?>
    </div>
	<form id="register_form" onsubmit="return form_process()" method="POST" action="actions/register">
        <table>
            <tr>
            	<td><label for="username">Nombre de usuario:</label></td><td><input type="text" id="username" name="username" value=<?php echo $contact_form['name'] ?>></td>
                <td><label for="password">Contraseña</label></td><td><input type="password" id="password" name="password" value=<?php echo $contact_form['email'] ?>></td>
            </tr>
            <tr>
                <td><label for="email">Correo electrónico</label></td><td><input type="text" id="email" name="email" value=<?php echo $contact_form['email'] ?>></td>
            	<td><label for="genre">Género</label></td>
                <td><select id="genre" name="genre">
                    <option value="default">----------</option>
                    <option value="Masculino">
                        Masculino
                    </option>
                    <option value="Femenino">
                        Femenino
                    </option>
                </select>
                </td>
            </tr>
            <tr>
            	<td><label for="age">Edad</label></td><td><input type="number" id="age" name="age" min="1" value=<?php echo $register_form['age']?>></td>
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
                <td colspan="2"><input type="submit" id="submit" value ="Enviar" class="contact"/></td>
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