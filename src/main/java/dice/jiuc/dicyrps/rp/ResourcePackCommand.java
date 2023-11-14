package dice.jiuc.dicyrps.rp;

import dice.jiuc.dicyrps.DataBase;
import dice.jiuc.dicyrps.Main;
import net.kyori.adventure.text.Component;
import org.apache.commons.validator.routines.UrlValidator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResourcePackCommand implements CommandExecutor{
    private static final UrlValidator urlValidator = new UrlValidator();
    private static final DataBase dataBase = Main.dataBase;
    private static final FileConfiguration config = Main.instance.getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length != 1) return false;

        switch(args[0]){
            case "create":{
                createResourcePack(player, args);
                break;
            }
            case "set":{
                setResourcePack(player, args[1]);
                break;
            }
            case "edit":{
                editResourcePack(player, args);
                break;
            }
            case "delete":{
                deleteResourcePack(player, args);
                break;
            }
            case "link":{
                linkResourcePack(player, args);
                break;
            }
        }
        return true;
    }

    private void linkResourcePack(Player player, String[] args) {
        if(!Main.dataBase.getConfig().contains(args[1])){
            player.sendMessage(Component.text(config.getString("messages.rp-not-found")));
            return;
        }
        player.sendMessage(Component.text(config.getString("messages.rp-link")));
    }

    private void deleteResourcePack(Player player, String[] args){

    }

    private void editResourcePack(Player player, String[] args){

    }

    private void createResourcePack(Player player, String[] args){
        if(Main.dataBase.getConfig().contains(args[0])){
            player.sendMessage(ChatColor.RED + "Название занято");
            return;
        }
        if(!urlValidator.isValid(args[1])){
            player.sendMessage(ChatColor.RED + "Ссылка некорректна");
            return;
        }
        Main.dataBase.write(args[0], args[1]);
        player.sendMessage(ChatColor.GREEN + "Ресурспак создан");
    }

    public static void setResourcePack(Player player, String name){
        if(!Main.dataBase.getConfig().contains(name)){
            player.sendMessage(Component.text(config.getString("messages.rp-not-found")));
            return;
        }

        try {
            player.setResourcePack(dataBase.getConfig().getString("resourcepacks."+name));
        }
        catch(IllegalArgumentException exception){
            player.sendMessage(ChatColor.RED + "Ресурспак некоректен");
            return;
        }
        player.sendMessage(Component.text(config.getString("messages.rp-installed")));
    }
}
