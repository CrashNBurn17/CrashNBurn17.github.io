BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


--INSERT INTO members (
--	first_name, last_name, email, street_address, city, state, zipcode)
--	VALUES ('user', 'user', 'user@techelevator.com', '456 Main Street', 'New York', 'NY', '10017');
--INSERT INTO members (
--	first_name, last_name, email, street_address, city, state, zipcode)
--	VALUES ('Admin', 'Admin', 'admin@techelevator.com', '128 Main Street', 'New York', 'NY', '10017');
-- INSERT INTO members (
	-- first_name, last_name, email, street_address, city, state, zipcode)
	-- VALUES ('Kyle', 'Clemenz', 'kyle.clemenz@techelevator.com', '123 Main Street', 'New York', 'NY', '10017');

INSERT INTO pets (
	pet_name, pet_breed, pet_personality, pet_location)
	VALUES ('Anthony', 'Bulldog', 'Friendly', '15106');
INSERT INTO pets (
	pet_name, pet_breed, pet_personality, pet_location)
	VALUES ('Orlando', 'Beagle', 'Loyal', '15203');

COMMIT TRANSACTION;
