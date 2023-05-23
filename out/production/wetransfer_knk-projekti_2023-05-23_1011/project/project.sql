create
database project;

use
project;
CREATE TABLE users
(
    id        INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(555) NOT NULL,
    salt      VARCHAR(255) NOT NULL,
    role      CHAR(1)      NOT NULL,
    active    bit          NOT NULL DEFAULT 1,
    createdAt DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE game
(
    id        INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ekipiI    VARCHAR(30) NOT NULL,
    ekipiII   VARCHAR(30) NOT NULL,
    hour      VARCHAR(30) NULL,
    result    VARCHAR(10) NULL,
    playsAt   VARCHAR(50) NULL,
    createdAt DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE team
(
    id         INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(30) NOT NULL,
    coach      VARCHAR(30) NOT NULL,
    logo       TEXT NULL,
    nr_players INTEGER NULL,
    createdAt  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE standings
(
    id      INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ekipi   VARCHAR(30) NOT NULL,
    w       INTEGER     NOT NULL,
    l       INTEGER     NOT NULL,
    streak  VARCHAR(10) NOT NULL,
    last_10 VARCHAR(10) NOT NULL,
    win     REAL        NOT NULL
);


INSERT INTO game
VALUES (1, ''Trepça'', ''Ponte Prizreni'', '''', ''89 - 61 '', null, ''2022 - 10 - 11 20:00:00'',
        ''2022 - 10 - 11 20:00:00'');
INSERT INTO game
VALUES (2, ''Bashkimi'', ''Vëllaznimi'', '''', ''71 - 60 '', null, ''2022 - 10 - 11 20:00:00'',
        ''2022 - 10 - 11 20:00:00'');
INSERT INTO game
VALUES (3, ''Rahoveci'', ''Golden Eagle Ylli'', '''', ''90 - 88 '', null, ''2022 - 10 - 11 20:00:00'',
        ''2022 - 10 - 11 20:00:00'');
INSERT INTO game
VALUES (4, ''Sigal Prishtina'', ''Peja'', '''', ''70 - 76 '', null, ''2022 - 10 - 11 20:00:00'',
        ''2022 - 10 - 11 20:00:00'');
INSERT INTO game
VALUES (5, ''Ponte Prizreni'', ''Peja'', '''', ''130 - 103 '', null, ''2022 - 10 - 19 20:00:00'',
        ''2022 - 10 - 19 20:00:00'');
INSERT INTO game
VALUES (6, ''Golden Eagle Ylli'', ''Sigal Prishtina'', '''', ''56 - 77 '', null, ''2022 - 10 - 19 20:00:00'',
        ''2022 - 10 - 19 20:00:00'');
INSERT INTO game
VALUES (7, ''Vëllaznimi'', ''Rahoveci'', '''', ''94 - 87 '', null, ''2022 - 10 - 19 20:00:00'',
        ''2022 - 10 - 19 20:00:00'');
INSERT INTO game
VALUES (8, ''Trepça'', ''Bashkimi'', '''', ''75 - 68 '', null, ''2022 - 10 - 19 20:00:00'',
        ''2022 - 10 - 19 20:00:00'');
INSERT INTO game
VALUES (9, ''Bashkimi'', ''Ponte Prizreni'', '''', ''93 - 101 '', null, ''2022 - 10 - 27 20:00:00'',
        ''2022 - 10 - 27 20:00:00'');
INSERT INTO game
VALUES (10, ''Rahoveci'', ''Trepça'', '''', ''80 - 87 '', null, ''2022 - 10 - 27 20:00:00'',
        ''2022 - 10 - 27 20:00:00'');
INSERT INTO game
VALUES (11, ''Sigal Prishtina'', ''Vëllaznimi'', '''', ''99 - 81 '', null, ''2022 - 10 - 27 20:00:00'',
        ''2022 - 10 - 27 20:00:00'');
INSERT INTO game
VALUES (12, ''Peja'', ''Golden Eagle Ylli'', '''', ''73 - 57 '', null, ''2022 - 10 - 27 20:00:00'',
        ''2022 - 10 - 27 20:00:00'');
INSERT INTO game
VALUES (13, ''Ponte Prizreni'', ''Golden Eagle Ylli'', '''', ''99 - 82 '', null, ''2022 - 11 - 04 20:00:00'',
        ''2022 - 11 - 04 20:00:00'');
INSERT INTO game
VALUES (14, ''Vëllaznimi'', ''Peja'', '''', ''63 - 75 '', null, ''2022 - 11 - 04 20:00:00'',
        ''2022 - 11 - 04 20:00:00'');
INSERT INTO game
VALUES (15, ''Trepça'', ''Sigal Prishtina'', '''', ''80 - 75 '', null, ''2022 - 11 - 04 20:00:00'',
        ''2022 - 11 - 04 20:00:00'');
INSERT INTO game
VALUES (16, ''Bashkimi'', ''Rahoveci'', '''', ''84 - 79 '', null, ''2022 - 11 - 04 20:00:00'',
        ''2022 - 11 - 04 20:00:00'');
INSERT INTO game
VALUES (17, ''Rahoveci'', ''Ponte Prizreni'', '''', ''90 - 95 '', null, ''2022 - 11 - 11 20:00:00'',
        ''2022 - 11 - 11 20:00:00'');
INSERT INTO game
VALUES (18, ''Sigal Prishtina'', ''Bashkimi'', '''', ''87 - 80 '', null, ''2022 - 11 - 11 20:00:00'',
        ''2022 - 11 - 11 20:00:00'');
INSERT INTO game
VALUES (19, ''Peja'', ''Trepça'', '''', ''83 - 72 '', null, ''2022 - 11 - 11 20:00:00'', ''2022 - 11 - 11 20:00:00'');
INSERT INTO game
VALUES (20, ''Golden Eagle Ylli'', ''Vëllaznimi'', '''', ''81 - 70 '', null, ''2022 - 11 - 11 20:00:00'',
        ''2022 - 11 - 11 20:00:00'');
INSERT INTO game
VALUES (21, ''Ponte Prizreni'', ''Vëllaznimi'', '''', ''116 - 102 '', null, ''2022 - 11 - 18 20:00:00'',
        ''2022 - 11 - 18 20:00:00'');
INSERT INTO game
VALUES (22, ''Trepça'', ''Golden Eagle Ylli'', '''', ''70 - 72 '', null, ''2022 - 11 - 18 20:00:00'',
        ''2022 - 11 - 18 20:00:00'');
INSERT INTO game
VALUES (23, ''Bashkimi'', ''Peja'', '''', ''67 - 72 '', null, ''2022 - 11 - 18 20:00:00'', ''2022 - 11 - 18 20:00:00'');
INSERT INTO game
VALUES (24, ''Rahoveci'', ''Sigal Prishtina'', '''', ''82 - 88 '', null, ''2022 - 11 - 18 20:00:00'',
        ''2022 - 11 - 18 20:00:00'');
INSERT INTO game
VALUES (25, ''Sigal Prishtina'', ''Ponte Prizreni'', '''', ''75 - 81 '', null, ''2022 - 11 - 25 20:00:00'',
        ''2022 - 11 - 25 20:00:00'');
INSERT INTO game
VALUES (26, ''Peja'', ''Rahoveci'', '''', ''86 - 76 '', null, ''2022 - 11 - 25 20:00:00'', ''2022 - 11 - 25 20:00:00'');
INSERT INTO game
VALUES (27, ''Golden Eagle Ylli'', ''Bashkimi'', '''', ''76 - 73 '', null, ''2022 - 11 - 25 20:00:00'',
        ''2022 - 11 - 25 20:00:00'');
INSERT INTO game
VALUES (28, ''Vëllaznimi'', ''Trepça'', '''', ''75 - 72 '', null, ''2022 - 11 - 25 20:00:00'',
        ''2022 - 11 - 25 20:00:00'');
INSERT INTO game
VALUES (29, ''Ponte Prizreni'', ''Trepça'', '''', ''85 - 75 '', null, ''2022 - 11 - 30 20:00:00'',
        ''2022 - 11 - 30 20:00:00'');
INSERT INTO game
VALUES (30, ''Vëllaznimi'', ''Bashkimi'', '''', ''76 - 84 '', null, ''2022 - 11 - 30 20:00:00'',
        ''2022 - 11 - 30 20:00:00'');
INSERT INTO game
VALUES (31, ''Golden Eagle Ylli'', ''Rahoveci'', '''', ''109 - 59 '', null, ''2022 - 11 - 30 20:00:00'',
        ''2022 - 11 - 30 20:00:00'');
INSERT INTO game
VALUES (32, ''Peja'', ''Sigal Prishtina'', '''', ''79 - 77 '', null, ''2022 - 11 - 30 20:00:00'',
        ''2022 - 11 - 30 20:00:00'');
INSERT INTO game
VALUES (33, ''Peja'', ''Ponte Prizreni'', '''', ''75 - 81 '', null, ''2022 - 12 - 07 20:00:00'',
        ''2022 - 12 - 07 20:00:00'');
INSERT INTO game
VALUES (34, ''Sigal Prishtina'', ''Golden Eagle Ylli'', '''', ''68 - 86 '', null, ''2022 - 12 - 07 20:00:00'',
        ''2022 - 12 - 07 20:00:00'');
INSERT INTO game
VALUES (35, ''Rahoveci'', ''Vëllaznimi'', '''', ''65 - 71 '', null, ''2022 - 12 - 07 20:00:00'',
        ''2022 - 12 - 07 20:00:00'');
INSERT INTO game
VALUES (36, ''Bashkimi'', ''Trepça'', '''', ''85 - 87 '', null, ''2022 - 12 - 07 20:00:00'',
        ''2022 - 12 - 07 20:00:00'');
INSERT INTO game
VALUES (37, ''Ponte Prizreni'', ''Bashkimi'', '''', ''80 - 81 '', null, ''2022 - 12 - 14 20:00:00'',
        ''2022 - 12 - 14 20:00:00'');
INSERT INTO game
VALUES (38, ''Trepça'', ''Rahoveci'', '''', ''95 - 87 '', null, ''2022 - 12 - 14 20:00:00'',
        ''2022 - 12 - 14 20:00:00'');
INSERT INTO game
VALUES (39, ''Vëllaznimi'', ''Sigal Prishtina'', '''', ''71 - 79 '', null, ''2022 - 11 - 04 20:00:00'',
        ''2022 - 12 - 14 20:00:00'');
INSERT INTO game
VALUES (40, ''Golden Eagle Ylli'', ''Peja'', '''', ''58 - 57 '', null, ''2022 - 12 - 14 20:00:00'',
        ''2022 - 12 - 14 20:00:00'');

INSERT INTO team
VALUES (1, ''Sigal Prishtina'', ''Ahmet Kandemir'', '''', 10, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (2, ''Peja'', ''Rami Hadar'', '''', 15, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (3, ''Golden Eagle Ylli'', ''Adis Beciragic'', '''', 10, ''2023 - 05 - 20 17:32:53'',
        ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (4, ''Trepça'', ''Ilir Selmani'', '''', 13, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (5, ''Bashkimi'', ''Elbesar Geshtenja'', '''', 9, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (6, ''Vëllaznimi'', ''Franko Sterle'', '''', 11, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (7, ''Ponte Prizreni'', ''Arbnor Rifati'', '''', 6, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');
INSERT INTO team
VALUES (8, ''Rahoveci'', ''Muhammed Thaci'', '''', 9, ''2023 - 05 - 20 17:32:53'', ''2023 - 05 - 20 17:32:53'');

INSERT INTO standings
VALUES (1, ''Sigal Prishtina'', 22, 6, ''W-6'', ''7 - 3 '', 0.786);
INSERT INTO standings
VALUES (2, ''Peja'', 21, 7, ''W-2'', ''5 - 5 '', 0.75);
INSERT INTO standings
VALUES (3, ''Golden Eagle Ylli'', 20, 8, ''W-3'', ''4 - 6 '', 0.714);
INSERT INTO standings
VALUES (4, ''Trepça'', 17, 11, ''W-3'', ''5 - 5 '', 0.607);
INSERT INTO standings
VALUES (5, ''Bashkimi'', 11, 17, ''L-5'', ''2 - 8 '', 0.393);
INSERT INTO standings
VALUES (6, ''Vëllaznimi'', 9, 19, ''L-6'', ''1 - 9 '', 0.321);
INSERT INTO standings
VALUES (7, ''Ponte Prizreni'', 8, 20, ''W-2'', ''4 - 6 '', 0.286);
INSERT INTO standings
VALUES (8, ''Rahoveci'', 4, 24, ''L-4'', ''2 - 8 '', 0.143);
