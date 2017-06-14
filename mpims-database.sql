-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 03, 2011 at 12:25 PM
-- Server version: 5.1.33
-- PHP Version: 5.2.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mpims`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `cat_name`) VALUES
(1, 'Ayurveda'),
(2, 'Allopathy'),
(3, 'Naturopathy'),
(4, 'Homeopathy'),
(5, 'Yoga & Meditation'),
(9, 'Physiotherapy'),
(10, 'Acupressure');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `city_name`) VALUES
(1, 'Kathmandu'),
(2, 'Nepalgunj'),
(3, 'Bhaktapur'),
(11, 'Ilam'),
(5, 'Pokhara'),
(6, 'Butwal'),
(7, 'Biratnagar'),
(8, 'Narayanghat'),
(10, 'Itahari'),
(12, 'Dharan'),
(13, 'Nuwakot'),
(14, 'Dang'),
(15, 'Tikapur'),
(16, 'Fuck you'),
(17, 'Fuck you'),
(18, 'Fuck Land');

-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- Table structure for table `locality`
--

CREATE TABLE IF NOT EXISTS `locality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `locality_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `city`
--

INSERT INTO `locality` (`id`, `locality_name`) VALUES
(1, 'Velachery'),
(2, 'AnnaNagar'),
(3, 'Ashok Nagar');
(4, 'Madipakkam');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_info`
--

CREATE TABLE IF NOT EXISTS `doctor_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `locality` varchar(40) NOT NULL,
  `city` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(200) NOT NULL,
  `web` varchar(20) NOT NULL,
  `cno` varchar(10) NOT NULL,
  `edu` varchar(20) NOT NULL,
  `spec` varchar(20) NOT NULL,
  `hospital` varchar(30) NOT NULL,
  `time` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `doctor_info`
--

INSERT INTO `doctor_info` (`id`, `fname`, `lname`, `address`,`locality`, `city`, `phone`, `email`, `web`, `cno`, `edu`, `spec`, `hospital`, `time`) VALUES
(1, 'Seshaan', 'Thiyagarajay', 'Sadasivam Nagar','Madipakkam', 'Chennai', '9849026533', 'info@bhola.com', 'bhola.com', '2', 'MD', 'Gynecology', 'Apollo Hospital', '6-7am');

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE IF NOT EXISTS `hospital` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hospital_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`id`, `hospital_name`) VALUES
(1, 'Apollo Hospitals'),
(2, 'Sri Ramachandra'),
(3, 'Saraswathi Hospitals'),
(5, 'Dr.Kamakshi Memorial Hospital'),
(6, 'Achariya Dentals'),
(7, 'Dentist Tree'),
(8, 'Mother and Child Hospital'),
(9, 'Thai Clinic'),
(10, 'Child Care hospitals');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `user_name`, `password`) VALUES
(5, 'admin', 'admin'),
(8, 'sivapratha', 'sivapratha'),
(16, 'test', 'test'),
(18, 'rajesh', 'rajesh'),
(19, 'seshaan', 'seshaan'),
(20, 'Thamarai', 'Thamarai');

-- --------------------------------------------------------

--
-- Table structure for table `specialization`
--

CREATE TABLE IF NOT EXISTS `specialization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specialization_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `specialization`
--

INSERT INTO `specialization` (`id`, `specialization_name`) VALUES
(1, 'Immunology	'),
(2, 'Cardiology'),
(3, 'Dermatology'),
(4, 'Gastroenterology'),
(5, 'General Surgery'),
(6, 'Internal Medicine'),
(7, 'Neurology'),
(8, 'Obstetrics & Gynecology'),
(9, 'Oncology'),
(10, 'Ophthalmology'),
(11, 'Orthopaedic'),
(12, 'Pediatrics'),
(13, '	Psychiatry'),
(14, 'Hard Core Fuck');
