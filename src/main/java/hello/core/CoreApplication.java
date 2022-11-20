package hello.core;

import hello.core.config.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class CoreApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 등록된 빈 전부 출력
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(definitionName);
            System.out.println("bean = " + bean);
        }

        // 등록된 빈의 개수 출력
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println("beanDefinitionCount = " + beanDefinitionCount);

    }
}
