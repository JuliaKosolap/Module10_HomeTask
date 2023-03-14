INSERT INTO client (name)
VALUES
('Ivan'),
('Karl'),
('John'),
('Shane'),
('Lena'),
('Alex'),
('Kate'),
('Nik'),
('Anna'),
('Andrew');


INSERT INTO planet (id, name)
VALUES
('MAR1', 'Mars'),
('VEN1', 'Venera'),
('JUP1', 'Jupiter'),
('SAT1', 'Saturn'),
('EAR1', 'Earth');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id)
VALUES ('2023-01-01 00:00:00', 1, 'MAR1', 'VEN1'),
       ('2023-01-02 01:00:00', 2, 'VEN1', 'MAR1'),
       ('2023-01-03 02:00:00', 3, 'VEN1', 'JUP1'),
       ('2023-01-04 03:00:00', 4, 'JUP1', 'VEN1'),
       ('2023-01-05 04:00:00', 5, 'SAT1', 'VEN1'),
       ('2023-01-06 05:00:00', 6, 'EAR1', 'SAT1'),
       ('2023-01-07 06:00:00', 7, 'SAT1', 'EAR1'),
       ('2023-01-08 07:00:00', 8, 'JUP1', 'VEN1'),
       ('2023-01-09 08:00:00', 9, 'EAR1', 'SAT1'),
       ('2023-01-10 09:00:00', 10, 'SAT1', 'VEN1')
       ;