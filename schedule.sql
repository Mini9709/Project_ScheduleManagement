use schedule;

drop TABLE if exists schedule;
drop TABLE if exists user;

CREATE TABLE user
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    email VARCHAR(30) NOT NULL,
    fixed_date VARCHAR(30) NOT NULL,
    registered_date VARCHAR(30) NOT NULL
);

CREATE TABLE schedule
(
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(20) NOT NULL,
    fixed_date VARCHAR(30) NOT NULL,
    registered_date VARCHAR(30) NOT NULL,
    name VARCHAR(10) NOT NULL,
    password VARCHAR(20) NOT NULL,
    contents VARCHAR(20),

    FOREIGN KEY (user_id) REFERENCES user(user_id)
);