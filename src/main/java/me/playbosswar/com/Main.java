package me.playbosswar.com;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public class Main extends JavaPlugin implements Listener {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Tools.initConfig();

        if(!ConfigVerification.checkConfigurationFileValidity()) {
            return;
        }

        registerCommands();
        TaskRunner.startTasks();
        Tools.printDate();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            Tools.sendConsole("&eCommandTimer hooked in PlaceholderAPI");
        } else {
            Tools.sendConsole("&eCould not find PlaceholderAPI, placeholders will not work");
        }
        Tools.sendConsole("&e" + getDescription().getVersion() + "&a loaded!");
    }

    @Override
    public void onDisable() {
        for(Timer t : Tools.timerList) {
            t.cancel();
        }

        saveDefaultConfig();
        plugin = null;
    }

    private void registerCommands() {
        getCommand("commandtimer").setExecutor(new Commands());
    }


    public static Plugin getPlugin() {
        return plugin;
    }
}
