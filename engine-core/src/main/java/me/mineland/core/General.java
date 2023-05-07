package me.mineland.core;

import lombok.Getter;
import lombok.Setter;
import me.mineland.ProxyMain;
import me.mineland.core.connections.MySQL;
import me.mineland.core.instances.PluginInstances;
import me.mineland.spigot.SpigotMain;
import net.md_5.bungee.api.ProxyServer;
import org.bukkit.Bukkit;

import java.util.concurrent.TimeUnit;

@Getter
public class General {

    private static PluginInstances pluginInstances = PluginInstances.NONE;

    @Getter
    @Setter
    private static final MySQL mySQL = new MySQL();

    public static PluginInstances getPluginInstance() {
        return pluginInstances;
    }

    public static void setPluginInstance(PluginInstances pluginInstance) {
        General.pluginInstances = pluginInstance;
    }

    public static void error(String message) {
        console("§c[ERROR] " + message);
    }

    public static void console(String message) {
        if (pluginInstances == PluginInstances.SPIGOT) {
            spigotConsole(message);
        } else {
            proxyConsole(message);
        }
    }

    public static void spigotConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage("[engine-spigot] " + msg);
    }

    @SuppressWarnings("deprecation")
    public static void proxyConsole(String msg) {
        ProxyServer.getInstance().getConsole().sendMessage("[engine-proxy] " + msg);
    }

    public static void runAsync(Runnable runnable) {
        if (pluginInstances == PluginInstances.SPIGOT) {
            runSpigotAsync(runnable);
        } else {
            runProxyAsync(runnable);
        }
    }

    public static void runSpigotAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(SpigotMain.getInstance(), runnable);
    }

    public static void runSpigotLater(Runnable runnable) {
        runSpigotLater(runnable, 5);
    }

    public static void runSpigotLater(Runnable runnable, long ticks) {
        Bukkit.getScheduler().runTaskLater(SpigotMain.getInstance(), runnable, ticks);
    }

    public static void runProxyAsync(Runnable runnable) {
        ProxyServer.getInstance().getScheduler().runAsync(ProxyMain.getInstance(), runnable);
    }

    public static void runProxyLater(Runnable runnable) {
        ProxyServer.getInstance().getScheduler().schedule(ProxyMain.getInstance(), runnable, 500, TimeUnit.MILLISECONDS);
    }

    public static void runProxyLater(Runnable runnable, long ms) {
        ProxyServer.getInstance().getScheduler().schedule(ProxyMain.getInstance(), runnable, ms, TimeUnit.MILLISECONDS);
    }

    public static void runProxyLater(Runnable runnable, int tempo, TimeUnit timeUnit) {
        ProxyServer.getInstance().getScheduler().schedule(ProxyMain.getInstance(), runnable, tempo, timeUnit);
    }

}
