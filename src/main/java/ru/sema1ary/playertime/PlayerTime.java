package ru.sema1ary.playertime;

import org.bukkit.plugin.java.JavaPlugin;
import ru.sema1ary.playertime.command.PlayerTimeCommand;
import ru.sema1ary.vedrocraftapi.command.LiteCommandBuilder;
import ru.sema1ary.vedrocraftapi.service.ConfigService;
import ru.sema1ary.vedrocraftapi.service.ServiceManager;
import ru.sema1ary.vedrocraftapi.service.impl.ConfigServiceImpl;

public final class PlayerTime extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ServiceManager.registerService(ConfigService.class, new ConfigServiceImpl(this));

        LiteCommandBuilder.builder()
                .commands(new PlayerTimeCommand(ServiceManager.getService(ConfigService.class)))
                .build();
    }

    @Override
    public void onDisable() {
        ServiceManager.disableServices();
    }
}
