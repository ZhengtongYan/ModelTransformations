LOAD DATA
INFILE '/home/oracle/Desktop/Dataset/SocialNetwork/person_knows_person_0_0.csv'
BADFILE 'personKnowsPerson.bad'
DISCARDFILE 'personKnowsPerson.dsc'
INSERT
INTO TABLE knows
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS	  
( personIdFrom INTEGER EXTERNAL,
	personIdTo INTEGER EXTERNAL,
	creationDate
)
