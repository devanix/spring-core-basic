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


## 회원 도메인 설계
* 회원 도메인 요구사항
  * 회원을 가입하고 조회할 수 있다.
  * 회원은 일반과 VIP 두 가지 등급이 있다.
  * 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)

**[회원 도메인 협력 관계]** <br>
![image](https://user-images.githubusercontent.com/1131775/202879403-00270739-262b-44f2-83d8-4a592dc0cb05.png)

**[회원 클래스 다이어그램]** <br>
![image](https://user-images.githubusercontent.com/1131775/202879434-73574919-9189-49b5-876b-1056984b6d04.png)

**[회원 객체 다이어그램]** <br>
![image](https://user-images.githubusercontent.com/1131775/202879443-e026375f-32fb-4e82-a1ef-dd9cf7667ce6.png)

<br>
회원 서비스: MemberServiceImpl


## 회원 도메인 개발
소스코드 참조

**[참조] Service와 Repository의 이름 구분** <br>
![image](https://user-images.githubusercontent.com/1131775/202879592-12a14cf2-b0e5-49f6-9d55-8619bb22a094.png)

**[관례] ~impl** <br>
MemberServiceImpl과 같이 구현체가 하나만 존재할 경우<br>
*impl과 같이 접두어(suffix)를 사용해서 만든다.


## 회원 도메인 실행과 테스트

### 회원 도메인 - 회원 가입 테스트
```java
class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("회원 가입 및 회원 조회하여 비교 테스트")
    void join() {
        // given
        Member member = new Member(1L, "Luka", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
```


**[참조] JUnit5 - LifCyle**<br>
![image](https://user-images.githubusercontent.com/1131775/202880200-02aa8c95-c678-41eb-b059-fbe1891bb1a8.png)

#### 회원 도메인 설계의 문제점
* MemberServiceTest 문제점
* 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요?
* DIP를 잘 지키고 있을까요?
* 구현까지 모두 의존하는 문제가 있음
  * MemberService memberService = new MemberServiceImpl();


## 주문과 할인 도메인 설계
* 주문과 할인 정책
  * 회원은 상품을 주문할 수 있다.
  * 회원 등급에 따라 할인 정책을 적용할 수 있다.
  * 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
  * 할인 정책은 변경 가능성이 높다. <br>회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)
  