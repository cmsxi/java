##### 4일차 복습
###### 객체지향프로그래밍(OOP)
- "부품(컴포넌트)" 단위로 조합하는 형태의 프로그래밍
- 현재: 객체지향 + 함수형
- 디자인 패턴

###### 클래스 목적
- 데이터(값) 저장 ~ (타입)
- 기능(메서드)을 모아놓기 위해(나중에 사용하기 위해서)

###### 클래스 구성멤버
- 필드 
	- 변수와 비슷
	- 클래스 블록에 선언(변수는 메서드 블록에 선언)
	- 클래스 전체에서 사용해야 하는 경우 선언
	- 자동 초기화(기본 자료형:0, 참조 자료형: null)
```
class Test{
	int a ; // 필드
	void test(){
		int a; // 변수, 지역변수
	}
}
```

- 생성자 
	- 리턴 타입이 없고, 이름이 클래스명과 동일함
	- 초기화(객체 생성할 때)
	- `Test test = new Test()` : 'new Test()'가 생성자 호출, 실행한 거임. 
	- 기본 생성자: 생성자가 없을 시 컴파일러에 의해 자동 추가(`.class`파일 안에)

- 메서드
	-  기능 
	-  코드의 재사용성: 나중에 사용하기 위해서 사용
	- 선언부(`[접근제한자] [static] 리턴타입 메서드명([매개변수])`)

- static, 인스턴스(필드, 메서드)
	- 인스턴스: 객체 통해서 사용
	- static : 클래스 통해서 사용. 모든 객체에서 공유


--------------
### 5일차
##### `final`
- final: 마지막, 최종. 필드 선언 시 사용하면 초기화 이후 수정 불가
- 필드, 메서드, 클래스에서 사용 가능
- 상수 선언: `static final 타입 상수명`, 상수명은 대문자 표기

##### 패키지
- 경로를 통해 빨리 찾을 수 있음(식별)
- 다른 클래스에서 사용하기 위해서 패키지 사용
- 패키지 없는 클래스 사용 불가(단, 외부에서 실행은 가능)
- import 없이 사용 가능한 클래스
	- 같은 페이지에 있는 경우
	- `String`, `Math`와 같이 java.lang 패키지에 있는 경우

##### 접근 제한자
- 범위(기준): 클래스, 패키지
- public: 제한 범위 없음
- private: 객체 내부에서만 활용 가능(ex. 클래스 내부 메서드에서 실행)
- default: 같은 패키지에 있으면 사용 가능. 따로 정의 안해줘도 됨
- protected: 같은 패키지 혹은 자식 객체에서 사용(다른 패키지에 있어도 상속받는 자식 객체라면 사용 가능)
- // 파일 내에 public 하나는 있어야 접근 가능함.\<\<? 두 개 이상은 X

##### ==Getter, Setter==
>  - VO: Value Object, 값 객체
>  - DTO: Data Transfer Object, 데이터 전송 객체
>   값을 저장하기 위해 위의 객체 활용

- VO, DTO에 있는 필드는 private(~보안, 기능 설정 등의 이유)
- 접근 및 정의에 getter, setter 사용
	- setter: 데이터 검증 후 유효 값만 데이터 필드에 저장
	- getter: 데이터가 출력하기 적절하지 않을 경우 적절한 타입으로 변환 후 리턴
- getter/setter 메서드 자동 생성 단축키: 
	`alt + shift + s` → `r` →`alt + a` → `r`

##### 싱글톤(Singletone)
- 하나의 객체만 생성해 놓고 사용
- 메모리 사용을 줄임 + 속도 
- 생성자 private: 외부에서 생성자 호출이 불가능함.
- 기능 위주의 클래스는 싱글톤 사용
- 값을 저장하는 객체에는 사용 금지: 객체가 하나이므로 여러 값 저장 불가
```
public class Singletone{
	private static Singletone singletone = new Singletone();
	
	private Singletone(){
	}
	 
	public static Singletone getInstance(){ 
	// 해당 메서드 호출해서 싱글톤 객체 얻을 수 있음
		if (singletone == null) {
		Singletone singletone = new Singletone();
		}
		return singletone;
	}
}
```

##### ==상속==
- **자식**이 부모로부터 필드 및 메서드를 물려받음
- 물려받은 필드와 메서드는 `.java`파일에는 없으나 컴파일 이후의 `.class`파일에는 존재함
- 상속의 장점: 재사용, 중복 코드 감소, 클래스 수정 최소화(부모 클래스 수정하면 자식 클래스에도 적용), **다형성**
- 다중 상속 불가능. `extends`뒤에 하나의 부모 클래스만 상속 가능
- `super`: 부모 클래스 생성자 호출하는 키워드 
- `java.lang.Object`: 모든 클래스의 상위 클래스
- 자식 클래스의 생성자에는 부모클래스의 생성자`super();`가 생략되어 있음
- final 클래스는 상속 불가능. final 메서드도 오버라이딩이 불가능함. 

- 부모 클래스
```
public class Phone {
	Phone(){
		System.out.println("부모 클래스 생성자");
	}
}
```

- 자식 클래스
```
public class SmartPhone extends Phone {
	SmartPhone(){
		// super();
		super(<매개변수>); // 부모 클래스에 매개 변수가 있을 경우 반드시 매개변수를 포함하여 적어줘야함. 아니면 오류
		System.out.println("자식 클래스 생성자");
	}
}
```

- 실행 클래스
```
public class Main {
	public static void main(String[] args) {
		SmartPhone sp = new SmartPhone();
	}
}
```

- 출력
```
부모 클래스 생성자
자식 클래스 생성자
```


##### 메서드 오버라이딩(method overriding)
- 상속된 메서드를 자식 클래스에서 다시 재정의하는 것 → 부모 클래스의 메서드는 숨겨지고 자식 클래스의 메서드가 우선 시 사용됨
- `@Override`어노테이션(annotation) 사용
	- 컴파일시에 오버라이딩이 제대로 되었는지 확인해 줌. 생략은 가능하나 사용 권장
- 오버로딩(overloading)과 달리 매개변수가 다 동일해야 함.


##### 자동 형변환(Casting)
- 자식 타입은 부모 타입으로 자동 형변환이 가능하다. 
- `long a = 1;`이 가능한 것과 비슷한 원리임.
- `부모클래스: Animal`과 `자식클래스:Cat`이 있을 경우 `Animal animal = new Cat();`이 가능하다.
- 단, 위의 예시에서 `Cat`에만 정의된 메서드는 `animal`에서 사용할 수 없다. animal은 Animal 타입이기 때문. (∵ 부모 타입에는 존재하지 않는 메서드)
- 그러나 Cat에서 오버라이딩 된 메서드는 animal에서도 재정의한 형태로 사용된다.
###### 자동 형변환 예시
- Phone 클래스
```
public class Phone {
	void test() {
		System.out.println("test");
	}
	void work() {
		System.out.println("work");
	}
}
```

- SmartPhone 클래스
```
public class SmartPhone extends Phone {
	@Override
	void test() {
		System.out.println("테스트");
	}
	void study() {
		System.out.println("공부");
	}
}
```

- Main 클래스(main 메서드 有)
```
public class Main {
	public static void main(String[] args) {
		SmartPhone sp = new SmartPhone();
		sp.test();
		sp.work();
		sp.study();
		
		System.out.println();
		
		Phone p = sp;
		p.test();
		p.work();
		// p.study(); Phone 클래스에 존재하지 않는 메서드 이므로 오류 발생
	}
}
```

- 출력
```
테스트
work
공부

테스트
work
```

###### 자동 형변환 사용 이유
- 한 번에 다른 타입의 클래스를 상위 클래스로 묶을 수 있음.
- 하나의 코드로 각 객체에 적합한 결과를 낼 수 있음 → ==다형성
```
Phone[] phone = {smartPhone, watch}; 
for (Phone ph : phone){
	ph.test()
}
```



##### 강제 형변환
```
// Phone: SmartPhone의 부모 클래스, SmartPhone: Phone의 자식 클래스
Phone p = new Phone();
SmartPhone sp2 = (SmartPhone)p;
sp2.study(); // → 형변환을 했기 때문에 실행 가능
```

- 아래 코드는 error 발생함. (실행하면 에러. 메세지 정확하게 안나옴)
```
Phone p2 = new Phone(1);
SmartPhone sp3 = (SmartPhone)p2; // 에러 발생

Object sp4 = new SmartPhone();
SmartPhone sp5 = (SmartPhone)sp4; // 가능

Object obj = new Object();
SmartPhone sp6 = (SmartPhone)obj; // 위와 동일하게 에러 발생
```

- **강제 형변환 조건**
	- 자식 타입의 객체가 부모 타입으로 형변환이 된 상태여야 가능
	- SmartPhone이 Phone으로부터 형변환이 된 상태라면 가능
	- 원래 부모 객체였다면 형변환 불가능함

##### toString 오버라이딩
```
@Override
public String toString(){
	return "toString Overried 메서드";
}
```

```
Object sp4 = new SmartPhone();
System.out.println(sp4.toString());
System.out.println(sp4); // 객체를 출력해도 toString이 자동으로 호출됨
```

##### ==다형성==
- 동일한 사용 방법에 대해 다른 결과가 나올 수 있는 성질
- 모든 클래스는 Object를 상속받기 때문에 Object 배열에는 모든 클래스를 다 담을 수 있음.
- `Object[] obj = {sp, 1, "문장", p, smartWatch1 ....};` ← 모두 Object 타입으로 형변환이 일어나고 있음.

###### 필드 다형성
- 변수와 동일
```
Phone phone;
void test() {
	phone = new SmartPhone();
}
```

###### 매개변수의 다형성
```
Phone phone;
void test() {
	phone = new SmartPhone();
	test(phone);
	test(new Phone(1));
	test(new SmartWatch());
}
void test(Phone p){
	// 자동 형변환이 일어남. 
	// SmartPhone, SmartWatch이 Phone을 상속받으므로 가능
}
```