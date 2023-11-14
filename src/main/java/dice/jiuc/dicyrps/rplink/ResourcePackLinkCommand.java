package dice.jiuc.dicyrps.rplink;

import dice.jiuc.dicyrps.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ResourcePackLinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 1){
            sender.sendMessage(ChatColor.RED + "Используйте /rplink <Название>");
            return false;
        }
        if(!Main.dataBase.getConfig().contains(args[0])){
            sender.sendMessage(ChatColor.RED + "Ресурспак не найден");
            return false;
        }
        sender.sendMessage(ChatColor.GREEN + "Ссылка на скачивание: " + Main.dataBase.getConfig().getString(args[0]));
        return false;
    }
}
