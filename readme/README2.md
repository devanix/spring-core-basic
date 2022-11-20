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

