<?php
	include_once "../database.php";
	include_once "../auth.php";
	if(!isset($_GET['method']) || $_GET['method'] == ""){
		badRequest();
	}else{
		switch ($_GET['method']) {
			case 'getUsers':
				getUsers();
				break;
			case 'checkToken':
				if(!isset($_GET['token'])){
					badRequest();
				}else{
					checkToken($_GET['token']);
				}
				break;
			case 'checkTokenUser':
				if(!isset($_GET['token']) || !isset($_GET['user'])){
					badRequest();
				}else{
					checkTokenUser($_GET['token'], $_GET['user']);
				}
				break;
			default:
				badRequest();
				break;
		}
	}

	function badRequest(){
		header('HTTP/1.1 400 Bad Request');

		echo "Bad Request. This method doesn't exists or the necessary parameters weren't provided";
	}

	function getUsers(){
		header('HTTP/1.1 200 OK');

		echo json_encode(getAllUsers());
	}

	function checkToken($token){
		header('HTTP/1.1 200 OK');
		$result['valid']=tokenIsCorrect($token);

		echo json_encode($result);
	}

	function checkTokenUser($token, $user){
		header('HTTP/1.1 200 OK');
		$result['valid']=checkUserToken($token, $user);

		echo json_encode($result);
	}
?>