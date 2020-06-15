USE Hotel;



INSERT INTO Guest (FirstName, LastName, Address, City, State, Zip, Phone)
VALUES ('Ashley', 'Tulloch', '111 Love Ln', 'Peace', 'CA', '75708', '214-738-8998'),
('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '291-553-0508'),
('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '478-277-9632'),
('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '308-494-0198'),
('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '214-730-0298'),
('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '377-507-0974'),
('Zachery', 'Luechtefeld', '7 Popular Dr.', 'Arvada', 'CO', '80003', '814-485-2615'),
('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '279-491-0960'),
('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '446-396-6785'),
('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '834-727-1001'),
('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '446-351-6860'),
('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '231-893-2755');

INSERT INTO Type (TypeID, Description, StandardOccupancy, MaxOccupancy, ExtraPerson)
VALUES (1, 'Single', 2, 2, 0),
(2, 'Double', 2, 4, 10),
(3, 'Suite', 3, 8, 20);

INSERT INTO Amenities (AmenitiesID, AmenityType)
VALUES (1, 'Microwave'),
(2, 'Refrigerator'),
(3, 'Oven'),
(4, 'Jacuzzi');




INSERT INTO Room (RoomNum, TypeID, ADAAccessible, BasePrice)
VALUES (201, 2, FALSE, 199.990),
(202, 2, TRUE, 174.99),
(203, 2, FALSE, 199.99),
(204, 2, TRUE, 174.99),
(205, 1, FALSE, 174.99),
(206, 1, FALSE, 149.99),
(207, 1, FALSE, 174.99),
(208, 1, TRUE, 149.99),
(301, 2, FALSE, 199.99),
(302, 2, TRUE, 174.99),
(303, 2, FALSE, 199.99),
(304, 2, TRUE, 174.99),
(305, 1, FALSE, 174.99),
(306, 1, TRUE, 149.99),
(307, 1, FALSE, 174.99),
(308, 1, TRUE, 149.99),
(401, 3, TRUE, 399.99),
(402, 3, TRUE, 399.99);


INSERT INTO Reservations (GuestID, Adults, Children, StartDate, EndDate, TotalRoomCost)
VALUES (2, 1, 0, '2023/02/02', '2023/02/04', 299.98),
(3, 2, 1, '2023/02/05', '2023/02/10', 999.95),
(4, 2, 0, '2023/02/22', '2023/02/24', 349.98),
(5, 2, 2, '2023/03/06', '2023/03/07', 199.99),
(1, 1, 1, '2023/03/17', '2023/03/20', 524.97),
(6, 3, 0, '2023/03/18', '2023/03/23', 924.95),
(7, 2, 2, '2023/03/29', '2023/03/31', 349.98),
(8, 2, 0, '2023/03/31', '2023/04/05', 874.95),
(9, 1, 0, '2023/04/09', '2023/04/13', 799.96),
(10, 1, 1, '2023/04/23', '2023/04/24', 174.99),
(11, 2, 4, '2023/05/30', '2023/06/02', 1199.97),
(12, 2, 0, '2023/06/10', '2023/06/14', 599.96),
(12, 1, 0, '2023/06/10', '2023/06/14', 599.96),
(6, 3, 0, '2023/06/17', '2023/06/18', 184.99),
(1, 2, 0, '2023/06/28', '2023/07/02', 699.96),
(9, 3, 1, '2023/07/13', '2023/07/14', 184.99),
(10, 4, 2, '2023/07/18', '2023/07/21', 1259.97),
(3, 2, '1', '2023/07/28', '2023/07/29', 199.99),
(3, 1, 0, '2023/08/30', '2023/09/01', 349.98),
(2, 2, 0, '2023/09/16', '2023/09/17', 149.99),
(5, 2, 2, '2023/09/13', '2023/09/15', 399.98),
(4, 2, 2, '2023/11/22', '2023/11/25', 1199.97),
(2, 2, 0, '2023/11/22', '2023/11/25', 449.97),
(2, 2, 2, '2023/11/22', '2023/11/25', 599.97),
(11, 2, 0, '2023/12/24', '2023/12/28', 699.96);

INSERT INTO RoomReservations(ReservationID, RoomNum)
VALUES (1, 308),
(2, 203),
(3, 305),
(4, 201),
(5, 307),
(6, 302),
(7, 202),
(8, 304),
(9, 301),
(10, 207),
(11, 401),
(12, 206),
(13, 208),
(14, 304),
(15, 205),
(16, 204),
(17, 401),
(18, 303),
(19, 305),
(20, 208),
(21, 203),
(22, 401),
(23, 206),
(24, 301),
(25, 302),
(0, 306),
(0, 402);

INSERT INTO RoomAmenities (RoomNum, AmenitiesID)
VALUES (201, 1),
(201, 4),
(202, 2),
(203, 1),
(203, 4),
(204, 2),
(205, 1),
(205, 2),
(205, 4),
(206, 1),
(206, 2),
(207, 1),
(207, 2),
(207, 4),
(208, 1),
(208, 2),
(301, 1),
(301, 4),
(302, 2),
(303, 1),
(303, 4),
(304, 2),
(305, 1),
(305, 2),
(305, 4),
(306, 1),
(306, 2),
(307, 1),
(307, 2),
(307, 4),
(308, 1),
(308, 2),
(401, 1),
(401, 2),
(401, 3),
(402, 1),
(402, 2),
(402, 3);






 

-- Delete Jeremiah Pendergrass(GuestID = 8) from reservations
-- First Delete from Foreign Key
-- DELETE FROM Reservations
-- WHERE GuestID = 8;

-- Then Delete from the Guest List
-- DELETE FROM GUEST
-- WHERE GuestID = 8;







