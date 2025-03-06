package ru.sema1ary.playertime;

import org.bukkit.plugin.java.JavaPlugin;
import ru.sema1ary.playertime.command.PlayerTimeCommand;
import ru.vidoskim.bukkit.service.ConfigService;
import ru.vidoskim.bukkit.service.impl.ConfigServiceImpl;
import ru.vidoskim.bukkit.util.LiteCommandUtil;
import service.ServiceManager;

public final class PlayerTime extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ServiceManager.registerService(ConfigService.class, new ConfigServiceImpl(this));

        new LiteCommandUtil().create("playertime",
            new PlayerTimeCommand(ServiceManager.getService(ConfigService.class)));
    }

    @Override
    public void onDisable() {
        ServiceManager.disableServices();
    }
}
