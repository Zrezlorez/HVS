package algiri.hvs.command;

import algiri.hvs.Main;
import org.bukkit.command.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbsctractCommand implements CommandExecutor, TabCompleter {

    public AbsctractCommand(String command){
        PluginCommand pluginCommand = Main.getInstance().getCommand(command);
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
    }

    public abstract void execute(CommandSender sender, String label, String[] args);

    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        execute(sender, label, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        return filter(complete(sender, args), args);
    }

    private List<String> filter(List<String> list, String[] args){
        if(list==null) return null;
        String last = args[args.length-1];
        List<String> result = new ArrayList<>();
        for(String arg: list){
            if(arg.toLowerCase().startsWith(last.toLowerCase())) result.add(arg);
        }
        return result;
    }
}
