LOAD DATA
INFILE '/home/oracle/Desktop/Dataset/Product/Product.csv'
BADFILE 'product.bad'
DISCARDFILE 'product.dsc'
INSERT
INTO TABLE product
fields terminated by "," optionally enclosed by '"'
TRAILING NULLCOLS	  
( 	asin,
	title,
	price FLOAT EXTERNAL,
	imgUrl,
	productId INTEGER EXTERNAL,
	brand
)
