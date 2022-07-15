package algiri.hvs.command;

import algiri.hvs.GameData;
import algiri.hvs.Main;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;


public class Hvs extends AbsctractCommand {
    public Hvs(){
        super("hvs");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GREEN + "ERROR: console moment lol");
            return;
        }
        if(args[0].equals("start")){
            if(GameData.isStart) {
                Bukkit.broadcastMessage("Игра уже идёт");
                return;
            }
            GameData.isStart = true;
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Main.getInstance(), () -> {
                for (Player p: getServer().getOnlinePlayers()) {
                    if(p!=GameData.speedrunner)
                        p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                }
            }, 6000);
            Bukkit.broadcastMessage(ChatColor.GREEN + "Игра началась!");

            return;
        }
        if(Bukkit.getPlayer(args[0])!= null) {
            GameData.speedrunner = Bukkit.getPlayer(args[0]);
            Bukkit.broadcastMessage(ChatColor.GREEN + "Новый (спид)раннер - " + args[0]);
        }
        else sender.sendMessage(ChatColor.RED + "Unknown command: " + args[0]);
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args){
        if(args.length==1) {
            List<String> tabComp = Lists.newArrayList("start");
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player player : players) tabComp.add(player.getName());
            return tabComp;
        }
        return Lists.newArrayList();
    }
}
