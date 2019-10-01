package me.playbosswar.com.SQLite;

import me.playbosswar.com.CommandTimer;

import java.util.logging.Level;

public class Error {
    public static void execute(CommandTimer plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }
    public static void close(CommandTimer plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}