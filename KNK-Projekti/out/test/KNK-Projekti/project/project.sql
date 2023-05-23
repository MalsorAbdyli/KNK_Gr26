create database project;
use project;

CREATE TABLE users (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
					name VARCHAR(100) NOT NULL,
                    email VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(555) NOT NULL,
                    salt VARCHAR(255) NOT NULL,
                    role CHAR(1) NOT NULL,
                    active bit NOT NULL DEFAULT 1,
                    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE game (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
						ekipiI VARCHAR(30) NOT NULL,
                        ekipiII VARCHAR(30) NOT NULL,
                        hour VARCHAR(30) NULL,
                        result VARCHAR(10) NULL,
                        playsAt VARCHAR(50) NULL,
                        createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE team (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
						name VARCHAR(30) NOT NULL,
                        coach VARCHAR(30) NOT NULL,
                        logo TEXT NULL,
                        nr_players INTEGER NULL,
                        createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE standings (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
						ekipi VARCHAR(30) NOT NULL,
                        w INTEGER NOT NULL,
                        l INTEGER NOT NULL,
                        streak VARCHAR(10) NOT NULL,
                        last_10 VARCHAR(10) NOT NULL,
                        win REAL NOT NULL);


INSERT INTO game VALUES (1,'Golden State Warriors','LA Clippers','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (2,'Bucks','Suns','','118-107',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (3,'Hawks','Bucks','','120-108',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (4,'Bucks','Hawks','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (5,'Suns','Clippers','','130-103',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (6,'Hawks','Bucks','','88-110',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (7,'Clippers','Suns','','116-102',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (8,'Golden State Warriors','LA Clippers','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (9,'Bucks','Suns','','118-107',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (10,'Hawks','Bucks','','120-108',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (11,'Bucks','Hawks','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (12,'Suns','Clippers','','130-103',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (13,'Hawks','Bucks','','88-110',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (14,'Clippers','Suns','','116-102',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (15,'Golden State Warriors','LA Clippers','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (16,'Bucks','Suns','','118-107',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (17,'Hawks','Bucks','','120-108',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (18,'Bucks','Hawks','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (19,'Suns','Clippers','','130-103',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (20,'Hawks','Bucks','','88-110',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (21,'Clippers','Suns','','116-102',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (22,'Golden State Warriors','LA Clippers','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (23,'Bucks','Suns','','118-107',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (24,'Hawks','Bucks','','120-108',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (25,'Bucks','Hawks','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (26,'Suns','Clippers','','130-103',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (27,'Hawks','Bucks','','88-110',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (28,'Clippers','Suns','','116-102',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (29,'Golden State Warriors','LA Clippers','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (30,'Bucks','Suns','','118-107',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (31,'Hawks','Bucks','','120-108',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (32,'Bucks','Hawks','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (33,'Suns','Clippers','','130-103',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (34,'Hawks','Bucks','','88-110',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (35,'Clippers','Suns','','116-102',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (36,'Golden State Warriors','LA Clippers','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (37,'Bucks','Suns','','118-107',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (38,'Hawks','Bucks','','120-108',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (39,'Bucks','Hawks','','105-118',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (40,'Suns','Clippers','','130-103',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (41,'Hawks','Bucks','','88-110',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO game VALUES (42,'Clippers','Suns','','116-102',null,'2022-05-22 17:18:53', '2022-05-22 17:18:53');

INSERT INTO team VALUES(1,'LA Clippers','Brad Stevens','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(2,'Golden State Warriors','Brandon Bailey','',18,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(3,'Bucks','Jamie Young','',16,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(4,'Hawks','Scott Morrison','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(5,'Suns','Joe Mazulla','',18,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(6,'Clippers','Art Horne','',16,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(7,'Lakers','Jerome Allen','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(8,'LA Clippers','Brad Stevens','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(9,'Golden State Warriors','Brandon Bailey','',18,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(10,'Bucks','Jamie Young','',16,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(11,'Hawks','Scott Morrison','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(12,'Suns','Joe Mazulla','',18,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(13,'Clippers','Art Horne','',16,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(14,'Lakers','Jerome Allen','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(15,'LA Clippers','Brad Stevens','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(16,'Golden State Warriors','Brandon Bailey','',18,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(17,'Bucks','Jamie Young','',16,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(18,'Hawks','Scott Morrison','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(19,'Suns','Joe Mazulla','',18,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(20,'Clippers','Art Horne','',16,'2022-05-22 17:18:53', '2022-05-22 17:18:53');
INSERT INTO team VALUES(21,'Lakers','Jerome Allen','',17,'2022-05-22 17:18:53', '2022-05-22 17:18:53');

INSERT INTO standings VALUES(1,'Golden State Warriors',49,23,'W-2','8-2',0.681);
INSERT INTO standings VALUES(2,'LA Clippers',48,24,'W-5','6-4',0.667);
INSERT INTO standings VALUES(3,'Lakers',46,26,'L-1','8-2',0.639);
INSERT INTO standings VALUES(4,'Bucks',41,31,'W-3','7-3',0.569);
INSERT INTO standings VALUES(5,'Suns',41,31,'W-4','7-3',0.569);
INSERT INTO standings VALUES(6,'Hawks',40,32,'W-1','8-2',0.556);
INSERT INTO standings VALUES(7,'Clippers',36,36,'L-1','4-6',0.500);
INSERT INTO standings VALUES(8,'Miami Heat',34,38,'W-2','6-4',0.472);
INSERT INTO standings VALUES(9,'Chicago Bulls',34,38,'W-1','5-5',0.472);
INSERT INTO standings VALUES(10,'Philadelphia',49,23,'W-2','8-2',0.681);
INSERT INTO standings VALUES(11,'Brooklyn Nets',33,	39,'L-5','3-7',0.458);
INSERT INTO standings VALUES(12,'Toronto Raptors',31,41,'W-1','5-5',0.431);
INSERT INTO standings VALUES(13,'Cleveland Cavaliers',27,45,'L-7','1-9',0.375);
INSERT INTO standings VALUES(14,'Boston Celtics',22,50,'L-2','1-9',0.306);
