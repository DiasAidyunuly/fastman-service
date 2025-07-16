package kz.magnum.magnumback.fastmanservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = {"kz.magnum.magnumback.fastmanservice.repository.fastman",
        "kz.magnum.magnumback.fastmanservice.repository.checklist",
        "kz.magnum.magnumback.fastmanservice.repository.file",
        "kz.magnum.magnumback.fastmanservice.repository.structure"},
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager"
)
public class FastmanDataSourceConfiguration {

    @Value("${spring.datasource.primary.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.primary.username}")
    private String username;

    @Value("${spring.datasource.primary.password}")
    private String password;

    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
            .url(jdbcUrl)
            .username(username)
            .password(password)
            .build();
    }

    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(primaryDataSource())
            .packages("kz/magnum/magnumback/fastmanservice/entity/fastman",
                "kz/magnum/magnumback/fastmanservice/entity/general",
                "kz/magnum/magnumback/fastmanservice/entity/checklist",
                "kz/magnum/magnumback/fastmanservice/entity/file",
                "kz/magnum/magnumback/fastmanservice/entity/structure")
            .persistenceUnit("primaryDataSource")
            .build();
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public JpaTransactionManager primaryTransactionManager(
        @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}