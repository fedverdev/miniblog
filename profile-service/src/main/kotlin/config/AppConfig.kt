package com.github.fedverdev.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.yaml.YamlConfig

data class DbConfig(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val name: String,
)

class AppConfig(config: ApplicationConfig) {
    val db: DbConfig
    val objectMapper: ObjectMapper = ObjectMapper()

    init {
        val configMap = (config as YamlConfig).toMap()
        val config: Config = ConfigFactory.parseMap(configMap, "YAML")

        db = DbConfig(
            host = config.getString("database.host"),
            port = config.getInt("database.port"),
            name = config.getString("database.name"),
            password = config.getString("database.password"),
            username = config.getString("database.username"),
        )
    }
}