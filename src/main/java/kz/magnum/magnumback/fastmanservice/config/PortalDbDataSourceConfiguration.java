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
    basePackages = "kz.magnum.magnumback.fastmanservice.repository.portal",
    entityManagerFactoryRef = "tertiaryEntityManagerFactory",
    transactionManagerRef = "tertiaryTransactionManager"
)
public class PortalDbDataSourceConfiguration {
    @Value("${spring.datasource.tertiary.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.tertiary.username}")
    private String username;
    @Value("${spring.datasource.tertiary.password}")
    private String password;
    @Value("${spring.datasource.tertiary.driver-class-name}")
    private String driver;

    @Bean(name = "tertiaryDataSource")
    public DataSource tertiaryDataSource() {
        return DataSourceBuilder.create()
            .url(jdbcUrl)
            .username(username)
            .password(password)
            .driverClassName(driver)
            .build();
    }

    @Bean(name = "tertiaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tertiaryEntityManagerFactory(
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(tertiaryDataSource())
            .packages("kz/magnum/magnumback/fastmanservice/entity/portal")
            .persistenceUnit("tertiaryDataSource")
            .build();
    }

    @Bean(name = "tertiaryTransactionManager")
    public JpaTransactionManager tertiaryTransactionManager(
        @Qualifier("tertiaryEntityManagerFactory") EntityManagerFactory tertiaryEntityManagerFactory) {
        return new JpaTransactionManager(tertiaryEntityManagerFactory);
    }
}
