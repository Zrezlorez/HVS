package algiri.hvs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static org.bukkit.Bukkit.getServer;

public class EventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(e.getEntity() != GameData.speedrunner) return;
        if(!GameData.isStart) return;

        GameData.isStart = false;
        for (Player p: getServer().getOnlinePlayers()) {
            p.setGameMode(GameMode.CREATIVE);
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "Хантеры победили!");

    }
    @EventHandler
    public void onDragonDeath(EntityDeathEvent e){
        if(!(e.getEntity() instanceof EnderDragon)) return;
        if(!GameData.isStart) return;

        GameData.isStart = false;
        for (Player p: getServer().getOnlinePlayers()) {
            p.setGameMode(GameMode.CREATIVE);
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "(Спид)раннер победил!");


    }

    @EventHandler
    public void onSpeedrunnerMove(PlayerMoveEvent e){
        if(e.getPlayer() != GameData.speedrunner) return;
        for (Player p: getServer().getOnlinePlayers()) {
            p.setCompassTarget(e.getPlayer().getLocation());
        }
    }
}
