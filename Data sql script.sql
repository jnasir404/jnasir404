

###### Data Insertion ############
insert into auth_user (auth_user_id,first_name,last_name,email,password,status) values (1,'Jamal','Nasir','jn1@gmail.com','$2a$10$R/OmAyTZzeYRMcvdkaXd2.VrQ0cvJ3zi2cMdgoBn6JX2WozONLKRy','VERIFIED');


INSERT INTO auth_role VALUES (1,'SUPER_USER','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2,'ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3,'SITE_USER','This user has access to site, after login - normal user');


insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','3');


INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('8','1', 'Guardians of the Galaxy', 'A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.', '8','2010', '4', '10$', 'country', 'SciFi','thumb1.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('7','1', 'Captain Marvel', 'Carol Danvers becomes one of the universes most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.', '7','2010', '4', '10$', 'country', 'SciFi',  'thumb2.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('6','1', 'Star Wars', 'Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empires world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.','6', '2019', '4', '10$', 'country', 'SciFi',  'thumb9.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('5','1', '21 Bridges', 'An embattled NYPD detective is thrust into a citywide manhunt for a pair of cop killers after uncovering a massive and unexpected conspiracy.', '5','2010', '4', '10$', 'country', 'SciFi','thumb8.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('4','1', 'The City of Gold', 'An anguished media magnate, Jonathan Davenport, accompanies his estranged lover to the Peruvian Amazon in pursuit of a reclusive artist living in rebel occupation. Despite their philanthropic intentions, the mission proves to be the harbinger of something dark and ominous rooted deep within Jonathan.','4', '2010', '4', '10$', 'country', 'SciFi',  'thumb7.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('3','1', 'Total Recall', 'When a man goes in to have virtual vacation memories of the planet Mars implanted in his mind, an unexpected and harrowing series of events forces him to go to the planet for real - or is he?', '3', '2020', '4', '10$', 'country', 'Drama',  'thumb6.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('2','1', 'Brighton Rock', 'Brighton Rock is a 2010 British crime film written and directed by Rowan Joffé and loosely based on Graham Greenes 1938 novel of the same name. The film stars Sam Riley, Andrea Riseborough, Andy Serkis, John Hurt, Sean Harris and Helen Mirren.', '2','2010', '4', '10$', 'country', 'SciFi', 'thumb5.jpg');
INSERT INTO films (id,userid,name,description,film_id,release_date,rating,ticket_price,country,genre,photo) VALUES ('1', '1', 'Working Woman', 'Working Woman is a 2018 Israeli drama film directed by Michal Aviad.[1] It was screened in the Contemporary World Cinema section at the 2018 Toronto International Film Festival.','1', '2018', '5', '10$', 'country', 'Drama', 'thumb4.jpg');
