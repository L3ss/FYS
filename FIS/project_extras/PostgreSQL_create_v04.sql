DROP SCHEMA IF EXISTS fys CASCADE;
CREATE SCHEMA IF NOT EXISTS fys;

DROP TABLE IF EXISTS fys.Plane;
DROP TABLE IF EXISTS fys.Flight;
DROP TABLE IF EXISTS fys.Flightdate;
DROP TABLE IF EXISTS fys.InternetLog;
DROP TABLE IF EXISTS fys.Person;
DROP TABLE IF EXISTS fys.Passenger;
DROP TABLE IF EXISTS fys.Id;
DROP TABLE IF EXISTS fys.Country;
DROP TABLE IF EXISTS fys.Airport;
DROP TABLE IF EXISTS fys.Leg;
DROP TABLE IF EXISTS fys.BoardingPass;
DROP TABLE IF EXISTS fys.Booking;
DROP TABLE IF EXISTS fys.Bag;
DROP TABLE IF EXISTS fys.Ticket;
DROP TABLE IF EXISTS fys.Internet;
DROP TABLE IF EXISTS fys.InternetHour;
DROP TABLE IF EXISTS fys.InternetFlight;
DROP TABLE IF EXISTS fys.Agent;
DROP TABLE IF EXISTS fys.Seat;
DROP TABLE IF EXISTS fys.CheckedBag;
DROP TABLE IF EXISTS fys.Seat_has_Plane;


CREATE TABLE IF NOT EXISTS fys.Plane
(
  planecode   INT NULL,
  planetype   VARCHAR(45) NULL,
  PRIMARY KEY (planecode)
);

CREATE TABLE IF NOT EXISTS fys.Person
(
  personcode   INT NULL,
  firstname    VARCHAR(45) NULL,
  lastname     VARCHAR(45) NULL,
  dob          VARCHAR(45) NULL,
  password     VARCHAR(45) NULL,
  PRIMARY KEY (personcode)
);

CREATE TABLE IF NOT EXISTS fys.Bag
(
  bagcode     INT NULL,
  delivered   VARCHAR(45) NULL,
  PRIMARY KEY (bagcode)
);


--
-- Create dependent tables
--
CREATE TABLE IF NOT EXISTS fys.Flight
(
  flightcode   INT NULL,
  price        VARCHAR(45) NULL,
  planecode    INT NULL,
  PRIMARY KEY (flightcode)
);

CREATE TABLE IF NOT EXISTS fys.Flightdate
(
  flightdate   VARCHAR(45) NULL,
  flighttime   VARCHAR(45) NULL,
  flightcode   INT NULL,
  PRIMARY KEY (flightdate)
);

CREATE TABLE IF NOT EXISTS fys.Internet
(
  internetcode       INT NULL,
  activemacaddress   VARCHAR(45) NULL,
  ticketcode         INT NULL,
  PRIMARY KEY (internetcode)
);

CREATE TABLE IF NOT EXISTS fys.InternetLog
(
  internetlogcode   INT NULL,
  startdatetime     VARCHAR(45) NULL,
  macaddress        VARCHAR(45) NULL,
  ipaddress         VARCHAR(45) NULL,
  internetcode      INT NULL,
  PRIMARY KEY (internetlogcode)
);

CREATE TABLE IF NOT EXISTS fys.Passenger
(
  passengercode   INT NULL,
  email           VARCHAR(45) NULL,
--  ticketcode      INT NULL,
  ticketcode      INT NULL,
  personcode      INT NULL,
  PRIMARY KEY (passengercode)
);

CREATE TABLE IF NOT EXISTS fys.Id
(
  idcode          INT NULL,
  idcity          VARCHAR(45) NULL,
  passengercode   INT NULL,
  PRIMARY KEY (idcode)
);

CREATE TABLE IF NOT EXISTS fys.Country
(
  countrycode2   INT NULL,
  countrycode3   VARCHAR(45) NULL,
  countryname    VARCHAR(45) NULL,
  Idcode         INT NULL,
  PRIMARY KEY (countrycode2)
);

CREATE TABLE IF NOT EXISTS fys.Airport
(
  airportcode     INT NULL,
  airportcity    VARCHAR(45) NULL,
  arrivaltax     INT NULL,
  departuretax   INT NULL,
  countrycode    INT NULL,
  PRIMARY KEY (airportcode)
);

CREATE TABLE IF NOT EXISTS fys.Leg
(
  legcode               INT NULL,
  arrivaltimeoffset     VARCHAR(45) NULL,
  departuretimeoffset   VARCHAR(45) NULL,
  internetlogcode       INT NULL,
  flightcode            INT NULL,
  airportcode_orig       INT NULL,
  airportcode_dest       INT NULL,
  PRIMARY KEY (legcode)
);

CREATE TABLE IF NOT EXISTS fys.BoardingPass
(
  boardingcode   INT NULL,
  legcode        INT NULL,
  flightdate     VARCHAR(45) NULL,
  PRIMARY KEY (boardingcode)
);

CREATE TABLE IF NOT EXISTS fys.Booking
(
  bookingcode   INT NULL,
  legcode       INT NULL,
  PRIMARY KEY (bookingcode)
);

CREATE TABLE IF NOT EXISTS fys.Ticket
(
--  ticketcode      INT NULL,
  ticketcode     INT NULL,
--  flightdate     VARCHAR(45) NULL,
  flightdate     VARCHAR(45) NULL,
  boardingcode   INT NULL,
  bookingcode    INT NULL,
  bagcode        INT NULL,
  PRIMARY KEY (ticketcode)
);

CREATE TABLE IF NOT EXISTS fys.InternetHour
(
  hoursavailable   INT NULL,
  hoursused        VARCHAR(45) NULL DEFAULT '',
  internetcode     INT NULL,
  PRIMARY KEY (hoursavailable)
);

CREATE TABLE IF NOT EXISTS fys.InternetFlight
(
  flightsavailable   INT NULL,
  flightsused        VARCHAR(45) NULL,
  internetcode       INT NULL,
  PRIMARY KEY (flightsavailable)
);

CREATE TABLE IF NOT EXISTS fys.Agent
(
  agentcode    INT NULL,
  personcode   INT NULL,
  PRIMARY KEY (agentcode)
);

CREATE TABLE IF NOT EXISTS fys.Seat
(
  seatcode       INT NULL,
  boardingcode   INT NULL,
  PRIMARY KEY (seatcode)
);

CREATE TABLE IF NOT EXISTS fys.CheckedBag
(
  checkedbagcode   INT NULL,
  indatetime       VARCHAR(45) NULL,
  outdatetime      VARCHAR(45) NULL,
  bagcode          INT NULL,
  legcode          INT NULL,
  PRIMARY KEY (checkedbagcode)
);

CREATE TABLE IF NOT EXISTS fys.Seat_has_Plane
(
  seatcode    INT NULL,
  planecode   INT NULL,
  PRIMARY KEY (seatcode,planecode)
);


--
-- Insert Foreign keys
--

ALTER TABLE fys.Flight
  ADD CONSTRAINT fk_Flight_Plane1 FOREIGN KEY (planecode) REFERENCES fys.Plane (planecode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Flightdate
  ADD CONSTRAINT fk_Flightdate_Flight1 FOREIGN KEY (flightcode) REFERENCES fys.Flight (flightcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Internet
  ADD CONSTRAINT fk_Internet_Ticket1 FOREIGN KEY (ticketcode) REFERENCES fys.Ticket (ticketcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.InternetLog
  ADD CONSTRAINT fk_InternetLog_Internet1 FOREIGN KEY (internetcode) REFERENCES fys.Internet (internetcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Passenger
  ADD CONSTRAINT fk_Passenger_Person1 FOREIGN KEY (personcode) REFERENCES fys.Person (personcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Passenger_Ticket1 FOREIGN KEY (ticketcode) REFERENCES fys.Ticket (ticketcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Id
  ADD CONSTRAINT fk_Id_Passenger1 FOREIGN KEY (passengercode) REFERENCES fys.Passenger (passengercode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Country
  ADD CONSTRAINT fk_Country_Id1 FOREIGN KEY (Idcode) REFERENCES fys.Id (idcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Airport
  ADD CONSTRAINT fk_Airport_Country1 FOREIGN KEY (countrycode) REFERENCES fys.Country (countrycode2) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Leg
  ADD CONSTRAINT fk_Leg_InternetLog1 FOREIGN KEY (internetlogcode) REFERENCES fys.InternetLog (internetlogcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Leg_Flight1 FOREIGN KEY (flightcode) REFERENCES fys.Flight (flightcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Leg_Airport1 FOREIGN KEY (airportcode_orig) REFERENCES fys.Airport (airportcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Leg_Airport2 FOREIGN KEY (airportcode_dest) REFERENCES fys.Airport (airportcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.BoardingPass
  ADD CONSTRAINT fk_BoardingPass_Leg1 FOREIGN KEY (legcode) REFERENCES fys.Leg (legcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_BoardingPass_Flightdate1 FOREIGN KEY (flightdate) REFERENCES fys.Flightdate (flightdate) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Booking
  ADD CONSTRAINT fk_Booking_Leg1 FOREIGN KEY (legcode) REFERENCES fys.Leg (legcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Ticket
  ADD CONSTRAINT fk_Ticket_Flightdate1 FOREIGN KEY (flightdate) REFERENCES fys.Flightdate (flightdate) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Ticket_BoardingPass1 FOREIGN KEY (boardingcode) REFERENCES fys.BoardingPass (boardingcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Ticket_Booking1 FOREIGN KEY (bookingcode) REFERENCES fys.Booking (bookingcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Ticket_Bag1 FOREIGN KEY (bagcode) REFERENCES fys.Bag (bagcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.InternetHour
  ADD CONSTRAINT fk_InternetHour_Internet FOREIGN KEY (internetcode) REFERENCES fys.Internet (internetcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.InternetFlight
  ADD CONSTRAINT fk_InternetFlight_Internet1 FOREIGN KEY (internetcode) REFERENCES fys.Internet (internetcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Agent
  ADD CONSTRAINT fk_Agent_Person1 FOREIGN KEY (personcode) REFERENCES fys.Person (personcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Seat
  ADD CONSTRAINT fk_Seat_BoardingPass1 FOREIGN KEY (boardingcode) REFERENCES fys.BoardingPass (boardingcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.CheckedBag
  ADD CONSTRAINT fk_CheckedBag_Bag1 FOREIGN KEY (bagcode) REFERENCES fys.Bag (bagcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_CheckedBag_Leg1 FOREIGN KEY (legcode) REFERENCES fys.Leg (legcode) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE fys.Seat_has_Plane
  ADD CONSTRAINT fk_Seat_has_Plane_Seat1 FOREIGN KEY (seatcode) REFERENCES fys.Seat (seatcode) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT fk_Seat_has_Plane_Plane1 FOREIGN KEY (planecode) REFERENCES fys.Plane (planecode) ON DELETE NO ACTION ON UPDATE NO ACTION;
  
COMMIT;
