##### Java
* main 메서드: 프로그램 실행 시작점
* 소스(.java) -> 컴파일 -> 클래스(.class) -> 실행
java파일 수정했는데 결과가 안 변한다면 컴파일이 안되고 있는지 확인하기. (디버깅)

##### 변수
* 프로그램 실행 중에 변할 수 있는 수 
* 다른 곳에서 사용(=여러번 사용)시 활용
* 메모리에 저장된 값을 가리키는 주소
* 선언(JS, 파이썬에서는 안해줘도 됨)
```
자료형 변수명;
```
* 초기값 설정, 초기화
```
변수명 = 값;
```

##### 자료형(data type)
* 정적 타이핑(static typing): 자바, C++, 자료형 지정 후 바로 변경(다른 자료형으로 변수 지정) 안됨
* 동적 타이핑(dynamic typing): 파이썬, JavaScript

* 기본자료형: 정수, 실수, 문자, 논리
* 참조자료형: 배열, 클래스, 인터페이스 ...

##### 이스케이프 문자
```
\t  \"  \n  \\ 
```

##### ==형변환==(타입 변환)
```
// 자동형변환: 큰 타입에 작은 타입 대입하면 자동으로 형변환이 일어남
int a = 1;
long b = a;
// a = b 는 안된다.
double = b;

// 강제형변환(Casting, cast): 직접 명령해서 형변환 시킴
b = (long)c; 

// 소수의 경우 정수로 강제형변환하면 소수점 버림.
// java 에서 정수끼리 연산 결과 정수 -> 10/3=3


// 연산 중 자동 형변환
// byte, short 타입은 연산 과정에서 int로 바뀐다.
// 실수는 double로
byte x = 10;
byte y = 20;
// byte z = x + y; <-- 컴파일 에러. 
int z = x + y; // 정상 실행
```

##### 문자열 형변환
```
String s1 = "1";
// int i1 = (int)s1; <-- 에러 발생
```

- **문자열 -> 기본자료형:** `클래스명.parse자료형(값)`
- Interger.parseInt(str);
- Short.parseShort();
- Long.parseLong();
- Byte.parseByte();
- Boolean.parseBoolean();
- Double.parseDouble();
- Float.parseFloat();

- **기본자료형 -> 문자열:** `String.valueOf(값)`

##### 변수 사용 범위: 중괄호 '{ }'


##### 문자열 포맷팅 printf()
- `%s`: String, 다른 타입도 들어갈 수 있음.
- `%d`: Decimal
- `%f`: Float

##### ==데이터 입력 받기==
```
import java.util.*; // java util 라이브러리 활용

Scanner scanner = new Scanner(System.in);
// 타입    변수           값(객체)
// Scanner를 활용하는 방법은 다소 느리다.
String inputData1 = scanner.nextLine();
int inputData2 = scanner.nextInt();
String inputData3 = scanner.next();
```


#### 연산자
##### 증감연산
- 전위 연산: 현재 실행문이 실행되기 전
```
int a = 1; 
System.out.println(--a); // 0 출력
System.out.println(++a); // 1 출력
```

- 후위 연산: 현재 실행문이 실행된 후 
```
int b = 1; 
System.out.println(b--); // 1 출력
System.out.println(b++); // 0 출력
System.out.println(b); // 1 출력
```

##### 나눗셈 연산
```
int score = 80; // %
System.out.println(score/100.0);
System.out.println(score/0.0); // -> Infinity
System.out.println(score%0.0); // -> NaN(Not a Number)
```

##### 문자열 비교 연산
: 문자열과 같은 참조 자료형에서 비교 연산(`==`) : 주소 값이 동일한지 확인함
```
boolean result1 = str1.equals(str2) // 같은 문자열인지
boolean result2 = !str1.equals(str2) // 다른 문자열인지
```

```
String data = null;
System.out.println(data.equals("q")) // 첫 번째
System.out.println("q".equals(data))  // 두 번째
// 두 번째 방법으로 작성하기 권장.
// data가 null값일 수도 있기 때문 -> nullPointerException 발생
```


##### 논리 연산자
- AND `&&`
```
if ((논리1)&&(논리2)){
	(실행문)
}
// 논리1이 false이면 논리2 확인x, 논리2가 예외 발생 코드여도 실행x
// 논리1이 true이고 논리2가 exception 발생하면 exception 발생
```

* shortcut
```
if (id != null && id.equals("admin")){
	(실행문)
} 
// -> 확인해야할 예외 상황을 앞에 배치해서 필요없는 비교 상황 및 논리 확인 최소화 
// or 연산에서 앞에 논리가 true이면 뒤에 확인x
```

##### 삼항 연산자
- `조건 ? 참 일때 실행문(혹은 변수) : 거짓일때 실행문(혹은 변수)`
* html에서 줄 바꿈 없이  조건문을 활용하기 위해서 삼항 연산자를 사용할 수 있음
```
// score가 60이상이면 합격, 미만이면 불합격
int score = 60;
System.out.println(score >= 60 ? "합격" : "불합격"); 
```

- 중첩해서 쓰기
```
// score가 60이상이면 합격, 30이상 60미만이면 재시험, 30미만 유급
System.out.println(score >= 60 ? "합격" : score >= 30 && score < 60 ? "재시험" : "유급");
```

##### 연산자 우선순위
- 소괄호`()`가 우선순위 제일 높다.
- 대입 연산`=`이 우선순위 제일 낮다.


##### 랜덤 난수 생성
- `Math.random()`: 0이상 1미만의 실수 난수
- 활용
	- `(int)(Math.random() * 6) + 1` : 1~6까지의 정수












