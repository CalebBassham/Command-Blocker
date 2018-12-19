package me.calebbassham.commandblocker.listener;

import me.calebbassham.commandblocker.CommandBlocker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private CommandBlocker plugin;

    public CommandListener(CommandBlocker plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (!plugin.isBlocked(e.getMessage())) return;
        if (plugin.playerHasPermissionToBypass(e.getPlayer())) return;

        e.setCancelled(true);
        e.getPlayer().sendMessage(plugin.getBlockedMessage());
    }

    public static CommandListener register(CommandBlocker plugin) {
        var instance = new CommandListener(plugin);
        Bukkit.getPluginManager().registerEvents(instance, plugin);
        return instance;
    }

    public CommandListener register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return this;
    }

}
