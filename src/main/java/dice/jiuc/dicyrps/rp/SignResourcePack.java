package dice.jiuc.dicyrps.rp;

import dice.jiuc.dicyrps.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignResourcePack implements Listener{
    @EventHandler
    public static void onClick(PlayerInteractEvent e){
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(!(e.getClickedBlock().getState() instanceof Sign)) return;

        Sign block = (Sign) e.getClickedBlock().getState();
        String line = block.getSide(Side.FRONT).getLine(0);
        if(!line.startsWith("/rp ")) return;

        e.getPlayer().swingMainHand();
        Bukkit.getScheduler().runTaskLater(Main.instance, ()-> {
            ResourcePackCommand.setResourcePack(e.getPlayer(), line.replaceAll("/rp ", ""));
        }, 10);
    }
}
