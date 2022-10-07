# JAVA_PROJECT
수강신청 및 시간표 관리(2022.09.21~2022.10.07)
> 
## 목표
> 1. JAVA의 특징과 배운 개념들을 정리해보고, 확인하기 위해 순수 JAVA로 이루어진 프로젝트를 진행해본다.  
> 2. 가장 기본적인 CRUD 기능과 관련된 하나의 시나리오를 직접 설계하고 구현해 봄으로서 하나의 시스템을 구성하는 여러 객체와 이들의 연관관계를 생각해본다.
> 3. 설계한 내용을 여러가지 다이어그램으로 표현해봄으로서 설계한 내용을 보완하고 구체화하는데 익숙해진다.
> 4. 추가적으로 공부한 데이터베이스를 연동해보고, 이를 적용해본다.

<br/>
  
## 주제
수강 관리 시스템을 설계하고 구현해본다. 화면 구성은 콘솔로 진행한다.
  - 학생 - 강의목록, 수강내역, 수강신청, 수강철회, 시간표 생성
  - 관리자 - 학생목록, 강의목록, 강의등록, 강의삭제, 강의수정
   
<br/>

## 요구사항 명세
- 학생
   1. 강의목록 : 강의 목록을 확인할 수 있다.
   2. 수강내역 : 수강 내역을 확인할 수 있다.
   3. 수강신청 : 강의 목록의 번호를 통해 수강 신청할 수 있다. 동일한 과목 및 동일한 시간대 신청은 불가하다.
   4. 수강철회 : 강의 목록의 번호를 통해 수강 철회할 수 있다.
   5. 시간표 생성 : 수강 신청 내역을 시간표의 형태로 확인할 수 있다.
   ![학생_프로그램_구동](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/student.gif)
   </br></br>
- 관리자
   1. 학생목록 : 학생 목록을 확인할 수 있다.
   2. 강의목록 : 강의 목록을 확인할 수 있다.
   3. 강의등록 : 강의코드, 강의타입, 강의명, 학점, 강의시간을 통해 새로운 강의를 등록할 수 있다.
   4. 강의삭제 : 강의 목록의 번호를 통해 강의를 삭제할 수 있다.
   5. 강의수정 : 강의명, 강의타입, 학점 강의시간을 변강할 수 있다.
   ![관리자_프로그램_구동](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/admin.gif)

<br/>

## 설계
수강 관리 시스템을 설계하고 다이어그램을 통해 표현해본다.
  - 유스케이스 다이어그램
  ![유스케이스_다이어그램](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/usecaseDiagram.png)
  </br></br>
  - ER 다이어그램
  ![ER_다이어그램](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/ER_Diagram.png)
  </br></br>
  - 시퀀스 다이어그램
  1. 공통
  ![시퀀스_다이어그램_로그인](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/sequenceDiagram_Login_Join.png)
  2. 학생
  ![시퀀스_다이어그램_학생](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/sequenceDiagram_Student.png)
  3. 관리자
  ![시퀀스_다이어그램_관리자](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/sequenceDiagram_Admin.png)
  </br></br>
  - 클래스 다이어그램
  ![클래스_다이어그램](https://raw.githubusercontent.com/hellheit2/JAVA_PROJECT/main/document/classDiagram.png)

 



