-- =========================
-- BUILDINGS
-- =========================
INSERT INTO building (id, name) VALUES (1, 'T1');
INSERT INTO building (id, name) VALUES (2, 'T2');

-- =========================
-- FLOORS
-- =========================
-- T1 floors
INSERT INTO floor (id, name, building_id) VALUES (1, 'Parter', 1);
INSERT INTO floor (id, name, building_id) VALUES (2, 'Etaj 1', 1);
INSERT INTO floor (id, name, building_id) VALUES (3, 'Etaj 2', 1);

-- T2 floors
INSERT INTO floor (id, name, building_id) VALUES (4, 'Parter', 2);
INSERT INTO floor (id, name, building_id) VALUES (5, 'Etaj 1', 2);
INSERT INTO floor (id, name, building_id) VALUES (6, 'Etaj 2', 2);

-- =========================
-- ROOMS (săli de ședință / evenimente)
-- =========================
-- Clădire T1 ----
-- T1
INSERT INTO room (id, name, seat_count, floor_id) VALUES (1, 'Lounge', 8, 1);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (2, 'Evenimente', 25, 2);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (3, 'Side-evenimente 1', 5, 2);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (4, 'Stand-up Chat room', 15, 2);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (5, 'La terasa', 10, 2);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (6, 'Tenis', 5, 3);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (7, 'Gaming', 11, 3);

-- T2
INSERT INTO room (id, name, seat_count, floor_id) VALUES (8, '404', 10, 5);
INSERT INTO room (id, name, seat_count, floor_id) VALUES (9, 'Outland', 10, 6);


-- =========================
-- SEATS (scaune individuale, doar pe etaje)
-- =========================
-- Clădire T1
INSERT INTO seat (id, seat_number, floor_id) VALUES (1, 'Seat 1', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (2, 'Seat 2', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (3, 'Seat 3', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (4, 'Seat 4', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (5, 'Seat 5', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (6, 'Seat 6', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (7, 'Seat 7', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (8, 'Seat 8', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (9, 'Seat 9', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (10, 'Seat 10', 1);
INSERT INTO seat (id, seat_number, floor_id) VALUES (11, 'Seat 11', 1);

INSERT INTO seat (id, seat_number, floor_id) VALUES (12, 'Seat 1', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (13, 'Seat 2', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (14, 'Seat 3', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (15, 'Seat 4', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (16, 'Seat 5', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (17, 'Seat 6', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (18, 'Seat 7', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (19, 'Seat 8', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (20, 'Seat 9', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (21, 'Seat 10', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (22, 'Seat 11', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (23, 'Seat 12', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (24, 'Seat 13', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (25, 'Seat 14', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (26, 'Seat 15', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (27, 'Seat 16', 2);
INSERT INTO seat (id, seat_number, floor_id) VALUES (28, 'Seat 17', 2);

INSERT INTO seat (id, seat_number, floor_id) VALUES (29, 'Seat 1', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (30, 'Seat 2', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (31, 'Seat 3', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (32, 'Seat 4', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (33, 'Seat 5', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (34, 'Seat 6', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (35, 'Seat 7', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (36, 'Seat 8', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (37, 'Seat 9', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (38, 'Seat 10', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (39, 'Seat 11', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (40, 'Seat 12', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (41, 'Seat 13', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (42, 'Seat 14', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (43, 'Seat 15', 3);
INSERT INTO seat (id, seat_number, floor_id) VALUES (44, 'Seat 16', 3);

-- Clădire T2 (similar, ajustezi seat_count după etaj)

-- Insert Sample Users
INSERT INTO user (name, email) VALUES ('Bogdan David', 'bogdan.david@email.com');
INSERT INTO user (name, email) VALUES ('Test Test', 'test.test@email.com');
