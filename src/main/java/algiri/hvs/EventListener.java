package algiri.hvs;

import org.bukkit.*;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.*;
import org.bukkit.World.Environment;

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
    public void OnCompassInteract(PlayerInteractEvent e){
        if (e.getItem() == null) return;
        if (e.getItem().getType() != Material.COMPASS) return;

        Location loc = e.getPlayer().getLocation();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(loc.getWorld().getEnvironment() != World.Environment.NORMAL)
                e.getPlayer().sendMessage("x: " + Math.round(loc.getX()) + ", z: " + Math.round(loc.getZ()));
        }
    }

    @EventHandler
    public void onSpeedrunnerMove(PlayerMoveEvent e){
        if(e.getPlayer() != GameData.speedrunner) return;
        if(GameData.speedrunner.getLocation().getWorld().getEnvironment() == World.Environment.NORMAL){
            for (Player p: getServer().getOnlinePlayers())
                p.setCompassTarget(GameData.speedrunner.getLocation());
        }
    }
}
