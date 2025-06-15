package com.github.fedverdev.database

import com.github.fedverdev.config.DbConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

class DatabaseFactory {
    fun init(dbConfig: DbConfig) {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://${dbConfig.host}:${dbConfig.port}/${dbConfig.name}"
            driverClassName = "org.postgresql.Driver"
            username = dbConfig.username
            password = dbConfig.password
            maximumPoolSize = 20
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }

        val dataSource = HikariDataSource(config)
        Flyway.configure()
            .validateMigrationNaming(true)
            .dataSource(dataSource).load().migrate()


        Database.connect(dataSource)
    }
}