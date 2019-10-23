LOAD DATA
INFILE '/home/oracle/Desktop/Dataset/Customer/person_0_0.csv'
BADFILE 'person.bad'
DISCARDFILE 'person.dsc'
INSERT
INTO TABLE person
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS	  
( id INTEGER EXTERNAL,
	firstName,
	lastName,
	gender,
	birthday DATE 'YYYY-MM-DD',
	creationDate,
	locationIP,
	browserUsed,
	place INTEGER EXTERNAL 
)
