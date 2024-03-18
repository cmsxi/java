##### 반복문
- `for`: 반복 횟수가 정해져 있을 때
```
for(초기값; 조건; (증감)연산){
	실행문
}
```
- `while`: 반복 횟수가 정해져 있지 않을 때
```
while(조건){
	실행문
}
```
- 반복문 중지
	- `break` : 반복문 자체 중지(중단)
	- `continue`: 현재 반복 중지

- 반복문에 이름 붙여서 중지시키기
```
dan: for (int i = 2; i <= 9; i++) {
	System.out.printf("****%d단****\n", i);
	for (int j = 1; j <= 9; j++) {
		System.out.printf("%d × %d = %d\n", i, j, i * j);
		if (i==3) break dan; // dan이라는 반복문 중지
	}
}
```


#### 참조 자료형

##### 문자열
 > ==불변 - immutable==, 일부만 수정할 수 없음
```
// length, charAt
String name = "홍길동";
for(int i = 0; i < name.length(); i++){
	System.out.println(name.charAt(i));
}

// replace
String oldStr = "자바 프로그래밍";
String newStr = oldStr.replace("자바", "java");
System.out.println(newStr); // 출력: java 프로그래밍 

// 문자열 += tempStr
String num = "1"; 
num += 1
System.out.println(num); // 출력: 11

// substring
String str = "자바 프로그래밍";
System.out.println(str.substring(3,5)); // 출력: 프로 

// indexOf()
String str = "자바 프로그래밍";
System.out.println(str.indexOf("자바")); // 출력: 0
```
- `.length()` : 문자열 길이
- `.charAt(i)` : i번째 문자 
- `.replace("원래 내용", "바꿀 내용")` : 문자열을 수정하는 것이 아니라, ==새로운 문자열==을 생성해서 대체한 것처럼 보이는 것
-  `문자열 += tempStr` : 'tempStr'을 단순히 더하는 게 아니라 새로운 객체를 생성함
- `.substring(시작 인덱스)`: 시작 인덱스부터 끝까지 잘라냄
- `.substring(시작 인덱스, 마지막 인덱스)`: 시작 인덱스 이상 마지막 인덱스 미만 구간까지 잘라냄
- `.indexOf("찾을 내용")`: 찾을 내용 인덱스 시작 지점 알려줌. 여러 개 존재한다면 첫 번째 지점 알려줌.
+ `.contains("찾을 내용")` : 출력 boolean, 포함되어있는지.
+ `.split("구분 기준")`: 구분 기준을 바탕으로 문자열 나눠서 배열로 반환

```
// 파일명 저장 시간으로 바꾸기
package ch05;

import java.util.Scanner;

public class StringEx {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();
		
		int idx = fileName.indexOf(".");
		fileName = System.currentTimeMillis() + fileName.substring(idx);
		System.out.println(fileName);
	}
}

// 예외 생각해보기 : "2024.03.15.xlsx"이 입력이라면?
// -> lastIndexOf(idx)
```


##### 배열
- 같은 타입으로만 구성(int, String, long, ...)
- 초기화 후 추가/삭제 불가능(= 한 번 길이가 정해지면 길이 변경 안됨)
	- 값은 변경 가능하다
- 바로 문자열 형태로 출력 안됨(객체이기 때문에 주소 값이 출력된다)
- 중괄호 표기 (ex. {1, 2, 3, 4})
- 하나의 변수에 여러 값들을 하나로 저장하기 위해서 사용
- 반복문과 함께 사용하는 경우 多
```
String tempStr = "가 나 다 라 마";
String[] strArr = tempStr.split(" "); // -> {"가", "나", "다", "라", "마"}
// 출력하고 싶으면: Arrays.toString(strArr)
```

###### 배열 선언 & 초기화
```
int[] arr = {1, 2, 3, 4, 5}; // int arr[]도 가능함
// arr = {1, 2, 3, 4}  --> error
int[] arr2 = new int[]{1, 2, 3, 4, 5};
int[] arr3 = new int[5]; // -> {0, 0, 0, 0, 0}
```

###### 값 변경
```
arr3[0] = 1; // -> {1, 0, 0, 0, 0} 
```

###### 동일 주소 가리키는 두 배열
```
int[] a2 = {1, 2};
int[] b2 = a2;
b2[0] = 3;
System.out.println(Arrays.toString(a2));
```
> a2와 b2가 같은 주소를 가리키고 있었는데,  b2에서 새 배열 값을 지정해주며 a2의 내용도 바뀜.
>> 같은 곳을 가리키고 있었기 때문

###### 다차원 배열(2차원 이상)
```
int[][] arr2d = {
	{1, 2, 3},
	{4, 5, 6},
	{7, 8, 9}
};

// [행][열]
System.out.println(arr2d[1][1]); // 출력: 5
System.out.println(arr2d[0][2]); // 출력: 3

// 배열 안의 배열
arr2d = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; //.. 위랑 똑같음
System.out.println(arr2d.length); // 출력: 3
System.out.println(Arrays.toString(arr2d[1])); // 출력: [4, 5, 6]

// 배열 안의 모든 요소 출력
for(int i = 0; i < arr2d.length; i++){
	for(int j = 0; j < arr2d[i].length; j++){
		System.out.print(arr2d[i][j] + " ");
	}
}
```

###### 배열 복사
```
// 얕은 복사
// 배열이 저장되어있는 주소값을 복사하는 것이므로,  값은 복사되지 않음. 둘 중 하나만 수정하여도 둘다 수정 되는 것처럼 보임(애초에 같은 것이기 때문)
int[] array = {1, 2, 3, 4, 5};
int[] array2 = array;
System.out.println(Arrays.toString(array)); // 출력: [1, 2, 3, 4, 5] 
System.out.println(Arrays.toString(array2)); // 출력: [1, 2, 3, 4, 5] 


// 깊은 복사
int[] array3 = new int[array.length];
for(int i = 0; i < array.length; i++){
	array3[i] = array[i];
}
System.out.println(Arrays.toString(array)); // 출력: [1, 2, 3, 4, 5] 
System.out.println(Arrays.toString(array3)); // 출력: [1, 2, 3, 4, 5] 

// 비교해보기 
System.out.println(array == array2); // 출력: true
System.out.println(array == array3); // 출력: false
array[1] = 6; 
System.out.println(Arrays.toString(array)); // 출력: [1, 6, 3, 4, 5] 
System.out.println(Arrays.toString(array2)); // 출력: [1, 6, 3, 4, 5] 
System.out.println(Arrays.toString(array3)); // 출력: [1, 2, 3, 4, 5] 


// 깊은 복사, 메서드 활용하기
int[] array4 = new int[array.length];
System.arraycopy(array, 0, array4,0, array.length); // 매개변수 참고하면서 하기..
System.out.println(Arrays.toString(array4)); // 출력: [1, 6, 3, 4, 5]


// 깊은 복사, Arrays 활용하기
int[] array5 = Arrays.copyOf(array, array.length);
System.out.println(Arrays.toString(array5)); // 출력: [1, 6, 3, 4, 5]

```

###### 배열 출력
```
int[] array = new int[]{1, 2, 3, 4, 5};
for(int i = 0; i < array.length; i++){
	System.out.println(array[i]);
}

// 배열에서 index를 사용하지 않고 값만 필요한 경우가 있음 -> 요소로 for문 작성 가능 
// foreach...(단축어)
for(int a : array){
	System.out.println(a)
}
```


##### Enum 타입(열거형)
- 상수와 같이 미리 값 지정해두고 활용