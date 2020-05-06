-- IT18137156
-- Aluthgedara C.R.B.
-- Doctor Service

create database pafHospitalManagementDB2020;

use pafHospitalManagementDB2020;

-- Doctor table --------------
DROP TABLE IF EXISTS Doctor;

CREATE TABLE IF NOT EXISTS Doctor (
  DocID int(4) NOT NULL,
  DocFName varchar(20) NOT NULL,
  DocLName varchar(20) NOT NULL,
  DocPosition varchar(40) NOT NULL,
  DocFee varchar(20) NOT NULL,
  MobileNo int(12) NOT NULL,
  DocAddress varchar(150) NOT NULL,
  hosID int(11) NOT NULL,
  PRIMARY KEY (DocID)
);
select * from Doctor;


INSERT INTO doctor VALUES ('0001', 'Henry', 'Thomas', 'Eye Specialist', 1200, 0779098399, '11/C15, Kadawatha', 1);
INSERT INTO doctor VALUES ('0002', 'Ishan', 'Alvis', 'Dentist', 2500, 0711098392, '1/E2, Kelaniya', 1);
INSERT INTO doctor VALUES ('0003', 'Sahan', 'Medagedara', 'Neurosurgeon', 2500, 0769898399, '143/C, Rathnepura', 2);
INSERT INTO doctor VALUES ('0004', 'Pasindu', 'Bandula', 'Brain Surgeon', 2000, 0726998399, '133/A, Colombo', 3);



-- Hospitals table for retrive Hospital IDs --------------
DROP TABLE IF EXISTS Hospitals;

CREATE TABLE IF NOT EXISTS Hospitals (
  hostId int(11) NOT NULL ,
  hosName varchar(60) NOT NULL,
  address varchar(60) NOT NULL,
  contNum int(11) NOT NULL,
  hosCharges double NOT NULL,
  PRIMARY KEY (hostId)
);

INSERT INTO `hospitals` (`hostId`, `hosName`, `address`, `contNum`, `hosCharges`) VALUES
(1, 'Asiri Hospitals', '112/E,Fort', 112830078, 122),
(2, 'Nawaloka Hospitals', '12/A, Rajagiriya', 112934455, 1078),
(3, 'Lanka Hospitals', '123/C,Rajagiriya', 112930055, 3456),
(4, 'ENT', '43/E,Rajagiriya', 112934466, 4000);




