<?php
    /** 
    * @file
    * \brief Métodos de autenticación.
    * \author auth.agoraUS
    */
    include_once "database.php";

    /**
    * \brief Crear la cookie.
    * \details Crear la cookie en cliente para especificar que el cliente está autenticado.
    */
    function setAuthCookie($username, $password) {
        if (validUser($username, $password)) {
            setcookie("token", getToken($username, md5($password)), time()+ONE_YEAR, "/", "", 0, true);
            setcookie("user", $username, time()+ONE_YEAR, "/", "", 0, true);
        }
    }

    /**
    * \brief Borrar la cookie.
    */
    function removeAuthCookie($username, $password) {
        setcookie("token", getToken($username, $password), time()-ONE_YEAR);
        setcookie("user", $username, time()-ONE_YEAR);
    }

    /**
    * \brief Obtener token.
    * \details Obtener el token generado, que estará en la cookie
    * \param $username Nombre de usuario
    * \param $password Contraseña
    * \return Token
    */
    function getToken($username, $password){
        return $username.':'.md5($username.md5($password));
    }

    /**
    * \brief Comprobar usuario
    * \details Comprobar si un usuario dado está ya autenticado en el sistema,
    * comprobando un token.
    * \param $token Token
    * \param $username Nombre de usuario
    * \return Boolean
    */
    function checkUserToken($token, $username) {
        $user = getUser($username);
        return (isset($user) and getToken($username, $user["PASSWORD"])==$token);
    }

    /**
    * \brief Comprobar autenticación actual.
    * \details Comprobar si el usuario en la sesión actual está autenticado en el
    * sistema mirando en las cookies.
    * \return Boolean
    */
    function isAuthenticated() {
        if (isset($_COOKIE["user"]) and isset($_COOKIE["token"])) {
            return checkUserToken($_COOKIE["token"], $_COOKIE["user"]);
        } else {
            return false;
        }
    }

    /**
    * \brief Usuario válido.
    * \details Comprobar si un usuario dado existe en la base de datos.
    * \param $username Nombre de usuario.
    * \param $password Contraseña.
    * \return Boolean
    */
    function validUser($username, $password){
        $result = False;
        $user = getUser($username);
        if (isset($user) && $user["PASSWORD"]==md5($password)) {
            $result = True;
        }
        return $result;
    }

    /**
    * \brief Token correcto
    * \details Comprobar que un usuario está autenticado, usando el
    * nombre de usuario en el token.
    * \param $token Token.
    * \return Boolean
    */
    function tokenIsCorrect($token){
        $username = explode(':', $token)[0];
        return checkUserToken($token, $username);
    }

    /**
    * \brief Nombre de usuario único
    * \details Comprobar si el nombre de usuario dado ya existe en
    * la base de datos.
    * \param $username Nombre de usuario
    * \return Boolean
    */
    function uniqueUsername($username){
        $result = True;
        $user = getUser($username);
        if (isset($user['USERNAME'])) {
            $result = False;
        }
        return $result;
    }

    /**
    * \brief Email único.
    * \details Comprobar si la dirección de email dado ya existe en la base de datos.
    * \param $email Dirección de email.
    * \return Boolean
    */
    function uniqueEmail($email){
        $result = True;
        $email = getEmail($email);
        if (!empty($email)) {
            $result = False;
        }
        return $result;
    }