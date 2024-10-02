CREATE DATABASE IF NOT EXISTS escape_room;
USE escape_room;

-- Eliminando tablas si existen
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS decorations;
DROP TABLE IF EXISTS clues;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS rooms;

-- Creación de la tabla rooms
CREATE TABLE rooms (
  idrooms int NOT NULL AUTO_INCREMENT,
  thematic varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  difficulty varchar(45) NOT NULL,
  PRIMARY KEY (idrooms)
);

-- Creación de la tabla clients
CREATE TABLE clients (
  idclients int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  surname varchar(45) NOT NULL,
  subscribed tinyint NOT NULL,
  PRIMARY KEY (idclients)
);

-- Creación de la tabla clues
CREATE TABLE clues (
  idclues int NOT NULL AUTO_INCREMENT,
  thematic varchar(45) NOT NULL,
  value float NOT NULL,
  idroom int DEFAULT NULL,
  PRIMARY KEY (idclues),
  KEY fk_clues_rooms1_idx (idroom),
  CONSTRAINT fk_clues_rooms1 FOREIGN KEY (idroom) REFERENCES rooms (idrooms) ON DELETE SET NULL ON UPDATE RESTRICT
);

-- Creación de la tabla decorations
CREATE TABLE decorations (
  iddecorations int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  thematic varchar(45) NOT NULL,
  material varchar(45) NOT NULL,
  value float NOT NULL,
  idroom int DEFAULT NULL,
  PRIMARY KEY (iddecorations),
  KEY fk_decoration_rooms1_idx (idroom),
  CONSTRAINT fk_decoration_rooms1 FOREIGN KEY (idroom) REFERENCES rooms (idrooms) ON DELETE SET NULL
);

-- Creación de la tabla tickets
CREATE TABLE tickets (
  idtickets int NOT NULL AUTO_INCREMENT,
  idclient int NOT NULL,
  value float NOT NULL,
  idroom int DEFAULT NULL,
  PRIMARY KEY (idtickets),
  KEY fk_tickets_rooms1_idx (idroom),
  KEY fk_tickets_clients1_idx (idclient),
  CONSTRAINT fk_tickets_clients1 FOREIGN KEY (idclient) REFERENCES clients (idclients),
  CONSTRAINT fk_tickets_rooms1 FOREIGN KEY (idroom) REFERENCES rooms (idrooms) ON DELETE SET NULL ON UPDATE SET NULL
);

-- Insertando datos en la tabla rooms
INSERT INTO rooms (thematic, name, difficulty) VALUES
('TERROR', 'The Haunted Mansion', 'HARD'),
('MEDIEVAL', 'Jungle Escape', 'MEDIUM'),
('SCIFI', 'Space Odyssey', 'HARD'),
('FANTASTIC', 'Wizard\'s Tower', 'EASY'),
('MEDIEVAL', 'Sherlock\'s Case', 'MEDIUM');

-- Insertando datos en la tabla clients
INSERT INTO clients (name, surname, subscribed) VALUES
('John', 'Doe', 1),
('Jane', 'Smith', 0),
('Alice', 'Johnson', 1),
('Bob', 'Brown', 0),
('Charlie', 'Davis', 1);

-- Insertando datos en la tabla clues, incluyendo valores con idroom NULL
INSERT INTO clues (thematic, value, idroom) VALUES
('TERROR', 50.00, 1),
('MEDIEVAL', 30.00, 2),
('SCIFI', 45.00, 3),
('FANTASTIC', 20.00, 4),
('TERROR', 40.00, 1),
('MEDIEVAL', 25.00, 2),
('MEDIEVAL', 35.00, 5),
('MEDIEVAL', 10.00, NULL),  -- Clue sin sala asignada
('SCIFI', 15.00, NULL),    -- Clue sin sala asignada
('FANTASTIC', 10.00, NULL),    -- Clue sin sala asignada
('MEDIEVAL', 12.00, NULL),    -- Clue sin sala asignada
('TERROR', 12.00, NULL),    -- Clue sin sala asignada
('SCIFI', 25.00, NULL),    -- Clue sin sala asignada
('TERROR', 12.00, NULL),    -- Clue sin sala asignada
('FANTASTIC', 11.00, NULL);    -- Clue sin sala asignada

-- Insertando datos en la tabla decorations, incluyendo valores con idroom NULL
INSERT INTO decorations (name, thematic, material, value, idroom) VALUES
('Old Mirror', 'TERROR', 'Glass', 150.00, 1),
('Vine Wall', 'MEDIEVAL', 'Wood', 100.00, 2),
('Spaceship Model', 'SCIFI', 'Metal', 200.00, 3),
('Wizard\'s Staff', 'FANTASTIC', 'Wood', 75.00, 4),
('Sherlock\'s Desk', 'MEDIEVAL', 'Wood', 120.00, 5),
('Chair', 'FANTASTIC', 'Wood', 50.00, NULL),  -- Decoración sin sala asignada
('table', 'MEDIEVAL', 'Wood', 50.00, NULL),  -- Decoración sin sala asignada
('Weapon', 'SCIFI', 'Plastic', 50.00, NULL),  -- Decoración sin sala asignada
('Axe', 'TERROR', 'Plastic', 50.00, NULL),  -- Decoración sin sala asignada
('Ancient Scroll', 'MEDIEVAL', 'Paper', 50.00, NULL),  -- Decoración sin sala asignada
('Mystic Orb', 'FANTASTIC', 'Crystal', 90.00, NULL);  -- Decoración sin sala asignada

-- Insertando datos en la tabla tickets
INSERT INTO tickets (idclient, value, idroom) VALUES
(1, 25.00, 1),
(2, 20.00, 2),
(3, 30.00, 3),
(4, 15.00, 4),
(5, 25.00, 5),
(1, 25.00, 3),
(2, 20.00, 4),
(3, 35.00, 5),
(4, 10.00, NULL);  -- Ticket sin sala asignada
