### 10일차

#### 컬렉션
#### Set
- 저장 순서 유지x,
- 수학에서의 집합, 순서가 상관없고 중복 허용이 안됨
- add, contains, isEmpty, iterator, size, clear, remove 등의 메서드 → get없음(인덱스가 없어서)
- Map에서 key 저장할 때 많이 사용
#### HashSet
```java
Set<E> set = new HashSet<E>();
Set<E> set = new HashSet<>();
```
- 다른 객체여도 hashCode() 메서드의 리턴값이 같고 equals() 메서드가 true를 리턴하면 동일 객체라 판단함 
- 같은 문자열 동일하게 판단

###### 예시
- Member 클래스
```java
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class Member {
	public String name;
	public int age;
	
	@Override
	public int hashCode() {
		return name.hashCode() + age;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member target) {
			return target.name.equals(name) && (target.age==age);
		}else {
			return false;
		}
	}
}
```

- 실행 클래스
```java
import java.util.*;

public class HashSetExample {
	public static void main(String[] args) {
		Set<Member> set = new HashSet<Member>();
		
		Member m = new Member("홍길동", 30);
		set.add(m);
		set.add(m); // 같은 객체 추가
		
		set.add(new Member("홍길동", 30));
		set.add(new Member("홍길동", 30)); // 다른 객체 추가
		  
		System.out.println("총 객체 수 : " + set.size()); // 결과: 총 객체 수 : 1
		// 현재 코드에서 다른 객체를 저장하더라도 내용이 동일하면 한 번만 저장이 되는 것을 알 수 있음
		// Member 객체에서 equals를 오버라이딩 했기 때문 (오버라이딩 하지 않았을 시 총 객체 수 3개 나옴)
		}
}
```


#### Map
- key와 value로 구성된 entry로 객체 저장
- 아래와 같은 메서드로 구성
	- 추가: **put(key, value)**
	- 객체 검색:
		- `containsKeys(key)`, `containsValues(value)`, `Set<Map.Entry<K,V>> entrySet()`
		- **get**, isEmpty, **`Set<K> keyset()`**, **size**, values
	- 삭제: clear, remove
#### HashMap
- key, value가 쌍으로 존재
- key는 중복 불가, 불변의 값 사용
- value는 중복 가능
- 타입 제한이 없음(제네릭, 참조자료형)
- index 없음 → 순서가 없음
```java
Map<K, V> map = new HashMap<K, V>();
```








#### Properties
- `=`왼쪽은 key, `=`오른쪽은 value
- 보안 목적으로 사용
```properties
driver = oracle.jdbc.OracleDriver
url = jdbc:oracle:thin:@localhost:1521:orcl
username = scott
password = tiger
admin = \uD55C\uAE00
```

#### 경로
- path: 폴더 + \[파일명]
- 절대 경로
	- 최상위 디렉토리부터 시작
		- 윈도우: 드라이브 명 `C:` 으로 시작
		- 맥, 리눅스, 유닉스: `/`로 시작
	- 예시: `C:\Windows`
- 상대 경로
	- 현재 위치에 따라 달라짐
		- 현재 위치: `.`
		- 현재 위치보다 한 단계 위: `..`

#### Comparable과 Comparator
- 객체가 Comparable 인터페이스를 구현하고 있어야 정렬 가능
- Integer, Double, String 타입은 구현 하고 있어서 사용 가능
- 사용자 정의 객체를 저장할 시에는 Comparable을 구현하고 있어야 사용 가능 함(오버라이딩)
- `compareTo` : 주어진 객체와 같으면 0, 작으면 음수, 크면 양수 return
- 비교 예시
```java
public class SortEx {
	public static void main(String[] args) {
		
		// 기본자료형 정렬
		int[] array = { 50, 10, 60, 30, 80, 40, 90 };
		Arrays.sort(array); // 오름차순
		
		// Arrays.sort(array, Collections.reverseOrder());// 기본자료형은 해당 방식 에러 발생
		System.out.println(Arrays.toString(array));
		
		  
		// 참조자료형 배열
		Integer[] array2 = new Integer[] { 50, 10, 60, 30, 80, 40, 90 };
		
		// Arrays.sort(array2); // 오름차순
		Arrays.sort(array2, Collections.reverseOrder());// 내림차순
		System.out.println(Arrays.toString(array2));
		  
		
		// Person 대신 Map 사용 => List 객체에 Map 담기
		// 나이순 정렬
		List<Map<String, Object>> list = new ArrayList<>();
		
		// K: 이름, V: 나이
		Map<String, Object> person = new HashMap<>();
		person.put("name", "홍길동");
		person.put("age", 45);
		list.add(person);
		  
		person = new HashMap<>();
		person.put("name", "감자바");
		person.put("age", 25);
		list.add(person);
		
		person = new HashMap<>();
		person.put("name", "박지원");
		person.put("age", 31);
		list.add(person);
		
		System.out.println(list);
		
		// 정렬
		// 기본 자료형의 래퍼 클래스가 제네릭 타입인 경우
		List<Integer> list2 = new ArrayList<>();
		list2.add(10);
		list2.add(30);
		Collections.sort(list2);
		
		// list 안에
		// 제네릭 타입이 Map<String, Integer>
		Collections.sort(list, new Comparator<Map>(){
			@Override
			public int compare(Map o1, Map o2) {
				// return (int) o1.get("age") - (int)o2.get("age"); // 오름차순
				return (int) o2.get("age") - (int)o1.get("age"); // 내림차순
				}
			});
		System.out.println(list);
		System.out.println(list.get(2).get("name")); // 3번째로 나이가 많은 사람 출력
	}
}
```


#### 람다식
- 함수
	- 단독으로 존재. 
	- 함수를 정의하고 해당 함수를 데이터 처리부로 보낼 수 있음.
	- 데이터 처리부는 데이터만 가지고 처리 방법은 정해져 있지 않음
- 메서드 
	- 클래스 안에 존재
- 함수형 프로그래밍: 매개변수에 함수를 넣을 수 있음 -> 자바에서는 안 됨(매개변수로 메서드를 전달할 수 없음, 객체를 전달할 수는 있음)

- **람다식**
	- 매개변수 자리에 값이 아닌 기능을 넣어주고 싶어서 사용
	- 변수에 타입 안 씀
	- 자바는 람다식을 익명 구현 객체로 변환함
	- `->` : 이름이 없는 메서드
	- 추상메서드가 하나만 있는 인터페이스는 람다식 활용하기 좋음

- 예시1
```java
Thread thread = new Thread(() - > {
	System.out.println("run 메서드 재정의"); // 실제로 run 메서드를 재정의한 구문임
	// Runnable 인터페이스에서 메서드가 단 하나만 존재하기 때문에 람다식 재정의가 가능함
	// 함수적 인터페이스: 함수처럼 사용하기 위해서 추상 메서드 하나만을 가지고 있는 인터페이스 → 람다식 활용이 가능함
	// 스레드, 이벤트 등에 람다식 많이 활용
}); ```

- 예시2
```java
Collections.sort(list, (o1, o2) -> (int)o2.get("age") - (int)o1.get("age"));
// 내부가 실행문이면 세미콜론 넣어져있음
// new Comparator없이 재정의 가능
// 위에서 2번째 매개변수는 Comparator 타입의 객체이어야 함
// 즉 람다식은 익명 구현 객체로 변환된다는 의미
```


```
@FunctionalInterface
public interface Calculable {
	void calculate(int x, int y);
}
```

```
public class LambdaExample {
	public static void main(String[] args) {
		action((x, y) -> {
			int result = x + y;
			System.out.println("result: " + result);
		});
		
		action((x, y) -> {
			int result = x - y;
			System.out.println("result: " + result);
		});
	}
	
	public static void action(Calculable calculable) {
		int x = 10;
		int y = 4;
		
		//데이터 처리
		calculable.calculate(x,y);
	}
}
```