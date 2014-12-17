<?php
/**
* @file
* \brief Métodos de operaciones con votaciones
* \author auth.agoraUS
*/


    /**
    * \brief Usuario ha votado
    * \details Comprobar si un usuario ha votado en una votación específica
    * \param $user Nombre de usuario
    * \param $votingId ID de votación
    * \return Boolean
    */
    function hasVoted($user, $votingId) {
        $voted = false;
        $dbUser = getUser($user);
        if (isset($dbUser)) {
            $voted = in_aray($votingId, $dbUser["voting"]);
        }

    return $voted;
    }

    /**
    * \brief Marcar como votado
    * \details Marca un usuario como que ha votado en una votación específica.
    * \param $user Nombre de usuario
    * \param $votingId ID de votación
    */
    function markAsVoted($user, $votingId) {
        $votings = $user["votings"];
        if (has_voted($user, $votingId) && !in_array($votingId, $votings)) {
            $votings[] = $votingId;
        }
    }