LOAD DATA
INFILE '/home/oracle/Desktop/Dataset/SocialNetwork/post_0_0.csv'
BADFILE 'post.bad'
DISCARDFILE 'post.dsc'
INSERT
INTO TABLE post
fields terminated by "|" optionally enclosed by '"'
TRAILING NULLCOLS	  
( id INTEGER EXTERNAL,
	imageFile FILLER,
	createDate,
	location,
	browserUsed,
	language,
	content,
	length INTEGER EXTERNAL 
)
