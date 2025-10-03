-- Insert Buildings
INSERT INTO building (name) VALUES ('T1');
INSERT INTO building (name) VALUES ('T2');

-- Insert Floors for T1
INSERT INTO floor (name, building_id) VALUES ('Ground Floor', 1);
INSERT INTO floor (name, building_id) VALUES ('First Floor', 1);
INSERT INTO floor (name, building_id) VALUES ('Second Floor', 1);

-- Insert Floors for T2
INSERT INTO floor (name, building_id) VALUES ('First Floor', 2);
INSERT INTO floor (name, building_id) VALUES ('Second Floor', 2);

-- ============================================
-- T1 - GROUND FLOOR ROOMS
-- ============================================

-- Office Room - Ground Floor (11 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Office - Ground Floor', 'DESK_ROOM', 11, 1);

-- Lounge (8 stand-up desks only)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Lounge', 'RECREATIONAL', 8, 1);

-- ============================================
-- T1 - FIRST FLOOR ROOMS
-- ============================================

-- Office Room - First Floor (17 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Office - First Floor', 'DESK_ROOM', 17, 2);

-- Evenimente (25 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Evenimente', 'CONFERENCE_ROOM', 25, 2);

-- Side-evenimente 1 (5 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Side-evenimente 1', 'CONFERENCE_ROOM', 5, 2);

-- Stand-up Chat room (15 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Stand-up Chat room', 'COLLABORATIVE', 15, 2);

-- La terasa (10 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('La terasa', 'RECREATIONAL', 10, 2);

-- ============================================
-- T1 - SECOND FLOOR ROOMS
-- ============================================

-- Office Room - Second Floor (16 regular + 2 stand-up = 18 total seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Office - Second Floor', 'DESK_ROOM', 18, 3);

-- Tenis (5 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Tenis', 'RECREATIONAL', 5, 3);

-- Gaming (11 seats, including 8 stand-up desks)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Gaming', 'RECREATIONAL', 11, 3);

-- ============================================
-- T2 - FIRST FLOOR ROOMS
-- ============================================

-- 404 (10 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('404', 'CONFERENCE_ROOM', 10, 4);

-- ============================================
-- T2 - SECOND FLOOR ROOMS
-- ============================================

-- Outland (10 seats)
INSERT INTO room (name, room_type, seat_count, floor_id) VALUES ('Outland', 'CONFERENCE_ROOM', 10, 5);

-- ============================================
-- SEATS - T1 GROUND FLOOR
-- ============================================

-- Office - Ground Floor (11 seats) - Room ID 1
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 1', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 2', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 3', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 4', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 5', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 6', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 7', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 8', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 9', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 10', 1);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 11', 1);

-- Lounge (8 stand-up desks) - Room ID 2
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 1', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 2', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 3', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 4', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 5', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 6', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 7', 2);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 8', 2);

-- ============================================
-- SEATS - T1 FIRST FLOOR
-- ============================================

-- Office - First Floor (17 seats) - Room ID 3
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 1', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 2', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 3', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 4', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 5', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 6', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 7', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 8', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 9', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 10', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 11', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 12', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 13', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 14', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 15', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 16', 3);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 17', 3);

-- Evenimente (25 seats) - Room ID 4
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
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 16', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 17', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 18', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 19', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 20', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 21', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 22', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 23', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 24', 4);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 25', 4);

-- Side-evenimente 1 (5 seats) - Room ID 5
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 5);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 5);

-- Stand-up Chat room (15 seats) - Room ID 6
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
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 12', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 13', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 14', 6);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 15', 6);

-- La terasa (10 seats) - Room ID 7
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 6', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 7', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 8', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 9', 7);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 10', 7);

-- ============================================
-- SEATS - T1 SECOND FLOOR
-- ============================================

-- Office - Second Floor (16 regular + 2 stand-up = 18 seats) - Room ID 8
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 1', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 2', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 3', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 4', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 5', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 6', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 7', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 8', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 9', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 10', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 11', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 12', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 13', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 14', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 15', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Desk 16', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 1', 8);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 2', 8);

-- Tenis (5 seats) - Room ID 9
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 4', 9);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 5', 9);

-- Gaming (11 seats, including 8 stand-up) - Room ID 10
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 1', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 2', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Seat 3', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 1', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 2', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 3', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 4', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 5', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 6', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 7', 10);
INSERT INTO seat (seat_number, room_id) VALUES ('Stand-up 8', 10);

-- ============================================
-- SEATS - T2 FIRST FLOOR
-- ============================================

-- 404 (10 seats) - Room ID 11
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

-- ============================================
-- SEATS - T2 SECOND FLOOR
-- ============================================

-- Outland (10 seats) - Room ID 12
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

-- ============================================
-- LOCATIONS FOR WEATHER DATA
-- ============================================

-- Location for T1 Building
INSERT INTO location (building_id, city, district, latitude, longitude, address)
VALUES (1, 'Bucharest', 'District 1', 44.4368, 26.1025, 'T1 Building, Strada Știrbei Vodă 107, București');

-- Location for T2 Building
INSERT INTO location (building_id, city, district, latitude, longitude, address)
VALUES (2, 'Bucharest', 'District 2', 44.4268, 26.0925, 'T2 Building, Bucharest');