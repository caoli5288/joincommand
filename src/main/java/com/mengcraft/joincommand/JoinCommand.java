package com.mengcraft.joincommand;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 16-6-20.
 */
@Entity
public class JoinCommand {

    @Id
    private int id;
    @Column(nullable = false)
    private String permission;
    @Column(nullable = false)
    private String join;
    @Column(nullable = false)
    private String quit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getQuit() {
        return quit;
    }

    public void setQuit(String quit) {
        this.quit = quit;
    }
}
