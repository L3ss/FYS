DROP SCHEMA IF EXISTS fys01 CASCADE;
CREATE SCHEMA IF NOT EXISTS fys01;

DROP TABLE IF EXISTS fys01.Plane;
DROP TABLE IF EXISTS fys01.Flight;
DROP TABLE IF EXISTS fys01.Flightdate;
DROP TABLE IF EXISTS fys01.InternetLog;
DROP TABLE IF EXISTS fys01.Person;
DROP TABLE IF EXISTS fys01.Passenger;
DROP TABLE IF EXISTS fys01.Id;
DROP TABLE IF EXISTS fys01.Country;
DROP TABLE IF EXISTS fys01.Airport;
DROP TABLE IF EXISTS fys01.Leg;
DROP TABLE IF EXISTS fys01.BoardingPass;
DROP TABLE IF EXISTS fys01.Booking;
DROP TABLE IF EXISTS fys01.Bag;
DROP TABLE IF EXISTS fys01.Ticket;
DROP TABLE IF EXISTS fys01.Internet;
DROP TABLE IF EXISTS fys01.InternetHour;
DROP TABLE IF EXISTS fys01.InternetFlight;
DROP TABLE IF EXISTS fys01.Agent;
DROP TABLE IF EXISTS fys01.Seat;
DROP TABLE IF EXISTS fys01.CheckedBag;
DROP TABLE IF EXISTS fys01.Seat_has_Plane;


CREATE TABLE IF NOT EXISTS fys01.Plane
(
  planecode   INT NOT NULL,
  planetype   VARCHAR(45) NULL,
  PRIMARY KEY (planecode)
);

CREATE TABLE IF NOT EXISTS fys01.Person
(
  personcode   INT NOT NULL,
  firstname    VARCHAR(45) NULL,
  lastname     VARCHAR(45) NULL,
  dob          VARCHAR(45) NULL,
  password     VARCHAR(45) NULL,
  PRIMARY KEY (personcode)
);

CREATE TABLE IF NOT EXISTS fys01.Bag
(
  bagcode     INT NOT NULL,
  delivered   VARCHAR(45) NULL,
  PRIMARY KEY (bagcode)
);


--
-- Create dependent tables
--
CREATE TABLE IF NOT EXISTS fys01.Flight
(
  flightcode   INT NOT NULL,
  price        VARCHAR(45) NULL,
  planecode    INT NOT NULL,
  PRIMARY KEY (flightcode)
);

CREATE TABLE IF NOT EXISTS fys01.Flightdate
(
  flightdate   INT NOT NULL,
  flighttime   VARCHAR(45) NULL,
  flightcode   INT NOT NULL,
  PRIMARY KEY (flightdate)
);

CREATE TABLE IF NOT EXISTS fys01.Internet
(
  internetcode       INT NOT NULL,
  activemacaddress   VARCHAR(45) NULL,
  ticketcode         INT NULL,
  PRIMARY KEY (internetcode)
);

CREATE TABLE IF NOT EXISTS fys01.InternetLog
(
  internetlogcode   INT NOT NULL,
  startdatetime     VARCHAR(45) NULL,
  macaddress        VARCHAR(45) NULL,
  ipaddress         VARCHAR(45) NULL,
  internetcode      INT NOT NULL,
  PRIMARY KEY (internetlogcode)
);

CREATE TABLE IF NOT EXISTS fys01.Passenger
(
  passengercode   INT NOT NULL,
  email           VARCHAR(45) NULL,
  ticketcode      INT NOT NULL,
  personcode      INT NOT NULL,
  PRIMARY KEY (passengercode)
);

CREATE TABLE IF NOT EXISTS fys01.Id
(
  idcode          INT NOT NULL,
  idcity          VARCHAR(45) NULL,
  passengercode   INT NOT NULL,
  PRIMARY KEY (idcode)
);

CREATE TABLE IF NOT EXISTS fys01.Country
(
  countrycode2   INT NOT NULL,
  countrycode3   VARCHAR(45) NULL,
  countryname    VARCHAR(45) NULL,
  Idcode         INT NOT NULL,
  PRIMARY KEY (countrycode2)
);

CREATE TABLE IF NOT EXISTS fys01.Airport
(
  aiportcode     INT NOT NULL,
  airportcity    VARCHAR(45) NULL,
  arrivaltax     VARCHAR(45) NULL,
  departuretax   VARCHAR(45) NULL DEFAULT '',
  countrycode    INT NOT NULL,
  PRIMARY KEY (aiportcode)
);

CREATE TABLE IF NOT EXISTS fys01.Leg
(
  legcode               INT NOT NULL,
  arrivaltimeoffset     VARCHAR(45) NULL,
  departuretimeoffset   VARCHAR(45) NULL,
  internetlogcode       INT NULL,
  flightcode            INT NOT NULL,
  aiportcode_orig       INT NOT NULL,
  aiportcode_dest       INT NOT NULL,
  PRIMARY KEY (legcode)
);

CREATE TABLE IF NOT EXISTS fys01.BoardingPass
(
  boardingcode   INT NOT NULL,
  legcode        INT NOT NULL,
  flightdate     INT NOT NULL,
  PRIMARY KEY (boardingcode)
);

CREATE TABLE IF NOT EXISTS fys01.Booking
(
  bookingcode   INT NOT NULL,
  legcode       INT NOT NULL,
  PRIMARY KEY (bookingcode)
);

CREATE TABLE IF NOT EXISTS fys01.Ticket
(
  ticketcode     INT NOT NULL,
  flightdate     INT NOT NULL,
  boardingcode   INT NULL,
  bookingcode    INT NOT NULL,
  bagcode        INT NULL,
  PRIMARY KEY (ticketcode)
);

CREATE TABLE IF NOT EXISTS fys01.InternetHour
(
  hoursavailable   INT NOT NULL,
  hoursused        VARCHAR(45) NULL DEFAULT '',
  internetcode     INT NOT NULL,
  PRIMARY KEY (hoursavailable)
);

CREATE TABLE IF NOT EXISTS fys01.InternetFlight
(
  flightsavailable   INT NOT NULL,
  flightsused        VARCHAR(45) NULL,
  internetcode       INT NOT NULL,
  PRIMARY KEY (flightsavailable)
);

CREATE TABLE IF NOT EXISTS fys01.Agent
(
  agentcode    INT NOT NULL,
  personcode   INT NOT NULL,
  PRIMARY KEY (agentcode)
);

CREATE TABLE IF NOT EXISTS fys01.Seat
(
  seatcode       INT NOT NULL,
  boardingcode   INT NULL,
  PRIMARY KEY (seatcode)
);

CREATE TABLE IF NOT EXISTS fys01.CheckedBag
(
  checkedbagcode   INT NOT NULL,
  indatetime       VARCHAR(45) NULL,
  outdatetime      VARCHAR(45) NULL,
  bagcode          INT NOT NULL,
  legcode          INT NOT NULL,
  PRIMARY KEY (checkedbagcode)
);

CREATE TABLE IF NOT EXISTS fys01.Seat_has_Plane
(
  seatcode    INT NOT NULL,
  planecode   INT NOT NULL,
  PRIMARY KEY (seatcode,planecode)
);


--
-- Insert Foreign keys
--

ALTER TABLE fys01.Flight
  ADD CONSTRAINT fk_Flight_Plane1 FOREIGN KEY (planecode) REFERENCES fys01.Plane (planecode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Flightdate
  ADD CONSTRAINT fk_Flightdate_Flight1 FOREIGN KEY (flightcode) REFERENCES fys01.Flight (flightcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Internet
  ADD CONSTRAINT fk_Internet_Ticket1 FOREIGN KEY (ticketcode) REFERENCES fys01.Ticket (ticketcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.InternetLog
  ADD CONSTRAINT fk_InternetLog_Internet1 FOREIGN KEY (internetcode) REFERENCES fys01.Internet (internetcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Passenger
  ADD CONSTRAINT fk_Passenger_Person1 FOREIGN KEY (personcode) REFERENCES fys01.Person (personcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Passenger_Ticket1 FOREIGN KEY (ticketcode) REFERENCES fys01.Ticket (ticketcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Id
  ADD CONSTRAINT fk_Id_Passenger1 FOREIGN KEY (passengercode) REFERENCES fys01.Passenger (passengercode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Country
  ADD CONSTRAINT fk_Country_Id1 FOREIGN KEY (Idcode) REFERENCES fys01.Id (idcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Airport
  ADD CONSTRAINT fk_Airport_Country1 FOREIGN KEY (countrycode) REFERENCES fys01.Country (countrycode2) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Leg
  ADD CONSTRAINT fk_Leg_InternetLog1 FOREIGN KEY (internetlogcode) REFERENCES fys01.InternetLog (internetlogcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Leg_Flight1 FOREIGN KEY (flightcode) REFERENCES fys01.Flight (flightcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Leg_Airport1 FOREIGN KEY (aiportcode_orig) REFERENCES fys01.Airport (aiportcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Leg_Airport2 FOREIGN KEY (aiportcode_dest) REFERENCES fys01.Airport (aiportcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.BoardingPass
  ADD CONSTRAINT fk_BoardingPass_Leg1 FOREIGN KEY (legcode) REFERENCES fys01.Leg (legcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_BoardingPass_Flightdate1 FOREIGN KEY (flightdate) REFERENCES fys01.Flightdate (flightdate) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Booking
  ADD CONSTRAINT fk_Booking_Leg1 FOREIGN KEY (legcode) REFERENCES fys01.Leg (legcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Ticket
  ADD CONSTRAINT fk_Ticket_Flightdate1 FOREIGN KEY (flightdate) REFERENCES fys01.Flightdate (flightdate) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Ticket_BoardingPass1 FOREIGN KEY (boardingcode) REFERENCES fys01.BoardingPass (boardingcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Ticket_Booking1 FOREIGN KEY (bookingcode) REFERENCES fys01.Booking (bookingcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Ticket_Bag1 FOREIGN KEY (bagcode) REFERENCES fys01.Bag (bagcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.InternetHour
  ADD CONSTRAINT fk_InternetHour_Internet FOREIGN KEY (internetcode) REFERENCES fys01.Internet (internetcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.InternetFlight
  ADD CONSTRAINT fk_InternetFlight_Internet1 FOREIGN KEY (internetcode) REFERENCES fys01.Internet (internetcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Agent
  ADD CONSTRAINT fk_Agent_Person1 FOREIGN KEY (personcode) REFERENCES fys01.Person (personcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Seat
  ADD CONSTRAINT fk_Seat_BoardingPass1 FOREIGN KEY (boardingcode) REFERENCES fys01.BoardingPass (boardingcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.CheckedBag
  ADD CONSTRAINT fk_CheckedBag_Bag1 FOREIGN KEY (bagcode) REFERENCES fys01.Bag (bagcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_CheckedBag_Leg1 FOREIGN KEY (legcode) REFERENCES fys01.Leg (legcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys01.Seat_has_Plane
  ADD CONSTRAINT fk_Seat_has_Plane_Seat1 FOREIGN KEY (seatcode) REFERENCES fys01.Seat (seatcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Seat_has_Plane_Plane1 FOREIGN KEY (planecode) REFERENCES fys01.Plane (planecode) ON DELETE NO ACTION ON UPDATE NO ACTION;
