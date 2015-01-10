<?php
/** 
* @file
* @group tests
* \brief Clase para testear las funciones de operaciones con tokens.
*
* \details Clase que, usando el framework PHPUnit, pone a prueba a los distintos métodos
* auth.php sobre operaciones con tokens.</p>
* \author auth.agoraUS
*/

chdir(dirname(__FILE__));
include_once "../database.php";
include_once "../auth.php";

/** 
* \brief Clase para testear las funciones de operaciones con tokens.
*
* \details Clase que, usando el framework PHPUnit, pone a prueba a los distintos métodos
* auth.php sobre operaciones con tokens.</p>
* * \author auth.agoraUS
*/
class tokenTest extends PHPUnit_Framework_TestCase
{

    /**
    * \brief Inicialización de la prueba
    */
    protected function setUp() {
        $con = connect();
        $stmt = $con->query('
            DROP TABLE IF EXISTS USERS;
            CREATE TABLE USERS (
            U_ID INT AUTO_INCREMENT,
            USERNAME VARCHAR(40) UNIQUE,
            PASSWORD VARCHAR(40),
            EMAIL VARCHAR(100) UNIQUE,
            GENRE ENUM("Femenino","Masculino") NOT NULL,
            AUTONOMOUS_COMMUNITY ENUM(  "Andalucia",
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
                                        "Navarra") NOT NULL,
            AGE TINYINT NOT NULL,
            PRIMARY KEY(U_ID)
            );
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) 
                        VALUE(  "danayaher",
                                "7a1b688bc2bb3cc02e0d55c4e0188fb0",
                                "danayaher@alum.us.es",
                                "Masculino",
                                "Andalucia",
                                "22");
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) 
                        VALUE(  "dandelea",
                                "9cf23ad866a1953b3dd93c80f595ea11",
                                "dandelea@alum.us.es",
                                "Masculino",
                                "Andalucia",
                                "22");
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) 
                        VALUE(  "fidmazdel",
                                "b746ac06bca08e9c60f1e67f9a978253",
                                "fidmazdel@alum.us.es",
                                "Masculino",
                                "Andalucia",
                                "24");
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) 
                        VALUE(  "juarolsal",
                                "9f1644a43dbfbaf05fda6ec642430b4d",
                                "juarolsal@alum.us.es",
                                "Masculino",
                                "Andalucia",
                                "22");
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) 
                        VALUE(  "alesanmed",
                                "2c678f01c9222350776420037a69a1db",
                                "alesanmed@alum.us.es",
                                "Masculino",
                                "Andalucia",
                                "22");
            INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, GENRE, AUTONOMOUS_COMMUNITY, AGE) 
                        VALUE(  "juacaslop",
                                "f8e70dcaaf443f4fadd34959adaca9d2",
                                "juacaslop@alum.us.es",
                                "Masculino",
                                "Andalucia",
                                "27");
            ');
    }

    /**
    * \brief Finalización de la prueba
    */
    function tearDown(){
        $con = connect();
        $stmt = $con->query('
            DROP TABLE USERS;
            ');
    }

    /**
    * \brief Test positivo de checkToken()
    */
    public function test_checkToken(){
        $username = "dandelea";
        $password = getUser($username)['PASSWORD'];
        $token = getToken($username, $password);
        $this->assertTrue(tokenIsCorrect($token));
    }

    /**
    * \brief Test positivo de chekTokenUser()
    */
    public function test_checkTokenUser(){
        $username = "dandelea";
        $password = getUser($username)['PASSWORD'];
        $token = getToken($username, $password);
        $this->assertTrue(checkUserToken($token, $username));
    }

    /**
        * \brief Test negativo de checkToken()
        * @expectedException PHPUnit_Framework_ExpectationFailedException
        */
    public function testNegative_checkToken(){
        $username = "dandelea";
        $password = "incorrect_password";
        $token = getToken($username, $password);
        $this->assertTrue(tokenIsCorrect($token));
    }

    /**
        * \brief Test negativo de checkTokenUser()
        * @expectedException PHPUnit_Framework_ExpectationFailedException
        */
    public function testNegative_checkTokenUser(){
        $username = "dandelea";
        $password = "incorrect_password";
        $token = getToken($username, $password);
        $this->assertTrue(checkUserToken($token, $username));
    }
}