package com.github.sympatischxerserverteam.commons.api

import be.seeseemelk.mockbukkit.MockBukkit
import com.github.sympatischxerserverteam.commons.BaseTest
import com.github.sympatischxerserverteam.commons.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SymPluginTest : BaseTest(false) {

    @Test
    fun setup() {
        val p = MockBukkit.load(Main::class.java)
        assertInstanceOf(SymPlugin::class.java, p)

        val oldPrefix = p.prefix
        p.setup(
            Component.text("TestPrefix", NamedTextColor.GOLD),
            NamedTextColor.AQUA,
            NamedTextColor.AQUA,
            NamedTextColor.AQUA
        )
        assertNotNull(p.prefix)
        assertNotEquals(p.prefix, oldPrefix)
        assertEquals(p.msgColor, NamedTextColor.AQUA)
    }

    @Test
    fun onEnable() {
        val p = MockBukkit.load(Main::class.java)
        assertNotNull(p.prefix)
        assertNotNull(p.msgColor)
        assertNotNull(p.warnColor)
        assertNotNull(p.errorColor)
    }

    @Test
    fun sendMessage() {
        // TODO: Add test
    }
}
