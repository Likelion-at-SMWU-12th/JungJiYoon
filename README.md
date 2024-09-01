## 🦁 멋사 1주차 과제 🦁
| 실습 <br> 번호 | 캡쳐 | 
|:------:|:------|
|`MTV 구조`|<img width="782" src="https://github.com/Likelion-at-SMWU-12th/JungJiYoon/assets/111862541/163a752a-f15a-47fc-968a-44ece9620ae9">|
|`Model`|데이터베이스에 저장되는 '데이터' <br> DB와 상호작용 하며, 모델은 '클래스'로 정의됨|
|`View`|서비스 내부의 로직. <br>클라이언트의 요청에 따른 로직을 수행하여 결과를 응답함|
|`Template`|사용자에게 보여지는 부분(HTML 파일)|


## 🦁 스프링 어노테이션 정리 🦁
| 이름 | 내용 |
|:------:|:------|
|@RestController|스프링 컨트롤러 중 view로 응답하지 않는 컨트롤러. <br> method의 반환 결과를 JSON 형태로 반환. |
|@RequestMapping|어떤 URL을 어떤 method가 처리할 지 매핑해주는 어노테이션.<br> 별다른 설정 없이 선언하면 HTTP의 모든 요청을 받는다. <br> 스프링 4.3 버전 이후로는 새로 나온 어노테이션을 사용하기 때문에 더 이상 사용되지 않는다.|
|@GetMapping|@RequestMapping(Method=RequestMethod.GET)과 같은 역할을 한다. HTTP GET 요청을 받는다.|
|@PostMapping|@RequestMapping(Method=RequestMethod.POST)와 같은 역할을 한다. HTTP POST 요청을 받는다.|
|@PutMapping|@RequestMapping(Method=RequestMethod.PUT)와 같은 역할을 한다. HTTP PUT 요청을 받는다.|
|@DeleteMapping|@RequestMapping(Method=RequestMethod.Delete)와 같은 역할을 한다. HTTP DELETE 요청을 받는다.|
|@PathVariable|URL에서 {특정값}을 변수로 받아 올 수 있게 한다. <br> REST API에서 값을 호출할 때 많이 사용한다.|
|@RequestParam|@PathVariable과 유사하다. `?id=1`와 같은 쿼리 파라미터를 파싱해준다.|
|@RequestBody|요청 시 보내져 온 데이터를 바로 class나 model 형태로 매핑하기 위한 어노테이션이다. <br> POST,PUT,PATCH로 요청 시, Request.body 값들을 자바 타입으로 파싱해준다.|


## 🦁 각 계층의 기능과 역할 🦁 
![image](https://github.com/user-attachments/assets/da7fd0a9-2865-4f58-9332-39bbc00c710d)
![image](https://github.com/user-attachments/assets/9933c6b6-4a70-4171-898c-316d2cd896af)

| 이름 | 내용 |
|:------:|:------|
|클라이언트|서비스를 요청하는 애플리케이션(or 컴퓨터, 브라우저)|
|컨트롤러|MVC패턴에서 View와 Model을 연결시키는 역할. 클라이언트가 보낸 요청을 해당 요청을 처리하는 메서드와 매핑시켜준다.|
|서비스|클라이언트가 보낸 요청에 대한 비즈니스 로직(데이터 가공 및 처리)을 수행하는 컴포넌트.|
|DAO|Data Access Object. 실제로 DB에 접근하는 객체. Service와 DB를 연결하는 역할을 한다. <br>DB에 접근하는 로직과 그 외 비즈니스 로직을 분리시키는 용도.|
|DB|데이터 저장소. DBMS라는 소프트웨어를 사용하여 데이터를 저장/검색/편집 할 수 있다.|
|DTO|Data Transfer Object. 계층 간의 데이터 교환 역할. 특별한 로직을 가지지 않는 순수한 데이터 객체여야 함.|
|Entity|DB의 테이블에 존재하는 컬럼들을 필드로 가지는 객체. DB의 테이블과 1:1 대응된다.|
### DTO 사용하는 이유
1. View계층과 DB계층과의 분리
   - 객체를 표현하기 위한 계층과 저장하는 계층의 역할 분리
2. Entity 객체의 수정 방지
3. View(클라이언트)와 통신하는 DTO 클래스는 수정이 많이 요구됨.
   - DTO 클래스는 요구사항에 따라 자주 변경되어야 하므로 Entity를 대체하여 사용됨(Entity는 수정되면 안되므로).
4. 도메인 모델링 유지
   - 원하는 데이터 포맷을 표현하고자 Entity의 특정 컬럼들을 조합하여 출력하는 필드나 로직이 추가된다면 객체 설계를 망가뜨릴 수 있음.
   - Entity 대신 DTO에 표현을 위한 로직을 추가하여 사용

