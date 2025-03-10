CREATE TABLE uea
(
    Clave       VARCHAR(10) PRIMARY KEY,
    Nombre      VARCHAR(50),
    IdUnidad    INT,
    Creditos    INT,
    IdTronco    INT,
    IdTrimestre INT
);

CREATE TABLE unidad
(
    IdUnidad INT PRIMARY KEY,
    Nombre   VARCHAR(100)
);

CREATE TABLE tronco
(
    IdTronco INT PRIMARY KEY,
    Nombre   VARCHAR(100)
);

CREATE TABLE trimestre
(
    IdTrimestre INT PRIMARY KEY,
    Nombre      VARCHAR(10)
);

ALTER TABLE uea ADD FOREIGN KEY (IdUnidad) REFERENCES unidad (IdUnidad);
ALTER TABLE uea ADD FOREIGN KEY (IdTrimestre) REFERENCES trimestre (IdTrimestre);
ALTER TABLE uea ADD FOREIGN KEY (IdTronco) REFERENCES tronco (IdTronco);

/*CREATE TABLE profesores
(
                           NoEconomico VARCHAR(10) PRIMARY KEY,
                           Nombre VARCHAR(20),
                           ApellidoPaterno VARCHAR(20),
                           ApellidoMaterno VARCHAR(20),
                           IdUnidad INT,
                           IdDepartamento INT,
                           IdDivision INT,
                           Email VARCHAR(20),
                           Password VARCHAR(100)
);

ALTER TABLE profesores ADD FOREIGN KEY (IdUnidad) REFERENCES unidad (IDUNIDAD);
ALTER TABLE profesores ADD FOREIGN KEY (IdDepartamento) REFERENCES departamento (IdDepartamento);
ALTER TABLE profesores ADD FOREIGN KEY (IdDivision) REFERENCES division (IdDivision);
*/

CREATE TABLE departamento
(
                             IdDepartamento INT PRIMARY KEY,
                             Nombre VARCHAR(100)
);

CREATE TABLE division
(
                         IdDivision INT PRIMARY KEY,
                         Nombre VARCHAR(20)
);

/*CREATE TABLE alumnos
(
                        Matricula VARCHAR(20) PRIMARY KEY,
                        Nombre VARCHAR(20),
                        ApellidoPaterno VARCHAR(20),
                        ApellidoMaterno VARCHAR(20),
                        IdUnidad INT,
                        IdDivision INT,
                        IdCarrera INT
);

ALTER TABLE alumnos ADD FOREIGN KEY (IdCarrera) REFERENCES carrera (IdCarrera);
ALTER TABLE alumnos ADD FOREIGN KEY (IdDivision) REFERENCES division (IdDivision);
ALTER TABLE alumnos ADD FOREIGN KEY (IdUnidad) REFERENCES unidad (IdUnidad);*/

CREATE TABLE carrera
(
                        IdCarrera INT PRIMARY KEY,
                        Nombre VARCHAR(50)
);

CREATE TABLE usuarios
(
                         NoEconomico VARCHAR(10) PRIMARY KEY,
                         Email VARCHAR(50),
                         Password VARCHAR(100),
                         IdRol INT,
                         Nombre VARCHAR(20),
                         ApellidoPaterno VARCHAR(20),
                         ApellidoMaterno VARCHAR(20),
                         IdUnidad INT,
                         IdDepartamento INT,
                         IdDivision INT

);

CREATE TABLE roles
(
                      IdRol INT PRIMARY KEY,
                      Nombre VARCHAR(100)
);

ALTER TABLE usuarios ADD FOREIGN KEY (IdRol) REFERENCES roles (IdRol);
ALTER TABLE usuarios ADD FOREIGN KEY (IdUnidad) REFERENCES unidad (IDUNIDAD);
ALTER TABLE usuarios ADD FOREIGN KEY (IdDepartamento) REFERENCES departamento (IdDepartamento);
ALTER TABLE usuarios ADD FOREIGN KEY (IdDivision) REFERENCES division (IdDivision);

CREATE TABLE grupos
(
                       ClaveGrupo VARCHAR(10) PRIMARY KEY ,
                       ClaveUea VARCHAR(10),
                       Unidad INT,
                       Horario JSON,
                       NoEconomico VARCHAR(10),
                       CupoUnidad INT,
                       Salon INT,
                       Inscritos INT

);

CREATE TABLE salon
(
                      IdSalon INT PRIMARY KEY ,
                      Nombre VARCHAR(10),
                      Cupo INT,
                      Ubicacion VARCHAR(20)
);

/*CREATE TABLE horario
(
                        IdHorario INT PRIMARY KEY ,
                        HoraInicio VARCHAR(20),
                        HoraTermino VARCHAR(20),
                        Dias VARCHAR(30)
);*/

ALTER TABLE grupos ADD FOREIGN KEY (ClaveUea) REFERENCES uea (Clave);
ALTER TABLE grupos ADD FOREIGN KEY (Unidad) REFERENCES unidad (IdUnidad);
# ALTER TABLE grupos ADD FOREIGN KEY (Horario) REFERENCES horario (IdHorario);
ALTER TABLE grupos ADD FOREIGN KEY (NoEconomico) REFERENCES usuarios (NoEconomico);
ALTER TABLE grupos ADD FOREIGN KEY (Salon) REFERENCES salon (IdSalon);

INSERT INTO roles VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO carrera VALUES (1, 'ICT'), (2, 'ISMI'), (3, 'IRH'),
                           (4, 'PP'), (5, 'EyTD'), (6, 'AyCD'),
                           (7, 'BA'), (8, 'PB'), (9, 'CyTA');

INSERT INTO unidad VALUES (1, 'Lerma'),
                          (2, 'Cuajimalpa'),
                          (3, 'Azcapotzalco'),
                          (4, 'Xochimilco'),
                          (5, 'Iztapalapa');

INSERT INTO trimestre VALUES (1, '1'), (2, '2'), (3, '3'),
                             (4, '4'), (5, '5'), (6, '6'),
                             (7, '7'), (8, '8'), (9, '9'),
                             (10, '10'), (11, '11'), (12, '12');

INSERT INTO division VALUES (1, 'CBI'), (2, 'CSH'), (3, 'CBS');

INSERT INTO departamento VALUES (1, 'Recursos de la Tierra'),
                                (2, 'Sistemas de Información y Comunicaciones'),
                                (3, 'Procesos Productivos'),
                                (4, 'Ciencias de la alimentación'),
                                (5, 'Ciencias ambientales'),
                                (6, 'Ciencias de la salud'),
                                (7, 'Artes y humanidades'),
                                (8, 'Estudios Culturales'),
                                (9, 'Procesos Sociales');

INSERT INTO tronco VALUES (1, 'Tronco General Divisional'), (2, 'Tronco Básico de Carrera'),
                          (3, 'Tronco Interdivisional de Fromación Interdisciplinaria'),
                          (4, 'Tronco Específico de Carrera'), (5, 'Tronco de Integración');





