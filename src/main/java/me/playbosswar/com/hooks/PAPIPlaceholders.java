package me.playbosswar.com.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.playbosswar.com.Tools;
import me.playbosswar.com.utils.CommandTimer;
import me.playbosswar.com.utils.Messages;
import me.playbosswar.com.utils.TimerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PAPIPlaceholders extends PlaceholderExpansion {
    private Plugin plugin;
    public PAPIPlaceholders(Plugin p){
        plugin = p;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "commandtimer";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        String[] identifierParts = identifier.split("_");

        if(identifierParts.length != 2) {
            return null;
        }

        String commandName = identifierParts[0];
        String commandField = identifierParts[1];

        CommandTimer timer = TimerManager.getCommandTimer(commandName);

        if(timer == null) {
            Messages.sendConsole("Tried to use PAPI placeholder for unknown command: %commandtimer_" + identifier);
            return null;
        }

        if(commandField.equalsIgnoreCase("seconds")) {
            return timer.getSeconds() + "";
        }

        if(commandField.equalsIgnoreCase("secondsFormat")) {
            return Tools.getTimeString(timer.getSeconds());
        }

        if(commandField.equalsIgnoreCase("nextExecution")) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastExecution = timer.getLastExecuted();
            long difference = lastExecution.until(now, ChronoUnit.SECONDS);
            return timer.getSeconds() - difference + "";
        }

        if(commandField.equalsIgnoreCase("nextExecutionFormat")) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastExecution = timer.getLastExecuted();
            long difference = lastExecution.until(now, ChronoUnit.SECONDS);
            long timeLeft = timer.getSeconds() - difference;

            return Tools.getTimeString((int) timeLeft);
        }

        return null;
    }
}