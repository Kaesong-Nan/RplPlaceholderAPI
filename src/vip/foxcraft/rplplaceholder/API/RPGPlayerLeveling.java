package vip.foxcraft.rplplaceholder.API;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.baks.rpl.PlayerList;
import me.baks.rpl.api.API;
import me.baks.rpl.config.ConfigManager;
import me.baks.rpl.manager.ItemManager;
import me.baks.rpl.manager.PlayerManager;
import me.baks.rpl.manager.StatsManager;
import me.baks.rpl.manager.Utils;

public class RPGPlayerLeveling {
	static public String getRPGPlayerLeveling(Player player,String string){
		//if(string.contains("rpl")
		if (Bukkit.getPluginManager().isPluginEnabled("RPGPlayerLeveling")){
			if(player == null) return "错误，请联系作者";
			if(string.equals("exp")) return String.valueOf(API.getExp(player));
			if(string.equals("mana")) return String.valueOf(API.getMana(player));
			if(string.equals("max_exp")) return String.valueOf(API.getMaxExp(player));
			if(string.equals("max_mana")) return String.valueOf(API.getMaxMana(player));
			if(string.equals("max_power")) return String.valueOf(API.getMaxPower(player));
			if(string.equals("level")) return String.valueOf(API.getPlayerLevel(player));
			if(string.equals("power")) return String.valueOf(API.getPower(player));
			if(string.equals("stat_points")) return String.valueOf(API.getStatPoints(player));
			if(getRPLStats(player, string) != null) return getRPLStats(player, string);
		}else{
			return "未检测到RPGPlayerLeveling!";
		}
		return null;
	}
	
	static public String getRPLStats(Player player,String string){
		ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemStack itemStack2 = player.getInventory().getItemInOffHand();
        if (!ItemManager.checkMainItemBlackList(itemStack)) {
            itemStack = new ItemStack(Material.AIR);
        }
        if (!ItemManager.checkOffItemBlackList(itemStack2)) {
            itemStack2 = new ItemStack(Material.AIR);
        }
        PlayerList playerList = PlayerList.getByName(player.getName());
        int getStr = playerList.getStr() + StatsManager.getStr(itemStack) + StatsManager.getStr(itemStack2) + StatsManager.getStrArmour(player);
        int getSta = playerList.getSta() + StatsManager.getSta(itemStack) + StatsManager.getSta(itemStack2) + StatsManager.getStaArmour(player);
        int getDex = playerList.getDex() + StatsManager.getDex(itemStack) + StatsManager.getDex(itemStack2) + StatsManager.getDexArmour(player);
        int getInt = playerList.getInt() + StatsManager.getInt(itemStack) + StatsManager.getInt(itemStack2) + StatsManager.getIntArmour(player);
        double meleeDamage = ConfigManager.DAMAGE_PER_STR * (double)getStr + StatsManager.getDamageMelee(itemStack) + StatsManager.getDamageMelee(itemStack2) + StatsManager.getDamageMeleeArmour(player) + ItemManager.getItemDamage(itemStack);
        double arrowDamage = ConfigManager.DAMAGE_PER_DEX * (double)getDex + StatsManager.getDamageRanged(itemStack) + StatsManager.getDamageRanged(itemStack2) + StatsManager.getDamageRangedArmour(player) + 5.0;
        double mageDamage = ConfigManager.DAMAGE_PER_INT * (double)getInt + StatsManager.getDamageMage(itemStack) + StatsManager.getDamageMage(itemStack2) + StatsManager.getDamageMageArmour(player);

		if(string.equals("str")) return String.valueOf(Utils.roundDouble(getStr));
		if(string.equals("sta")) return String.valueOf(Utils.roundDouble(getSta));
		if(string.equals("dex")) return String.valueOf(Utils.roundDouble(getDex));
		if(string.equals("int")) return String.valueOf(Utils.roundDouble(getInt));
		if(string.equals("melee_d")) return String.valueOf(Utils.roundDouble(meleeDamage));
		if(string.equals("arrow_d")) return String.valueOf(Utils.roundDouble(arrowDamage));
		if(string.equals("mage_d")) return String.valueOf(Utils.roundDouble(mageDamage));
		if(string.equals("health")) return String.valueOf(Utils.roundDouble(player.getMaxHealth()));
		if(string.equals("def")) return String.valueOf(Utils.roundDouble(StatsManager.getDefense(itemStack) + StatsManager.getDefense(itemStack2) + StatsManager.getDefenseArmour(player)));
		if(string.equals("regen")) return String.valueOf(Utils.roundDouble(1.0 + StatsManager.getRegeneration(itemStack) + StatsManager.getRegeneration(itemStack2) + StatsManager.getRegenerationArmour(player)));
		if(string.equals("crit_c")) return String.valueOf(Utils.roundDouble(ConfigManager.BASE_CRIT_CHANCE + StatsManager.getCritChance(itemStack) + StatsManager.getCritChance(itemStack2) + StatsManager.getCritChanceArmour(player) + (double)getDex * ConfigManager.CRIT_CHANCE_PER_DEX) + "%");
		if(string.equals("crit_p")) return String.valueOf(Utils.roundDouble(ConfigManager.BASE_CRIT_DANAGE + StatsManager.getCritDamage(itemStack) + StatsManager.getCritDamage(itemStack2) + StatsManager.getCritDamageArmour(player) + (double)getStr * ConfigManager.CRIT_DAMAGE_PER_STR) + "%");
		if(string.equals("block")) return String.valueOf(Utils.roundDouble(ConfigManager.BASE_BLOCK_CHANCE + StatsManager.getBlock(itemStack) + StatsManager.getBlock(itemStack2) + StatsManager.getBlockArmour(player) + (double)getDex * ConfigManager.BLOCK_CHANCE_PER_DEX) + "%");
		if(string.equals("dodge")) return String.valueOf(Utils.roundDouble(ConfigManager.BASE_DODGE_CHANCE + StatsManager.getDodge(itemStack) + StatsManager.getDodge(itemStack2) + StatsManager.getDodgeArmour(player) + (double)getDex * ConfigManager.DODGE_CHANCE_PER_DEX) + "%");
		if(string.equals("walk_s")) return String.valueOf(Utils.roundDouble(100 + StatsManager.getWalkSpeed(itemStack) + StatsManager.getWalkSpeed(itemStack2) + StatsManager.getWalkSpeedArmour(player)) + "%");
		if(string.equals("life_s")) return String.valueOf(Utils.roundDouble(StatsManager.getLifeSteal(itemStack) + StatsManager.getLifeSteal(itemStack2) + StatsManager.getLifeStealArmour(player)));
		if(string.equals("ignition")) return String.valueOf(Utils.roundDouble(StatsManager.getIgnition(itemStack) + StatsManager.getIgnition(itemStack2) + StatsManager.getIgnitionArmour(player)) + "%");
		if(string.equals("refl")) return String.valueOf(Utils.roundDouble(StatsManager.getReflection(itemStack) + StatsManager.getReflection(itemStack2) + StatsManager.getReflectionArmour(player)) + "%");
		if(string.equals("slow")) return String.valueOf(Utils.roundDouble(StatsManager.getSlowness(itemStack) + StatsManager.getSlowness(itemStack2) + StatsManager.getSlownessArmour(player)) + "%");
		if(string.equals("poison")) return String.valueOf(Utils.roundDouble(StatsManager.getPoison(itemStack) + StatsManager.getPoison(itemStack2) + StatsManager.getPoisonArmour(player)) + "%");
		if(string.equals("wither")) return String.valueOf(Utils.roundDouble(StatsManager.getWither(itemStack) + StatsManager.getWither(itemStack2) + StatsManager.getWitherArmour(player)) + "%");
		if(string.equals("harm")) return String.valueOf(Utils.roundDouble(StatsManager.getHarm(itemStack) + StatsManager.getHarm(itemStack2) + StatsManager.getHarmArmour(player)) + "%");
		if(string.equals("blind")) return String.valueOf(Utils.roundDouble(StatsManager.getBlindness(itemStack) + StatsManager.getBlindness(itemStack2) + StatsManager.getBlindnessArmour(player)) + "%");
		return null;
	}

}
