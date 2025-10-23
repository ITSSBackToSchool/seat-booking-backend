-- Insert Buildings
INSERT INTO building (id, name) VALUES (1, 'T1');
INSERT INTO building (id, name) VALUES (2, 'T2');

-- Insert Floors for T1
INSERT INTO floor (id, name, building_id) VALUES (1, 'Ground Floor', 1);
INSERT INTO floor (id, name, building_id) VALUES (2, 'First Floor', 1);
INSERT INTO floor (id, name, building_id) VALUES (3, 'Second Floor', 1);

-- Insert Floors for T2
INSERT INTO floor (id, name, building_id) VALUES (4, 'First Floor', 2);
INSERT INTO floor (id, name, building_id) VALUES (5, 'Second Floor', 2);

-- Insert DESK_ROOM (Sali de birou) for T1
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (1, 'Desk Area 0', 'DESK_ROOM', 11, 1);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (2, 'Desk Area 1', 'DESK_ROOM', 17, 2);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (3, 'Desk Area 2', 'DESK_ROOM', 16, 3);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (4, 'Stand-up Area', 'DESK_ROOM', 2, 3);

-- Insert CONFERENCE_ROOM (Sali de sedinta) for T1
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (5, 'Evenimente', 'CONFERENCE_ROOM', 25, 2);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (6, 'Side-evenimente', 'CONFERENCE_ROOM', 5, 2);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (7, 'Stand-up Chat room', 'CONFERENCE_ROOM', 15, 2);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (8, 'La terasa', 'CONFERENCE_ROOM', 10, 2);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (9, 'Tenis', 'CONFERENCE_ROOM', 5, 3);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (10, 'Gaming', 'CONFERENCE_ROOM', 11, 3);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (11, 'Lounge', 'CONFERENCE_ROOM', 8, 1);

-- Insert CONFERENCE_ROOM for T2
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (12, '404', 'CONFERENCE_ROOM', 10, 4);
INSERT INTO room (id, name, room_type, seat_count, floor_id) VALUES (13, 'Outland', 'CONFERENCE_ROOM', 10, 5);

-- Insert Seats for Desk Area 0 (11 seats) - Ground Floor T1
INSERT INTO seat (id, seat_number, room_id) VALUES (1, 'Seat 1', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (2, 'Seat 2', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (3, 'Seat 3', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (4, 'Seat 4', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (5, 'Seat 5', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (6, 'Seat 6', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (7, 'Seat 7', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (8, 'Seat 8', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (9, 'Seat 9', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (10, 'Seat 10', 1);
INSERT INTO seat (id, seat_number, room_id) VALUES (11, 'Seat 11', 1);

-- Insert Seats for Desk Area 1 (17 seats) - First Floor T1
INSERT INTO seat (id, seat_number, room_id) VALUES (12, 'Seat 1', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (13, 'Seat 2', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (14, 'Seat 3', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (15, 'Seat 4', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (16, 'Seat 5', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (17, 'Seat 6', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (18, 'Seat 7', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (19, 'Seat 8', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (20, 'Seat 9', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (21, 'Seat 10', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (22, 'Seat 11', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (23, 'Seat 12', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (24, 'Seat 13', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (25, 'Seat 14', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (26, 'Seat 15', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (27, 'Seat 16', 2);
INSERT INTO seat (id, seat_number, room_id) VALUES (28, 'Seat 17', 2);

-- Insert Seats for Desk Area 2 (16 seats) - Second Floor T1
INSERT INTO seat (id, seat_number, room_id) VALUES (29, 'Seat 1', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (30, 'Seat 2', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (31, 'Seat 3', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (32, 'Seat 4', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (33, 'Seat 5', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (34, 'Seat 6', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (35, 'Seat 7', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (36, 'Seat 8', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (37, 'Seat 9', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (38, 'Seat 10', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (39, 'Seat 11', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (40, 'Seat 12', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (41, 'Seat 13', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (42, 'Seat 14', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (43, 'Seat 15', 3);
INSERT INTO seat (id, seat_number, room_id) VALUES (44, 'Seat 16', 3);

-- Insert Seats for Stand-up Area (2 stand-up desks) - Second Floor T1
INSERT INTO seat (id, seat_number, room_id) VALUES (45, 'Stand-up 1', 4);
INSERT INTO seat (id, seat_number, room_id) VALUES (46, 'Stand-up 2', 4);
