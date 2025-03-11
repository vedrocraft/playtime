package ru.sema1ary.playertime.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.async.Async;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.sema1ary.vedrocraftapi.service.ConfigService;

@RequiredArgsConstructor
@Command(name = "playertime", aliases = {"ptime", "player-time", "pt"})
public class PlayerTimeCommand {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final ConfigService configService;

    @Async
    @Execute(name = "reload")
    @Permission("playertime.reload")
    void reload(@Context CommandSender sender) {
        configService.reload();
        sender.sendMessage(miniMessage.deserialize(configService.get("reload-message")));
    }

    @Async
    @Execute
    @Permission("playertime.use")
    void execute(@Context Player sender, @Arg("время") long time) {
        sender.setPlayerTime(time, false);
        sender.sendMessage(miniMessage.deserialize(
                ((String) configService.get("change-message")).replace("{time}", String.valueOf(time))
        ));
    }

    @Async
    @Execute
    @Permission("playertime.use")
    void execute(@Context Player sender) {
        sender.resetPlayerTime();
        sender.sendMessage(miniMessage.deserialize(configService.get("reset-message")));
    }

    @Async
    @Execute
    @Permission("playertime.use.other")
    void execute(@Context CommandSender sender, @Arg("игрок") Player target
            , @Arg("время") long time) {
        target.setPlayerTime(time, false);
        sender.sendMessage(miniMessage.deserialize(
                ((String) configService.get("target-change-message")).replace("{time}", String.valueOf(time))
                        .replace("{player}", target.getName())
        ));
    }
}
