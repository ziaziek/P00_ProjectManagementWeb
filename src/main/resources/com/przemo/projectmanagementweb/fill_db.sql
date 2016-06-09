truncate table users restart identity cascade;
truncate table time_log restart identity;
truncate table sprint restart identity cascade;
truncate table role restart identity cascade;
truncate table task_type restart identity cascade;
truncate table status restart identity cascade;
truncate table projects restart identity cascade;

insert into role(name) values('Developer');

insert into sprint(name, start_date, end_date, time_available) values('Sprint 00', '2016-04-24 09:30:00', '2016-05-01 09:30:00', 40);

insert into status(name) values('Created');
insert into status(name) values('ToDo');
insert into status(name) values('In Progress');
insert into status(name) values('Under review');
insert into status(name) values('Done');
insert into status(name) values('Closed');
insert into status(name) values('Reopened');

insert into task_type(name) values('Task');
insert into task_type(name) values('Improvement');
insert into task_type(name) values('Bug');
insert into task_type(name) values('Feature');

insert into users(role, email) values(1, 'ziaziek@poczta.fm');

insert into projects(name, startDate, owner) values('Project Management System', '2016-04-18', 1);

insert into task(task_type, title, description, estimated_time, real_time, assignee, sprint, status, projects)
values(4, 'Examplary task', 'This is an examplary task, just for the purposes of creating the application.', 4, 1.5, 1, 1, 1, 1);

insert into task_comments(task, author, date, comment) values(1, 1, '2016-04-24 15:24:00', 'This is an examplary comments to the examplary task.');
