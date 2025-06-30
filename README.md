## 📚 Spring Boot Practice Projects Collection

> **Title suggestion for the GitHub repo:**
> **`springboot-lab-2407`**
> *Hands-on Spring Boot examples from the 2024-07 class sessions*

---

### Table of Contents

| No. | Project                       | Quick Purpose                   |
| --- | ----------------------------- | ------------------------------- |
| 1   | [`ex1`](#ex1)                 | 가장 기본적인 “Hello, Spring Boot” 예제 |
| 2   | [`ex2`](#ex2)                 | Spring Data JPA + H2 CRUD 실습    |
| 3   | [`ex3`](#ex3)                 | Paging & Sorting JPA            |
| 4   | [`ex4`](#ex4)                 | JPQL / Query 메소드 고급 예제          |
| 5   | [`ex5mv`](#ex5mv)             | MVC 패턴 - Model & View 분리        |
| 6   | [`ex6`](#ex6)                 | Validation & Exception Handling |
| 7   | [`ex7`](#ex7)                 | 파일 업로드 / 다운로드                   |
| 8   | [`ex8`](#ex8)                 | RESTful API & JSON 응답           |
| 9   | [`exJSP`](#exjsp)             | JSP View, Spring Security 기본    |
| 10  | [`test-server`](#test-server) | 통합 테스트 전용 서버 스켈레톤               |
| 11  | [`InstaPrj`](#instaprj)       | “Instagram Clone” - 게시글 & 댓글    |
| 12  | [`MyInstaPrj`](#myinstaprj)   | InstaPrj 개선판 (+Likes, Follow)   |

> **TIP** : 각 항목의 *디렉터리 트리* 는 접어서 보도록 \<details> 태그를 사용했습니다.
> 필요 없는 코드를 줄여 README 길이를 관리할 수 있으니, 상황에 따라 열/닫아 주세요.

---

### 공통 구조

모든 예제는 표준 Maven 레이아웃을 따릅니다.

```
<project>/
 ├─ pom.xml
 ├─ src/
 │  ├─ main/
 │  │  ├─ java/…        # 애플리케이션/도메인 코드
 │  │  └─ resources/…   # 설정, 정적 리소스
 │  └─ test/java/…       # 테스트 코드
 └─ README.md            # (← 바로 이 파일!)
```

---

## 프로젝트별 디렉터리 트리 & 설명

### ex1

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex1/
├─ .gitignore
├─ mvnw*
├─ pom.xml
└─ src/
   ├─ main/
   │  ├─ java/com/example/ex1/
   │  │  ├─ Ex1Application.java
   │  │  └─ controller/
   │  │     ├─ HelloController.java
   │  │     └─ DataController.java
   │  └─ resources/
   │     ├─ application.properties
   │     └─ static/
   │        └─ index.html
   └─ test/java/com/example/ex1/
      └─ Ex1ApplicationTests.java
```

</details>

> **핵심 학습 포인트** : 정적 리소스 서빙, 단순 컨트롤러 매핑

---

### ex2

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex2/
├─ pom.xml
└─ src/
   ├─ main/java/com/example/ex2/
   │  ├─ Ex2Application.java
   │  ├─ entity/Memo.java
   │  └─ repository/MemoRepository.java
   ├─ main/resources/application.properties
   └─ test/java/com/example/ex2/
      ├─ Ex2ApplicationTests.java
      └─ repository/MemoRepositoryTests.java
```

</details>

> **핵심 학습 포인트** : Spring Data JPA 기본 CRUD, 테스트 작성

---

### ex3

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex3/
├─ pom.xml
└─ src/main/java/com/example/ex3/
   ├─ Ex3Application.java
   ├─ entity/
   ├─ dto/
   └─ repository/
```

</details>

> **핵심 학습 포인트** : 페이징·정렬, DTO 변환

---

### ex4

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex4/
├─ pom.xml
└─ src/main/
   ├─ java/com/example/ex4/
   └─ resources/
```

</details>

> **핵심 학습 포인트** : JPQL, Query 메소드 커스터마이징

---

### ex5mv

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex5mv/
├─ pom.xml
└─ src/main/
   ├─ java/com/example/ex5mv/
   │  ├─ Ex5Application.java
   │  ├─ controller/
   │  └─ service/
   └─ resources/templates/   # Thymeleaf views
```

</details>

> **핵심 학습 포인트** : MVC 계층 분리, Thymeleaf 연동

---

### ex6

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex6/
├─ pom.xml
└─ src/main/
   ├─ java/com/example/ex6/
   │  ├─ Ex6Application.java
   │  └─ controller/
   └─ resources/
```

</details>

> **핵심 학습 포인트** : Bean Validation, 전역 예외 처리

---

### ex7

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex7/
├─ pom.xml
└─ src/main/
   ├─ java/com/example/ex7/
   └─ resources/
```

</details>

> **핵심 학습 포인트** : 멀티파트 파일 업로드, 로컬 저장

---

### ex8

<details>
<summary>디렉터리 트리 보기</summary>

```text
ex8/
├─ pom.xml
└─ src/main/
   ├─ java/com/example/ex8/
   └─ resources/
```

</details>

> **핵심 학습 포인트** : REST Controller, JSON 직렬화

---

### exJSP

<details>
<summary>디렉터리 트리 보기</summary>

```text
exJSP/
├─ pom.xml
└─ src/
   ├─ main/java/com/example/exJSP/
   │  ├─ ExJspApplication.java
   │  ├─ config/SecurityConfig.java
   │  └─ controller/SampleController.java
   ├─ main/resources/application.properties
   └─ main/webapp/WEB-INF/views/
```

</details>

> **핵심 학습 포인트** : JSP 뷰, 기본 Spring Security 설정

---

### test-server

<details>
<summary>디렉터리 트리 보기</summary>

```text
test-server/
├─ pom.xml
└─ src/
   ├─ main/java/com/example/testserver/
   └─ test/java/com/example/testserver/
```

</details>

> **핵심 학습 포인트** : MockMvc / TestRestTemplate 등을 이용한 통합 테스트

---

### InstaPrj

<details>
<summary>디렉터리 트리 보기</summary>

```text
InstaPrj/
├─ pom.xml
└─ src/
   ├─ main/java/com/example/insta/
   │  ├─ controller/
   │  ├─ domain/
   │  ├─ security/
   │  └─ service/
   └─ main/resources/
```

</details>

> **핵심 학습 포인트** : 게시물·댓글·회원가입, Spring Security 로그인

---

### MyInstaPrj

<details>
<summary>디렉터리 트리 보기</summary>

```text
MyInstaPrj/
├─ pom.xml
└─ src/
   ├─ main/java/com/example/myinsta/
   │  ├─ controller/
   │  ├─ domain/
   │  ├─ security/
   │  └─ service/
   └─ main/resources/
```

</details>

> **핵심 학습 포인트** : InstaPrj 확장판 – 좋아요, 팔로우, S3 이미지 업로드 등

---

## 사용 방법

```bash
# 예: ex3 실행
cd ex3
./mvnw spring-boot:run
# 또는
mvn spring-boot:run
```

> **JDK 17+** 및 **Maven 3.9+** 환경 권장
> 일부 예제는 **H2 DB** 메모리 모드로 자동 실행됩니다.


