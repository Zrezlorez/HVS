package algiri.hvs;

import algiri.hvs.command.Hvs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static final Logger log = Logger.getLogger("Minecraft");
    @Override
    public void onEnable() {

        instance = this;

        new Hvs();

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }



    public static Main getInstance() {
        return instance; }
}
