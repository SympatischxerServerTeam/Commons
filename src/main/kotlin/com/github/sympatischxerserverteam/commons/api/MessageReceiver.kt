package com.github.sympatischxerserverteam.commons.api

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor

interface MessageReceiver {

    val msgColor: TextColor
    val warnColor: TextColor
    val errorColor: TextColor

    fun sendMessage(comp: Component)

    fun sendMessage(text: String) = this.sendMessage(Component.text(text, this.msgColor))

    fun sendWarning(text: String) = this.sendMessage(Component.text(text, this.warnColor))

    fun sendError(text: String) = this.sendMessage(Component.text(text, this.errorColor))
}
