DROP DATABASE IF EXISTS lecture_db; -- 만약 market_db가 존재하면 우선 삭제한다.
CREATE DATABASE lecture_db;

USE lecture_db;
CREATE TABLE lecture -- 회원 테이블
( lec_id  		VARCHAR(20) NOT NULL PRIMARY KEY, -- 강의코드(PK)
  lec_type    	VARCHAR(20) NOT NULL, -- 강의타입
  lec_name    	VARCHAR(20) NOT NULL,  -- 강의명
  lec_credit	INT -- 강의 학점
);
CREATE TABLE schedule -- 구매 테이블
(  
   lec_id  	CHAR(8) NOT NULL, -- 강의 코드(FK)
   start_time	  	TIMESTAMP NOT NULL, -- 강의 시간
   end_time	  	TIMESTAMP NOT NULL, -- 강의 시간
   FOREIGN KEY (lec_id) REFERENCES lecture(lec_id),
   PRIMARY KEY(lec_id, start_time)
);

select * from lecture;
select * from schedule;

INSERT INTO lecture VALUES('0006', '전공선택', 'JAVA 심화', 6);
INSERT INTO lecture VALUES('0007', '전공필수', '프로젝트 설계 기초', 4);
INSERT INTO lecture VALUES('0008', '전공선택', 'ERD 다이어그램의 이해', 3);
INSERT INTO lecture VALUES('0009', '교양', '프로그래밍의 역사', 1);
INSERT INTO lecture VALUES('0001', '전공필수', 'JAVA의 기초', 4);
INSERT INTO lecture VALUES('0002', '전공필수', 'Spring 실습', 4);
INSERT INTO lecture VALUES('0003', '교양', 'HTML 기초', 2);
INSERT INTO lecture VALUES('0004', '교양', 'CSS 응용', 2);
INSERT INTO lecture VALUES('0005', '전공선택', 'SQL 데이터베이스', 3);
INSERT INTO lecture VALUES('0010', '전공선택', '정보처리학개론', 3);


INSERT INTO schedule VALUES('0005', '22-09-06 12:00:00.000000000', '22-09-06 14:00:00.000000000');
INSERT INTO schedule VALUES('0005', '22-09-08 13:00:00.000000000', '22-09-08 15:00:00.000000000');
INSERT INTO schedule VALUES('0006', '22-09-07 11:00:00.000000000', '22-09-07 14:00:00.000000000');
INSERT INTO schedule VALUES('0006', '22-09-08 09:00:00.000000000', '22-09-08 13:00:00.000000000');
INSERT INTO schedule VALUES('0007', '22-09-09 09:00:00.000000000', '22-09-09 13:00:00.000000000');
INSERT INTO schedule VALUES('0001', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO schedule VALUES('0001', '22-09-07 09:00:00.000000000', '22-09-07 11:00:00.000000000');
INSERT INTO schedule VALUES('0002', '22-09-05 11:00:00.000000000', '22-09-05 13:00:00.000000000');
INSERT INTO schedule VALUES('0002', '22-09-06 10:00:00.000000000', '22-09-06 12:00:00.000000000');
INSERT INTO schedule VALUES('0003', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO schedule VALUES('0004', '22-09-05 14:00:00.000000000', '22-09-05 16:00:00.000000000');
INSERT INTO schedule VALUES('0004', '22-09-08 09:00:00.000000000', '22-09-08 10:00:00.000000000');
INSERT INTO schedule VALUES('0008', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO schedule VALUES('0008', '22-09-07 09:00:00.000000000', '22-09-07 11:00:00.000000000');
INSERT INTO schedule VALUES('0009', '22-09-05 16:00:00.000000000', '22-09-05 17:00:00.000000000');
INSERT INTO schedule VALUES('0010', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO schedule VALUES('0010', '22--0907 09:00:00.000000000', '22--0907 11:00:00.000000000');

select * from lecture;
select * from schedule;
-- select lecture.lec_id, lecture.lec_type, lecture.lec_name, lecture.lec_credit, schedule.lec_time, schedule.lec_duration
-- from lecture inner join schedule on lecture.lec_id = schedule.lec_id;

select A.lec_id, A.lec_type, A.lec_name, B.lec_time, B.lec_duration, A.lec_credit
from lecture A inner join schedule B on A.lec_id = B.lec_id order by A.lec_id;

select *
from lecture natural join schedule;

SELECT @@global.time_zone, @@session.time_zone;