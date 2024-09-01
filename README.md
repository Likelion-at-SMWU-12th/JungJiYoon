### 🦁 멋사 1주차 과제 🦁
| 실습 <br> 번호 | 캡쳐 | 
|:------:|:------|
|`MTV 구조`|<img width="782" src="https://github.com/Likelion-at-SMWU-12th/JungJiYoon/assets/111862541/163a752a-f15a-47fc-968a-44ece9620ae9">|
|`Model`|데이터베이스에 저장되는 '데이터' <br> DB와 상호작용 하며, 모델은 '클래스'로 정의됨|
|`View`|서비스 내부의 로직. <br>클라이언트의 요청에 따른 로직을 수행하여 결과를 응답함|
|`Template`|사용자에게 보여지는 부분(HTML 파일)|


### 🦁 스프링 어노테이션 정리 🦁
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


