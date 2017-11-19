package vip.foxcraft.rplplaceholder;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class RplPlaceholder extends JavaPlugin implements Listener{

	//即将改名为: PlaceholderExtend
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
        	new OurCoolPlaceholders(this).hook();
        	Bukkit.getConsoleSender().sendMessage("[RplPlaceholder] Find PlacholderAPI!");
        } else {
        	Bukkit.getConsoleSender().sendMessage("[RplPlaceholder] §c未检测到 PlaceholderAPI! 加载失败!");
            throw new RuntimeException("未检测到 PlaceholderAPI! 请检查是否有此插件!");
        }
        Bukkit.getConsoleSender().sendMessage(getplugins());
        Bukkit.getConsoleSender().sendMessage("[RplPlaceholder] §a加载成功! 插件作者: §eSaukiya");
    }
    
    public String getplugins(){
        String plugins = "[RplPlaceholder] §a检测支持插件列表:";
        if (Bukkit.getPluginManager().isPluginEnabled("RPGPlayerLeveling")) plugins += "§7 §eRPGPlayerLeveling";
    	return plugins;
    }
}
