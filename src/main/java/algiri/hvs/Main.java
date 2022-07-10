package algiri.hvs;

import algiri.hvs.command.Hvs;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;


public final class Main extends JavaPlugin {

    private static Main instance;
    @Override
    public void onEnable() {

        instance = this;

        new Hvs();

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }



    public static Main getInstance() {
        return instance; }
}
