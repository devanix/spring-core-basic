# 세션4. 스프링 컨테이너와 스프링 빈
*****


## 스프링 컨테이너 생성
*****
스프링 컨테이너가 생성되는 과정을 알아보자.

```java
//스프링 컨테이너 생성
ApplicationContext applicationContext = 
        new AnnotationConfigApplicationContext(AppConfig.class);
```

* ApplicationContext 를 스프링 컨테이너라 한다.
* ApplicationContext 는 인터페이스이다.
* 스프링 컨테이너는 XML을 기반으로 만들 수 있고, <br>
  애노테이션 기반의 자바 설정 클래스로 만들 수 있다. <br> 
  직전에 AppConfig 를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것이다.
* 자바 설정 클래스를 기반으로 스프링 컨테이너( ApplicationContext )를 만들어보자.
  * new AnnotationConfigApplicationContext(AppConfig.class); 
  * 이 클래스는 ApplicationContext 인터페이스의 구현체이다.

### 스프링 컨테이너의 생성 과정
1. 스프링 컨테이서 생성
<img width="849" alt="image" src="https://user-images.githubusercontent.com/1131775/202915420-2ced9195-4828-4531-b80e-d2ef67cc3eed.png">

2. 스프링 빈 등록
<img width="822" alt="image" src="https://user-images.githubusercontent.com/1131775/202915486-d02f9725-87c2-4a30-be47-ca7f8da27ab6.png">
스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다.

**빈 이름**<br>
* 빈 이름은 메서드 이름을 사용한다.
* 빈 이름을 직접 부여할 수 도 있다.
  * @Bean(name="memberService2")

> **[주의] 빈 이름은 항상 다른 이름을 부여해야 한다.**<br>
> 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.

3. 스프링 빈 의존관계 설정 - 준비
   <img width="817" alt="image" src="https://user-images.githubusercontent.com/1131775/202915631-1c5d036a-6501-4bae-9500-04e7db7f8584.png">

4. 스프링 빈 의존관계 설정 - 완료
   <img width="852" alt="image" src="https://user-images.githubusercontent.com/1131775/202915654-d1e5cf0c-f08e-4590-b107-90878f4d9168.png">

> **[참고]**<br>
> 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다.<br>
> 그런데 이렇게 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다.<br>
> 여기서는 이해를 돕기 위해 개념적으로 나누어 설명했다. <br>
> 자세한 내용은 의존관계 자동 주입에서 다시 설명하겠다.


**정리**<br>
스프링 컨테이너를 생성하고, 설정(구성) 정보를 참고해서 스프링 빈도 등록하고, 의존관계도 설정했다. <br>
이제 스프링 컨테이너에서 데이터를 조회해보자.
