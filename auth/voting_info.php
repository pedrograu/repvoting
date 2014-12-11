<?php
/**
* @file
* \brief Métodos de operaciones con votaciones
*/


	/**
	* \brief Usuario ha votado
	* \details Comprobar si un usuario ha votado en una votación específica
	* \param $user Nombre de usuario
	* \param $voting_id ID de votación
	* \return Boolean
	*/
	function hasVoted($user, $voting_id){
		$voted = false;
		$db_user = getUser($user);
		if(isset($db_user)){
			$voted = in_aray($voting_id, $db_user["voting"]);
		}

	return $voted;
	}

	/**
	* \brief Marcar como votado
	* \details Marca un usuario como que ha votado en una votación específica.
	* \param $user Nombre de usuario
	* \param $voting_id ID de votación
	*/
	function markAsVoted($user, $voting_id){
		$votings = $user["votings"];
		if(has_voted($user, $voting_id) && !in_array($voting_id, $votings)){
			$votings[] = $voting_id;
		}
	}
?>