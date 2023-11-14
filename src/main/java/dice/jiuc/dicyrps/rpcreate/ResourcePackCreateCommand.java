package dice.jiuc.dicyrps.rpcreate;

import dice.jiuc.dicyrps.Main;
import org.apache.commons.validator.routines.UrlValidator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ResourcePackCreateCommand implements CommandExecutor {
    private static final UrlValidator urlValidator = new UrlValidator();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 2){
            sender.sendMessage(ChatColor.RED + "Используйте /rpcreate <Название> <URL>");
            return false;
        }
        if(Main.dataBase.getConfig().contains(args[0])){
            sender.sendMessage(ChatColor.RED + "Название занято");
            return false;
        }
        if(!urlValidator.isValid(args[1])){
            sender.sendMessage(ChatColor.RED + "Ссылка некорректна");
            return false;
        }
        Main.dataBase.write(args[0], args[1]);
        sender.sendMessage(ChatColor.GREEN + "Ресурспак создан");
        return false;
    }
}
