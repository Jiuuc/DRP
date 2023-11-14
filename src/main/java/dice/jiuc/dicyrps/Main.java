package dice.jiuc.dicyrps;

import dice.jiuc.dicyrps.rp.ResourcePackCommand;
import dice.jiuc.dicyrps.rp.ResourcePackTabCompleter;
import dice.jiuc.dicyrps.rp.SignResourcePack;
import dice.jiuc.dicyrps.rpcreate.ResourcePackCreateCommand;
import dice.jiuc.dicyrps.rpcreate.ResourcePackCreateTabCompleter;
import dice.jiuc.dicyrps.rplink.ResourcePackLinkCommand;
import dice.jiuc.dicyrps.rplink.ResourcePackLinkTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main instance;
    public static DataBase dataBase;
    @Override
    public void onEnable() {
        instance = this;
        dataBase = new DataBase("database.yml", instance);
        saveDefaultConfig();

        getCommand("rp").setExecutor(new ResourcePackCommand());
        getCommand("rp").setTabCompleter(new ResourcePackTabCompleter());
        getCommand("rpcreate").setExecutor(new ResourcePackCreateCommand());
        getCommand("rpcreate").setTabCompleter(new ResourcePackCreateTabCompleter());
        getCommand("rplink").setExecutor(new ResourcePackLinkCommand());
        getCommand("rplink").setTabCompleter(new ResourcePackLinkTabCompleter());

        Bukkit.getServer().getPluginManager().registerEvents(new SignResourcePack(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
