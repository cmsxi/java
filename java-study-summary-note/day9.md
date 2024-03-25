### 9일차

#### 멀티 스레드
- 프로세스: 실행 중인 프로그램, 운영체제가 관리
- 멀티 태스킹: 두 가지 이상의 작업을 동시에 처리
- 스레드: 코드의 실행 흐름

- 멀티 태스킹이 멀티 프로세스를 뜻하지는 않음
- 멀티 프로세스 - 서로 독립적이므로 하나의 프로세스가 종료되더라도 다른 프로세스에 영향을 끼치지는 않는다
- 멀티 스레드 - 하나의 스레드가 종료되면 프로세스가 종료되므로 다른 스레드에 영향을 끼치기도 함

##### 멀티스레드 만들기
1. Runnable() 인터페이스 구현
-  익명 구현 객체 만들기
```java
import java.awt.Toolkit;

public class BeepPrintExample2 {
	public static void main(String[] args) {
	// 익명 구현 객체 활용, 구현 클래스 따로 만들어서 사용할 수도 있음
		Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			for (int i = 0; i < 5; i++) {
				toolkit.beep();
				try { Thread.sleep(500);} catch (Exception e) {}
			}
		}
	});
		thread.start();
		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
		}
	}
}
```

- 익명 자식 객체 만들기 
```java
public class BeepPrintExample {
	public static void main(String[] args) {
		Thread thread = new Thread() {
		@Override
		public void run() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			for (int i = 0; i < 5; i++) {
				toolkit.beep();
				try {Thread.sleep(500);} catch (Exception e) {}
				}
			}
		};
		thread.start();
		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
		}
	}
}
```
2. Thread() 클래스 상속


##### 동기화
- `synchronized`: 동기
	- 메서드 or 블록에 해당 키워드 붙이면 동기적이 됨
- `asynchronized`: 비동기
	- AJAX : A
	- synchronized JavaScript And Xml




#### 컬렉션
- 주요 인터페이스: <mark>List, Set, Map</mark>
	- List: 컬렉션 상속, 순서 유지 저장, 중복 가능
		- **ArrayList**
		- LinkedList
		- Vector
	- Set: 컬렉션 상속, 순서X, 중복X
	- Map: key, value로 구성된 엔트리 저장, 키 중복 불가
		- **HashMap**


##### List 컬렉션
- boolean add(E e) : 객체 맨 끝에 추가
- int size() : 저장된 객체 개수 리턴
- E get(int index) : 주어진 인덱스에 해당하는 객체 리턴

##### ArrayList
- 내부 배열에 객체가 저장되고 제한없이 객체를 추가할 수 있음 ↔ 기본 array와 다른 점
- 객체 번지 저장. 객체 중복 저장 시 동일 번지가 저장되며 null 저장이 가능
- 인덱스 0번부터 차례대로 저장
	- .remove(0)을 하면 뒤에 있는 인덱스가 하나씩 당겨짐 → 중간에 빈칸이 없음
- 빈번한 객체 삭제와 삽입이 일어나는 곳에서 바람직하지 않음
	- 해당 경우 Linked List 사용하는 게 좋음
	- Linked List는 앞의 순서를 통해 뒤의 순서를 알아내므로 순차적 출력의 경우 느림
	- LinkedList 검색 느림
	- 어플리케이션은 DB에서 삭제 삽입을 하는 경우가 많으므로 잘 안 사용함
- 순서가 있고 차례대로 저장되어 있으므로 순차적 작업이 필요한 경우 유리함


##### 