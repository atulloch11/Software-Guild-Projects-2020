DROP DATABASE IF EXISTS Hotel;

CREATE DATABASE IF NOT EXISTS Hotel;


USE Hotel;

CREATE TABLE IF NOT EXISTS Guest (
	GuestID INT AUTO_INCREMENT NOT NULL,
	FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(100) NOT NULL,
    State CHAR(2) NOT NULL,
    Zip VARCHAR(10) NOT NULL,
    Phone VARCHAR(13) NOT NULL,
    PRIMARY KEY (GuestID)
    );
    
CREATE TABLE IF NOT EXISTS Reservations (
	ReservationID INT NOT NULL AUTO_INCREMENT,
    GuestID INT NOT NULL,
    Adults INT NOT NULL,
    Children INT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    TotalRoomCost DECIMAL(12, 2) NOT NULL,
    PRIMARY KEY (ReservationID)
    );
    
    CREATE TABLE IF NOT EXISTS Type (
	TypeID INT NOT NULL,
    Description VARCHAR(10) NOT NULL,
    StandardOccupancy INT NOT NUll,
    MaxOccupancy INT NOT NULL,
    ExtraPerson DECIMAL(12, 2) NULL,
    PRIMARY KEY (TypeID)
);
    
    CREATE TABLE IF NOT EXISTS Room (
	RoomNum INT NOT NULL,
    TypeID INT NOT NULL,
    ADAAccessible BOOLEAN NOT NULL,
    BasePrice DECIMAL(12, 2),
    PRIMARY KEY (RoomNum)
	);
    
CREATE TABLE IF NOT EXISTS RoomReservations (
	RoomNum INT NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY pk_RoomReservations (RoomNum, ReservationID)
	);
    

CREATE TABLE IF NOT EXISTS Amenities (
	AmenitiesID INT NOT NULL AUTO_INCREMENT,
    AmenityType VARCHAR(50) NOT NULL,
    PRIMARY KEY(AmenitiesID)
);

CREATE TABLE IF NOT EXISTS RoomAmenities (
	RoomNum INT NOT NULL,
    AmenitiesID INT NOT NULL,
    PRIMARY KEY pk_RoomAmenities (RoomNum, AmenitiesID)
	);
    
    ALTER TABLE Room
		ADD CONSTRAINT fk_RoomType FOREIGN KEY (TypeID) REFERENCES Type (TypeID)
		ON DELETE NO ACTION;
    
    ALTER TABLE Reservations
		ADD CONSTRAINT fk_GuestReservation FOREIGN KEY (GuestID) REFERENCES Guest (GuestID)
        ON DELETE NO ACTION;
        
	ALTER TABLE RoomReservations
		ADD CONSTRAINT fk_RoomReservations_Room FOREIGN KEY (RoomNum) REFERENCES Room (RoomNum)
        ON DELETE NO ACTION;
	ALTER TABLE RoomReservations
		ADD CONSTRAINT fk_RoomReservation_Reservation FOREIGN KEY (ReservationID) REFERENCES Reservations (ReservationID)
        ON DELETE NO ACTION;
	ALTER TABLE RoomAmenities
		ADD CONSTRAINT fk_RoomAmenities_Room FOREIGN KEY (RoomNum) REFERENCES Room (RoomNum)
        ON DELETE NO ACTION;
	ALTER TABLE RoomAmenities
		ADD CONSTRAINT fk_RoomAmenities_Amenities FOREIGN KEY (AmenitiesID) REFERENCES Amenities (AmenitiesID)
        ON DELETE NO ACTION;
    
    
    
    
    
    

