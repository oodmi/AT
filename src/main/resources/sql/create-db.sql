DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS book CASCADE;

CREATE TABLE user (
  login    VARCHAR(30) PRIMARY KEY NOT NULL,
  password VARCHAR(50)             NOT NULL
);

CREATE TABLE book (
  isn    INT(11) PRIMARY KEY  NOT NULL,
  author VARCHAR(30)          ,
  name   VARCHAR(30)          NOT NULL,
  owner  VARCHAR(30)
);

ALTER TABLE book
  ADD FOREIGN KEY (owner) REFERENCES user (login);




