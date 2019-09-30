package me.playbosswar.com;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIHandler implements Listener {
	public static void listCommandsGUI(Player p) { //GUI that shows all the loaded commands
		FileConfiguration c = CommandTimer.getPlugin().getConfig();
		int commandSize = c.getConfigurationSection("tasks").getKeys(false).size();
		int invSize = (int) Math.ceil(commandSize / 9d) * 9;
		if(commandSize > 0) {
			Inventory allCommands = Bukkit.getServer().createInventory(p, invSize, "CommandTimer");
			for (final String path : CommandTimer.getPlugin().getConfig().getConfigurationSection("tasks").getKeys(false)) {
				ItemStack genStack = new ItemStack(Material.HARD_CLAY);
		        ItemMeta genStackMeta = genStack.getItemMeta();
		        ArrayList<String> lore= new ArrayList<>();

				lore.add(ChatColor.GOLD + "Commands : " + c.getStringList("tasks." + path + ".commands"));
				lore.add(ChatColor.GOLD + "On hour : " + c.getBoolean("tasks." + path + ".onhour"));
				lore.add(ChatColor.GOLD + "Time : " + c.getStringList("tasks." + path + ".time"));
				lore.add(ChatColor.GOLD + "On load : " + c.getBoolean("tasks." + path + ".onload"));
				lore.add(ChatColor.GOLD + "On day : " + c.getBoolean("tasks." + path + ".onday"));
				lore.add(ChatColor.GOLD + "Days : " + c.getStringList("tasks." + path + ".days"));
				lore.add(ChatColor.GOLD + "Seconds : " + c.getInt("tasks." + path + ".seconds"));
				lore.add(ChatColor.GOLD + "UseRandom : " + c.getBoolean("tasks." + path + ".useRandom"));
				lore.add(ChatColor.GOLD + "Random : " + c.getDouble("tasks." + path + ".random"));
				lore.add(ChatColor.GOLD + "Gender : " + c.getString("tasks." + path + ".gender"));
				lore.add(ChatColor.GOLD + "Bungee : " + c.getBoolean("tasks." + path + ".bungee"));
				lore.add(ChatColor.GOLD + "Permission : " + c.getString("tasks." + path + ".permissoin"));
		        genStackMeta.setLore(lore);
		        genStackMeta.setDisplayName(path + "");
		        genStack.setItemMeta(genStackMeta);
		        allCommands.setItem(Integer.parseInt(path)-1, genStack); //Set item in GUI
			}
			p.openInventory(allCommands);
		} else {
			p.sendMessage(ChatColor.RED + "You don't have any loaded commands");
		}
	}
}