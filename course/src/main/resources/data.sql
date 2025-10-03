-- Insert Buildings
INSERT INTO building (name) VALUES ('T1');
INSERT INTO building (name) VALUES ('T2');

-- Insert Floors for T1
INSERT INTO floor (name, building_id) VALUES ('Ground Floor', 1);
INSERT INTO floor (name, building_id) VALUES ('First Floor', 1);
INSERT INTO floor (name, building_id) VALUES ('Second Floor', 1);

-- Insert Rooms for T1 - Ground Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Lounge', 'CONFERENCE_ROOM', 8, 1);

-- Insert Rooms for T1 - First Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Evenimente', 'CONFERENCE_ROOM', 25, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Side-evenimente', 'CONFERENCE_ROOM', 5, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Stand-up chat room', 'CONFERENCE_ROOM', 15, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('La terasa', 'CONFERENCE_ROOM', 10, 2);

-- Insert Rooms for T1 - Second Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Gaming', 'CONFERENCE_ROOM', 11, 3);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Tenis', 'CONFERENCE_ROOM', 5, 3);

-- Insert Floors for T2
INSERT INTO floor (name, building_id) VALUES ('First Floor', 2);
INSERT INTO floor (name, building_id) VALUES ('Second Floor', 2);

-- Insert Rooms for T2 - First Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('404', 'CONFERENCE_ROOM', 10, 2);

-- Insert Rooms for T2 - Second Floor
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Outland', 'CONFERENCE_ROOM', 10, 3);

-- Insert Office Rooms
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Office Room Ground Floor', 'OFFICE_ROOM', 11, 1);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Office Room First Floor', 'OFFICE_ROOM', 17, 2);
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Office Room Second Floor', 'OFFICE_ROOM', 16, 3);

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

-- Insert Seats for 404 (10 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 8);

-- Insert Seats for Outland (10 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 9);

-- Insert Seats for Office Room Ground Floor (11 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 10);

-- Insert Seats for Office Room First Floor (17 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 16', 11);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 17', 11);

-- Insert Seats for Office Room Second Floor (17 seats)
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 11', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 12);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 16', 12);

-- Insert Sample Users
INSERT INTO user (name, email) VALUES ('Bogdan David', 'bogdan.david@email.com');
INSERT INTO user (name, email) VALUES ('Test Test', 'test.test@email.com');
INSERT INTO user (name, email) VALUES ('Tudor Arabagiu', 'tudor.arabagiu@email.com');
