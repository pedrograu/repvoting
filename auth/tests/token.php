<?php
	include_once "../database.php";
	include_once "../auth.php";

	$user = getUser('name1');
	echo getToken($user['USERNAME'], $user['PASSWORD']);
?>