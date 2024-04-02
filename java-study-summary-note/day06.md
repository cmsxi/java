### 6일차

- 객체 타입 확인: instanceof
	- 사용: boolean result = `객체 instanceof 타입`

##### 추상 클래스(Abstract Class)
- 실체 클래스들의 공통 필드나 메서드를 추출해서 선언한 클래스
- 하나 이상의 추상 메서드가 존재한다면 해당 클래스는 추상 클래스이어야한다.
- 추상 클래스는 객체로 생성할 수 없다 → 인스턴스화가 불가능하다.
```java
public abstract class Vehicle{
	public void back(){
	
	}

	public abstract void run(); // 추상 메서드, 자식 클래스에서 재정의 해줘야 함.
	// 추상 메서드 재정의 안해주면 자식 클래스에서 오류 발생
}

//Main 클래스
public class Main extends Vehicle {
	public static void main(String[] args){
		// Vehicle v = new Vehicle() <-- 오류 발생(∵ 추상클래스 인스턴스화 불가능)
	}
}
```

###### sealed 클래스
- 무분별한 자식 클래스 생성을 방지하기 위해 도입
- permits 뒤에 상속 가능한 자식 클래스를 지정
- 잘 안 쓴다. 

#### <mark>인터페이스</mark>
- 추상 클래스와 비슷하지만, 메서드 앞에 `public abstract`를 생략할 수 있음(∵ 적혀 있지 않아도 어차피 다 추상 메서드)
- 접근 제한자로는 `public`, `(default)`를 사용할 수 있음. 
```java
public interface 인터페이스명{
	// 인스턴스와 불가능하지만 인터페이스명.메서드로 기능 사용은 가능함
	public 상수 필드
	public 추상 메서드 <<- 이거 사용하기 위해서 인터페이스를 사용함
	public 디폴트 메서드 -> 일반 메서드
	public 정적 메서드  
	private 메서드
	private 정적 메서드
}
```

- 구현 클래스에서 인터페이스에 정의된 추상 메서드 실행 내용을 구현(implement)함. (상속과 다른점)
```java
public class 클래스명 implements 인터페이스명 {

}
```

- 인터페이스는 인터페이스끼리 상속 받을 수 있음. 다중 상속이 가능함(extends뒤에 여러 개 적으면 됨)
- 인터페이스는 참조 타입에 속함.
```java
RemoteControl rc;
RemoteControl rc = null;  // null을 대입할 수 있음
// 객체를 참조하고 있지 않다는 뜻으로 null 대입

rc = new Television(); // 인터페이스를 통해 구현 객체를 사용하려면 인터페이스 변수에 구현 객체 번지 대입
// 자동 형변환
```

- 인터페이스 내에서 만든 필드는 다 상수. `public static final` 생략 가능함
```java
public interface RemoteControl{
	[public static final] int MAX_VOLUME;
	int MIN_VOLUME;
	// 둘 다 상수. 인스턴스 없이 '인터페이스.상수명'으로 바로 접근 가능하다
}
```

- 디폴트 메서드 
	- 일반 메서드. 실행부가 존재함. default키워드가 리턴 타입 앞에 붙음
	- 똑같은 내용 재정의가 필요한 경우에 효율적인 코드 작성을 방지하기 위해서 작성
	- (원래 없었던 기능인데 추가됨)
```java
public interface I {
	default void volumeUp(){  //default 안쓰면 오류 생김. 
	// 인터페이스지만 실행부가 존재하기 때문.
		System.out.println("볼륨업");
	}
}
```

- 다중 인터페이스 구현이 가능함 (implements를 여러 개 할 수 있음)

- 자동 타입 변환 `인터페이스 변수 = 구현 객체;`
- 강제 타입 변환 `구현클래스 변수 = (구현클래스) 인터페이스변수;` 

- 인터페이스 성질 활용: JDBC, 다양한 DB서버에 알맞게 연동 


##### ==익명 객체==
- 이름이 없는 객체로, 클래스를 명시적으로 선언하지 않고 객체를 생성할 수 있음
- 클래스를 상속 받는 경우 **익명 자식 객체**
```java
new 부모생성자(매개값){
	//필드
	//메소드
}
```
- 예시
```java
// Main클래스 main 메서드
// 익명 자식 객체
Phone ring = new Phone(1){
	@Override
	void test(){
		System.out.println("익명 자식 객체 test")
		}
}

ring.test();
```

- 인터페이스를 구현하는 경우 **익명 구현 객체**
```java
new 인터페이스(){
	//필드
	//메서드
}
```
- 예시
```
// RemoteControl 인터페이스
public interface RemoteControl {
	void turnOn();
	void turnOff();
}
```

```
// Home 클래스
public class Home {
	// 보통 일회용, 클래스없이 인터페이스를 익명 구현 객체를 만들어낸 것
	// 구현이기 때문에 안에 있는 객체 모두 재정의 해줘야함
	// 이름이 없음.(변수명은 객체가 아님)

	// 필드에 익명 구현 객체 대입
	private RemoteControl rc = new RemoteControl() {
		@Override
		public void turnOn() {
		System.out.println("TV를 켭니다.");
		}
		
		@Override
		public void turnOff() {
		System.out.println("TV를 끕니다.");
		}
	};
	  
	// 대입한 필드 이용
	public void use1() {
		rc.turnOn();
		rc.turnOff();
	}
	
	// 메소드 로컬 변수를 이용하여, 로컬 변수에 익명 구현 객체 대입
	public void use2() {
		RemoteControl rc = new RemoteControl() {
			@Override
			public void turnOn() {
			System.out.println("에어컨을 켭니다.");
			}
			  
			@Override
			public void turnOff() {
			System.out.println("에어컨을 끕니다.");
			}
		};
		rc.turnOn();
		rc.turnOff();
	}
	
	// 메소드 매개변수 이용
	public void use3(RemoteControl rc) {
		rc.turnOn();
		rc.turnOff();
	};
}
```

```
// HomeExample 클래스(main 메서드)
public class HomeExample {
	public static void main(String[] args) {
		Home home = new Home();
		// use1: 익명 구현 객체가 대입된 필드 이용
		home.use1();
		
		// use2: 익명 구현 객체가 대입된 로컬 변수 사용
		home.use2();
		
		// use3: 익명 구현 객체가 대입된 매개 변수 사용
		home.use3(new RemoteControl() {
			@Override
			public void turnOn() {
				System.out.println("난방을 켭니다.");
			}
			
			@Override
			public void turnOff() {
				System.out.println("난방을 끕니다.");
			}
		});
	}
}
```

- 한번 만든 객체를 수정해서 사용할 수 없음. (생성하는 코드가 일회성. 계속 사용할 거면 클래스 새로 생성해야함)
- 스레드나 이벤트에 사용하는 경우가 많음
- 클래스 내부의 코드가 클래스의 필드와 메서드를 정의하는 것이라면, 익명 객체 구현/상속 코드는 객체를 생성하는 실행 코드


##### 라이브러리
- 프로그램 개발 시 활용할 수 있는 클래스와 인터페이스를 모아놓은 것
- 일반적으로 JAR(~.jar)형태. 클래스와 인터페이스의 바이트코드파일(.class)들이 압축되어 있음
- 라이브러리 JAR파일을 활용하기 위해서 ClassPath에 추가
	- ClassPath: 클래스를 찾기 위한 경로
- 콘솔에서 프로그램을 실행할 경우: -classpath로 제공(`java -cp`, `java -classpath`). 또는 CLASSPATH 환경 변수에 경로를 추가해야함.
	- 새 시스템 변수(`변수이름: CLASSPATH`, `변수 값: 경로`)
- 이클립스 프로젝트에서 실행할 경우: Build Path에 추가
- window cmd에서 classpath구분자: `;`
	- `java -classpath D:\Java\workspace\my_lib\dist\my_lib.jar;. app.Main` 와 같은 방식으로 cmd에서 사용
	- `D:\Java\workspace\my_lib\dist\my_lib.jar;` : 라이브러리가 존재하는 경로
	- `. app.Main`: 현재 경로에서 app.Main을 찾음