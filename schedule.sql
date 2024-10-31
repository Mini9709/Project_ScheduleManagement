-- 일정 테이블 생성
CREATE TABLE SCHEDULE (
   schedule_id INT NOT NULL,
   user_id INT NOT NULL,
   title VARCHAR(20) NOT NULL,
   fixed_date DATE NOT NULL,
   set_date DATE NOT NULL,
   name VARCHAR(10) NOT NULL,
   password VARCHAR(20) NOT NULL,
   content VARCHAR(20),

   PRIMARY KEY (schedule_id),
   FOREIGN KEY (user_id) REFERENCES USER(user_id)
);

-- 작성자 테이블 생성
CREATE TABLE USER (
    user_id INT NOT NULL,
    name VARCHAR(10) NOT NULL,
    email VARCHAR(30) NOT NULL,
    fixed_date DATE NOT NULL,
    set_date DATE NOT NULL
);

-- 일정 작성
INSERT INTO SCHEDULE(*) VALUES (1, 1, "test", "2024-10-30", "2024-10-30", "name", "qwe123", "test_content");
-- 전체 일정 조회
SELECT title, fixed_date, set_date, name, content FROM SCHEDULE;
-- 선택 일정 조회
SELECT title, fixed_date, set_date, name, content FROM SCHEDULE WHERE schedule_id = 1;
-- 일정 수정
UPDATE SCHEDULE SET title = "test1", content = "test_content1" WHERE schedule_id = 1;
-- 일정 삭제
DELETE FROM SCHEDULE WHERE schedule_id = 1;
