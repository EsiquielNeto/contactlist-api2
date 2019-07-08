CREATE TABLE contact (
  id        BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email     VARCHAR(255),
  telephone VARCHAR(11) NOT NULL,
  twitter   VARCHAR(10),
  skype     VARCHAR(255),
  photo     VARCHAR(255)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO contact VALUES (1, 'Esiquiel', 'Neto', 'ziki.neto@gmail.com', '14991085676', '', 'ziki.neto', '');
INSERT INTO contact VALUES (2, 'Henrique', 'Oliveira', 'rique-oli@hotmail.com', '14991446677', '', 'rique.oli', '');
INSERT INTO contact VALUES (3, 'Geane', 'Camargo', 'ge.camargo@gmail.com', '14991233244', '@gecamargo', 'ge.camargo', '');
INSERT INTO contact VALUES (4, 'Flavio', 'Cabral', 'favio.cabral@gmail.com', '14987654321', '@flavioCab', '14987654321', '');
INSERT INTO contact VALUES (5, 'Diana', 'Souza', 'diana_souza@gmail.com', '14982765514', '', '', '');
INSERT INTO contact VALUES (6, 'Maria', 'Silva', 'mary.silva@gmail.com', '14982775511', '', '', '');
INSERT INTO contact VALUES (7, 'Victor', 'Conti', 'victor-conti@hotmail.com', '14982997766', '@vitorcont', 'vitor_conti', '');
INSERT INTO contact VALUES (8, 'Julia', 'Barry', 'julia-barr@gmail.com', '14988112233', '@juliabarr', '', '');
INSERT INTO contact VALUES (9, 'Stefany', 'Severino', 'stefany.seve@gmail.com', '14991999111', '@stefanySe', '', '');
INSERT INTO contact VALUES (10, 'Alesson', 'Evangelista', 'ale_evangelista@gmail.com', '14991234567', '', 'alesson.evangelista', '');
