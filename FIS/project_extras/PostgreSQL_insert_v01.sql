/*
 * postgresql insert scsript
 * FYS groep 5 2015/2016
 *
 * Cities:   0001 - 0999
 * Perons:   1001 - 1999
 * Tickets:  2001 - 2999
 * Planes:   3001 - 3999
 * Internet: 4001 - 4999
 * 
 * INSERT INTO fys.
 * () VALUES
 * ();
 */

SET search_path TO fys;

INSERT INTO fys.Person
(personcode,firstname, lastname,       dob,   password) VALUES
(      1001,   'arno','beekman','19711214','welkom123');

INSERT INTO fys.Agent
(agentcode,personcode) VALUES
(     1021,      1001);
  
INSERT INTO fys.Passenger
(passengercode,                email,ticketcode,personcode) VALUES
(         1011,'arno.beekman@hva.nl',      NULL,      1001);

INSERT INTO fys.Id
(idcode,idcity,passengercode) VALUES
(  1031, 'AMS',         NULL);

INSERT INTO fys.Country
(countrycode2,countrycode3,  countryname,idcode) VALUES
(        0011,        'nl','netherlands',  1031);

INSERT INTO fys.Airport
(airportcode,airportcity,arrivaltax,departuretax,countrycode) VALUES
(       0051,'amsterdam',       016,         021,       0011),
(       0052,'rotterdam',       016,         021,       0011);

INSERT INTO fys.Internet
(internetcode,activemacaddress,ticketcode) VALUES
(        4001,'00:11:22:33:44',      NULL);

INSERT INTO fys.InternetLog
(internetlogcode,startdatetime,       macaddress,         ipaddress,internetcode) VALUES
(           4011, 200109110800, '00:11:22:33:44', '010.000.005.001',        4001);

INSERT INTO fys.Plane
(planecode,   planetype) VALUES
(     3001,'boeing 767');

INSERT INTO fys.Flight
(flightcode,price,planecode) VALUES
(      2001,  250,     3001);

INSERT INTO fys.Flightdate
(flightdate,flighttime,flightcode) VALUES
(  20010911,      0759,      2001);

INSERT INTO fys.Leg
(legcode,arrivaltimeoffset,departuretimeoffset,internetlogcode,flightcode,airportcode_orig,airportcode_dest) VALUES
(   2021,             0047,              -0600,           4011,      2001,            0051,            0052);

INSERT INTO fys.Booking
(bookingcode,legcode) VALUES
(       2031,   2021);

INSERT INTO fys.Boardingpass
(boardingcode,legcode,flightdate) VALUES
(        2011,   2021,  20010911);

INSERT INTO fys.Bag
(bagcode,   delivered) VALUES
(   1031,'in transit');

INSERT INTO fys.Ticket
(ticketcode, flightdate, boardingcode, bookingcode, bagcode) VALUES
(      2001,   20010911,         2011,        2031,    1031);


-- FIX FOREIGN-KEY LOOP ISSUES
UPDATE fys.Internet
SET ticketcode=2001 WHERE internetcode=4001 AND activemacaddress='00:11:22:33:44';

UPDATE fys.Id
SET passengercode=1011 WHERE idcode=1031 AND idcity='AMS';

UPDATE fys.Passenger
SET ticketcode=2001 WHERE passengercode=1011 AND email='arno.beekman@hva.nl' AND personcode=1001;


COMMIT;
