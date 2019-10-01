package me.playbosswar.com;

import me.playbosswar.com.SQLite.Database;
import me.playbosswar.com.SQLite.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandTimer extends JavaPlugin implements Listener {
	private Database db;
	private static Plugin plugin;

	public void onEnable() {
		plugin = this;
		this.db = new SQLite(this);
		this.db.load();
		registerCommands();
		Tools.registerBungeeChannels();
		Tools.initConfig();
		TaskRunner.startTasks();
		Tools.printDate();
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[CommandTimer] v2.4.2 loaded");
	}
	
	public void onDisable() {
		Tools.closeAllInventories();
		saveDefaultConfig();
		plugin = null;
	}
	
	private void registerCommands() {
		getCommand("commandtimer").setExecutor(new CommandHandler());
	}
		
	
	public static Plugin getPlugin() {
		return plugin;
	}

	public Database getRDatabase() {
		return this.db;
	}
}
