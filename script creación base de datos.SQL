start transaction;


create database `EGC-Frontend`;
use `EGC-Frontend`;

grant select, insert, update, delete
on `EGC-Frontend`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view
on `EGC-Frontend`.* to 'acme-manager'@'%';

--
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;

CREATE TABLE `opcion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `voto`
--

DROP TABLE IF EXISTS `voto`;

CREATE TABLE `voto` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `num_votos` int(11) DEFAULT NULL,
  `opcion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s2830im2p8ljcuby44jkl3v4s` (`opcion_id`),
  CONSTRAINT `FK_s2830im2p8ljcuby44jkl3v4s` FOREIGN KEY (`opcion_id`) REFERENCES `opcion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
INSERT INTO `useraccount` VALUES (11,0,'21232f297a57a5a743894a0e4a801fc3','admin');
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
INSERT INTO `useraccount_authorities` VALUES (11,'ADMIN');
UNLOCK TABLES;


--
-- Table structure for table `votacion`
--

DROP TABLE IF EXISTS `votacion`;
CREATE TABLE `votacion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `votacion_opcion`
--

DROP TABLE IF EXISTS `votacion_opcion`;
CREATE TABLE `votacion_opcion` (
  `Votacion_id` int(11) NOT NULL,
  `opciones_id` int(11) NOT NULL,
  UNIQUE KEY `UK_oovxg5c61h8jxncwfxb2of1kq` (`opciones_id`),
  KEY `FK_oovxg5c61h8jxncwfxb2of1kq` (`opciones_id`),
  KEY `FK_1sdpd2g5hee541n6puunsmh1t` (`Votacion_id`),
  CONSTRAINT `FK_1sdpd2g5hee541n6puunsmh1t` FOREIGN KEY (`Votacion_id`) REFERENCES `votacion` (`id`),
  CONSTRAINT `FK_oovxg5c61h8jxncwfxb2of1kq` FOREIGN KEY (`opciones_id`) REFERENCES `opcion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `votacion_voto`
--

DROP TABLE IF EXISTS `votacion_voto`;
CREATE TABLE `votacion_voto` (
  `Votacion_id` int(11) NOT NULL,
  `votos_id` int(11) NOT NULL,
  UNIQUE KEY `UK_42l8uud0tejybny12ktivu701` (`votos_id`),
  KEY `FK_42l8uud0tejybny12ktivu701` (`votos_id`),
  KEY `FK_26slxb81lmuebfggbddryf441` (`Votacion_id`),
  CONSTRAINT `FK_26slxb81lmuebfggbddryf441` FOREIGN KEY (`Votacion_id`) REFERENCES `votacion` (`id`),
  CONSTRAINT `FK_42l8uud0tejybny12ktivu701` FOREIGN KEY (`votos_id`) REFERENCES `voto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
UNLOCK TABLES;




-- Dump completed on 2014-11-24 13:22:35
commit;