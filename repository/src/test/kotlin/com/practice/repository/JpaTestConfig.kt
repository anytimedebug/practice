package com.practice.repository

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager

@TestConfiguration
@EnableJpaRepositories(basePackages = ["com.practice.repository"])
class JpaTestConfig {

    @Bean
    fun dataSource() = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build()

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setDatabase(Database.H2)
        vendorAdapter.setGenerateDdl(true)
        vendorAdapter.setShowSql(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.dataSource = dataSource()
        factory.setPackagesToScan(javaClass.`package`.name)

        return factory
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        val txManager = JpaTransactionManager()
        txManager.entityManagerFactory = entityManagerFactory().`object`
        return txManager
    }

}