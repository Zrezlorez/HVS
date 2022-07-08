package algiri.hvs.command;

import algiri.hvs.GameData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.Console;

import static algiri.hvs.Main.log;

public class Hvs extends AbsctractCommand {
    public Hvs(){
        super("hvs");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Color.RED + "ERROR: console moment lol");
            return;
        }
        if(args.length!=1) return;
        if(args[0].equals("start")){
            GameData.isStart = true;
            log.info(Color.GREEN + "Игра началась!");
            return;
        }
        if(Bukkit.getPlayer(args[0])!= null) {
            GameData.speedrunner = Bukkit.getPlayer(args[0]);
            log.info(Color.GREEN + "Новый (спид)раннер - " + args[0]);
        }
        else sender.sendMessage(Color.RED + "Игрок не найден");
    }
}
