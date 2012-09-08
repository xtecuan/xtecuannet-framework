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
-- Create schema directorio
--

CREATE DATABASE IF NOT EXISTS directorio;
USE directorio;

--
-- Definition of table `directorio`.`agenda`
--

DROP TABLE IF EXISTS `directorio`.`agenda`;
CREATE TABLE  `directorio`.`agenda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `institucion` varchar(100) NOT NULL,
  `telefono` varchar(35) DEFAULT NULL,
  `correo` varchar(65) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  `clave` varchar(100) DEFAULT NULL,
  `idcat` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `indice_clave` (`clave`),
  KEY `indice_idcat` (`idcat`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `directorio`.`agenda`
--

/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
LOCK TABLES `agenda` WRITE;
INSERT INTO `directorio`.`agenda` VALUES  (1,'FISDL','78502340','ahernandez1@gmail.com',1,NULL,NULL),
 (2,'Xtesoft.com','78502340','xtecuan@gmail.com',1,NULL,NULL),
 (3,'csj.gob.sv','22318300','admin@csj.oj',1,NULL,NULL),
 (4,'Correos','78502340','xtecuan@yahoo.com',1,NULL,NULL),
 (5,'Julian Rivera Pineda','78502340','julian.riverapineda@gmail.com',0,NULL,NULL),
 (8,'Banco Agricola','22999989','foca@foca.com.sv',0,NULL,NULL),
 (9,'Ejemplo Institucion','22999989','foca@foca.com.sv',0,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;


--
-- Definition of table `directorio`.`categorias`
--

DROP TABLE IF EXISTS `directorio`.`categorias`;
CREATE TABLE  `directorio`.`categorias` (
  `idcat` bigint(20) NOT NULL AUTO_INCREMENT,
  `descat` varchar(25) NOT NULL,
  `fhingreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcat`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `directorio`.`categorias`
--

/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
LOCK TABLES `categorias` WRITE;
INSERT INTO `directorio`.`categorias` VALUES  (1,'FERRETERIAS','2012-09-08 10:07:37'),
 (2,'SUPERMERCADOS','2012-09-08 10:07:37'),
 (3,'FARMACIAS','2012-09-08 10:07:37');
UNLOCK TABLES;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
