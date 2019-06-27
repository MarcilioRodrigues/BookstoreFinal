-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 06-Mar-2015 às 09:45
-- Versão do servidor: 5.5.41-0ubuntu0.14.04.1
-- versão do PHP: 5.5.9-1ubuntu4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `bookle`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbcurso`
--

CREATE TABLE IF NOT EXISTS `tbcurso` (
  `codcurso` int(100) NOT NULL AUTO_INCREMENT,
  `nomecurso` varchar(100) NOT NULL,
  PRIMARY KEY (`codcurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `tbcurso`
--

INSERT INTO `tbcurso` (`codcurso`, `nomecurso`) VALUES
(1, 'SISTEMAS DE INFORMAÇÃO'),
(2, 'ENGENHARIA DE SOFTWARE'),
(3, 'CIÊNCIAS DA COMPUTAÇÃO'),
(4, 'ENGENHARIA DE SOFTWARES');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbdisciplina`
--

CREATE TABLE IF NOT EXISTS `tbdisciplina` (
  `coddisciplina` int(100) NOT NULL AUTO_INCREMENT,
  `codcurso` int(100) NOT NULL,
  `nomedisciplina` varchar(100) NOT NULL,
  PRIMARY KEY (`coddisciplina`),
  KEY `codcurso` (`codcurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Extraindo dados da tabela `tbdisciplina`
--

INSERT INTO `tbdisciplina` (`coddisciplina`, `codcurso`, `nomedisciplina`) VALUES
(1, 1, 'PROGRAMAÇÃO ORIENTADA A OBJETOS'),
(2, 1, 'INTRODUÇÃO A PROGRAMAÇÃO'),
(3, 1, 'ARQUITETURA DE COMPUTADORES'),
(4, 1, 'ESTRUTURA DE DADOS'),
(5, 2, 'REQUISITOS DE SOFTWARE'),
(6, 2, 'PROCESSO DE SOFTWARE'),
(7, 2, 'LEITURA DE SOFTWARE'),
(8, 2, 'SOFTWARE CONCORRENTE'),
(9, 3, 'CALCULO I'),
(10, 3, 'CALCULO II'),
(11, 3, 'ALGEBRA LINEAR'),
(12, 3, 'INTRODUÇÃO A COMPUTAÇÃO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tblivros`
--

CREATE TABLE IF NOT EXISTS `tblivros` (
  `codlivro` int(100) NOT NULL AUTO_INCREMENT,
  `coddisciplina` int(100) NOT NULL,
  `nomelivro` varchar(100) NOT NULL,
  `statuslivro` varchar(100) NOT NULL,
  `contexemplares` int(100) NOT NULL,
  `localbiblioteca` varchar(100) NOT NULL,
  PRIMARY KEY (`codlivro`),
  KEY `coddisciplina` (`coddisciplina`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Extraindo dados da tabela `tblivros`
--

INSERT INTO `tblivros` (`codlivro`, `coddisciplina`, `nomelivro`, `statuslivro`, `contexemplares`, `localbiblioteca`) VALUES
(1, 9, 'GUIDORIZZI, H. L. Um Curso de Cálculo Volume 1', 'Dev.12/07', 10, 'Campus II'),
(2, 10, 'STEWART, J. Cálculo Volume 2', 'Disponível', 15, 'Campus II'),
(3, 9, 'STEWART, J. Cálculo Volume 1', 'Disponível', 10, 'Campus II'),
(4, 9, 'THOMAS, G. B. Cálculo Volume 1', 'Disponível', 8, 'Campus II'),
(5, 10, 'LEITHOLD, L. O Cálculo com Geometria Analítica Vol. II', 'Disponível', 10, 'Campus II'),
(6, 10, 'GUIDORIZZI, H. L. Um Curso de Cálculo Volume 2', 'Disponível', 10, 'Campus II'),
(7, 11, 'CALLIOLI, CARLOS. Álgebra Linear e Aplicações', 'Disponível', 10, 'Campus II'),
(8, 11, 'LIMA, E.L. Álgebra Linear', 'Disponível', 8, 'Campus II'),
(9, 11, 'ANTON,H. RORRES, C. Álgebra Linear com Aplicações', 'Disponível', 8, 'Campus II'),
(10, 12, 'VIEIRA, J.N. Introdução à Computação', 'Disponível', 7, 'Campus II'),
(11, 12, 'MARQUES, M.A. Introdução à Ciência da Computação', 'Disponível', 11, 'Campus II'),
(12, 12, 'SIPSER, M. Introdução à Teoria da Computação', 'Disponível', 14, 'Campus II'),
(13, 5, 'WIEGERS, K. Software Requirements 2', 'Disponível', 3, 'Campus II'),
(14, 5, 'WITHALL, S. Software Requirements Patterns', 'Disponível', 5, 'Campus II'),
(15, 5, 'COCKBURN, A. Writing Effective Use Cases', 'Disponível', 3, 'Campus II'),
(16, 6, 'THAYER, R. H. The Supporting Processes', 'Disponível', 6, 'Campus II'),
(17, 6, 'THAYER, R. H. The Development Processes', 'Disponível', 2, 'Campus II'),
(18, 6, 'ADDISON-WESLEY. Agile Software Development', 'Disponível', 4, 'Campus II'),
(19, 7, 'SPINELLIS, D. Code Reading: Open Source Perspective', 'Disponível', 5, 'Campus II'),
(20, 8, 'LEA, D. Concurrent Programming in Java', 'Disponível', 5, 'Campus II'),
(21, 8, 'GOETZ, B. Java Concurrency in Practice', 'Disponível', 2, 'Campus II'),
(22, 3, 'TANENBAUM, A. S. Org. Estruturada de ComputadoresTANENBAUM, A. S. Org. Estruturada de Computadores', 'Disponível', 12, 'Campus II'),
(23, 3, 'MAIA, L. P. Arquitetura de Redes de Computadores', 'Disponível', 7, 'Campus II'),
(24, 3, 'STALLINGS, W. Arquitetura e Org. de Computadores', 'Disponível', 8, 'Campus II'),
(25, 4, 'CERQUEIRA, R. Introdução a Estrutura de Dados', 'Disponível', 12, 'Campus II'),
(26, 4, 'EDELWEISS, N. Estrutura de Dados', 'Disponível', 12, 'Campus II'),
(27, 4, 'LORENZI, F. MATTOS, P. N. Estruturas de Dados', 'Disponível', 6, 'Campus II'),
(28, 2, 'PEREIRA, S. L. Algoritmos e Lógica de Programação', 'Disponível', 5, 'Campus II'),
(29, 2, 'SCHILDT, H. C Completo e Total', 'Disponível', 11, 'Campus II'),
(30, 2, 'GARCIA, G. LOPES, A. Introdução à Programação', 'Disponível', 6, 'Campus II'),
(31, 1, 'DEITEL, H. M., Java Como Programar', 'Disponível', 12, 'Campus II'),
(32, 1, 'HORSTMANN, C. S., Core Java – Fundamentals', 'Disponível', 13, 'Campus II'),
(33, 1, 'SPEEGLE, G. D. JDBC : Practical Guide for Java Programmers', 'Disponível', 7, 'Campus I'),
(34, 1, 'teste', 'Disponivel', 2, 'Campus III'),
(35, 1, 'teste', 'disponivel', 2, 'Campus II');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbuser`
--

CREATE TABLE IF NOT EXISTS `tbuser` (
  `matriculauser` int(100) NOT NULL AUTO_INCREMENT,
  `loginunico` varchar(100) DEFAULT NULL,
  `nomeuser` varchar(100) NOT NULL,
  `senhauser` varchar(100) NOT NULL,
  `permissaouser` varchar(100) NOT NULL,
  PRIMARY KEY (`matriculauser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `tbuser`
--

INSERT INTO `tbuser` (`matriculauser`, `loginunico`, `nomeuser`, `senhauser`, `permissaouser`) VALUES
(1, 'kelvinsleonardo', 'Kelvin Santiago Leonardo', '123456', 'Administrador'),
(2, 'rodrigoclementino', 'Rodrigo Clementino ', '123456', 'Administrador'),
(3, 'rhuanpablo', 'Rhuan Pablo', '123456', 'Professor'),
(4, 'jeanmatsunaga', 'Jean Matsunaga', '123456', 'Aluno'),
(5, 'josemirdesouza', 'Josemir de Souza Leonardo', '123456', 'Professor');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_usuarios`
--

CREATE TABLE IF NOT EXISTS `tb_usuarios` (
  `Matricula` int(100) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Usuario` varchar(100) NOT NULL,
  `Senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_usuarios`
--

INSERT INTO `tb_usuarios` (`Matricula`, `Nome`, `Usuario`, `Senha`) VALUES
(1, 'Kelvin Santiago Leonardo', 'kelvinsleonardo', '123456'),
(1, 'Kelvin Santiago Leonardo', 'kelvinsleonardo', '123456'),
(1, 'Kelvin Santiago Leonardo', 'kelvinsleonardo', '123456'),
(1, 'Kelvin Santiago Leonardo', 'kelvinsleonardo', '123456'),
(1, 'Kelvin Santiago Leonardo', 'kelvinsleonardo', '123456');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tbdisciplina`
--
ALTER TABLE `tbdisciplina`
  ADD CONSTRAINT `tbdisciplina_ibfk_1` FOREIGN KEY (`codcurso`) REFERENCES `tbcurso` (`codcurso`);

--
-- Limitadores para a tabela `tblivros`
--
ALTER TABLE `tblivros`
  ADD CONSTRAINT `tblivros_ibfk_1` FOREIGN KEY (`coddisciplina`) REFERENCES `tbdisciplina` (`coddisciplina`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
