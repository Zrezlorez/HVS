package algiri.hvs;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static algiri.hvs.Main.log;

public class EventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(e.getEntity() != GameData.speedrunner) return;

        GameData.isStart = false;
        for (Player p: Bukkit.getWorld("world").getPlayers()) {
            p.setGameMode(GameMode.CREATIVE);
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
        }
        log.info("Хантеры победили!");

    }
    @EventHandler
    public void onDragonDeath(EntityDeathEvent e){
        if(!(e.getEntity() instanceof EnderDragon)) return;

        GameData.isStart = false;
        for (Player p: Bukkit.getWorld("world").getPlayers()) {
            p.setGameMode(GameMode.CREATIVE);
            p.teleport(new Location(Bukkit.getWorld("world"), 0, 100, 0));
        }
        log.info("(Спид)раннер победил!");

    }

    @EventHandler
    public void onSpeedrunnerMove(PlayerMoveEvent e){
        if(e.getPlayer() != GameData.speedrunner) return;
        for (Player p: Bukkit.getWorld("world").getPlayers()) {
            p.setCompassTarget(e.getPlayer().getLocation());
        }
    }
}
