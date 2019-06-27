
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_usuarios` (
  `Matricula` int(100) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Usuario` varchar(100) NOT NULL,
  `Senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES (1,'Marcilio Rodrigues','marcilior','123456'),(1,'Marcilio Rodrigues','marcilior','123456'),(1,'Marcilio Rodrigues','marcilior','123456'),(1,'Marcilio Rodrigues','marcilior','123456'),(1,'Marcilio Rodrigues','marcilior','123456');
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcurso`
--

DROP TABLE IF EXISTS `tbcurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbcurso` (
  `codcurso` int(100) NOT NULL AUTO_INCREMENT,
  `nomecurso` varchar(100) NOT NULL,
  PRIMARY KEY (`codcurso`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcurso`
--

LOCK TABLES `tbcurso` WRITE;
/*!40000 ALTER TABLE `tbcurso` DISABLE KEYS */;
INSERT INTO `tbcurso` VALUES (1,'SISTEMAS DE INFORMAÇÃO'),(2,'ENGENHARIA DE SOFTWARE'),(3,'CIÊNCIAS DA COMPUTAÇÃO'),(4,'ENGENHARIA DE SOFTWARES');
/*!40000 ALTER TABLE `tbcurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbdisciplina`
--

DROP TABLE IF EXISTS `tbdisciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbdisciplina` (
  `coddisciplina` int(100) NOT NULL AUTO_INCREMENT,
  `codcurso` int(100) NOT NULL,
  `nomedisciplina` varchar(100) NOT NULL,
  PRIMARY KEY (`coddisciplina`),
  KEY `codcurso` (`codcurso`),
  CONSTRAINT `tbdisciplina_ibfk_1` FOREIGN KEY (`codcurso`) REFERENCES `tbcurso` (`codcurso`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdisciplina`
--

LOCK TABLES `tbdisciplina` WRITE;
/*!40000 ALTER TABLE `tbdisciplina` DISABLE KEYS */;
INSERT INTO `tbdisciplina` VALUES (1,1,'PROGRAMAÇÃO ORIENTADA A OBJETOS'),(2,1,'INTRODUÇÃO A PROGRAMAÇÃO'),(3,1,'ARQUITETURA DE COMPUTADORES'),(4,1,'ESTRUTURA DE DADOS'),(5,2,'REQUISITOS DE SOFTWARE'),(6,2,'PROCESSO DE SOFTWARE'),(7,2,'LEITURA DE SOFTWARE'),(8,2,'SOFTWARE CONCORRENTE'),(9,3,'CALCULO I'),(10,3,'CALCULO II'),(11,3,'ALGEBRA LINEAR'),(12,3,'INTRODUÇÃO A COMPUTAÇÃO');
/*!40000 ALTER TABLE `tbdisciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblivros`
--

DROP TABLE IF EXISTS `tblivros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblivros` (
  `codlivro` int(100) NOT NULL AUTO_INCREMENT,
  `coddisciplina` int(100) NOT NULL,
  `nomelivro` varchar(100) NOT NULL,
  `statuslivro` varchar(100) NOT NULL,
  `contexemplares` int(100) NOT NULL,
  `localbiblioteca` varchar(100) NOT NULL,
  PRIMARY KEY (`codlivro`),
  KEY `coddisciplina` (`coddisciplina`),
  CONSTRAINT `tblivros_ibfk_1` FOREIGN KEY (`coddisciplina`) REFERENCES `tbdisciplina` (`coddisciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblivros`
--

LOCK TABLES `tblivros` WRITE;
/*!40000 ALTER TABLE `tblivros` DISABLE KEYS */;
INSERT INTO `tblivros` VALUES (1,9,'GUIDORIZZI, H. L. Um Curso de Cálculo Volume 1','Dev.12/07',10,'Campus II'),(2,10,'STEWART, J. Cálculo Volume 2','Disponível',15,'Campus II'),(3,9,'STEWART, J. Cálculo Volume 1','Disponível',10,'Campus II'),(4,9,'THOMAS, G. B. Cálculo Volume 1','Disponível',8,'Campus II'),(5,10,'LEITHOLD, L. O Cálculo com Geometria Analítica Vol. II','Disponível',10,'Campus II'),(6,10,'GUIDORIZZI, H. L. Um Curso de Cálculo Volume 2','Disponível',10,'Campus II'),(7,11,'CALLIOLI, CARLOS. Álgebra Linear e Aplicações','Disponível',10,'Campus II'),(8,11,'LIMA, E.L. Álgebra Linear','Disponível',8,'Campus II'),(9,11,'ANTON,H. RORRES, C. Álgebra Linear com Aplicações','Disponível',8,'Campus II'),(10,12,'VIEIRA, J.N. Introdução à Computação','Disponível',7,'Campus II'),(11,12,'MARQUES, M.A. Introdução à Ciência da Computação','Disponível',11,'Campus II'),(12,12,'SIPSER, M. Introdução à Teoria da Computação','Disponível',14,'Campus II'),(13,5,'WIEGERS, K. Software Requirements 2','Disponível',3,'Campus II'),(14,5,'WITHALL, S. Software Requirements Patterns','Disponível',5,'Campus II'),(15,5,'COCKBURN, A. Writing Effective Use Cases','Disponível',3,'Campus II'),(16,6,'THAYER, R. H. The Supporting Processes','Disponível',6,'Campus II'),(17,6,'THAYER, R. H. The Development Processes','Disponível',2,'Campus II'),(18,6,'ADDISON-WESLEY. Agile Software Development','Disponível',4,'Campus II'),(19,7,'SPINELLIS, D. Code Reading: Open Source Perspective','Disponível',5,'Campus II'),(20,8,'LEA, D. Concurrent Programming in Java','Disponível',5,'Campus II'),(21,8,'GOETZ, B. Java Concurrency in Practice','Disponível',2,'Campus II'),(22,3,'TANENBAUM, A. S. Org. Estruturada de ComputadoresTANENBAUM, A. S. Org. Estruturada de Computadores','Disponível',12,'Campus II'),(23,3,'MAIA, L. P. Arquitetura de Redes de Computadores','Disponível',7,'Campus II'),(24,3,'STALLINGS, W. Arquitetura e Org. de Computadores','Disponível',8,'Campus II'),(25,4,'CERQUEIRA, R. Introdução a Estrutura de Dados','Disponível',12,'Campus II'),(26,4,'EDELWEISS, N. Estrutura de Dados','Disponível',12,'Campus II'),(27,4,'LORENZI, F. MATTOS, P. N. Estruturas de Dados','Disponível',6,'Campus II'),(28,2,'PEREIRA, S. L. Algoritmos e Lógica de Programação','Disponível',5,'Campus II'),(29,2,'SCHILDT, H. C Completo e Total','Disponível',11,'Campus II'),(30,2,'GARCIA, G. LOPES, A. Introdução à Programação','Disponível',6,'Campus II'),(31,1,'DEITEL, H. M., Java Como Programar','Disponível',12,'Campus II'),(32,1,'HORSTMANN, C. S., Core Java – Fundamentals','Disponível',13,'Campus II'),(33,1,'SPEEGLE, G. D. JDBC : Practical Guide for Java Programmers','Disponível',7,'Campus I'),(34,1,'teste','Disponivel',2,'Campus III'),(35,1,'teste','disponivel',2,'Campus II');
/*!40000 ALTER TABLE `tblivros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuser`
--

DROP TABLE IF EXISTS `tbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbuser` (
  `matriculauser` int(100) NOT NULL AUTO_INCREMENT,
  `loginunico` varchar(100) DEFAULT NULL,
  `nomeuser` varchar(100) NOT NULL,
  `senhauser` varchar(100) NOT NULL,
  `permissaouser` varchar(100) NOT NULL,
  PRIMARY KEY (`matriculauser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (1,'marcilior','Marcilio Rodrigues','123456','Administrador'),(2,'erikalves','Érika Alves ','123456','Professor'),(3,'kaylaneg','Kaylane Guedes','123456','aluno'),(4,'davis','Davi Silva','123456','Aluno');
/*!40000 ALTER TABLE `tbuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bookstore'
--

--
-- Dumping routines for database 'bookstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-23  0:53:13
