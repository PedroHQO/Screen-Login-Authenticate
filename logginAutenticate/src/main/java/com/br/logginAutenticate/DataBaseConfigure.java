package com.br.logginAutenticate;

import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration//essa classe e uma classe de config
public class DataBaseConfigure {
	
	
	@Bean//essas classes sao componentes
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/logginautenticatea?useTimezone=true&serverTimerzone=UTC");
		dataSource.setUsername("pedrohqo");
		dataSource.setPassword("P2c77566@");
		return dataSource;
		
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdpater() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);//vai fazer a conexao efetiva com o DB que escolhi
		adapter.setShowSql(true);//vai exibir os sqls quando fazer a execucao
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
		adapter.setPrepareConnection(true);//conexao com banco de dados
		return adapter;
	}
	
	

}
