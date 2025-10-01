-- -- Insert Buildings
-- INSERT INTO building (name) VALUES ('T1');
-- INSERT INTO building (name) VALUES ('T2');
--
-- -- Insert Floors for T1
-- INSERT INTO floor (name, building_id) VALUES ('Ground Floor', 1);
-- INSERT INTO floor (name, building_id) VALUES ('First Floor', 1);
-- INSERT INTO floor (name, building_id) VALUES ('Second Floor', 1);
--
-- -- Insert Rooms for T1 - Ground Floor
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Lounge', 'DESK_ROOM', 8, 1);
--
-- -- Insert Rooms for T1 - First Floor
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Evenimente', 'DESK_ROOM', 25, 2);
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Side-evenimente', 'DESK_ROOM', 5, 2);
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Stand-up chat room', 'DESK_ROOM', 15, 2);
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('La terasa', 'DESK_ROOM', 10, 2);
--
-- -- Insert Rooms for T1 - Second Floor
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Gaming', 'DESK_ROOM', 11, 3);
-- INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Tenis', 'DESK_ROOM', 5, 3);
--
-- -- Insert Seats for Lounge (8 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 1);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 1);
--
-- -- Insert Seats for Evenimente (25 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 16', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 17', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 18', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 19', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 20', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 21', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 22', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 23', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 24', 2);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 25', 2);
--
-- -- Insert Seats for Side-evenimente (5 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 3);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 3);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 3);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 3);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 3);
--
-- -- Insert Seats for Stand-up chat room (15 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 4);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 4);
--
-- -- Insert Seats for La terasa (10 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 5);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 5);
--
-- -- Insert Seats for Gaming (11 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 6);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 6);
--
-- -- Insert Seats for Tenis (5 seats)
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 7);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 7);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 7);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 7);
-- INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 7);
--
-- -- Insert Sample Users
-- INSERT INTO users (name, email) VALUES ('Bogdan David', 'bogdan.david@email.com');
-- INSERT INTO users (name, email) VALUES ('Test Test', 'test.test@email.com');


INSERT INTO building (id, name) VALUES (1, 'Building A');
INSERT INTO building (id, name) VALUES (2, 'Building B');


INSERT INTO floor (id, name, building_id) VALUES (1, 'First Floor', 1);
INSERT INTO floor (id, name, building_id) VALUES (2, 'Second Floor', 1);
INSERT INTO floor (id, name, building_id) VALUES (3, 'Ground Floor', 2);


INSERT INTO room (id, name, floor_id, seat_count) VALUES (1, 'Room 101', 1, 20);
INSERT INTO room (id, name, floor_id, seat_count) VALUES (2, 'Room 102', 1, 25);
INSERT INTO room (id, name, floor_id, seat_count) VALUES (3, 'Conference Room A', 2, 50);

INSERT INTO seat (id, seat_number, room_id) VALUES (1, 'A1', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (2, 'A2', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (3, 'B1', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (4, 'C1', 3);

INSERT INTO users (id, user_name, password, email, phone, home_adress)
VALUES (1, 'Bogdan David', 'secret123', 'bogdan.david@email.com', '0712345678', 'Bucuresti');

INSERT INTO users (id, user_name, password, email)
VALUES (2, 'Mihaela Pop', 'pass123', 'ana.pop@email.com');


INSERT INTO reservations (id, reservation_date, start_time, end_time, status, seat_id, user_id, room_id)
VALUES (1, '2025-10-02', '10:00:00', '11:00:00', 'ACTIVE', 1, 1, 1);

INSERT INTO reservations (reservation_date, start_time, end_time, status, room_id, seat_id, user_id)
VALUES ('2025-10-01', '09:00:00', '11:00:00', 'ACTIVE', 1, 1, 1);

