Fasten your seatbelts 2015/2016

Flight Information System (FIS)

TODO list (van boven naar beneden afwerken):
	GIT pushen
	live Servlet en PSQL updaten
	HTML pagina updaten
	
	SearchFlights: Arrays van Flights in JSON ?
	SearchFlights class implementeren
	Bags class implementeren
	Boarding class implementeren
	BookFlights class implementeren
	CheckIn class implementeren
	InternetAccess class implementeren
	Error log Class
	
	
18-05-2016
	sessions vanuit Login class bijgehouden en
		gecheckt in elke Class
	Register Class hard coded expire date vervangen
		door "nu + 1 jaar" (nog niet in database)
	
15-05-2016
	GIT gepusht
	live Servlet en PSQL geupdate
	Login & Database Class fix (nog niet OK)
	HTML pagina geupdate
	Register class eerste implementatie: schrijft naar db
	Database Class aangepast: dbInsert erbij voor COMMIT
	PostgreSQL create & insert scripts aangepast
	IDs op auto-increment (=serial in psql) gezet
	key 'email' verplaatst van Passenger naar Person

14-05-2016
	nieuwe Servlet live maken
	HTML pagina geupdate
	hardcoded replies aan alle classes toevoegen alvorens ze te implementeren
	PNG van de ERD geupload
	PSQL insert script
	PSQL create script aangepast vanwege FK-loops (alle NOT NULLs eruit)
	Login Class haalt data uit database
	
13-05-2016
	HTML landing page losstaand gemaakt: /FIS/Java Resources/HTML/rtfm.html
	Core Classes geschreven: Index, Parser, Database, Communication, Login
	Vrijdag de 13e dus opnieuw begonnen ;)
