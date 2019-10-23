LOAD DATA
INFILE '/home/oracle/Desktop/Dataset/SocialNetwork/post_hasCreator_person_0_0.csv'
BADFILE 'post_hasCreator_person.bad'
DISCARDFILE 'post_hasCreator_person.dsc'
INSERT
INTO TABLE postHasCreator
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS	  
( postId INTEGER EXTERNAL,
	personId INTEGER EXTERNAL Terminated by Whitespace
)
