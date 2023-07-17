@file:Suppress("removal")

package com.github.sympatischxerserverteam.commons.api

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

open class SymPlugin : JavaPlugin, MessageReceiver {

    lateinit var prefix: Component
        private set
    override lateinit var msgColor: TextColor
    override lateinit var warnColor: TextColor
    override lateinit var errorColor: TextColor

    constructor() : super()
    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File) : super(
        loader,
        description,
        dataFolder,
        file
    )

    companion object {
        lateinit var plugin: SymPlugin
    }

    fun setup(prefix: Component, msgColor: TextColor, warnColor: TextColor, errorColor: TextColor) {
        plugin = this
        this.prefix = prefix
        this.msgColor = msgColor
        this.warnColor = warnColor
        this.errorColor = errorColor
    }

    override fun onEnable() {
        this.sendMessage("Hello World!")
    }

    override fun onDisable() {
        this.sendMessage("Goodbye!")
    }

    override fun sendMessage(comp: Component) = Bukkit.getConsoleSender().sendMessage(this.prefix.append(comp))
}
