package me.mineland;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;

public class ProxyMain extends Plugin {

    @Getter
    @Setter
    private static ProxyMain instance;

    @Override
    public void onLoad() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        setInstance(this);
    }
}