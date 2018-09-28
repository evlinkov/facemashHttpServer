package peak.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JooqConfig {

    private final DataSource dataSource;

    @Autowired
    public JooqConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSourceConnectionProvider dataSourceConnectionProvider;
    private TransactionAwareDataSourceProxy transactionAwareDataSourceProxy;
    private LazyConnectionDataSourceProxy lazyConnectionDataSource;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(lazyConnectionDataSource());
    }

    @Bean
    @DependsOn("flyway")
    public DSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

    private DefaultConfiguration configuration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider());
        jooqConfiguration.set(SQLDialect.MYSQL);

        return jooqConfiguration;
    }

    private DataSourceConnectionProvider connectionProvider() {
        if (dataSourceConnectionProvider == null) {
            dataSourceConnectionProvider = new DataSourceConnectionProvider(transactionalDataSource());
        }
        return dataSourceConnectionProvider;
    }

    private TransactionAwareDataSourceProxy transactionalDataSource() {
        if (transactionAwareDataSourceProxy == null) {
            transactionAwareDataSourceProxy = new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
        }
        return transactionAwareDataSourceProxy;
    }

    private LazyConnectionDataSourceProxy lazyConnectionDataSource() {
        if (lazyConnectionDataSource == null) {
            lazyConnectionDataSource = new LazyConnectionDataSourceProxy(dataSource);
        }

        return lazyConnectionDataSource;
    }

}
