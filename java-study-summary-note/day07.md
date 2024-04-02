## 7일차

##### 모듈(module)
- 일반 라이브러리는 내부에 포함된 모든 패키지에 외부 프로그램(.jar)에서의 접근이 가능하지만, 모듈은 일부 패키지를 은닉하여 접근할 수 없게 할 수 있다. 
- 의존(denpendency), 의존 모듈을 module-info에 기술
	- 프 → A → B
	- A가 B에 의존적인 의존 모듈이므로 프로그램을 사용하기 위해서는 A.jar와 B.jar 두 가지 다 필요함 => A와 B가 **의존 관계**에 있다

### <mark>예외 처리</mark>

#### 예외
- 에러: 하드웨어 고장으로 응용프로그램 실행 오류가 발생하는 것 → 대처 불가능
- 예외: 잘못된 사용 또는 코딩으로 발생하는 오류
	- 예외는 에러와 달리 계속 실행 상태를 유지할 수 있음
	- 일반 예외: 컴파일러가 예외 처리 코드 여부 검사하는 예외
		- ClassNotFoundException, InterruptedException, ...
	- 실행 예외: 컴파일러가 예외 처리 코드 여부를 검사하지 않는 예외
		- RuntimeException ⊃ NullPointerException, ArrayIndexOutOfBoundsException, NumberFormatException

#### 예외 처리
- 갑작스러운 종료를 막고 정상 실행을 유지할 수 있게 처리하는 코드
- try-catch구문
	-  catch에서 지정한 예외 클래스만 처리해주므로 여러 예외를 처리하려면 catch구문을 여러 개 사용해야 함
	- 단, 지정한 예외 중 여러가지가 발생된다면 예외 중 try문 가장 위에 있는 예외의 catch구문만 실행됨
	- catch구문을 여러 개 작성하는 거라면 가장 좁은 범위의 예외부터 적어야 함
- **다중 catch**
```java
try{
	예외 발생 코드
	...
} catch(예외클래스 e){
	예외 처리1
	System.out.println(e.getMessage());
} catch(예외클래스 e){
	예외 처리2
} finally{
	항상 실행
}
```

- Exception을 사용하면 모든 예외에 대한 처리가 가능함
- `e.getMessage()` : 어떠한 예외가 발생했는지 확인할 수 있음
- `e.printStackTrace();` : 발생한 예외 출력(프로그램이 종료되지 않고 예외 메시지가 출력)
```java
try{
	예외 발생 코드
	...
} catch(Exception e){
	예외 처리
} finally{
	항상 실행
}
```


##### 리소스 닫기
- **리소스**: 자원, 데이터를 제공하는 객체
	- 사용하기 위해서 열어야(open, connect) 하며, 사용 후 닫아야(close) 함
	- 예외 발생 시에도 안전하게 닫는 것이 중요함
	- `try-with-resources`블록을 사용하면 예외 발생 여부와 상관없이 리소스를 자동으로 닫아줌
- `finally`에서 close하기
```java
FileInputStream fis = null;
try{
	fis = new FileInputStream("file.txt");
	...
} catch(IOException e){
	예외 처리
} finally{
	fis.close();
}
```
- `try-with-resources`블록 사용하기. (단, 조건: **AutoCloseable 인터페이스**에서 close() 메서드를 재정의해야함)
```java
public class FileInputStream implements AutoCloseable{
	...
	@Override
	public void close() throws Exception{
	...
	}
}
```

```java
try(FileInputStream fis1 = new FileInputStream("file.txt")){
	...
	
} catch(IOException e){
	//예외 처리
} 
```

##### 예외 떠넘기기
- `throws` : 예외를 호출하는 곳에서 받아 처리. 즉, throws 키워드가 있는 메서드에서 예외 처리를 안하고 메서드를 실행한 곳에서 처리하도록 예외를 떠넘김.
- `throw` : 강제 예외 발생
```java
리턴타입 메소드명(매개변수, ..) throws Exception{
	...
}
```
- Class.forName("java.lang.String"); -> 예외를 던짐 
	- Unhandled exception : 무조건 예외 처리를 해야 함(강제)
	- 예외 처리 구문 작성하거나 throws 하거나 하면 됨
	- ~~처리 안하면 JVM, Tomcat까지 넘어간다..?~~
- 단, 예외를 떠넘기면 catch구문을 통해서 하는세부적인 처리를 진행할 수 없다.

##### 사용자 정의 예외 클래스
- 표준 라이브러리에 없어 직접 정의하는 예외 클래스
- 보통 Exception의 자식 클래스로 선언
##### 예외 강제로 발생시키기
- throw 키워드와 함께 예외 객체 제공
- 예외 상속을 통한 정의
```java
public class TestException extends Exception{
	public TestException (String msg) {
		super(msg);
	}
}
```

- main 메서드 클래스 코드
```java
public class ExceptionEx {
	public static void main(String[] args) {
		System.out.println("시작");
		int[] arr = { 1, 2, 3 };
		try {
			throw new TestException("내가 만든 예외");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		System.out.println("항상 실행");
	}
	System.out.println("끝");
	}
}
```

- 실행 결과
```java
시작
항상 실행
끝
ch11.TestException: 내가 만든 예외 at ch11.ExceptionEx.main(ExceptionEx.java:11)
```



-----------------
#### API(Application Programming Interface)
- 시스템과 시스템 / 프로그램과 프로그램 중간 역할 

##### java.lang
- StringBuilder, StringBuffer: 효율적 문자열 조작 기능이 필요할 때 사용
- java.util.StringTokenizer: 구분자로 연결된 문자열을 분리할 때 사용
- wrapper Class
	- Byte, Short, Character, Integer, Float, Double, Boolean
	- 기본 자료형의 값을 wrapping할 때 사용

##### 기존 Object 클래스에 정의돼 있는 메서드 재정의
```java
public class Member {
	String name;
	int score;
	
	@Override
	public String toString() {
		return "name: " +name + "score: " + score;
	}
	
	@Override
	public boolean equals (Object m) {
		if(this.name.equals(((Member)m).name) && this.score==(((Member)m).score)) {
		return true;
	}
	return false;
	}
}
```


#### 롬복 설치하기
- [링크](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30)에서 jar파일 다운로드 → 다운로드 받은 파일 이클립스가 있는 폴더에 설치 → cmd에서 `java -jar lombok-~~~~~~~`입력 → properties에서 라이브러리 추가
- 롬복 사용 시 롬복은 사용자가 정의한 부분을 제외하고 나머지 부분을 자동 생성함












