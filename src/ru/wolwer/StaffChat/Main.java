package ru.wolwer.StaffChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
  public void onEnable() {
    getLogger().info("StaffChat Enabled!");
    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }

  public void onDisable() {
    getLogger().info("StaffChat Disabled!");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (player.hasPermission(getConfig().getString("staffchat-send-permission"))) {
        if (args.length > 0) {
          String message = "";
          for (int i = 0; i < args.length; i++) {
            String arg = args[i] + " ";
            message = message + arg;
          }
          for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission(getConfig().getString("staffchat-view-permission"))) {
              p.sendMessage(ChatColor.translateAlternateColorCodes('&', (ChatColor.translateAlternateColorCodes('&', getConfig().getString("staffchat-prefix")  + "&c" + player.getDisplayName() + getConfig().getString("staffchat-message-format") + message))));
            }
          }
        }else {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("staffchat-no-arguments")));
        }
      }else {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("staffchat-no-permission-message")));
      }
    } else {
      System.out.println("You must be a player to execute this command!");
    }
    return true;
  }
}
