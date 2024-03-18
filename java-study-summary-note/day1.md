##### jdk 설치

##### 환경변수 설정: PATH 경로 설정을 통해 다른 위치에 있어도 java 프로그램 실행 가능하게 함
* JAVA_HOME 환경변수 생성: hadoop, kafka등 서버위에서 사용하는 프로그램에서 활용하므로 변수 생성 해줌
* ==PATH== 환경변수 추가

##### eclipse설치
* Java VM: 가상머신, 해당 머신에서 java 실행
* \: 윈도우 폴더 구분자
* /: 리눅스(unix, mac) 디렉토리 구분자

##### 프로그래밍 언어
* Compile: 소스(사람이 코딩) -> 기계어 -> 컴퓨터가 실행 | C, C++
* Interpreter: 소스(사람이 코딩) -> 번역 | 파이썬, 느리다. 파이썬 라이브러리는 C로 짜져 있는 경우가 많음
* Java
JIT(Just in type): 자바는 컴파일은 하지만 byte 코드로 나옴 
미리 일부 컴파일해두고 나중에 실행-> 빠르다(요즘은 파이썬, JS도 JIT)
소스 -(complie: eclipse와 같은 IDE에서 해줌)-> 바이트코드 -(JVM)-> 실행
* C는 윈도우, 맥 컴파일 방법 다름. Java는 JVM이 실행하므로 모든 OS에서 실행가능

##### Java
* 옛날에는 JDK(개발), JRE(실행) 따로 설치했어야했음. 최근에는 JDK안에 JRE가 포함되어 있다. 
* JRE(Java Runtime Environment): 자바 실행환경
* source는 src, output은 bin에 저장
* Library: 미리 만들어 놓은 프로그램
* jar: Java ARchive 하나의 라이브러리 단위. 수많은 class들을 모아놓은 것. class = java 파일을 실행하기 위해서 컴파일 한 것

##### 텍스트 파일 인코딩 설정, 다르면 문자 깨질 수 있음
character encoding set
문자 인코딩 집합
character set 
문자 집합 

UTF-8

##### 변수명명 
* 의미있는 이름으로, 자바 명명 캐멀

##### 정수타입
* byte < short < ==int (-21억~21억) < long==
* byte, short는 연산과정에서 중간 int로 변환됨 -> 오히려 부담. 그냥 int이상으로 쓰기

##### 실수타입
* double
* int < long < double ...

##### 자료형
* 기본자료형: primitive type
int long double boolean
* 참조자료형: reference type
