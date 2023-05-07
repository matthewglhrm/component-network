package me.mineland.core.player;

import me.mineland.core.Fields;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.player.roles.bukkit.RoleManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unchecked")
public class ProfilePlayer {

    private String name;
    private Player player;
    private RoleController group;
    private RoleManager roleManager;

    public ProfilePlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.group = RoleController.getRolePlayer(player);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(RoleController group) {
        this.group = group;
    }

    public void setup() {
        if (this.player == null)
            return;
        this.player.setHealth(20.0D);
        this.player.setLevel(0);
        this.player.setExp(0.0F);
        this.player.setExhaustion(0.0F);
        this.player.resetMaxHealth();
        this.player.setFoodLevel(20);
        this.player.setGameMode(GameMode.ADVENTURE);
        this.player.getActivePotionEffects().forEach(effect -> this.player.removePotionEffect(effect.getType()));

        this.player.closeInventory();
        this.player.getInventory().clear();
        this.player.getInventory().setArmorContents(new ItemStack[4]);

        if (this.player.hasPermission(Fields.PREFIX_PERMISSION + ".fly")) {
            this.player.setAllowFlight(true);
            this.player.setFlying(true);
        }

        RoleManager.setRolePlayer(player);

        this.player.updateInventory();
    }

}
