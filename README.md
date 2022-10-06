# 환경 오픈 사전 - 환경에 한 발짝

가속화되는 환경오염을 줄이기 위해 만든 오픈 사전 프로젝트
  
# 🌍page
   ![mainPage](https://user-images.githubusercontent.com/77195486/143795081-c80b5989-aeac-45e3-9116-ece03b77d75d.JPG)
   ![loginPage](https://user-images.githubusercontent.com/77195486/143795207-b1fca363-e6ea-46b7-b014-e72d8d578132.JPG)
   ![projectDetail](https://user-images.githubusercontent.com/77195486/143795236-991f2146-7992-4dc4-bfea-c3b9ee248d77.JPG)
   ![news](https://user-images.githubusercontent.com/77195486/143795299-f7f8cb81-56c7-4dc9-8e4a-5affa5d6de30.JPG)
 
# ERD
  ![환경오픈사전](https://user-images.githubusercontent.com/77195486/192509222-d1259dae-9e83-41d1-a6b0-bb40e22a4224.png)

# 개발 Tool
  - JDK 11
  - Gradle 7.1.1
  - Spring Boot 2.5.3
  - ojdbc 8
  - MyBatis, JdbcTemplate
  - IntelliJ IDEA
  

# 🏔주요기능
  
  🏜 메인화면
  * 메인 페이지에서 밑으로 스크롤을 내리면 현재 날짜에 가까운 환경 기념일이 뜨도록 설정
                
        아 오늘은 이런 날이구나

  🏝 회원가입 및 로그인
  * 회원가입은 아이디, 비밀번호, 전화번호, 생일, 주소 등을 입력해야 하며, 아이디는 이메일로 가입하는 방식으로 구현함.
  * 로그인은 총 4가지 방식이 있음. 이메일(이 홈페이지에서 회원 가입되어있는 아이디)뿐만 아니라 구글, 네이버, 카카오 등 소셜 로그인으로도 로그인 가능함.
  
  🏖 게시글
  * 본 홈페이지 회원 로그인 및 소셜 로그인을 통하여 게시글, 댓글 등 작성 가능. 게시글 목록에는 각 게시글마다 글 번호(auto-increment), 제목, 작성자, 작성날짜, 조회수 등이 출력됨.
  * 커뮤니티의 성격을 가짐
  
  📢뉴스
  * 투고자 닉네임을 따로 지정해서 사용 가능
  
  🏳‍🌈환경기념일
  * 각 날짜에 맞는 환경 기념일을 나열해서 출력
  
# 개발 기간
 - 21.06 ~ 21.10 (4달)
 
 ---
 
 
 ### [자세한 소스는 여기를 봐주세요](https://github.com/2021PassionProject/EnvironmentProject-2021/tree/main/DevelopTest/JAVATEST)
