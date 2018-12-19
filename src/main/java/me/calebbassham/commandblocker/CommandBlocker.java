package me.calebbassham.commandblocker;

import me.calebbassham.commandblocker.listener.CommandListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandBlocker extends JavaPlugin {

    private String[] blockedCmds;
    private String blockedMsg;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        CommandListener.register(this);
    }

    public boolean isBlocked(String cmd) {
        for (var loopCmd : blockedCmds) {
            if (cmd.startsWith(loopCmd) || cmd.startsWith("/" + loopCmd)) {
                return true;
            }
        }

        return false;
    }

    public boolean playerHasPermissionToBypass(Player player) {
        return player.hasPermission("commandblocker.bypass");
    }

    public String getBlockedMessage() {
        return this.blockedMsg;
    }

    private void loadConfig() {
        var config = getConfig();
        this.blockedCmds = config.getStringList("blocked commands").toArray(new String[0]);
        this.blockedMsg = ChatColor.translateAlternateColorCodes('&', config.getString("blocked message"));
    }
}
