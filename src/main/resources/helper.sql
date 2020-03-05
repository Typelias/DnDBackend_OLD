CREATE TABLE User (
    id INT PRIMARY KEY NOT NULL,
    userName VARCHAR (20) NOT NULL,
    password VARCHAR (20) NOT NULL,
    active BOOLEAN NOT NULL,
    roles VARCHAR (20) NOT NULL
);

INSERT INTO User VALUES (1,'user','pass',TRUE, 'user');

INSERT INTO User VALUES (2,'typelias','pass',TRUE, 'ROLE_USER');
INSERT INTO User VALUES (3 ,TRUE ,'pass', 'ROLE_USER', 'user');

/*DND*/
INSERT INTO User VALUE (1,'pass','typelias')