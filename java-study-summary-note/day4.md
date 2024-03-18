#### ==객체(Object)==
- 다른 것과 식별 가능한 것
- 속성과 동작(기능)으로 구성 → `속성:필드`, `동작:메소드`
- OOP: Object Oriented Programming, 객체 지향 프로그래밍
	- Java는 객체지향 프로그래밍 언어
	- 호출(실행, call), 리턴(return)을 통해서 객체 사이 상호작용, 다른 객체의 기능 이용
	- ==**상속(Inheritance)**==: 부모와 자식 관계, 필드와 메소드를 물려받음. 
	- ==**캡슐화**==: 데이터, 동작을 하나로 묶어서 실제 구성 내용을 외부에 감추는 것. (필드, 메소드 -> 클래스) 자바 특징, 파이썬이나 JS는 잘 안 됨.
	- ==**다형성**==: 다양한 자료형을 가질 수 있는 성질,? 

##### 클래스와 인스턴스(Class, Instance)
- 클래스와 연관 있게 호칭할 때 인스턴스
- ex. A클래스의 인스턴스 A1객체
- 클래스로부터 객체를 만드는 것을 인스턴스화라고 함
 
###### 생성자(Constructor)
- 객체를 생성해주며 객체의 초기화 담당
	- 클래스의 기본 생성자는 직접 정의한 생성자가 없을 때 컴파일러가 자동으로 생성시킴. 
	- 기본 생성자: 매개변수 존재x
	- 클래스가 public이면 기본 생성자도 public이 붙지만 없으면 기본 생성자에도 안붙는다.
	- `.java`파일에는 없지만 `.class`로 컴파일 되면 생김(직접 확인할 수는 없다)
- 리턴이 없어야 하며, 이름은 클래스명과 동일해야 함.

###### 필드(field)
- 변수와 달리 선언 위치 상관 없이 활용할 수 있음.
- 자동으로 초기화가 됨(기본 타입은 0, 참조 타입은 null으로)
	- 지역 변수는 초기값을 제공하지 않으면 사용할 수 없음.

###### 메서드(method)
- 기능, 동작
```
// 선언부, 맨윗줄
// 리턴타입 메서드명 ([매개변수])

double divide(int x, int y){
	return (double) x / y;
}
```
	-  함수(function): 단독으로 실행
	-  메서드(method): 객체를 통해서 실행. 클래스 안에 만들어야 함.

###### 매개변수의 길이가 가변 길이인 메서드
```
int sum(int... n) { // 그냥 배열로 처리하는게 더 일반적 int sum(int[] n)
	int sum = 0;
	for(int i = 0; i < n.length; i++) {
		sum += n[i];
	}
	return sum;
}
```

###### this
자기 자신 객체. 클래스 내부에서 해당 클래스의 생성자 호출로도 사용할 수 있음

###### 인스턴스(instance) - 정적(static)
-  `// 이게 저장되는 공간이 다른 거던가?`
- 인스턴스 멤버: 객체에 소속된 멤버. 객체를 생성해서 접근해야 한다
- 정적 멤버: static 표기해야 함.  객체를 생성할 필요없이 클래스 명으로 접근하여 필드를 활용할 수 있음
- static 메서드에서 사용할 필드는 static이어야 함.
- static 필드는 해당 클래스의 모든 객체에서 공유
	- static 변수 타 객체로 접근해서 수정하면 해당 객체 뿐만아니라 해당 클래스 변수 다 변경됨. 즉 해당 클래스의 모든 인스턴스에서 수정사항이 적용됨

- static블록
	- MemberMain 클래스
```
package ch06.sec07;

public class MemberMain {
	static {
		System.out.println("MemberMain static 블록"); // static 블록이 메인 메서드보다 더 빠르다
	}
	
	public static void main(String[] args) {
		System.out.println("메인 메서드");
	
	// 인스턴스 멤버(필드, 메서드) 사용하기 위해서 객체 생성
	Member m = new Member();
	m.name = "홍길동";
	m.print();
	
	// static 멤버는 객체 없이 클래스로 접근해서 사용이 가능하다
	System.out.println(Member.school);
	Member.print2();
	
	m2.school = "서울고등학교";
	
	System.out.println(m.school);
	System.out.println();
	}
}
```

 - Member 클래스
```
package ch06.sec07;

public class Member {
	
	Member() {
		System.out.println("생성자");
	}
	
	// static 블록 (메모리에 로드될 때 초기 한번)
	static {
		System.out.println("static 블록");
	}
	
	// 인스턴스 블록 (초기화)
	{
		System.out.println("instance 블록");
	}
	
	// 인스턴스 필드
	String name;
	
	// static 필드: 객체 생성 전 클래스가 메모리에 로드될 때 생성됨
	static String school = "신한고등학교";
	
	// 인스턴스 메서드
	void print() {
		System.out.println(this.name + "님 안녕하세요."); // this는 인스턴스화 되었을 경우를 고려함
	}
	
	static void print2() {
		System.out.println("안녕하세요");
		// 인스턴스가 아니라서 내부에 this 사용 X
	}
}
```