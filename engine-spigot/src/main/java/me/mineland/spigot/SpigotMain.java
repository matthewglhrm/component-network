package me.mineland.spigot;

import lombok.Getter;
import lombok.Setter;
import me.mineland.core.Fields;
import me.mineland.core.General;
import me.mineland.core.instances.PluginInstances;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {

    private PluginManager pluginManager;

    private static SpigotMain instance;

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        instance = this;
        setup();
        General.setPluginInstance(PluginInstances.SPIGOT);
        pluginManager = Bukkit.getPluginManager();

        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bessencialmente §7carregado com sucesso.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bessencialmente §7descarregado.");
    }

    public void setup() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public static SpigotMain getInstance() {
        return instance;
    }
}
