-- Insert Buildings
INSERT INTO building (name) VALUES ('T1');
INSERT INTO building (name) VALUES ('T2');

-- Insert Floors for T1
INSERT INTO floor (name, building_id) VALUES ('Ground Floor', 1);
INSERT INTO floor (name, building_id) VALUES ('First Floor', 1);
INSERT INTO floor (name, building_id) VALUES ('Second Floor', 1);

-- Insert Rooms for T1 - Ground Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Lounge', 'DESK_ROOM', 8, 1);

-- Insert Rooms for T1 - First Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Evenimente', 'DESK_ROOM', 25, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Side-evenimente', 'DESK_ROOM', 5, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Stand-up chat room', 'DESK_ROOM', 15, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('La terasa', 'DESK_ROOM', 10, 2);

-- Insert Rooms for T1 - Second Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Gaming', 'DESK_ROOM', 11, 3);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Tenis', 'DESK_ROOM', 5, 3);

-- Insert Seats for Lounge (8 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 1);

-- Insert Seats for Evenimente (25 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 16', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 17', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 18', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 19', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 20', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 21', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 22', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 23', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 24', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 25', 2);

-- Insert Seats for Side-evenimente (5 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 3);

-- Insert Seats for Stand-up chat room (15 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 4);

-- Insert Seats for La terasa (10 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 5);

-- Insert Seats for Gaming (11 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 6);

-- Insert Seats for Tenis (5 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 7);

-- Insert Sample Users
INSERT INTO users (user_name, email, password, phone, home_adress) VALUES ('Bogdan David', 'bogdan.david@email.com', 'password123', '0712345678', 'Bucuresti');
INSERT INTO users (user_name, email, password, phone, home_adress) VALUES ('Test Test', 'test.test@email.com', 'password456', '0722334455', 'Cluj');
