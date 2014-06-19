DROP TABLE IF EXISTS `quotes`;
DROP TABLE IF EXISTS `movies`;
DROP TABLE IF EXISTS `quote_movie`;
DROP TABLE IF EXISTS `quote_mismovie`;

CREATE TABLE `quotes` (
  `quote_id` varchar(255) NOT NULL,
  `quote` varchar(255) NOT NULL,
  PRIMARY KEY (`quote_id`),
  UNIQUE KEY `q_id_UNIQUE` (`quote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `movies` (
  `movie_id` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`movie_id`),
  UNIQUE KEY `m_id_UNIQUE` (`movie_id`),
  UNIQUE KEY `movie_UNIQUE` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `quote_movie` (
  `quote_movie_id` varchar(255) NOT NULL,
  `quote_id` varchar(255) NOT NULL,
  `movie_id` varchar(255) NOT NULL,
  PRIMARY KEY (`quote_movie_id`),
  UNIQUE KEY `quote_movie_id_UNIQUE` (`quote_movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `quote_mismovie` (
  `quote_mismovie_id` varchar(255) NOT NULL,
  `quote_id` varchar(255) NOT NULL COMMENT 'Quote that the mismatch will show up for.',
  `movie_id` varchar(255) NOT NULL COMMENT 'id of an incorrect movie to be paired with the quote.',
  PRIMARY KEY (`quote_mismovie_id`),
  UNIQUE KEY `misquote_movie_id_UNIQUE` (`quote_mismovie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO quotes (quote_id, quote) values ('a1', '"Can\'t wait. LOL. *smileyface*."');
INSERT INTO movies (movie_id, title) values ('b1', 'Megamind');
INSERT INTO movies (movie_id, title) values ('b2', 'Sleeping Beauty');
INSERT INTO movies (movie_id, title) values ('b3', 'Despicable Me');
INSERT INTO movies (movie_id, title) values ('b4', 'Shrek');
INSERT INTO quote_movie (quote_movie_id, quote_id, movie_id) values ('c1', 'a1', 'b1');
INSERT INTO quote_mismovie(quote_mismovie_id, quote_id, movie_id) values ('d1', 'a1', 'b2');
INSERT INTO quote_mismovie(quote_mismovie_id, quote_id, movie_id) values ('d2', 'a1', 'b3');
INSERT INTO quote_mismovie(quote_mismovie_id, quote_id, movie_id) values ('d3', 'a1', 'b4');


select * from movies;
select * from quotes;
select * from quote_movie;
select * from quote_mismovie;
