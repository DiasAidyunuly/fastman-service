package kz.magnum.magnumback.fastmanservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "kz.magnum.magnumback.fastmanservice.repository.gold",
    entityManagerFactoryRef = "secondEntityManagerFactory",
    transactionManagerRef = "secondTransactionManager"
)
public class GoldDataSourceConfiguration {
    @Value("${spring.datasource.secondary.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.secondary.username}")
    private String username;

    @Value("${spring.datasource.secondary.password}")
    private String password;

    @Value("${spring.datasource.secondary.driver-class-name}")
    private String driver;

    @Bean(name = "secondDataSource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create()
            .url(jdbcUrl)
            .username(username)
            .password(password)
            .driverClassName(driver)
            .build();
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(secondDataSource())
            .packages("kz/magnum/magnumback/fastmanservice/entity/gold")
            .persistenceUnit("second")
            .build();
    }

    @Bean(name = "secondTransactionManager")
    public JpaTransactionManager secondTransactionManager(
        @Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
    }
}
