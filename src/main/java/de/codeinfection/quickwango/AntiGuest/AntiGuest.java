package de.codeinfection.quickwango.AntiGuest;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;

public class AntiGuest extends JavaPlugin
{
    protected static final Logger log = Logger.getLogger("Minecraft");
    public static boolean debugMode = true;
    
    protected Server server;
    protected PluginManager pm;
    protected Configuration config;
    protected File dataFolder;
    protected PermissionHandler permissionHandler;
    protected HashMap<String, Boolean> actions;
    protected HashMap<String, String> messages;

    public AntiGuest()
    {
        this.actions = new HashMap<String, Boolean>();
        this.messages = new HashMap<String, String>();

        this.actions.put("interact", true);
        this.messages.put("interact", "&4You are not allowed to interact with the world!");

        this.actions.put("build", false);
        this.messages.put("build", "&4You are not allowed to build!");
        
        this.actions.put("pvp", false);
        this.messages.put("pvp", "&4You are not allowed to fight!");

        this.actions.put("pickup", false);
        this.messages.put("pickup", "&4You are not allowed to pickup items!");

        this.actions.put("vehicle", false);
        this.messages.put("vehicle", "&4You are not allowed to use vehicles!");

        this.actions.put("spam", false);
        this.messages.put("spam", "&4Don't spam the chat!");
    }

    public void onEnable()
    {
        this.server = this.getServer();
        this.pm = this.server.getPluginManager();
        this.config = this.getConfiguration();
        this.dataFolder = this.getDataFolder();

        this.dataFolder.mkdirs();
        // Create default config if it doesn't exist.
        if (!(new File(this.dataFolder, "config.yml")).exists())
        {
            this.defaultConfig();
        }
        this.loadConfig();

        Permissions permissions = (Permissions)this.pm.getPlugin("Permissions");
        if (permissions != null)
        {
            this.permissionHandler = permissions.getHandler();
        }

        AntiGuestPlayerListener playerListener = new AntiGuestPlayerListener(this);
        AntiGuestEntityListener entityListener = new AntiGuestEntityListener(this);
        AntiGuestBlockListener blockListener = new AntiGuestBlockListener(this);
        AntiGuestVehicleListener vehicleListener = new AntiGuestVehicleListener(this);

        if (this.actions.get("interact"))
        {
            this.pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.ENTITY_INTERACT, entityListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.VEHICLE_DAMAGE, vehicleListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.VEHICLE_COLLISION_ENTITY, vehicleListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.ENTITY_TARGET, entityListener, Priority.Lowest, this);
        }
        if (this.actions.get("build"))
        {
            this.pm.registerEvent(Type.BLOCK_DAMAGE, blockListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.BLOCK_PLACE, blockListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.PAINTING_BREAK, entityListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.PAINTING_PLACE, entityListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.VEHICLE_DESTROY, vehicleListener, Priority.Lowest, this);
        }
        if (this.actions.get("pvp"))
        {
            this.pm.registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.Lowest, this);
        }
        if (this.actions.get("pickup"))
        {
            this.pm.registerEvent(Type.PLAYER_PICKUP_ITEM, playerListener, Priority.Lowest, this);
        }
        if (this.actions.get("vehicle"))
        {
            this.pm.registerEvent(Type.VEHICLE_ENTER, vehicleListener, Priority.Lowest, this);
            this.pm.registerEvent(Type.VEHICLE_EXIT, vehicleListener, Priority.Lowest, this);
        }
        if (this.actions.get("spam"))
        {
            this.pm.registerEvent(Type.PLAYER_CHAT, playerListener, Priority.Lowest, this);
        }

        System.out.println(this.getDescription().getName() + " (v" + this.getDescription().getVersion() + ") enabled");
    }

    public void onDisable()
    {
        System.out.println(this.getDescription().getName() + " Disabled");
    }

    private void loadConfig()
    {
        this.config.load();

        this.actions.put("build", this.config.getBoolean("build.enable", this.actions.get("build")));
        this.messages.put("build", this.config.getString("build.message", this.messages.get("build")));

        this.actions.put("interact", this.config.getBoolean("interact.enable", this.actions.get("interact")));
        this.messages.put("interact", this.config.getString("interact.message", this.messages.get("interact")));

        this.actions.put("pvp", this.config.getBoolean("pvp.enable", this.actions.get("pvp")));
        this.messages.put("pvp", this.config.getString("pvp.message", this.messages.get("pvp")));

        this.actions.put("pickup", this.config.getBoolean("pickup.enable", this.actions.get("pickup")));
        this.messages.put("pickup", this.config.getString("pickup.message", this.messages.get("pickup")));

        this.actions.put("vehicle", this.config.getBoolean("vehicle.enable", this.actions.get("vehicle")));
        this.messages.put("vehicle", this.config.getString("vehicle.message", this.messages.get("vehicle")));

        this.actions.put("spam", this.config.getBoolean("spam.enable", this.actions.get("spam")));
        this.messages.put("spam", this.config.getString("spam.message", this.messages.get("spam")));

        for (Map.Entry<String, String> entry : this.messages.entrySet())
        {
            this.messages.put(entry.getKey(), entry.getValue().replaceAll("&([a-f0-9])", "\u00A7$1"));
        }
    }

    private void defaultConfig()
    {
        this.config.setProperty("spam.enable", this.actions.get("spam"));
        this.config.setProperty("spam.message", this.messages.get("spam"));
        
        this.config.setProperty("vehicle.enable", this.actions.get("vehicle"));
        this.config.setProperty("vehicle.message", this.messages.get("vehicle"));

        this.config.setProperty("pickup.enable", this.actions.get("pickup"));
        this.config.setProperty("pickup.message", this.messages.get("pickup"));

        this.config.setProperty("pvp.enable", this.actions.get("pvp"));
        this.config.setProperty("pvp.message", this.messages.get("pvp"));

        this.config.setProperty("build.enable", this.actions.get("build"));
        this.config.setProperty("build.message", this.messages.get("build"));

        this.config.setProperty("interact.enable", this.actions.get("interact"));
        this.config.setProperty("interact.message", this.messages.get("interact"));

        
        this.config.save();
    }

    public static void log(String msg)
    {
        log.log(Level.INFO, "[AntiGuest] " + msg);
    }

    public static void error(String msg)
    {
        log.log(Level.SEVERE, "[AntiGuest] " + msg);
    }

    public static void error(String msg, Throwable t)
    {
        log.log(Level.SEVERE, "[AntiGuest] " + msg, t);
    }

    public static void debug(String msg)
    {
        if (debugMode)
        {
            log("[debug] " + msg);
        }
    }

    public boolean can(Player player, String type)
    {
        String permission = "AntiGuest." + type;
        if (this.permissionHandler != null)
        {
            return this.permissionHandler.permission(player, permission);
        }
        else
        {
            return player.hasPermission(permission);
        }
    }

    public void message(Player player, String type)
    {
        String message = this.messages.get(type);
        if (message != null && !message.isEmpty())
        {
            player.sendMessage(message);
        }
    }
}
