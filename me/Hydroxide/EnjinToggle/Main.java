package me.Hydroxide.EnjinToggle;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Permission perms = null;
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	public void onEnable() {
		getConfig().addDefault("adminPermission", "some.permission.here");
		getConfig().addDefault("toggleOnMessage", "&8[&aEnjinToggle&8] &aAdmin Mode has been toggled on.");
		getConfig().addDefault("toggleOffMessage", "&8[&aEnjinToggle&8] &cAdmin Mode has been toggled off.");
		getConfig().addDefault("alreadyToggledMessage", "&8[&aEnjinToggle&8] &4Admin Mode is already on!");
		getConfig().addDefault("notToggledMessage", "&8[&aEnjinToggle&8] &4Admin Mode is already off!");
		saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		setupPermissions();
		this.getCommand("admin").setExecutor(new CommandHandler(this));
	}
	
}
