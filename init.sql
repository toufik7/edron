CREATE DATABASE IF NOT EXISTS string_generator;
CREATE DATABASE IF NOT EXISTS test;
USE string_generator;

-- --------------------------------------------------------

--
-- Structure de la table `letters`
--

CREATE TABLE `letters` (
  `id` bigint(20) NOT NULL,
  `letter` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `letters`
--

INSERT INTO `letters` (`id`, `letter`) VALUES
(1, 'a'),
(2, 'b'),
(3, 'c'),
(4, 'd'),
(5, 'e'),
(6, 'f'),
(7, 'g'),
(8, 'h'),
(9, 'i'),
(10, 'j'),
(11, 'k'),
(12, 'l'),
(13, 'm'),
(14, 'n'),
(15, 'o'),
(16, 'p'),
(17, 'q'),
(18, 'r'),
(19, 's'),
(20, 't'),
(21, 'u'),
(22, 'v'),
(23, 'w'),
(24, 'x'),
(25, 'y'),
(26, 'z');

-- --------------------------------------------------------

--
-- Structure de la table `letters_strings`
--

CREATE TABLE `letters_strings` (
  `strings_generated_params_id` bigint(20) NOT NULL,
  `letter_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `strings_generated_params`
--

CREATE TABLE `strings_generated_params` (
  `id` bigint(20) NOT NULL,
  `max` int(11) NOT NULL,
  `min` int(11) NOT NULL,
  `str_number` int(11) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `tasks`
--

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL,
  `end` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `millisec` bigint(20) NOT NULL,
  `min` bigint(20) NOT NULL,
  `sec` bigint(20) NOT NULL,
  `start` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tasks`
--

INSERT INTO `tasks` (`id`, `end`, `file_name`, `millisec`, `min`, `sec`, `start`, `state`) VALUES
(1, '29/10/2022 01:11:27', '1667002249106_task_A.txt', 298, 0, 38, '29/10/2022 01:10:48', 'COMPLETED'),
(2, '29/10/2022 01:11:11', '1667002267979_task_Z.txt', 74, 0, 3, '29/10/2022 01:11:07', 'COMPLETED');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `letters`
--
ALTER TABLE `letters`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `letters_strings`
--
ALTER TABLE `letters_strings`
  ADD PRIMARY KEY (`strings_generated_params_id`,`letter_id`),
  ADD KEY `FK7wd1uboekua17if68nsca8uog` (`letter_id`);

--
-- Index pour la table `strings_generated_params`
--
ALTER TABLE `strings_generated_params`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `letters`
--
ALTER TABLE `letters`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `strings_generated_params`
--
ALTER TABLE `strings_generated_params`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `letters_strings`
--
ALTER TABLE `letters_strings`
  ADD CONSTRAINT `FK7wd1uboekua17if68nsca8uog` FOREIGN KEY (`letter_id`) REFERENCES `letters` (`id`),
  ADD CONSTRAINT `FKo0dbtcmqdagvletgv78sf2c5w` FOREIGN KEY (`strings_generated_params_id`) REFERENCES `strings_generated_params` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
