# Generic	coding	exercise	

##INTRODUCTION

The	goal	of	this	exercise	is	to	solve	a	development	challenge	and	provide	self-running	artifact	or	Liferay	extension.	
Purpose	of	this	exercise	is	to	see	how	do	you	solve	and	develop	solution for	simple	java	problem	that	is	not	Liferay	specific.		
Please	read	the	exercise	description	carefully,	design	the	best	solution	for the	described	requirements	and	implement	it.	
Before	the	provided	deadline	you	must	provide	build	scripts	+	source	code	+	version	that	I	can	run	without	building,	including	short	installation instructions.	
Feel	free	to	adapt	the	requirements	if	you	think	it	is	necessary	to	achieve a	better	result.	If	you	do	so,	you	need	to	provide	an	explanation	that justifies	such	changes.	

## CODING PROBLEM: SALES TAXES
Basic	sales	tax	is	applicable	at	a	rate	of	10%	on	all	goods,	except	books, food,	and	medical	products	that	are	exempt.	Import	duty	is	an	additional sales	tax	applicable	on	all	imported	goods	at	a	rate	of	5%,	with	no excemptions.
	
When	I	purchase	items	I	receive	a	receipt	which	lists	the	name	of	all	the items	and	their	price	(including	tax),	finishing	with	the	total	cost	of	the items,	and	the	total	amounts	of	sales	taxes	paid.	The	rounding	rules	for	
sales	tax	are	that	for	a	tax	rate	of	n%,	a	shelf	price	of	p	contains	(np/100	rounded	up	to	the	nearest	0.05)	amount	of	sales	tax.
Write	a	Java	application	that	is	able	to	print	out	the	receipt	details	for these	shopping	baskets:	
	
### INPUT:

#### Input 1:
• 1 book at 12.49
• 1 music CD at 14.99
• 1 chocolate bar at 0.85 OUTPUT
#### Output 1:
• 1 book : 12.49
• 1 music CD: 16.49
• 1 chocolate bar: 0.85
• Sales Taxes: 1.50
• Total: 29.83

#### Input 2:
• 1 imported box of chocolates at 10.00
• 1 imported bottle of perfume at 47.50
#### Output 2:
• 1 imported box of chocolates: 10.50
• 1 imported bottle of perfume: 54.65
• Sales Taxes: 7.65
• Total: 65.15

#### Input 3:
• 1 imported bottle of perfume at 27.99
• 1 bottle of perfume at 18.99
• 1 packet of headache pills at 9.75
• 1 box of imported chocolates at 11.25
#### Output 3:
• 1 imported bottle of perfume: 32.19
• 1 bottle of perfume: 20.89
• 1 packet of headache pills: 9.75
• 1 imported box of chocolates: 11.85
• Sales Taxes: 6.70
• Total: 74.68