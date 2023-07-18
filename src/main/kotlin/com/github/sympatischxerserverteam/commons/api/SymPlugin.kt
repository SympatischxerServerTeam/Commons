@file:Suppress("removal")

package com.github.sympatischxerserverteam.commons.api

import com.github.sympatischxerserverteam.commons.config.DatabaseConnection
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import org.hibernate.cfg.Configuration
import org.hibernate.dialect.MariaDBDialect
import org.mariadb.jdbc.Driver
import java.io.File

open class SymPlugin : JavaPlugin, MessageReceiver {

    lateinit var prefix: Component
        private set
    override lateinit var msgColor: TextColor
    override lateinit var warnColor: TextColor
    override lateinit var errorColor: TextColor
    lateinit var dbConnection: DatabaseConnection

    constructor() : super()
    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File) : super(
        loader,
        description,
        dataFolder,
        file
    )

    fun setup(prefix: Component, msgColor: TextColor, warnColor: TextColor, errorColor: TextColor) {
        this.prefix = prefix
        this.msgColor = msgColor
        this.warnColor = warnColor
        this.errorColor = errorColor

        ConfigurationSerialization.registerClass(DatabaseConnection::class.java, "database_connection")

        this.dbConnection = this.config.get(
            "database",
            DatabaseConnection(
                "jdbc:mariadb://localhost:3306/testing",
                "testing",
                "password",
                Driver::class.java,
                MariaDBDialect::class.java
            )
        ) as DatabaseConnection
        this.config.addDefault("database", this.dbConnection)
        this.config.options().copyDefaults()
        this.saveConfig()

        this.dbConnection.connect(this).close()
    }

    override fun onEnable() {
        this.sendMessage("Hello World!")
    }

    override fun onDisable() {
        this.sendMessage("Goodbye!")
    }

    override fun sendMessage(comp: Component) = Bukkit.getConsoleSender().sendMessage(this.prefix.append(comp))
}
