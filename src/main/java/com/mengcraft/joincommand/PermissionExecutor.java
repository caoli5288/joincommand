package com.mengcraft.joincommand;

import com.mengcraft.permission.event.PermissionFetchedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

/**
 * Created on 16-6-20.
 */
public class PermissionExecutor extends Executor implements Listener {
    public PermissionExecutor(Main main, List<JoinCommand> commandList) {
        super(main, commandList);
    }

    @EventHandler
    public void handle(PlayerJoinEvent event) {

    }

    @EventHandler
    public void handle(PermissionFetchedEvent event) {
        process(event.getPlayer());
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {
        super.handle(event);
    }
}
