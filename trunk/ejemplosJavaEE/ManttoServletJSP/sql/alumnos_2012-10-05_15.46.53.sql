-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.27-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema alumnos
--

CREATE DATABASE IF NOT EXISTS alumnos;
USE alumnos;

--
-- Definition of table `alumnos`.`alumnos`
--

DROP TABLE IF EXISTS `alumnos`.`alumnos`;
CREATE TABLE  `alumnos`.`alumnos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carnet` varchar(10) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `fechanac` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `alumnos`.`alumnos`
--

/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
LOCK TABLES `alumnos` WRITE;
INSERT INTO `alumnos`.`alumnos` VALUES  (1,'RP950035','Julian','Rivera Pineda','xtecuan@gmail.com',NULL),
 (2,'RP950036','Julio','Rivera Pineda','juliux@gmail.com',NULL),
 (3,'RP950037','Jonatan','Rivera Pineda','kingofkings1982@live.com',NULL),
 (4,'HP0001','Amanda Elizabeth','Hernandez Rivera','amandadepaz@gmail.com','1980-11-13'),
 (5,'RR00023','Xenia Luz','Recinos Rivera','xrecinos@gmail.com','1978-01-03'),
 (7,'ER7890','Steve','Jobs','sjobs@apple.com',NULL);
UNLOCK TABLES;

grant all privileges on alumnos.* to 'alumnos'@'localhost' identified by 'welcome1';

/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
