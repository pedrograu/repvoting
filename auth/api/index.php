<?php
    /** 
    * @file
    * \brief API
    * \details Controlador de la API. Atiende las peticiones y devuelve los resultados
    * de los métodos o un error cuando proceda.
    * \author auth.agoraUS
    */


    header("Access-Control-Allow-Origin: *");
    include_once "../database.php";
    include_once "../auth.php";
    if (!isset($_GET['method']) || $_GET['method'] == "") {
        badRequest();
    } else {
        switch ($_GET['method']) {
            case 'getUsers':
                getUsers();
                break;
            case 'checkToken':
                if (!isset($_GET['token'])) {
                    badRequest();
                } else {
                    checkToken($_GET['token']);
                }
                break;
            case 'checkTokenUser':
                if (!isset($_GET['token']) || !isset($_GET['user'])) {
                    badRequest();
                } else {
                    checkTokenUser($_GET['token'], $_GET['user']);
                }
                break;
            default:
                badRequest();
                break;
        }
    }

    /**
    * \brief Código 400. Método no existe.
    */
    function badRequest() {
        header('HTTP/1.1 400 Bad Request');

        echo "Bad Request. This method doesn't exists or the necessary parameters weren't provided";
    }

    /**
    * \brief Obtener usuarios
    * \details Devuelve todos los usuarios de la base de datos.
    * \return JSON
    */
    function getUsers() {
        header('HTTP/1.1 200 OK');
        header('Content-type: application/json');
        echo json_encode(getAllUsers());
    }

    /**
    * \brief Comprobar token
    * \return JSON
    */
    function checkToken($token) {
        header('HTTP/1.1 200 OK');
        header('Content-type: application/json');
        $result['valid']=tokenIsCorrect($token);

        echo json_encode($result);
        return json_encode($result);
    }

    /**
    * \brief Comprobar usuario
    * \details Comprobar si un usuario dado está ya autenticado en el sistema,
    * comprobando un token.
    * \return JSON
    */
    function checkTokenUser($token, $user) {
        header('HTTP/1.1 200 OK');
        header('Content-type: application/json');
        $result['valid']=checkUserToken($token, $user);

        echo json_encode($result);
        return json_encode($result);
    }