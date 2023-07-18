package com.github.sympatischxerserverteam.commons.config

import com.github.sympatischxerserverteam.commons.user.User
import jakarta.persistence.Entity
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.plugin.Plugin
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernate.dialect.Dialect
import org.jboss.jandex.AnnotationTarget
import org.jboss.jandex.DotName
import org.jboss.jandex.IndexReader
import java.sql.Driver

class DatabaseConnection(
    var url: String,
    var user: String,
    var password: String,
    var driver: Class<out Driver>,
    var dialect: Class<out Dialect>,
    var showSql: Boolean = false,
    var poolSize: Int = 10
) : ConfigurationSerializable {

    private lateinit var sessionFactory: SessionFactory


    @Suppress("UNCHECKED_CAST")
    constructor(serialized: Map<String, Any?>) : this(
        serialized["url"] as String,
        serialized["user"] as String,
        serialized["password"] as String,
        serialized["driver"] as Class<out Driver>,
        serialized["dialect"] as Class<out Dialect>,
        serialized["showSql"] as Boolean,
        serialized["poolSize"] as Int
    )

    fun buildHibernateConfig(plugin: Plugin): Configuration {
        val cfg = Configuration()
            .configure()
            .setProperty("connection.driver_class", this.driver.name)
            .setProperty("hibernate.connection.url", this.url)
            .setProperty("hibernate.connection.username", this.user)
            .setProperty("hibernate.connection.password", this.password)
            .setProperty("dialect", this.dialect.name)
            .setProperty("hibernate.hbm2ddl.auto", "update")
            .setProperty("show_sql", this.showSql.toString())
            .setProperty("hibernate.connection.pool_size", this.poolSize.toString())

//        val indexReader = IndexReader(plugin.getResource("META-INF/jandex.idx"))
//        val index = indexReader.read()
//        val annotated = index.getAnnotations(DotName.createSimple(Entity::class.java))
//        annotated.filter { it.target().kind() == AnnotationTarget.Kind.CLASS }
//            .onEach { println(it.target().toString()) }
//            .forEach { cfg.addAnnotatedClass(Class.forName(it.target().asClass().name().toString())) }

//        cfg.addAnnotatedClass(User::class.java)
        return cfg
    }

    fun connect(plugin: Plugin): Session {
        if (!this::sessionFactory.isInitialized) this.sessionFactory =
            this.buildHibernateConfig(plugin).buildSessionFactory()
        return this.sessionFactory.openSession()
    }

    override fun serialize() = buildMap {
        this["url"] = url
        this["user"] = user
        this["password"] = password
        this["driver"] = driver.name
        this["dialect"] = dialect.name
        this["showSql"] = showSql
        this["poolSize"] = poolSize
    }
}