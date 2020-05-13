Create table Products(
	productId VARCHAR(225) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	quantity INT NOT NULL,
	price INT NOT NULL,
	invoice VARCHAR(255),
	shoppingID VARCHAR,
	FOREIGN KEY (shoppingID) REFERENCES ShoppingLists(shoppingID)
);

Create table ShoppingLists(
	shoppingID VARCHAR(225) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	id VARCHAR,
	FOREIGN KEY (id) REFERENCES users(id)
);

Create table Programs(
	programID VARCHAR(225) NOT NULL PRIMARY KEY,
	startDate timestamp NOT NULL,
	endDate timestamp,
	id VARCHAR,
	FOREIGN KEY (id) REFERENCES users(id)
);

Create table Activities(
	activityID VARCHAR(225) NOT NULL PRIMARY KEY,
	activityname VARCHAR(50) NOT NULL,
	type VARCHAR(10) NOT NULL,
	description VARCHAR(50),
	nbRepitions int NOT NULL,
	duration timestamp NOT NULL,
	performed timestamp NOT NULL,
	programID VARCHAR,
	FOREIGN KEY (programID) REFERENCES Programs(programID)
);

Create table Events(
	eventID VARCHAR(225) NOT NULL PRIMARY KEY,
	type VARCHAR(10) NOT NULL,
	place VARCHAR(50),
	day timestamp NOT NULL,
	performed timestamp NOT NULL,
);
