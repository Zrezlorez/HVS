package algiri.hvs.command;

import algiri.hvs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public abstract class AbsctractCommand implements CommandExecutor {

    public AbsctractCommand(String command){
        PluginCommand pluginCommand = Main.getInstance().getCommand(command);
        pluginCommand.setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String label, String[] args);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        execute(sender, label, args);
        return true;
    }
}
