package com.github.sympatischxerserverteam.commons;

import com.github.sympatischxerserverteam.commons.api.SymPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Main extends SymPlugin {
    public Main() {
        super();
    }
    
    public Main(@NotNull JavaPluginLoader loader, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        this.setup(Component.text("[", NamedTextColor.BLACK)
                .append(Component.text("Sympatischxer", NamedTextColor.YELLOW))
                .append(Component.text("] ",
                        NamedTextColor.BLACK)), NamedTextColor.GREEN, NamedTextColor.YELLOW, NamedTextColor.RED);
        super.onEnable();
    }
}
