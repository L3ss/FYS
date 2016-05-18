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
 * SERIAL values: personcode, bagcode, internetcode, internetlogcode, flightcode
 *                passengercode, idcode, legcode, boardingcode, bookingcode, ticketcode
 *
 * INSERT INTO fys.
 * () VALUES
 * ();
 */

SET search_path TO fys;

-- RESET SERIAL SEQUENCES FOR TESTING PURPOSES
ALTER SEQUENCE fys.flight_flightcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.person_personcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.internet_internetcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.internetlog_internetlogcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.passenger_passengercode_seq RESTART WITH 1;
ALTER SEQUENCE fys.id_idcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.leg_legcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.boardingpass_boardingcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.booking_bookingcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.ticket_ticketcode_seq RESTART WITH 1;
ALTER SEQUENCE fys.bag_bagcode_seq RESTART WITH 1;


INSERT INTO fys.Person
(firstname, lastname,       dob,                email,   password) VALUES
(   'arno','beekman','19711214','arno.beekman@hva.nl','welkom123');

INSERT INTO fys.Plane
(planecode,   planetype) VALUES
(     3001,'boeing 767');

INSERT INTO fys.Flight
(price,planecode) VALUES
(  250,     3001);

INSERT INTO fys.Flightdate
(flightdate,flighttime,flightcode) VALUES
(  20010911,      0759,         1);

INSERT INTO fys.Internet
(activemacaddress,ticketcode) VALUES
('00:11:22:33:44',      NULL);

INSERT INTO fys.InternetLog
(startdatetime,       macaddress,         ipaddress,internetcode) VALUES
( 200109110800, '00:11:22:33:44', '010.000.005.001',           1);

INSERT INTO fys.Passenger
(ticketcode,personcode) VALUES
(      NULL,         1); -- update ticketcode below!

INSERT INTO fys.Id
(idcity,passengercode) VALUES
( 'AMS',            1);

INSERT INTO fys.Country
(countrycode2,countrycode3,  countryname,idcode) VALUES
(        0011,        'nl','netherlands',     1);

INSERT INTO fys.Airport
(airportcode,airportcity,arrivaltax,departuretax,countrycode) VALUES
(       0051,'amsterdam',       016,         021,       0011),
(       0052,'rotterdam',       016,         021,       0011);

INSERT INTO fys.Leg
(arrivaltimeoffset,departuretimeoffset,internetlogcode,flightcode,airportcode_orig,airportcode_dest) VALUES
(             0047,              -0600,               1,        1,            0051,            0052);

INSERT INTO fys.Boardingpass
(legcode,flightdate) VALUES
(      1,  20010911);

INSERT INTO fys.Booking
(legcode) VALUES
(      1);

INSERT INTO fys.Bag
(delivered) VALUES
('in transit');

INSERT INTO fys.Ticket
(flightdate, boardingcode, bookingcode, bagcode) VALUES
(  20010911,            1,           1,       1);

INSERT INTO fys.Agent
(agentcode,personcode) VALUES
(     1021,         1);




-- FIX FOREIGN-KEY LOOP ISSUES
UPDATE fys.Internet
SET ticketcode=1 WHERE internetcode=1 AND activemacaddress='00:11:22:33:44';

UPDATE fys.Id
SET passengercode=1 WHERE idcode=1 AND idcity='AMS';

UPDATE fys.Passenger
SET ticketcode=1 WHERE passengercode=1 AND personcode=1;


COMMIT;
