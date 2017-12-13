-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost
-- Généré le :  Mer 11 Octobre 2017 à 19:58
-- Version du serveur :  10.1.18-MariaDB
-- Version de PHP :  7.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `projetihm`
--

-- --------------------------------------------------------

--
-- Structure de la table `Categorie`
--

CREATE TABLE `Categorie` (
  `id` mediumint(8) UNSIGNED NOT NULL,
  `sigle` char(3) NOT NULL,
  `texte` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Categorie`
--

INSERT INTO `Categorie` (`id`, `sigle`, `texte`) VALUES
(1, '1LS', 'un lit simple'),
(2, '1LD', 'un lit double'),
(3, '2LS', 'deux lits simples');

-- --------------------------------------------------------

--
-- Structure de la table `Client`
--

CREATE TABLE `Client` (
  `id` mediumint(8) UNSIGNED NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Client`
--

INSERT INTO `Client` (`id`, `prenom`, `nom`) VALUES
(1, 'Marine', 'Carpentier'),
(2, 'Aaron', 'Dumas'),
(3, 'Kimberley', 'Leroux'),
(4, 'Florentin', 'Giraud'),
(5, 'Martin', 'Gillet'),
(6, 'Alexandre', 'Bertrand'),
(7, 'Lutécia', 'Baron'),
(8, 'Romain', 'Girard'),
(9, 'Clément', 'Mallet'),
(10, 'Yüna', 'Lemoine'),
(11, 'Léonie', 'Germain'),
(12, 'Sarah', 'Sanchez'),
(13, 'Sarah', 'Gonzalez'),
(14, 'Capucine', 'Roche'),
(15, 'Romane', 'Duval'),
(16, 'Léon', 'David'),
(17, 'Constant', 'Collet'),
(18, 'Arthur', 'Rodriguez'),
(19, 'Eva', 'Gomez'),
(20, 'Océane', 'Henry'),
(21, 'Florentin', 'Huet'),
(22, 'Zoé', 'Barbier'),
(23, 'Maïlé', 'Robin'),
(24, 'Lamia', 'Julien'),
(25, 'Valentine', 'Humbert'),
(26, 'Davy', 'Huet'),
(27, 'Florian', 'Faure'),
(28, 'Ambre', 'Paul'),
(29, 'Mathilde', 'Bertrand'),
(30, 'Timothée', 'Perez'),
(31, 'Yüna', 'Moreau'),
(32, 'Émile', 'Carpentier'),
(33, 'Macéo', 'Rodriguez'),
(34, 'Solene', 'Blanc'),
(35, 'Yasmine', 'Fabre'),
(36, 'Zacharis', 'Perrot'),
(37, 'Yasmine', 'Petit'),
(38, 'Lola', 'Simon'),
(39, 'Martin', 'Royer'),
(40, 'Adam', 'Martin'),
(41, 'Félix', 'Aubert'),
(42, 'Félix', 'Collet'),
(43, 'Lou', 'Leroux'),
(44, 'Zacharis', 'Philippe'),
(45, 'Valentin', 'Humbert'),
(46, 'Yüna', 'Dupont'),
(47, 'Chaïma', 'Rodriguez'),
(48, 'Enzo', 'Deschamps'),
(49, 'Ambre', 'Laurent'),
(50, 'Noah', 'Gomez'),
(51, 'Yanis', 'Aubert'),
(52, 'Kyllian', 'Lopez'),
(53, 'Florentin', 'Bailly'),
(54, 'Rose', 'Charpentier'),
(55, 'Ambre', 'Jacob'),
(56, 'Kevin', 'Albert'),
(57, 'Gaspard', 'Gaillard'),
(58, 'Mélanie', 'Garcia'),
(59, 'Juliette', 'Le gall'),
(60, 'Capucine', 'Collin'),
(61, 'Loevan', 'Lefevre'),
(62, 'Valentin', 'Adam'),
(63, 'Rosalie', 'Joly'),
(64, 'Mehdi', 'Roux'),
(65, 'Sarah', 'Meunier'),
(66, 'Clara', 'Guerin'),
(67, 'Loevan', 'Gauthier'),
(68, 'Amélie', 'Guyot'),
(69, 'Jordan', 'Laine'),
(70, 'Romane', 'Jacquet'),
(71, 'Martin', 'Fabre'),
(72, 'Fanny', 'Lefevre'),
(73, 'Thibault', 'Charpentier'),
(74, 'Lutécia', 'Simon'),
(75, 'Guillemette', 'Petit'),
(76, 'Eva', 'Robert'),
(77, 'Mathis', 'Schneider'),
(78, 'Maïwenn', 'Duval'),
(79, 'Samuel', 'Lefebvre'),
(80, 'Sara', 'Berger'),
(81, 'Mélanie', 'Lacroix'),
(82, 'Élouan', 'Bertrand'),
(83, 'Nathan', 'Marchand'),
(84, 'Jordan', 'Bernard'),
(85, 'Alicia', 'Fleury'),
(86, 'Anna', 'Martin'),
(87, 'Léonie', 'David'),
(88, 'Alexia', 'Poirier'),
(89, 'Macéo', 'Weber'),
(90, 'Maïlé', 'Barbier'),
(91, 'Chloé', 'Sanchez'),
(92, 'Noémie', 'Lemoine'),
(93, 'Pierre', 'Boulanger'),
(94, 'Alexandra', 'Roche'),
(95, 'Chloé', 'Mercier'),
(96, 'Catherine', 'David'),
(97, 'Dylan', 'Dupuis'),
(98, 'Rémi', 'Michel'),
(99, 'Julien', 'Duval'),
(100, 'Maïlé', 'Petit');

-- --------------------------------------------------------

--
-- Structure de la table `Reservation`
--

CREATE TABLE `Reservation` (
  `id` mediumint(8) UNSIGNED NOT NULL,
  `reference` char(14) NOT NULL,
  `debut` date NOT NULL,
  `nuits` tinyint(4) UNSIGNED NOT NULL,
  `categorie` mediumint(8) UNSIGNED NOT NULL,
  `client` mediumint(8) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Reservation`
--

INSERT INTO `Reservation` (`id`, `reference`, `debut`, `nuits`, `categorie`, `client`) VALUES
(1, '6361-7185-KWJU', '2018-01-07', 2, 1, 45),
(2, '1188-8858-RBYG', '2018-01-03', 2, 1, 46),
(3, '6330-2364-BVYV', '2018-01-07', 2, 1, 54),
(4, '2982-2235-ZRDD', '2018-01-07', 1, 1, 100),
(5, '8154-0408-SHYP', '2018-01-03', 4, 3, 84),
(6, '0671-2551-PGJX', '2018-01-05', 2, 3, 69),
(7, '5660-8953-YKJO', '2018-01-06', 2, 2, 5),
(8, '5948-1995-LBUI', '2018-01-06', 1, 1, 42),
(9, '7564-7865-DWFP', '2018-01-07', 4, 1, 19),
(10, '1351-0775-BETZ', '2018-01-05', 2, 2, 2),
(11, '8137-0881-LCTI', '2018-01-06', 1, 3, 16),
(12, '0269-4371-BMPC', '2018-01-01', 1, 3, 51),
(13, '6497-5059-KIQO', '2018-01-02', 1, 2, 98),
(14, '8626-5865-UWBI', '2018-01-02', 1, 2, 97),
(15, '5279-5162-MQNP', '2018-01-06', 2, 3, 8),
(16, '6044-6749-FHNZ', '2018-01-07', 3, 2, 93),
(17, '1465-5542-BAVO', '2018-01-07', 3, 2, 73),
(18, '7780-7814-ARJO', '2018-01-07', 2, 2, 87),
(19, '6258-1633-JJLL', '2018-01-03', 3, 1, 50),
(20, '8907-4647-WYUX', '2018-01-07', 4, 1, 69),
(21, '6995-8108-AWGD', '2018-01-04', 2, 1, 48),
(22, '5289-7752-KRRU', '2018-01-03', 5, 2, 63),
(23, '7744-7722-TLIQ', '2018-01-02', 3, 1, 90),
(24, '9527-4557-XTLK', '2018-01-03', 1, 1, 80),
(25, '8450-6757-CASS', '2018-01-04', 4, 3, 91),
(26, '9555-4267-UUTY', '2018-01-05', 1, 1, 44),
(27, '3440-0631-NFCU', '2018-01-06', 2, 3, 2),
(28, '3011-6344-SNKG', '2018-01-04', 2, 1, 95),
(29, '1429-5038-KSAB', '2018-01-07', 2, 1, 71),
(30, '0794-3944-AFGF', '2018-01-04', 1, 2, 37),
(31, '5501-8409-DTIC', '2018-01-06', 1, 2, 77),
(32, '0095-0662-ULUW', '2018-01-04', 2, 3, 89),
(33, '6298-8407-CUFZ', '2018-01-04', 3, 3, 9),
(34, '5213-3440-XJDJ', '2018-01-03', 3, 1, 42),
(35, '4638-9736-RYKC', '2018-01-03', 1, 2, 83),
(36, '3865-1538-PZMI', '2018-01-05', 2, 1, 78),
(37, '3533-7081-JUYU', '2018-01-01', 1, 3, 98),
(38, '0664-8855-JQQN', '2018-01-07', 2, 1, 96),
(39, '0489-6520-NJZF', '2018-01-04', 2, 3, 13),
(40, '0693-5922-NPHM', '2018-01-02', 1, 3, 28),
(41, '7201-0279-IEGP', '2018-01-01', 1, 1, 73),
(42, '3768-1297-OATH', '2018-01-05', 2, 1, 50),
(43, '6625-0562-HOIJ', '2018-01-06', 1, 3, 50),
(44, '2604-1025-NXXQ', '2018-01-03', 2, 1, 100),
(45, '4534-7541-ZXFI', '2018-01-04', 1, 1, 41),
(46, '2523-7957-UDAD', '2018-01-03', 4, 1, 79),
(47, '8875-1797-YSKS', '2018-01-02', 1, 3, 38),
(48, '6499-3593-YZJM', '2018-01-04', 1, 2, 85),
(49, '8868-7021-LOTM', '2018-01-01', 2, 2, 70),
(50, '5468-4123-TRDT', '2018-01-07', 4, 3, 81),
(51, '5809-0206-SVIE', '2018-01-03', 1, 1, 83),
(52, '9166-7813-QSGX', '2018-01-05', 2, 2, 44),
(53, '5483-3504-GJHY', '2018-01-03', 1, 1, 65),
(54, '8438-3677-NVVJ', '2018-01-05', 5, 2, 27),
(55, '2846-1077-MJZD', '2018-01-02', 2, 2, 30),
(56, '4506-0649-VVYV', '2018-01-01', 2, 1, 81),
(57, '8970-3124-MACX', '2018-01-06', 1, 1, 91),
(58, '9192-9340-YDTB', '2018-01-07', 1, 3, 62),
(59, '9347-2446-JOXS', '2018-01-04', 2, 3, 97),
(60, '2338-6577-OJAB', '2018-01-05', 3, 2, 22),
(61, '7570-6461-VEGI', '2018-01-04', 1, 1, 9),
(62, '6639-9728-AYLX', '2018-01-04', 1, 3, 65),
(63, '7481-8092-IKFL', '2018-01-01', 1, 1, 52),
(64, '3095-1882-VKLZ', '2018-01-04', 2, 2, 31),
(65, '9763-5716-PHKX', '2018-01-03', 2, 2, 99),
(66, '3017-3297-XJPU', '2018-01-02', 4, 3, 27),
(67, '8747-6964-BNWM', '2018-01-01', 2, 3, 16),
(68, '9720-8524-XGGL', '2018-01-05', 1, 3, 55),
(69, '3250-0746-OAMJ', '2018-01-02', 2, 2, 87),
(70, '9155-7546-JGIV', '2018-01-03', 5, 1, 74),
(71, '6092-6343-EQGD', '2018-01-05', 4, 1, 81),
(72, '9389-3067-WHTD', '2018-01-01', 2, 1, 79),
(73, '6268-7022-BSAT', '2018-01-02', 3, 1, 41),
(74, '2080-1531-REMJ', '2018-01-04', 2, 3, 53),
(75, '4751-3708-LRFM', '2018-01-05', 1, 2, 1),
(76, '0288-7830-LWWI', '2018-01-01', 5, 1, 10),
(77, '6259-4422-GZJT', '2018-01-07', 4, 3, 36),
(78, '0975-3172-CBMH', '2018-01-01', 3, 1, 17),
(79, '6210-5234-LPMR', '2018-01-04', 3, 3, 77),
(80, '1301-1876-BZFQ', '2018-01-06', 3, 2, 56),
(81, '7889-1610-YWLB', '2018-01-03', 1, 1, 75),
(82, '1679-4254-VFPT', '2018-01-03', 1, 2, 64),
(83, '8348-4626-OCFV', '2018-01-04', 3, 1, 61),
(84, '9810-3412-UHWM', '2018-01-06', 1, 1, 53),
(85, '3626-9732-YFFX', '2018-01-05', 3, 2, 27),
(86, '2093-9338-CGWE', '2018-01-04', 3, 2, 14),
(87, '7558-2680-LXYI', '2018-01-07', 3, 2, 12),
(88, '2436-3909-NXLL', '2018-01-07', 1, 1, 1),
(89, '3198-8878-VSOB', '2018-01-04', 2, 1, 97),
(90, '6405-5922-ISVZ', '2018-01-07', 3, 2, 93),
(91, '2095-3045-OWLF', '2018-01-05', 1, 1, 62),
(92, '6715-5185-CQMF', '2018-01-07', 5, 1, 66),
(93, '2489-5369-LYHR', '2018-01-03', 1, 2, 59),
(94, '8910-3185-OTBU', '2018-01-05', 1, 3, 11),
(95, '3355-8122-LKXA', '2018-01-03', 2, 2, 98),
(96, '1499-2254-DBIU', '2018-01-04', 2, 1, 2),
(97, '0655-7347-DMOM', '2018-01-06', 3, 3, 53),
(98, '5535-0654-FVGB', '2018-01-05', 3, 1, 68),
(99, '3749-8244-YMCQ', '2018-01-01', 1, 2, 98),
(100, '8104-3859-TREN', '2018-01-06', 5, 1, 79);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Categorie`
--
ALTER TABLE `Categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ClientIndex` (`client`),
  ADD KEY `CategoryIndex` (`categorie`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Categorie`
--
ALTER TABLE `Categorie`
  MODIFY `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `Client`
--
ALTER TABLE `Client`
  MODIFY `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT pour la table `Reservation`
--
ALTER TABLE `Reservation`
  MODIFY `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Reservation`
--
ALTER TABLE `Reservation`
  ADD CONSTRAINT `MapToCategorie` FOREIGN KEY (`categorie`) REFERENCES `Categorie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `MapToClient` FOREIGN KEY (`client`) REFERENCES `Client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
