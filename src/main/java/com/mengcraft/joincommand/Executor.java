package com.mengcraft.joincommand;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created on 16-6-20.
 */
public class Executor implements Listener {
    private final Map<UUID, JoinCommand> quit = new HashMap<>();
    private final List<JoinCommand> commandList;
    private final Main main;

    public Executor(Main main, List<JoinCommand> commandList) {
        this.main = main;
        this.commandList = commandList;
    }

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        process(event.getPlayer());
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {
        JoinCommand remove = quit.remove(event.getPlayer().getUniqueId());
        if (remove != null) {
            main.process(event.getPlayer(), remove);
        }
    }

    protected void process(Player p) {
        commandList.forEach(j -> {
            if (p.hasPermission(j.getPermission())) {
                if (j.getJoin() != null && !j.getJoin().equals("")) {
                    main.process(p, j);
                }
                if (j.getQuit() != null && !j.getQuit().equals("")) {
                    quit.put(p.getUniqueId(), j);
                }
            }
        });
    }

}
