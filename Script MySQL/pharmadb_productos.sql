-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pharmadb
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `cod_producto` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `presentacion` varchar(45) DEFAULT NULL,
  `detalles` tinytext DEFAULT NULL,
  `proveedor` varchar(45) NOT NULL,
  `precio_uni` float NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,'Amoxicilina 500mg','50 capsulas','','La Santé',4.1,'amox.JPG'),(2,1,'Nolotil 0,4 g/ml','5 ampollas','Solución inyectable para perfusión','Boehringer Ingelheim',6.2,'nolo.JPG'),(3,1,'Hidroxil','30 comprimidos','Vitaminas B6-B9-B12','Laboratorio Admirall',3.7,'hidro.JPG'),(4,3,'Apósitos Transparentes','50 unds','10cm x 12,5cm','Bastos Viegas',105.5,'Presentación1.jpg'),(5,1,'Losartan Potásico 100mg','30 tabletas','','Pharmetique Labs',3.8,'losar.JPG'),(6,1,'Gelocatil Gripe','10 sobres','Polvo para solución oral','Laboratorios Ferrer',8.4,'gelo.JPG'),(7,1,'Buscapina 10mg','60 comprimidos','','Sanofi',5.5,'buscap.JPG'),(8,3,'Venda de Crepé','10 Rollos','10 cm x 4 m','DBN SPORT CARE',5.8,'venda.JPG'),(9,3,'Venda Elástica','8cm x 4m','1 und','Hartmann',4.2,'vendaje.JPG'),(10,3,'Esparadrapo Omnipore','2,5cm x 5m','Esparadrapo de Tela','Hartmann',2.48,'espara.JPG'),(11,3,'Jeringas Estériles Desechables','100 unds','5cc 21G x 1 1/2\"','Grossmed',20.3,'jering.JPG'),(12,3,'Termómetro Infrarrojo Digital','1 und','incluye 2 pilas AA','Dovant',12.2,'termome.JPG'),(13,2,'Pulsioxímetro','1 und','Incluye 2 pilas AAA','Hartmann',15.65,'pulsi.JPG'),(14,2,'Tensiómetro','1 und','Digital de Brazo, Recargable','Tensus',25.8,'tensiome.JPG'),(15,2,'Glucómetro','1 und ','Batería de Litio de 3v (CR2032)','Yassee',21.2,'gluco.JPG');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 14:23:11
