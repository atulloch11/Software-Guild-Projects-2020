USE Hotel;

-- Query # 1
-- Returns a list of reservations that end in July 2023
-- including the name of guest, room number(s) and reservation dates

SELECT
	CONCAT(g.FirstName, ' ', g.LastName) Name,
    r.RoomNum,
    rs.StartDate,
    rs.EndDate
FROM Guest g
INNER JOIN Reservations rs ON g.GuestID = rs.GuestID
INNER JOIN RoomReservations rr ON rs.ReservationID = rr.ReservationID
INNER JOIN Room r ON rr.RoomNum = r.RoomNum
WHERE rs.EndDate BETWEEN '2023/07/01' AND '2023/07/31';

--       Name      | RoomNum   |   StartDate    |   EndDate
-- Ashley Tulloch    205         2023-06-28       2023-07-02
-- Walter Holaway    204         2023-07-13       2023-07-14
-- Wilfred Vise      401         2023-07-18       2023-07-21
-- Bettyann Simmer   303         2023-07-28       2023-07-29



-- Query # 2
-- returns list of reservations for rooms with a jacuzzi
-- displaying guest's name
-- room number and dates

SELECT 
	Amenities.AmenityType,
    CONCAT(Guest.FirstName, ' ', Guest.LastName) Name,
    Room.RoomNum,
    Reservations.StartDate,
    Reservations.EndDate
FROM Guest 
INNER JOIN Reservations ON Guest.GuestID = Reservations.GuestID
INNER JOIN RoomReservations ON Reservations.ReservationID = RoomReservations.ReservationID
INNER JOIN Room ON RoomReservations.RoomNum = Room.RoomNum
INNER JOIN RoomAmenities ON Room.RoomNum = RoomAmenities.RoomNum
INNER JOIN Amenities ON RoomAmenities.AmenitiesID = Amenities.AmenitiesID
WHERE AmenityType LIKE ('%Jacuzzi%');

--       AmenityType      |   Name      |   RoomNum   |   StartDate   |   EndDate
-- 		  Jacuzzi         | Karie Yang  |    201      | 2023-03-06    | 2023-03-07
-- 		  Jacuzzi         |Bettyann Simmer|   203     | 2023-02-05    | 2023-02-10
-- 		  Jacuzzi         | Karie Yang  |    201      | 2023-09-13    | 2023-09-15
-- 		  Jacuzzi         | Ashley Tulloch  |    205  | 2023-06-28    | 2023-07-02
-- 		  Jacuzzi         | Wilfred Vise  |    207     | 2023-04-23    | 2023-04-24
-- 		  Jacuzzi         | Walter Holaway  |    301      | 2023-04-09    | 2023-04-13
-- 		  Jacuzzi         | Mack Simmer  |    301      | 2023-11-22    | 2023-11-25
-- 		  Jacuzzi         | Bettyan Simmer  |    303     | 2023-07-28    | 2023-07-29
-- 		  Jacuzzi         | Duane Cullison  |    305     | 2023-02-22   | 2023-02-24
-- 		  Jacuzzi         | Bettyan Simmer  |    305     | 2023-08-30    | 2023-09-01
-- 		  Jacuzzi         | Ashley Tulloch  |    307     | 2023-03-17    | 2023-03-20

-- Query # 3
-- all the rooms reserved for a specific guest
-- guest's name, the room reserved, starting date of reservation,
-- and how many people are included

SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) Name,
	Room.RoomNum,
    Reservations.StartDate,
    Reservations.Children + Reservations.Adults AS TotalPeople
FROM Room
INNER JOIN RoomReservations ON Room.RoomNum = RoomReservations.RoomNum
INNER JOIN Reservations ON RoomReservations.ReservationID = Reservations.ReservationID
INNER JOIN Guest ON Reservations.GuestID = Guest.GuestID
WHERE Guest.FirstName = 'Ashley';

--       Name      | RoomNum   |   StartDate    |   TotalPeople
--   Ashley Tulloch    205         2023-03-17           2
--   Ashley Tulloch    205         2023-06-28           2


-- Query # 4
-- returns list of rooms, reservationID, per-room cost
-- include all rooms whether or not there is a reservation

SELECT
		Room.RoomNum,
        Reservations.ReservationID,
		Reservations.TotalRoomCost
FROM Type
LEFT OUTER JOIN Room ON Type.TypeID = Room.TypeID
LEFT OUTER JOIN RoomReservations ON Room.RoomNum = RoomReservations.RoomNum
LEFT OUTER JOIN Reservations ON RoomReservations.ReservationID = Reservations.ReservationID
ORDER BY ISNULL(Reservations.ReservationID), Room.RoomNum ASC;
 
--       RoomNum      | ReservationID   |   TotalRoomCost   
--   		201            4                   199.99 	
--   		202            7                   349.98
--   		203            2                   999.95 
--   		203            21				   399.98
--   		204            16                  184.99
--   		205            15			       699.96
--   		206            12				   599.96
--   		206            23				   449.97
--   		207            10				   174.99
--   		208            13				   599.97
--   		208            20				   149.99
--   		301            9			       799.96
--   		301            24 		           599.97
--   		302            6				   924.95
--   		302            25				   699.96
--   		303            18				   199.99
--   		304            8				   874.95
--   		304            14			       184.99
--   		305            3				   349.98
--   		305            19				   349.98
--   		307            5				   524.97
--   		308            1				   299.98
--   		401            11				   1199.97
--   		401            17				   1259.97
--   		401            22				   1199.97
--   		306            NULL                149.99
--   		402            NULL                419.99                                                                                                                                                                                                                                                                                                                                                                                                                                                                        

-- Query # 5
-- returns all the rooms accommodating three guests
-- reserved on any date in April 2023

SELECT
	Room.RoomNum,
    Reservations.StartDate,
    Reservations.Children + Reservations.Adults AS TotalPeople
FROM Room
INNER JOIN RoomReservations ON Room.RoomNum = RoomReservations.RoomNum
INNER JOIN Reservations ON RoomReservations.ReservationID = Reservations.ReservationID
WHERE ((Reservations.Children + Reservations.Adults = 3) AND (Reservations.StartDate BETWEEN '2023-04-01' AND '2023-04-31'));

-- NO RESULTS

-- Query # 6
-- returns list of all guest names and number of reservations per guest
-- starting with most reservations then by guest last name


SELECT
	Guest.LastName,
    COUNT(Reservations.GuestID) TotalReservations
FROM Guest
INNER JOIN Reservations ON Guest.GuestID = Reservations.GuestID
GROUP BY Guest.LastName
ORDER BY TotalReservations DESC, Guest.LastName;

-- RESULTS: 12 Rows
-- LastName | TotalReservations
-- Simmer   |     4
-- Seery    |     3
-- Cullison |     2
-- Holaway 	|     2
-- Lipton   |     2
-- Tilton   |     2
-- Tison    |     2
-- Tulloch  |     2
-- Vise     |     2
-- Yang     |     2
-- Luechtefeld   |     1
-- Pendergrass   |     1


-- Query # 7
-- displays name, address, phone number
-- based on their phone number

SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) Name,
    Guest.Address,
    Guest.City,
    Guest.State,
    Guest.Zip,
    Guest.Phone
FROM Guest
WHERE Guest.Phone = '214-738-8998';

--       Name        | Address     |  City  |   State  | Zip   |   Phone      | 
--    Ashley Tulloch | 111 Love Ln |  Peace |    CA    | 75708 | 214-738-8998 |  
	
        
    
    
	