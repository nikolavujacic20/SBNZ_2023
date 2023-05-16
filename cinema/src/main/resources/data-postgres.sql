insert into authority (role) values ('ROLE_ADMIN');
insert into authority (role) values ('ROLE_REGISTERED_USER');

insert into users (type, email, password, verified) values ('admin','admin@gmail.com','$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO' ,true);
insert into users (type, email, password, verified) values ('registered_user','user@gmail.com','$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO' ,true);
insert into users (type, email, password, verified) values ('admin','admin2@gmail.com','$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO' ,true);
insert into users (type, email, password, verified) values ('registered_user','user2@gmail.com','$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO' ,true);

insert into users_authority (user_id, authority_id) values (1, 1);
insert into users_authority (user_id, authority_id) values (2, 2);
insert into users_authority (user_id, authority_id) values (3, 1);
insert into users_authority (user_id, authority_id) values (4, 2);

insert into movie (name, description, basic_ticket_price, start_time, end_time, capacity) values('Titanic','drama, thriller', 500, '2021-08-25 00:00:00', '2021-08-25 00:02:10', 500);
insert into movie (name, description, basic_ticket_price, start_time, end_time, capacity) values('Godfather','drama, crime', 700, '2021-07-25 00:00:00', '2021-07-25 00:02:10', 500);

insert into tickets (bill, num_of_children, num_of_adult, num_of_senior, initial_price, type_ticket, using_period, movie_id) values (12364, 2, 2, 2, 500, 'FAMILY', 'DAY', 1);


insert into users_tickets(registered_user_id, tickets_id) values (2, 1);
-- insert into users_tickets(registered_user_id, tickets_id) values (2, 2);

-- insert into users_tickets(registered_user_id, tickets_id) values (2, 3);
-- insert into users_tickets(registered_user_id, tickets_id) values (2, 4);
-- insert into users_tickets(registered_user_id, tickets_id) values (2, 5);

