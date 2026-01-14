package com.rjm.sfk.bank_api.core.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages =  "com.rjm.sfk.bank_api")
@EnableTransactionManagement
public class JpaTransactionConfig {

    /**
     * TransactionManager.
     *
     * @param emf a {@link EntityManagerFactory} object.
     * @return a {@link PlatformTransactionManager} object.
     */
    @Bean
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
