BEGIN TRANSACTION;

DROP TABLE IF EXISTS play_dates, users, pets;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

--CREATE TABLE members (
--  member_id SERIAL,
--  first_name VARCHAR(50) NOT NULL,
--  last_name VARCHAR(50) NOT NULL,
--  email VARCHAR (100) UNIQUE NOT NULL,
--  street_address VARCHAR(200),
--  city VARCHAR (100),
--  state VARCHAR(10),
--  zipcode integer,
--  CONSTRAINT pk_member_id PRIMARY KEY (member_id),
--  CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES users(user_id)
--);

CREATE TABLE pets (
    pet_id serial,
    pet_name VARCHAR(100) NOT NULL,
    pet_breed VARCHAR(100) NOT NULL,
	pet_personality VARCHAR(100) NOT NULL,
    pet_location integer,
    CONSTRAINT pk_pet_id PRIMARY KEY (pet_id)
);

CREATE TABLE play_dates (
    event_id SERIAL,
    event_date DATE NOT NULL,
    event_time VARCHAR(30) NOT NULL,
    event_location VARCHAR(50),
    organizer VARCHAR(50),
	CONSTRAINT pk_event_id PRIMARY KEY (event_id),
	CONSTRAINT fk_organizer FOREIGN KEY (organizer) REFERENCES users(username)
);

COMMIT TRANSACTION;
