### 8일차

#### 문자열 클래스

##### String
- 문자열 빈칸 , null, 공백 비교하기
```java
// 빈칸 null 공백 비교하기
// a와 b와 c 모두 다름
String a = "";
String b = null;
String c = " ";
```

- ~~문자열 인코딩과 디코딩(UTF-8, EUC-KR) → 잘 안씀~~
```java
// byte 배열 <-> 문자열 (기본은 UTF-8)
String data = "문자열";

byte[] arr1 = data.getBytes(); // UTF-8 인코딩
String str = new String(byte[] bytes); // byte배열 -> String: UTF-8 디코딩

byte[] arr2 = data.getBytes(); // EUC-KR 인코딩
String str2 = new String(byte[] bytes, "EUC-KR"); // byte배열 -> String: EUC-KR 디코딩
```

- 문자열 인코딩과 디코딩: data를 주고 받을 때 인코딩 디코딩을 해야 한글 등의 문자가 깨질 위험을 예방할 수 있음.
	- 요즘 브라우저에서는 자동으로 해주는 경우가 많음.
```java
public static void main(String[] args) throws Exception {
	String text = "한글";
	
	String text2 = URLEncoder.encode("한글", "UTF-8");
	System.out.println(text2);
	
	String text3 = URLDecoder.decode(text2, "UTF-8");
	System.out.println(text3);
}
```

- 출력
```
%ED%95%9C%EA%B8%80

한글
```

- 주석: 메서드 위에 적어두면, 이후 메서드 사용할 때 설명 표시되게 할 수 있음
```java
/**
* 로그인 처리
* 메서드에 대한 설명
* @param id 아이디
* @patam pwd 비밀번호
* @return 로그인 성공 여부
*/
```

##### StringBuilder
- 잦은 문자열 변경 작업을 해야한다면 String보다 StringBuilder가 좋음.
- StringBuilder: 내부 버퍼에 문자열을 저장해두고 그 안에서 추가, 수정, 삭제 작업을 하도록 설계
	- **append(기본값 | 문자열)** : 문자열을 끝에 추가
	- insert(위치, 기본값 | 문자열) : 문자열을 지정 위치에 추가
	- delete(시작 위치, 끝 위치) : 문자열 일부를 삭제
	- replace(시작위치, 끝위치, 문자열) : 문자열 일부를 대체
	- **toString()** : 완성된 문자열을 리턴
- 메서드 리턴타입이 StringBuilder이므로 위에서 toString을 제외하고 **메서드 체이닝 패턴**을 사용할 수 있음. 
- StringBuffer와 비슷
```java
String data = new StringBuilder()
		.append("ABC")
		.toString(); 
// == data.append("ABC");
// data.toString();
```



##### StringTokenizer
- 하나의 구분자만 있는 경우 split을 대신하여 사용할 수 있음
- split은 .split("&|,|-"); 와 같은 식으로 여러 개 구분자 사용 가능
- .countTokens() : token이 몇 개 있는지
- .hasMoreTokens() : token이 더 이상 있는지 확인
- .nextToken() : 다음 token으로 이동



#### wrapper 클래스
- 기본 자료형을 클래스로 만들어 놓은 것
- 포장하고 있는 기본 타입의 값을 변경할 수는 없고, 객체를 생성하는 목적으로 사용
- Boxing: wrapper 객체로 만드는 과정
- Unboxing: wrapper 객체에서 기본 타입 값을 얻어내는 과정
```java
Integer obj = 100; // auto boxing
int value = obj; // unboxing

int value = obj + 50; // unboxing 후 연산 
```
- **`parse+기본타입`** : 문자열을 기본 타입으로 변환할 때 사용
- **`String.valueOf(기본타입)`** : 기본타입을 문자열로 변환할 때 사용
	- `3 + ""` : 빈 문자열 더해줘도 문자열 변환 가능 
- 포장 객체는 번지로 비교를 하기 때문에 `==`, `!=`로 내용 비교 불가능


#### Math 클래스
- 자바에서 계산하는 것보다 DB에서 처리하는 게 빠르므로 실제 개발에서는 거의 사용 안 함
- `Math.abs(숫자)` : 절대값
- `Math.ceil(숫자)` : 올림값
- `Math.floor(숫자)` : 버림
- `Math.max(숫자1, 숫자2)` : 최대
- `Math.min(숫자1, 숫자2)` : 최소
- `Math.random()` : 난수 생성 
- `Math.round(숫자)`: 반올림, 메서드 자체에서 자릿수 지정 불가능

- 랜덤은 Random이라는 클래스가 존재함 
#### 형식 클래스
- DecimalFormat 클래스
- #,###
```java
int price = 187000;
double dPrice = 187000.1;

DecimalFormat df = new DecimalFormat("#,###.00");

String strPrice = df.format(price);
String dStrPrice = df.format(dPrice);

System.out.println(strPrice); // 출력: 187,000.00
System.out.println(dStrPrice); //출력: 187,000.10
```

##### 정규 표현식 Pattern 클래스로 검증
```java
public static void main(String[] args) {
	// 소문자 b + 알파벳 소문자 0개 이상
	// compile: 실행하기 위해서 미리 준비시키는 작업이라는 의미로 사용
	// 미리 compile하고 문자열로만 비교하면 수행 속도가 빨라짐
	Pattern p = Pattern.compile("b[a-z]*");
	Matcher m;
	
	m = p.matcher("c");
	System.out.println(m.matches());
	
	String[] str = { "bat", "cat", "bed", "blue" };
	for (String string : str) {
		m = p.matcher(string);
		System.out.println(m.matches());
	}
}
```

- 출력 
```java
false
true
false
true
true
```


##### 객체로부터 메타정보 얻어내기
```java
Class clazz = 객체참조변수.getClass();
```



#### 어노테이션
- `@AnnotationName`: 클래스 또는 인터페이스를 컴파일하거나 실행할 때 어떻게 처리해야 할 것인지 알려주는 설정 정보
	- 컴파일 시 사용하는 정보 전달 (예: `@Override`)
	- 빌드 툴이 코드를 자동으로 생성할 때 사용하는 정보 전달 (예: lombok)
	- 실행 시 특정 기능을 처리할 때 사용하는 정보 전달 
- 아무런 동작을 하지 않고 그냥 컴파일 시 설정 정보 만을 전달함

##### 어노테이션 정의
- 어노테이션 정의하기
```java
public @interface AnnotationName{
	String prop1(); // 속성 타입과 속성명
	int prop2() default 1; // default 뒤에 쓰면 기본값(어노테이션 사용시 생략 가능)
}
// => @AnnotationName(prop1 = "~~~", prop2 = 2)
// => @AnnotationName(prop1 = "~~~")
```

- value
```java
public @interface AnnotationName{
	String value(); 
	// 어노테이션을 쓸 때 @AnnotationName(value = "`~~")말고
	// 그냥 바로 어노테이션을 쓸 때 @AnnotationName("~~~")과 같은 형식으로 사용 가능함.
}
```

- 기타 적용 대상 및 유지 정책도 지정 가능함
	- `@Target`: 적용 대상 설정 키워드
	- `@Retention`: 유지 정책 설정 키워드


#### 제네릭
- 결정되지 않은 타입을 파라미터로 처리하고, 실제 사용 시 파라미터를 구체적인 타입으로 대체시키는 기능 
	- Object 타입은 어떤 객체든 다 담을 수 있지만 이후 활용하기 위해 형변환을 해야함
- 타입을 강제: 컴파일 단계에서 사전에 에러 차단
- 타입 재정의: 형변환 불필요
- (나중에 보기) 제한된 타입 파라미터 / 와일드카드
```java
public class C <T>{
	public T content;
}
```

- Box 클래스
```java
@Getter @Setter
public class Box<X> {
	public X content;
}
```
- GenericEx 클래스
```java
public class GenericEx {
	public static void main(String[] args) {
		List<String> list = new ArrayList();
		// List: 인터페이스, ArrayList: List 구현 클래스
		
		// list.add(1);
		list.add("1");
		
		list.add("홍길동");
		
		// list.add(new Car());
		list.add((new Car().getModel()));
		  
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			// 제네릭에서 String으로 지정해줬으므로 get의 return 타입도 String
			// 원래 정의는 E(element)로 되어있음
			// T(Type), K(Key), V(Value), E(Element)라는 의미로 각각 사용
		}
		
		Box b = new Box(); // 제네릭 안쓰면 경고메시지, Object 타입으로 사용하게 됨
		b.setContent("자바 프로그래밍");
		
		System.out.println(b.getContent());
		
		// System.out.println(b.getContent().replace("자바", "파이썬")); Object로 return이 나오므로 replace는 사용 못함
		System.out.println(((String) b.getContent()).replace("자바", "파이썬"));
		
		Box<String> box = new Box<>();
		box.setContent("자바 프로그래밍");
		System.out.println(box.getContent().replace("자바", "파이썬"));
		
	}
}
```

##### Comparator 재정의
- 익명 구현, 제네릭, 오버라이딩
```java
int[] arr = {2, 3, 1, 5, 4};
Arrays.sort(arr, new Comparator<T>(){
	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return 0;
	}
});
```

###### 생각해보기..
```java
List<String> list1 = new ArrayList<Object>(); // 오류
List list2 = new ArrayList<Integer>(); // 됨, 근데 list2 요소에 String도 담김....
List<Integer> list3 = new ArrayList<>();
```

##### 타입 추론
- 컴파일러가 알아서 타입을 추론해서 넣어줌
- Java11부터 가능
- <\T>에 대입 안 하면 안됨
```java
var b = new Box<String>();
```

