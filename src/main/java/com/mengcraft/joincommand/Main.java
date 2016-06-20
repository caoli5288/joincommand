package com.mengcraft.joincommand;

import com.mengcraft.simpleorm.DatabaseException;
import com.mengcraft.simpleorm.EbeanHandler;
import com.mengcraft.simpleorm.EbeanManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created on 16-6-20.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        EbeanHandler db = EbeanManager.DEFAULT.getHandler(this);
        if (db.isNotInitialized()) {
            db.define(JoinCommand.class);
            try {
                db.initialize();
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
        }
        db.reflect();
        db.install();

        List<JoinCommand> list = db.find(JoinCommand.class).findList();
        Plugin permission = getServer().getPluginManager().getPlugin("Permission");
        if (permission == null) {
            getServer().getPluginManager().registerEvents(new Executor(this, list), this);
        } else {
            getServer().getPluginManager().registerEvents(new PermissionExecutor(this, list), this);
        }
    }

    public void process(Player p, JoinCommand j) {
        getServer().dispatchCommand(getServer().getConsoleSender(), String.format(j.getJoin(), p.getName()));
    }
}
