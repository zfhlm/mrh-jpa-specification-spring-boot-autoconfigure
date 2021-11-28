package org.lushen.mrh.boot.data.jpa.specification;

import java.util.function.Function;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * jpa example 自动配置
 * 
 * @author helm
 */
@Configuration(proxyBeanMethods=false)
@EnableJpaRepositories(repositoryFactoryBeanClass=JpaExampleRepositoryFactoryBean.class)
public class JpaExampleAutoConfiguration {

	/**
	 * 配置命名策略，全部名称转换为小写
	 */
	@Bean
	public PhysicalNamingStrategy lowCasePhysicalNamingStrategy() {
		Function<Identifier, Identifier> func = (name) -> {
			return (name == null? null : Identifier.toIdentifier(name.getText().toLowerCase()));
		};
		return new PhysicalNamingStrategy() {
			@Override
			public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
				return func.apply(name);
			}
			@Override
			public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
				return func.apply(name);
			}
			@Override
			public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
				return func.apply(name);
			}
			@Override
			public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
				return func.apply(name);
			}
			@Override
			public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
				return func.apply(name);
			}
		};
	}

}
