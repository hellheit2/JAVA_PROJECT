DROP DATABASE IF EXISTS lecture_db; -- 만약 lecture_db가 존재하면 우선 삭제한다.
CREATE DATABASE lecture_db;

USE lecture_db;
CREATE TABLE lecture -- 강의 테이블
( lec_id  		CHAR(20) NOT NULL PRIMARY KEY, -- 강의코드(PK)
  lec_type    	CHAR(20) NOT NULL, -- 강의타입
  lec_name    	CHAR(20) NOT NULL,  -- 강의명
  lec_credit	INT -- 강의 학점
);
CREATE TABLE lecture_time -- 강의 시간 테이블
(  
   lec_id  			CHAR(8) NOT NULL, -- 강의 코드(FK)
   start_time	  	TIMESTAMP NOT NULL, -- 강의 시작 시간
   end_time	  		TIMESTAMP NOT NULL, -- 강의 종료 시간
   FOREIGN KEY (lec_id) REFERENCES lecture(lec_id),
   PRIMARY KEY(lec_id, start_time)
);

CREATE TABLE student -- 학생 테이블
(
	stu_id 		CHAR(15) NOT NULL,
    stu_pwd 	CHAR(20) NOT NULL,
    stu_no		CHAR(8) NOT NULL,
    stu_name	CHAR(20) NOT NULL,
    stu_major	CHAR(20) NOT NULL,
    PRIMARY KEY(stu_id)
);

CREATE TABLE course_regist ( -- 수강신청 테이블
	regist_no		INT NOT NULL AUTO_INCREMENT,
	stu_id  		CHAR(15) NOT NULL, -- 학생 아이디(FK)
    lec_id	  		CHAR(8) NOT NULL, -- 강의 코드(FK)
    submit_time	  	TIMESTAMP DEFAULT now(), -- 강의 종료 시간
    FOREIGN KEY (stu_id) REFERENCES student(stu_id),
    FOREIGN KEY (lec_id) REFERENCES lecture(lec_id),
    PRIMARY KEY(regist_no)
);



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


INSERT INTO lecture_time VALUES('0005', '22-09-06 12:00:00.000000000', '22-09-06 14:00:00.000000000');
INSERT INTO lecture_time VALUES('0005', '22-09-08 13:00:00.000000000', '22-09-08 15:00:00.000000000');
INSERT INTO lecture_time VALUES('0006', '22-09-07 11:00:00.000000000', '22-09-07 14:00:00.000000000');
INSERT INTO lecture_time VALUES('0006', '22-09-08 09:00:00.000000000', '22-09-08 13:00:00.000000000');
INSERT INTO lecture_time VALUES('0007', '22-09-09 09:00:00.000000000', '22-09-09 13:00:00.000000000');
INSERT INTO lecture_time VALUES('0001', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO lecture_time VALUES('0001', '22-09-07 09:00:00.000000000', '22-09-07 11:00:00.000000000');
INSERT INTO lecture_time VALUES('0002', '22-09-05 11:00:00.000000000', '22-09-05 13:00:00.000000000');
INSERT INTO lecture_time VALUES('0002', '22-09-06 10:00:00.000000000', '22-09-06 12:00:00.000000000');
INSERT INTO lecture_time VALUES('0003', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO lecture_time VALUES('0004', '22-09-05 14:00:00.000000000', '22-09-05 16:00:00.000000000');
INSERT INTO lecture_time VALUES('0004', '22-09-08 09:00:00.000000000', '22-09-08 10:00:00.000000000');
INSERT INTO lecture_time VALUES('0008', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO lecture_time VALUES('0008', '22-09-07 09:00:00.000000000', '22-09-07 11:00:00.000000000');
INSERT INTO lecture_time VALUES('0009', '22-09-05 16:00:00.000000000', '22-09-05 17:00:00.000000000');
INSERT INTO lecture_time VALUES('0010', '22-09-05 09:00:00.000000000', '22-09-05 11:00:00.000000000');
INSERT INTO lecture_time VALUES('0010', '22-09-07 09:00:00.000000000', '22-09-07 11:00:00.000000000');


INSERT INTO student VALUES('test','1234','11111111','KIM','컴퓨터공학과');


INSERT INTO course_regist (stu_id,lec_id) VALUES('test','0006');

select * from lecture;
select * from lecture_time;
select * from student;
select * from course_regist;