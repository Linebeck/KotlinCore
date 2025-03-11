package com.linebeck.kotlincore.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class KotlinCommand : CommandExecutor {

    private fun kotlinVersionText(version: String): Component = Component.text(
        "Kotlin Version: ",
        NamedTextColor.DARK_PURPLE,
        TextDecoration.BOLD
    ).append(
        Component.text(
            version,
            NamedTextColor.LIGHT_PURPLE,
            TextDecoration.ITALIC
        )
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val kotlinVersion = KotlinVersion.CURRENT
        sender.sendMessage(kotlinVersionText(kotlinVersion.toString()))
        return true
    }
}