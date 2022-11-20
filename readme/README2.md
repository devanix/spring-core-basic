# 세션2. 스프링 핵심 원리 이해1 - 예제만들기
*****

## 프로젝트 생성
### 스프링 부트 스타터 사이트로 이동해서 스프링 프로젝트 생성
https://start.spring.io

* 프로젝트 선택
  * Project: Gradle Project 
  * Spring Boot: 2.3.x 
  * Language: Java 
  * Packaging: Jar
  * Java: 11 
* Project Metadata
  * groupId: hello
  * artifactId: core Dependencies: 선택하지 않는다.

### Gradle 전체 설정
```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.6.13'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'hello'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

동작 확인<br>
기본 메인 클래스 실행( CoreApplication.main() )

### IntelliJ Gradle 대신에 자바 직접 실행
최근 IntelliJ 버전은 Gradle을 통해서 실행 하는 것이 기본 설정이다.<br>
이렇게 하면 실행속도가 느리다. <br>
다음과 같이 변경하면 자바로 바로 실행해서 실행속도가 더 빠르다.<br>


* Preferences Build, Execution, Deployment Build Tools Gradle
    * Build and run using: Gradle IntelliJ IDEA
    * Run tests using: Gradle IntelliJ IDEA
![image](https://user-images.githubusercontent.com/1131775/202828997-77b5d527-86bd-4309-9da4-6566e26f1925.png)


## 비즈니스 요구사항과 설계

* 회원
  * 회원을 가입하고 조회할 수 있다.
  * 회원은 일반과 VIP 두 가지 등급이 있다.
  * 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다.(미확정)
* 주문과 할인 정책
  * 회원은 상품을 주문할 수 있다.
  * 회원 등급에 따라 할인 정책을 적용할 수 있다.
  * 할인 정책은 모든 VIP는 1000원을 할인해주고 고정 금액 할인을 적용해달라(나중에 변경 될 수 있음)
  * 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다(미확정)

요구사항을 보면 회원 데이터, 할인 정책 같은 부분은 지금 결정하기 어려운 부분이다.<br>
그렇다고 이런 정책이 결정될 때 까지 개발을 무기한 기다릴 수 도 없다.<br>
우리는 앞에서 배운 객체 지향 설계 방법이 있지 않은가!<br>
인터페이스를 만들고 구현체를 언제든지 갈아끼울 수 있도록 설계하면 된다. <br>
그럼 시작해보자.


