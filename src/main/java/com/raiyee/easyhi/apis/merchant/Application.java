package com.raiyee.easyhi.apis.merchant;

import com.github.bingoohuang.springrest.boot.RestConfiguration;
import com.github.bingoohuang.springrestclient.spring.SpringRestClientEnabledScan;
import org.n3r.eql.eqler.spring.EqlerScan;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestConfiguration.class)
@SpringRestClientEnabledScan
@EqlerScan(basePackageClasses = Application.class)
public class Application {
    static ConfigurableApplicationContext context;

    public static void startup() {
        context = SpringApplication.run(Application.class);
    }

    public static void shutdown() {
        context.close();
    }

    public static void main(String[] args) {
        startup();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator() {
            @Override
            protected boolean shouldSkip(Class<?> beanClass, String beanName) {
                if (!beanClass.getName().startsWith("com.raiyee")) return true;

                return beanClass.getName().endsWith("Impl"); // ASM自动生成的实现类不需要代理
            }
        };
        creator.setProxyTargetClass(true);
        return creator;
    }
}


