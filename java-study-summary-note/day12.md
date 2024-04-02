#### day11
##### 람다식
- 익명 구현 객체 메서드 재정의 형태 
- 화살표 기호 `->`
- 조건: 추상 메서드가 하나만 존재하는 인터페이스
- 인터페이스명, 추상 메서드명 알 필요 없음 → 이름 없는 메서드로 재정의
- 자리(메서드의 매개변수, 필드, 변수)에 타입이 인터페이스
	- 추상메서드 2개 이상 → 익명 구현 객체
	- 추상메서드 1개 → 람다식
- **실행 코드(흐름이 발생하는 코드)에서 동작하는 게 아니라, 메서드 내에서 동작**

##### 스트림(Stream)
- 내부 반복 
- 컬렉션 자료형 → 기본 타입 자료형 배열 : mapToXXX() + toArray()
	- `mapToInt`...etc
- .forEach() 

#### 데이터 입출력
- 입출력 스트림 
	- 바이트 스트림 : 그림, 멀티미디어, 문자 등 모든 종류의 데이터를 입출력 할 때 사용. 용량이 적고 빠름
	- 문자 스트림 : 문자만 입출력할 때 사용, 메모장으로 깨지지 않게 열리면 문자 스트림
> 	스트림 활용시 try-catch구문에서 close는 final구문에 작성해야 함. 만약에 try 구문에 작성하면, close이전에 오류 발생시 서버 리소스 낭비 가능성 존재하기 때문

#### TCP/IP - UDP 차이점
- ###### TCP 
	- 연결 기반, 1대1, 신뢰성, 순서 존재, 비교적 느림
- ###### UDP
	- 비연결 기반, 1대다, 신뢰성없음, 순서 보장 안됨, 비교적 빠름

#### JSON(JavaScript Object Notation)
- JS에서 표기 다음과 같음. 
	- 배열: `[] `
	- 객체: `{}`
- 객체는 파이썬 딕셔너리와 비슷 `{"속성명":속성값 ,....}`