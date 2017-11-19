package vip.foxcraft.rplplaceholder;

import java.util.Random;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import vip.foxcraft.rplplaceholder.API.RPGPlayerLeveling;

public class OurCoolPlaceholders extends EZPlaceholderHook{
	
	@SuppressWarnings("unused")
	private RplPlaceholder ourPlugin;


	public OurCoolPlaceholders(RplPlaceholder  ourPlugin) {
		super(ourPlugin, "rpl");
		this.ourPlugin = ourPlugin;
	}


	@Override 
	public String onPlaceholderRequest(Player player, String string) {

		string = string.toLowerCase();
		if(string.contains("random")){
			int int1 = Integer.valueOf(string.split("-")[0].replaceAll("[^0-9.+-]", ""));
			int int2 = Integer.valueOf(string.split("-")[1].replaceAll("[^0-9.+-]", ""));
			if(int1 >= int2) return "null";
			int random = new Random().nextInt(int2 - int1);
			random = random +int1;
			return String.valueOf(random);
		}
		if(RPGPlayerLeveling.getRPGPlayerLeveling(player,string) != null)return RPGPlayerLeveling.getRPGPlayerLeveling(player,string);
		return "§c请核对你的变量是否正确!";
			
	}

}
