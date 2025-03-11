package com.linebeck.kotlincore.internal

import com.linebeck.kotlincore.commands.KotlinCommand
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    @Suppress("DEPRECATION")
    override fun onEnable() {
        logger.info(ChatColor.GREEN.toString() + "##############################")
        logger.info(ChatColor.GREEN.toString() + description.name)
        logger.info(ChatColor.GREEN.toString() + "Version: " + description.version)
        logger.info(ChatColor.GREEN.toString() + "Author: " + description.authors)
        logger.info(ChatColor.GREEN.toString() + "##############################")

        getCommand("kotlin")?.setExecutor(KotlinCommand())
    }

    override fun onDisable() {}
}