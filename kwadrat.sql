-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Lis 2019, 13:52
-- Wersja serwera: 10.4.6-MariaDB
-- Wersja PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `kwadrat`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `bathroom_equipment`
--

CREATE TABLE `bathroom_equipment` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `equipment_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `equipment_description` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `bathroom_equipment`
--

INSERT INTO `bathroom_equipment` (`id`, `equipment_name`, `equipment_description`) VALUES
(1, 'pralka', ''),
(2, 'prysznic', ''),
(3, 'wanna', ''),
(4, 'zestaw ręczników', ''),
(5, 'suszarka do ubrań', ''),
(6, 'suszarka do włosów', ''),
(7, 'lustro', ''),
(8, 'wiadro z mopem', ''),
(9, 'detergenty', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `bathroom_has`
--

CREATE TABLE `bathroom_has` (
  `id` int(11) UNSIGNED NOT NULL,
  `flat_id` int(11) UNSIGNED NOT NULL,
  `equipment_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cities`
--

CREATE TABLE `cities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `city_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `cities`
--

INSERT INTO `cities` (`id`, `city_name`) VALUES
(1, 'Warszawa'),
(2, 'Kraków'),
(3, 'Łódź'),
(4, 'Wrocław'),
(5, 'Poznań'),
(6, 'Gdańsk'),
(7, 'Szczecin'),
(8, 'Bydgoszcz'),
(9, 'Lublin'),
(10, 'Białystok'),
(11, 'Katowice'),
(12, 'Gdynia'),
(13, 'Częstochowa'),
(14, 'Radom'),
(15, 'Sosnowiec'),
(16, 'Toruń'),
(17, 'Kielce'),
(18, 'Rzeszów'),
(19, 'Gliwice'),
(20, 'Zabrze'),
(21, 'Olsztyn'),
(22, 'Bielsko-Biała'),
(23, 'Bytom'),
(24, 'Zielona Góra'),
(25, 'Rybnik'),
(26, 'Ruda Śląska'),
(27, 'Opole'),
(28, 'Tychy'),
(29, 'Gorzów Wielkopolski'),
(30, 'Dąbrowa Górnicza'),
(31, 'Elbląg'),
(32, 'Płock'),
(33, 'Wałbrzych'),
(34, 'Włocławek'),
(35, 'Tarnów'),
(36, 'Chorzów'),
(37, 'Koszalin'),
(38, 'Kalisz'),
(39, 'Legnica'),
(40, 'Grudziądz'),
(41, 'Jaworzno'),
(42, 'Słupsk'),
(43, 'Jastrzębie-Zdrój'),
(44, 'Nowy Sącz'),
(45, 'Jelenia Góra'),
(46, 'Siedlce'),
(47, 'Mysłowice'),
(48, 'Konin'),
(49, 'Piotrków Trybunalski'),
(50, 'Piła'),
(51, 'Inowrocław'),
(52, 'Lubin'),
(53, 'Ostrów Wielkopolski'),
(54, 'Suwałki'),
(55, 'Ostrowiec Świętokrzyski'),
(56, 'Gniezno'),
(57, 'Stargard'),
(58, 'Głogów'),
(59, 'Siemianowice Śląskie'),
(60, 'Pabianice'),
(61, 'Zamość'),
(62, 'Leszno'),
(63, 'Łomża'),
(64, 'Chełm'),
(65, 'Tomaszów Mazowiecki'),
(66, 'Żory'),
(67, 'Ełk'),
(68, 'Stalowa Wola'),
(69, 'Pruszków'),
(70, 'Przemyśl'),
(71, 'Kędzierzyn-Koźle'),
(72, 'Tarnowskie Góry'),
(73, 'Mielec'),
(74, 'Tczew'),
(75, 'Bełchatów'),
(76, 'Biała Podlaska'),
(77, 'Świdnica'),
(78, 'Będzin'),
(79, 'Zgierz'),
(80, 'Piekary Śląskie'),
(81, 'Racibórz'),
(82, 'Legionowo'),
(83, 'Ostrołęka'),
(84, 'Świętochłowice'),
(85, 'Wejherowo'),
(86, 'Zawiercie');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `distances`
--

CREATE TABLE `distances` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `flat_id` bigint(20) UNSIGNED NOT NULL,
  `place_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `place_description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `distance` double(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `districts`
--

CREATE TABLE `districts` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `district_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `city_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `districts`
--

INSERT INTO `districts` (`id`, `district_name`, `city_id`) VALUES
(1, 'Bemowo', '1'),
(2, 'Białołęka', '1'),
(3, 'Bielany', '1'),
(4, 'Mokotów', '1'),
(5, 'Ochota', '1'),
(6, 'Praga-Południe', '1'),
(7, 'Praga-Północ', '1'),
(8, 'Rembertów', '1'),
(9, 'Śródmieście', '1'),
(10, 'Targówek', '1'),
(11, 'Ursus', '1'),
(12, 'Ursynów', '1'),
(13, 'Wawer', '1'),
(14, 'Wesoła', '1'),
(15, 'Wilanów', '1'),
(16, 'Włochy', '1'),
(17, 'Wola', '1'),
(18, 'Żoliborz', '1'),
(19, 'I Stare Miasto', '2'),
(20, 'II Grzegórzki', '2'),
(21, 'III Prądnik Czerwony', '2'),
(22, 'IV Prądnik Biały', '2'),
(23, 'V Krowodrza', '2'),
(24, 'VI Bronowice', '2'),
(25, 'VII Zwierzyniec', '2'),
(26, 'VIII Dębniki', '2'),
(27, 'IX Łagiewniki-Borek Fałęcki', '2'),
(28, 'X Swoszowice', '2'),
(29, 'XI Podgórze Duchackie', '2'),
(30, 'XII Bieżanów-Prokocim', '2'),
(31, 'XIII Podgórze', '2'),
(32, 'XIV Czyżyny', '2'),
(33, 'XV Mistrzejowice', '2'),
(34, 'XVI Bieńczyce', '2'),
(35, 'XVII Wzgórza Krzesławickie', '2'),
(36, 'XVIII Nowa Huta', '2'),
(37, 'Bałuty', '3'),
(38, 'Górna', '3'),
(39, 'Polesie', '3'),
(40, 'Śródmieście', '3'),
(41, 'Widzew', '3'),
(42, 'Stare Miasto', '4'),
(43, 'Śródmieście', '4'),
(44, 'Psie Pole', '4'),
(45, 'Przedmieście Oławskie', '4'),
(46, 'Krzyki', '4'),
(47, 'Fabryczna', '4'),
(48, 'Grunwald', '5'),
(49, 'Jeżyce', '5'),
(50, 'Nowe Miasto', '5'),
(51, 'Piątkowo', '5'),
(52, 'Rataje', '5'),
(53, 'Stare Miasto', '5'),
(54, 'Wilda', '5'),
(55, 'Aniołki', '6'),
(56, 'Brętowo', '6'),
(57, 'Brzeźno', '6'),
(58, 'Chełm', '6'),
(59, 'Jasień', '6'),
(60, 'Kokoszki', '6'),
(61, 'Krakowiec - Górki Zachodnie', '6'),
(62, 'Letnica', '6'),
(63, 'Matarnia', '6'),
(64, 'Młyniska', '6'),
(65, 'Nowy Port', '6'),
(66, 'Oliwa', '6'),
(67, 'Olszynka', '6'),
(68, 'Orunia Górna - Gdańsk Południe', '6'),
(69, 'Orunia - Św. Wojciech - Lipce', '6'),
(70, 'Osowa', '6'),
(71, 'Piecki - Migowo', '6'),
(72, 'Przeróbka', '6'),
(73, 'Przymorze Małe', '6'),
(74, 'Przymorze Wielkie', '6'),
(75, 'Rudniki', '6'),
(76, 'Siedlce', '6'),
(77, 'Stogi', '6'),
(78, 'Strzyża', '6'),
(79, 'Suchanino', '6'),
(80, 'Środmieście', '6'),
(81, 'Ujeścisko - Łostowice', '6'),
(82, 'VII Dwór', '6'),
(83, 'Wrzeszcz Dolny', '6'),
(84, 'Wrzeszcz Górny', '6'),
(85, 'Wyspa Sobieszewska', '6'),
(86, 'Wzgórze Mickiewicza', '6'),
(87, 'Zaspa - Młyniec', '6'),
(88, 'Zaspa - Rozstaje', '6'),
(89, 'Żabianka - Wejhera - Jelitkowo - Tysiąclecia', '6'),
(90, 'Śródmieście', '7'),
(91, 'Zachód', '7'),
(92, 'Północ', '7'),
(93, 'Prawobrzeże', '7'),
(94, 'Bartodzieje', '8'),
(95, 'Bielawy', '8'),
(96, 'Błonie', '8'),
(97, 'Bocianowo-Śródmieście-Stare Miasto', '8'),
(98, 'Brdyujście', '8'),
(99, 'Bydgoszcz Wschód-Siernieczek', '8'),
(100, 'Czyżkówko', '8'),
(101, 'Flisy', '8'),
(102, 'Glinki-Rupienica', '8'),
(103, 'Górzyskowo', '8'),
(104, 'Jachcice', '8'),
(105, 'Kapuściska', '8'),
(106, 'Leśne', '8'),
(107, 'Łęgnowo', '8'),
(108, 'Łęgnowo Wieś', '8'),
(109, 'Miedzyń–Prądy', '8'),
(110, 'Nowy Fordon', '8'),
(111, 'Okole', '8'),
(112, 'Osowa Góra', '8'),
(113, 'Piaski', '8'),
(114, 'Smukała-Opławiec-Janowo', '8'),
(115, 'Stary Fordon', '8'),
(116, 'Szwederowo', '8'),
(117, 'Tatrzańskie', '8'),
(118, 'Terenów Nadwiślańskich', '8'),
(119, 'Wilczak-Jary', '8'),
(120, 'Wyżyny', '8'),
(121, 'Wzgórze Wolności', '8'),
(122, 'Zimne Wody–Czersko Polskie', '8'),
(123, 'Abramowice', '9'),
(124, 'Bronowice', '9'),
(125, 'Czechów Południowy', '9'),
(126, 'Czechów Północny', '9'),
(127, 'Czuby Południowe', '9'),
(128, 'Czuby Północne', '9'),
(129, 'Dziesiąta', '9'),
(130, 'Felin', '9'),
(131, 'Głusk', '9'),
(132, 'Hajdów-Zadębie', '9'),
(133, 'Kalinowszczyzna', '9'),
(134, 'Konstantynów', '9'),
(135, 'Kośminek', '9'),
(136, 'Ponikwoda', '9'),
(137, 'Rury', '9'),
(138, 'Sławin', '9'),
(139, 'Sławinek', '9'),
(140, 'Stare Miasto', '9'),
(141, 'Szerokie', '9'),
(142, 'Śródmieście', '9'),
(143, 'Tatary', '9'),
(144, 'Węglin Południowy', '9'),
(145, 'Węglin Północny', '9'),
(146, 'Wieniawa', '9'),
(147, 'Wrotków', '9'),
(148, 'Za Cukrownią', '9'),
(149, 'Zemborzyce', '9'),
(150, 'Centrum', '10'),
(151, 'Białostoczek', '10'),
(152, 'Sienkiewicza', '10'),
(153, 'Bojary', '10'),
(154, 'Piaski', '10'),
(155, 'Przydworcowe', '10'),
(156, 'Młodych', '10'),
(157, 'Antoniuk', '10'),
(158, 'Jaroszówka', '10'),
(159, 'Wygoda', '10'),
(160, 'Piasta I', '10'),
(161, 'Piasta II', '10'),
(162, 'Skorupy', '10'),
(163, 'Mickiewicza', '10'),
(164, 'Dojlidy', '10'),
(165, 'Bema', '10'),
(166, 'Kawaleryjskie', '10'),
(167, 'Nowe Miasto', '10'),
(168, 'Zielone Wzgórza', '10'),
(169, 'Starosielce', '10'),
(170, 'Słoneczny Stok', '10'),
(171, 'Leśna Dolina', '10'),
(172, 'Wysoki Stoczek', '10'),
(173, 'Dziesięciny I', '10'),
(174, 'Dziesięciny II', '10'),
(175, 'Bacieczki', '10'),
(176, 'Zawady', '10'),
(177, 'Dojlidy Górne', '10'),
(178, 'Śródmieście', '11'),
(179, 'Os. Paderewskiego – Muchowiec', '11'),
(180, 'Koszutka', '11'),
(181, 'Bogucice', '11'),
(182, 'Załęże', '11'),
(183, 'Os. Witosa', '11'),
(184, 'Osiedle Tysiąclecia', '11'),
(185, 'Dąb', '11'),
(186, 'Wełnowiec-Józefowiec', '11'),
(187, 'Załęska Hałda-Brynów', '11'),
(188, 'Brynów-Osiedle Zgrzebnioka', '11'),
(189, 'Ligota-Panewniki', '11'),
(190, 'Zawodzie', '11'),
(191, 'Dąbrówka Mała', '11'),
(192, 'Szopienice-Burowiec', '11'),
(193, 'Janów-Nikiszowiec', '11'),
(194, 'Giszowiec', '11'),
(195, 'Murcki', '11'),
(196, 'Piotrowice-Ochojec', '11'),
(197, 'Zarzecze', '11'),
(198, 'Kostuchna', '11'),
(199, 'Podlesie', '11'),
(200, 'Babie Doły', '12'),
(201, 'Chwarzno-Wiczlino', '12'),
(202, 'Chylonia', '12'),
(203, 'Cisowa', '12'),
(204, 'Działki Leśne', '12'),
(205, 'Dąbrowa', '12'),
(206, 'Grabówek', '12'),
(207, 'Kamienna Góra', '12'),
(208, 'Karwiny', '12'),
(209, 'Leszczynki', '12'),
(210, 'Mały Kack', '12'),
(211, 'Obłuże', '12'),
(212, 'Oksywie', '12'),
(213, 'Orłowo', '12'),
(214, 'Pogórze', '12'),
(215, 'Pustki Cisowskie-Demptowo', '12'),
(216, 'Redłowo', '12'),
(217, 'Śródmieście', '12'),
(218, 'Wielki Kack', '12'),
(219, 'Witomino-Radiostacja', '12'),
(220, 'Witomino-Leśniczówka', '12'),
(221, 'Wzgórze św. Maksymiliana', '12'),
(222, 'Błeszno', '13'),
(223, 'Częstochówka-Parkitka', '13'),
(224, 'Dźbów', '13'),
(225, 'Gnaszyn-Kawodrza', '13'),
(226, 'Grabówka', '13'),
(227, 'Kiedrzyn', '13'),
(228, 'Lisiniec', '13'),
(229, 'Mirów', '13'),
(230, 'Ostatni Grosz', '13'),
(231, 'Podjasnogórska', '13'),
(232, 'Północ', '13'),
(233, 'Raków', '13'),
(234, 'Stare Miasto', '13'),
(235, 'Stradom', '13'),
(236, 'Śródmieście', '13'),
(237, 'Trzech Wieszczów', '13'),
(238, 'Tysiąclecie', '13'),
(239, 'Wrzosowiak', '13'),
(240, 'Wyczerpy-Aniołów', '13'),
(241, 'Zawodzie-Dąbie', '13'),
(242, 'osiedle Akademickie', '14'),
(243, 'Borki', '14'),
(244, 'Brzustówka', '14'),
(245, 'Długojów', '14'),
(246, 'Długojów Górny', '14'),
(247, 'Dzierzków', '14'),
(248, 'Firlej', '14'),
(249, 'Glinice', '14'),
(250, 'Godów', '14'),
(251, 'Gołębiów', '14'),
(252, 'Gołębiów I', '14'),
(253, 'Gołębiów II', '14'),
(254, 'Halinów', '14'),
(255, 'Huta Józefowska', '14'),
(256, 'Idalin', '14'),
(257, 'Janiszpol', '14'),
(258, 'Jeżowa Wola', '14'),
(259, 'Józefów', '14'),
(260, 'Kaptur', '14'),
(261, 'Kierzków', '14'),
(262, 'Koniówka', '14'),
(263, 'Kończyce', '14'),
(264, 'Kozia Góra', '14'),
(265, 'Krychnowice', '14'),
(266, 'Krzewień', '14'),
(267, 'Malczew', '14'),
(268, 'Malenice', '14'),
(269, 'Miasto Kazimierzowskie', '14'),
(270, 'Michałów', '14'),
(271, 'Mleczna', '14'),
(272, 'Młodzianów', '14'),
(273, 'Młynek Janiszewski', '14'),
(274, 'osiedle Nad Potokiem', '14'),
(275, 'Nowa Wola Gołębiowska', '14'),
(276, 'Nowiny Malczewskie', '14'),
(277, 'Obozisko', '14'),
(278, 'Planty', '14'),
(279, 'Południe', '14'),
(280, 'Potkanów', '14'),
(281, 'Prędocinek', '14'),
(282, 'Pruszaków', '14'),
(283, 'Rajec Poduchowny', '14'),
(284, 'Rajec Szlachecki', '14'),
(285, 'Sadków', '14'),
(286, 'Stara Wola Gołębiowska', '14'),
(287, 'Stare Miasto', '14'),
(288, 'Śródmieście', '14'),
(289, 'Ustronie', '14'),
(290, 'Wacyn', '14'),
(291, 'Wielogóra', '14'),
(292, 'Wincentów', '14'),
(293, 'Wośniki', '14'),
(294, 'Wólka Klwatecka', '14'),
(295, 'osiedle XV-lecia', '14'),
(296, 'Zamłynie', '14'),
(297, 'Żakowice', '14'),
(298, 'Środula', '15'),
(299, 'Pogoń', '15'),
(300, 'Niwka', '15'),
(301, 'Klimontów', '15'),
(302, 'Milowice', '15'),
(303, 'Toruniak', '16'),
(304, 'Jordanki', '16'),
(305, 'Osiedle JAR', '16'),
(306, 'Osiedle Kochanowskiego', '16'),
(307, 'Działki św. Józefa', '16'),
(308, 'Białogon', '17'),
(309, 'Niewachlów I', '17'),
(310, 'Ostra Górka', '17'),
(311, 'Skrzetle', '17'),
(312, 'Niewachlów II', '17'),
(313, 'Zalesie', '18'),
(314, 'Pobitno', '18'),
(315, 'Drabinianka', '18'),
(316, 'Staromieście', '18'),
(317, 'Staroniwa', '18'),
(318, 'Sośnica', '19'),
(319, 'Wilcze Gardło', '19'),
(320, 'Stare Łabędy', '19'),
(321, 'Przyszówka', '19'),
(322, 'Zabrze', '20'),
(323, 'Stare Zabrze', '20'),
(324, 'Os. Młodego Górnika', '20'),
(325, 'Małe Zabrze', '20'),
(326, 'Kortowo', '21'),
(327, 'Pieczewo', '21'),
(328, 'Wapienica', '22'),
(329, 'Hałcnów', '22'),
(330, 'Rozbark', '23'),
(331, 'Górniki', '23'),
(332, 'Osiedle Zdrojowe', '24'),
(333, 'Chynów', '24'),
(334, 'Boguszowice', '25'),
(335, 'Piaski', '25'),
(336, 'Zaodrze', '27'),
(337, 'Stare Miasto', '27'),
(338, 'Koźlina', '28'),
(339, 'Śródmieście', '28'),
(340, 'Zamoście', '29'),
(341, 'Karnin', '29'),
(342, 'Gołonóg', '30'),
(343, 'Trzebiesławice', '30'),
(344, 'Stare Miasto', '31'),
(345, 'Gronowo', '31'),
(346, 'Mlock', '32'),
(347, 'Kolegialna', '32'),
(348, 'Biały Kamień', '33'),
(349, 'Szczawienko', '33'),
(350, 'Brzezie-Stacja', '34'),
(351, 'Grzywno', '34'),
(352, 'Ruda Południowa', '26'),
(353, 'Chebzie', '26'),
(354, 'Osiedle Niepodległości', '35'),
(355, 'Legionów Henryka Dąbrowskiego', '35'),
(356, 'Os. Irys', '36'),
(357, 'Centrum', '36'),
(358, 'Zakole', '37'),
(359, 'Przylesie', '37'),
(360, 'Majków', '38'),
(361, 'Kaliniec', '38'),
(362, 'Piekary Wielkie', '39'),
(363, 'Stare Piekary', '39'),
(364, 'Rządz', '40'),
(365, 'Tuszewo', '40'),
(366, 'Osiedle Stałe', '41'),
(367, 'Dąbrowa Narodowa', '41'),
(368, 'Osiedle Akademickie', '42'),
(369, 'Osiedle Bałtyckie', '42'),
(370, 'Bzie Dolne', '43'),
(371, 'Cisówka', '43'),
(372, 'Poreba Mała', '44'),
(373, 'Zawada', '44'),
(374, 'Dąbrowa Bolesławiecka', '45'),
(375, 'Osiedle Skowronków', '45'),
(376, 'Śródmieście', '46'),
(377, 'Sekuła', '46'),
(378, 'Słupna', '47'),
(379, 'Larysz', '47'),
(380, 'Gaj', '48'),
(381, 'Maliniec', '48'),
(382, 'Osiedle Mickiewicza', '49'),
(383, 'Meszcze', '49'),
(384, 'Gładyszewo', '50'),
(385, 'Osiedle Górne', '50'),
(386, 'Osiedle Bajka', '51'),
(387, 'Osiedle Rąbin', '51'),
(388, 'Małomice', '52'),
(389, 'Olsza', '52'),
(390, 'Osiedle Bajkowe', '53'),
(391, 'Osiedle Wschód', '53'),
(392, 'Osiedle Kamena', '54'),
(393, 'Osiedle Daszyńskiego', '54'),
(394, 'Denków', '55'),
(395, 'Osiedle Ogrody', '55'),
(396, 'Pustachowa', '56'),
(397, 'Kokoszki', '56'),
(398, 'Osiedle Hallera', '57'),
(399, 'Osiedle Letnie', '57'),
(400, 'Os. Kopernik', '58'),
(401, 'Stare Miasto', '58'),
(402, 'Bańgów', '59'),
(403, 'Bytków', '59'),
(404, 'Karniszewice', '60'),
(405, 'Jutrzkowice', '60'),
(406, 'Osiedle Karolówka', '61'),
(407, 'Majdan', '61'),
(408, 'Zaborowo', '62'),
(409, 'Podwale', '62'),
(410, 'Skowronki', '63'),
(411, 'Łomżyca', '63'),
(412, 'Śródmieście', '64'),
(413, 'Dyrekcja Górna', '64'),
(414, 'Białobrzegi', '65'),
(415, 'Nagórzyce', '65'),
(416, 'Rój', '66'),
(417, 'Rogozna', '66'),
(418, 'Północ II', '67'),
(419, 'Północ I', '67'),
(420, 'Osiedle Hutnik', '68'),
(421, 'Osiedle Poręby', '68'),
(422, 'Tworki', '69'),
(423, 'Pruszki', '69'),
(424, 'Zasanie', '70'),
(425, 'Bakończyce', '70'),
(426, 'Koźle-Rogi', '71'),
(427, 'Kłodnica', '71'),
(428, 'Repty Śląskie', '72'),
(429, 'Lasowice', '72'),
(430, 'Osiedle Cyranka', '73'),
(431, 'Osiedle Borek', '73'),
(432, 'Suchostrzygi', '74'),
(433, 'Prątnica', '74'),
(434, 'Grocholice', '75'),
(435, 'Osiedle Dolnośląskie', '75'),
(436, 'Osiedle Podmiejska', '76'),
(437, 'Osiedle Francuska', '76'),
(438, 'Osiedle Młodych', '77'),
(439, 'Osiedle Słowiańskie', '77'),
(440, 'Warpie', '78'),
(441, 'Ksawera', '78'),
(442, 'Stępowizna', '79'),
(443, 'Kurak', '79'),
(444, 'Dołki', '80'),
(445, 'Józefka', '80'),
(446, 'Miedonia', '81'),
(447, 'Brzezie', '81'),
(448, 'Centrum', '82'),
(449, 'Osiedle Jagiellońska', '82'),
(450, 'Śródmieście', '83'),
(451, 'Stare Miasto', '83'),
(452, 'Centrum', '84'),
(453, 'Lipiny', '84'),
(454, 'Nanice', '85'),
(455, 'Zamek', '85'),
(456, 'Żerkowice', '86'),
(457, 'Bzów', '86');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flats`
--

CREATE TABLE `flats` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `street` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number_of_tenants` int(11) DEFAULT NULL,
  `flat_area` double(8,2) DEFAULT NULL,
  `elevator` tinyint(4) DEFAULT NULL,
  `garage` tinyint(4) DEFAULT NULL,
  `rubbish` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `balcony` tinyint(4) DEFAULT NULL,
  `ground_floor_flats` int(11) DEFAULT NULL,
  `number_of_floors` int(11) DEFAULT NULL,
  `animals` tinyint(4) DEFAULT NULL,
  `family_with_children` tinyint(4) DEFAULT NULL,
  `smoking_person` tinyint(4) DEFAULT NULL,
  `flat_direction` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `brightness_of_flat` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `heating_id` int(11) DEFAULT NULL,
  `floor_id` int(11) DEFAULT NULL,
  `type_of_building_id` int(11) DEFAULT NULL,
  `number_of_rooms_id` int(11) DEFAULT NULL,
  `number_of_lifts` int(11) DEFAULT NULL,
  `parking_place_id` int(11) DEFAULT NULL,
  `environment_description` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rental_history_id` int(11) DEFAULT NULL,
  `sublet_permission` tinyint(4) DEFAULT NULL,
  `visibility` tinyint(1) DEFAULT NULL,
  `available_from` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `rent` float NOT NULL,
  `additional_fees` float DEFAULT NULL,
  `deposit` float DEFAULT NULL,
  `media_fees` float DEFAULT NULL,
  `number_of_parking_place` int(11) DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `blocked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flat_equipment`
--

CREATE TABLE `flat_equipment` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `equipment_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `equipment_description` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `flat_equipment`
--

INSERT INTO `flat_equipment` (`id`, `equipment_name`, `equipment_description`) VALUES
(1, 'odkurzacz', ''),
(2, 'telewizor', ''),
(3, 'łóżko', ''),
(4, 'pościel', ''),
(5, 'rolety w oknach', ''),
(6, 'deska do prasowania', ''),
(7, 'szafa', ''),
(8, 'biurko', ''),
(9, 'żelazko', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flat_has`
--

CREATE TABLE `flat_has` (
  `id` int(11) UNSIGNED NOT NULL,
  `flat_id` int(11) UNSIGNED NOT NULL,
  `equipment_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flat_media`
--

CREATE TABLE `flat_media` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `flat_id` bigint(20) UNSIGNED NOT NULL,
  `media_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flat_user`
--

CREATE TABLE `flat_user` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `flat_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `floors`
--

CREATE TABLE `floors` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `floor_number` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `floors`
--

INSERT INTO `floors` (`id`, `floor_number`) VALUES
(1, 'dowolnie'),
(2, 'suterena'),
(3, 'parter'),
(4, '1'),
(5, '2'),
(6, '3'),
(7, '4'),
(8, '5'),
(9, '6'),
(10, '7'),
(11, '8'),
(12, '9'),
(13, 'powyżej 9'),
(14, 'poddasze');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `heatings`
--

CREATE TABLE `heatings` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `type_of_heating` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `heatings`
--

INSERT INTO `heatings` (`id`, `type_of_heating`) VALUES
(1, 'wszystkie'),
(2, 'miejskie'),
(3, 'gazowe'),
(4, 'piece kaflowe'),
(5, 'elektryczne'),
(6, 'kotłownia'),
(7, 'inne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `inconveniences`
--

CREATE TABLE `inconveniences` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `inconvenience_description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `flat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kitchen_equipment`
--

CREATE TABLE `kitchen_equipment` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `equipment_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `equipment_description` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `kitchen_equipment`
--

INSERT INTO `kitchen_equipment` (`id`, `equipment_name`, `equipment_description`) VALUES
(1, 'lodówka', ''),
(2, 'czajnik elektryczny', ''),
(3, 'płyta grzewcza', ''),
(4, 'zastawa kuchenna', ''),
(5, 'otwieracze', 'np. do wina, do konserw, do butelek'),
(6, 'garnki', ''),
(7, 'zmywarka', ''),
(8, 'ekspres do kawy', ''),
(9, 'mikrofalówka', ''),
(10, 'piekarnik', ''),
(11, 'toster / opiekacz', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kitchen_has`
--

CREATE TABLE `kitchen_has` (
  `id` int(10) UNSIGNED NOT NULL,
  `flat_id` int(10) UNSIGNED NOT NULL,
  `equipment_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `media`
--

CREATE TABLE `media` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `media_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `media`
--

INSERT INTO `media` (`id`, `media_name`) VALUES
(1, 'Internet'),
(2, 'Telewizja kablowa'),
(3, 'Telewizja cyfrowa'),
(4, 'Telefon');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `messages`
--

CREATE TABLE `messages` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `seen` tinyint(4) NOT NULL,
  `flat_id` int(11) NOT NULL,
  `user_to` bigint(20) UNSIGNED NOT NULL,
  `user_from` bigint(20) UNSIGNED NOT NULL,
  `content_of_message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `deleted1` tinyint(1) DEFAULT NULL,
  `deleted2` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `number_of_rooms`
--

CREATE TABLE `number_of_rooms` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `number_of_rooms` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `number_of_rooms`
--

INSERT INTO `number_of_rooms` (`id`, `number_of_rooms`) VALUES
(1, 'Dowolnie'),
(2, 'Kawalerka'),
(3, '2 pokoje'),
(4, '3 pokoje'),
(5, '4 pokoje'),
(6, '5+');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `parking_places`
--

CREATE TABLE `parking_places` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `type_of_parking_place` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `parking_places`
--

INSERT INTO `parking_places` (`id`, `type_of_parking_place`) VALUES
(1, 'wszystkie'),
(2, 'plac parkingowy z ogrodzeniem'),
(3, 'parking otwarty'),
(4, 'parking piętrowy'),
(5, 'parking podziemny');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `photos`
--

CREATE TABLE `photos` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `photo_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `path` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flat_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rental_histories`
--

CREATE TABLE `rental_histories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `flat_id` int(6) NOT NULL,
  `since_when` date NOT NULL,
  `until_when` date NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `flat_id` tinyint(4) NOT NULL,
  `user_about` bigint(20) UNSIGNED NOT NULL,
  `user_from` bigint(20) UNSIGNED NOT NULL,
  `content_of_review` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `number_of_stars` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `blocked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `type_of_buildings`
--

CREATE TABLE `type_of_buildings` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `type_of_building` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `type_of_buildings`
--

INSERT INTO `type_of_buildings` (`id`, `type_of_building`) VALUES
(1, 'dowolnie'),
(2, 'blok'),
(3, 'kamienica'),
(4, 'dom wolnostojący'),
(5, 'plomba'),
(6, 'szeregowiec'),
(7, 'apartamentowiec'),
(8, 'loft'),
(9, 'pozostałe');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `second_name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `place` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `postcode` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `login` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type_of_user` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `blocked` tinyint(1) NOT NULL,
  `inactive` tinyint(1) NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `bathroom_equipment`
--
ALTER TABLE `bathroom_equipment`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `bathroom_has`
--
ALTER TABLE `bathroom_has`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `distances`
--
ALTER TABLE `distances`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `districts`
--
ALTER TABLE `districts`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `flats`
--
ALTER TABLE `flats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `flats_user_id_index` (`user_id`);

--
-- Indeksy dla tabeli `flat_equipment`
--
ALTER TABLE `flat_equipment`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `flat_has`
--
ALTER TABLE `flat_has`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `flat_media`
--
ALTER TABLE `flat_media`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `flat_user`
--
ALTER TABLE `flat_user`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `floors`
--
ALTER TABLE `floors`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `heatings`
--
ALTER TABLE `heatings`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `inconveniences`
--
ALTER TABLE `inconveniences`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `kitchen_equipment`
--
ALTER TABLE `kitchen_equipment`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `kitchen_has`
--
ALTER TABLE `kitchen_has`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `number_of_rooms`
--
ALTER TABLE `number_of_rooms`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `parking_places`
--
ALTER TABLE `parking_places`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indeksy dla tabeli `photos`
--
ALTER TABLE `photos`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `rental_histories`
--
ALTER TABLE `rental_histories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rental_histories_user_id_index` (`user_id`);

--
-- Indeksy dla tabeli `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `type_of_buildings`
--
ALTER TABLE `type_of_buildings`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `bathroom_equipment`
--
ALTER TABLE `bathroom_equipment`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `bathroom_has`
--
ALTER TABLE `bathroom_has`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `cities`
--
ALTER TABLE `cities`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT dla tabeli `distances`
--
ALTER TABLE `distances`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `districts`
--
ALTER TABLE `districts`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=458;

--
-- AUTO_INCREMENT dla tabeli `flats`
--
ALTER TABLE `flats`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `flat_equipment`
--
ALTER TABLE `flat_equipment`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `flat_has`
--
ALTER TABLE `flat_has`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `flat_media`
--
ALTER TABLE `flat_media`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `flat_user`
--
ALTER TABLE `flat_user`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `floors`
--
ALTER TABLE `floors`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT dla tabeli `heatings`
--
ALTER TABLE `heatings`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `inconveniences`
--
ALTER TABLE `inconveniences`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `kitchen_equipment`
--
ALTER TABLE `kitchen_equipment`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `kitchen_has`
--
ALTER TABLE `kitchen_has`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `media`
--
ALTER TABLE `media`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `messages`
--
ALTER TABLE `messages`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `number_of_rooms`
--
ALTER TABLE `number_of_rooms`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `parking_places`
--
ALTER TABLE `parking_places`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `photos`
--
ALTER TABLE `photos`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `rental_histories`
--
ALTER TABLE `rental_histories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `type_of_buildings`
--
ALTER TABLE `type_of_buildings`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
