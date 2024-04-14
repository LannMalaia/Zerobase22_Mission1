
# 개요

 제로베이스 백엔드 스쿨 22기 Mission1 과제 제출물입니다.


# 코드 설명

### Controller 패키지
클라이언트의 요청을 처리하는 로직을 담당합니다. Servlet이라고 할 수 있습니다.

### DAO 패키지
JDBC 라이브러리를 통해 DB에 직접적인 CRUD 작업을 요청합니다.

DB에서 데이터를 읽어올 경우, ~VO로 끝나는 클래스를 반환합니다. UserDAO라면 UserVO를 반환하는 식입니다.

DAO는 Data Access Object의 약자입니다.

### VO 패키지
DB로 정의된 Entity에 대응하기 위해 정의한 데이터 클래스입니다.

VO는 Value Object의 약자입니다.

### DATA 패키지
DB 내 Join 연산을 해서 얻어야 하는 데이터거나, DB와의 연관이 없는, 순수하게 비즈니스 로직에만 사용되는 데이터를 정의한 클래스입니다.

### FILTER 패키지
클라이언트의 요청을 처리하기 전, 사전 작업을 해주는 Filter Annotation을 적용한 클래스입니다.

### MANAGER 패키지
보다 상세한 작업을 처리하는 클래스입니다.

### UTIL 패키지
간단하면서도 반복적으로 사용되는 코드들을 정리해놓은 클래스입니다.

### 기타 설명
DB 자료는 src/database 폴더에 저장하여 상대 경로값을 통해 사용했습니다.

jsp의 경우 결과물을 출력하는 정도로만 사용했습니다.


# 사용 도구
- Eclipse IDE for Enterprise Java Developers
- DB Browser for SQLite
- ERD Cloud (https://www.erdcloud.com/)

# 사용 프레임워크
- Java 1.8
- JSP/Servlet
- Apache Tomcat 8.5
- ERD Cloud

# 사용 라이브러리
- Okhttp3
- Gson
- Lombok
- SQLite JDBC

