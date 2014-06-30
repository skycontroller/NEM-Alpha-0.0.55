package org.nem.deploy;

import com.googlecode.flyway.core.Flyway;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nem.core.serialization.b;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.BlockChain;
import org.nem.nis.Foraging;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.gn;
import org.nem.nis.j.cf;
import org.nem.nis.j.nn;
import org.nem.nis.j.zh;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.nis.t.fj;
import org.nem.nis.t.gm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages={"org.nem.nis"}, excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value={Controller.class})})
@EnableTransactionManagement
public class NisAppConfig {
    @Autowired
    private nn cU;
    @Autowired
    private zh cV;
    @Autowired
    private BlockChainLastBlockLayer cW;
    @Autowired
    private cf cX;

    @Bean
    public DataSource bO() throws IOException {
        Properties properties = new Properties();
        properties.load(NisAppConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        driverManagerDataSource.setUrl(properties.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(properties.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(properties.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    @Bean(initMethod="migrate")
    public Flyway flyway() throws IOException {
        Properties properties = new Properties();
        properties.load(NisAppConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        Flyway flyway = new Flyway();
        flyway.setDataSource(this.bO());
        flyway.setLocations(new String[]{properties.getProperty("flyway.locations")});
        return flyway;
    }

    @Bean
    @DependsOn(value={"flyway"})
    public SessionFactory bP() throws IOException {
        Properties properties = new Properties();
        properties.load(NisAppConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(this.bO());
        localSessionFactoryBuilder.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
        localSessionFactoryBuilder.setProperty("hibernate.show_sql", properties.getProperty("hibernate.show_sql"));
        localSessionFactoryBuilder.setProperty("hibernate.use_sql_comments", properties.getProperty("hibernate.use_sql_comments"));
        localSessionFactoryBuilder.setProperty("hibernate.jdbc.batch_size", properties.getProperty("hibernate.jdbc.batch_size"));
        localSessionFactoryBuilder.addAnnotatedClasses(new Class[]{Account.class});
        localSessionFactoryBuilder.addAnnotatedClasses(new Class[]{Block.class});
        localSessionFactoryBuilder.addAnnotatedClasses(new Class[]{Transfer.class});
        return localSessionFactoryBuilder.buildSessionFactory();
    }

    @Bean
    public BlockChain bQ() {
        return new BlockChain(this.bS(), this.cU, this.cW, this.cV, this.bR());
    }

    @Bean
    public Foraging bR() {
        return new Foraging(this.bS(), this.cV, this.cW, this.cX);
    }

    @Bean
    public AccountAnalyzer bS() {
        return new AccountAnalyzer(new fj());
    }

    @Bean
    public HibernateTransactionManager bU() throws IOException {
        return new HibernateTransactionManager(this.bP());
    }

    @Bean
    public gn bV() {
        return new gn();
    }

    @Bean
    public NisPeerNetworkHost bW() {
        return new NisPeerNetworkHost(this.bS(), this.bQ());
    }
}