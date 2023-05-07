package me.mineland.lobby;

import me.mineland.core.Fields;
import me.mineland.core.instances.PluginInstances;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PluginManager pluginManager;
    private Main instance;

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        instance = this;
        setup();
        pluginManager = Bukkit.getPluginManager();

        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bserver-lobby §7carregado com sucesso.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Fields.PREFIX_PLUGIN + "§7Plugin §bserver-lobby §7descarregado.");
    }

    public void setup() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }
}
