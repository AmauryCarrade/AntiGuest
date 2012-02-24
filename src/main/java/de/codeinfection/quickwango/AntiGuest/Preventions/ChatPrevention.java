package de.codeinfection.quickwango.AntiGuest.Preventions;

import de.codeinfection.quickwango.AntiGuest.AntiGuest;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author Phillip
 */
public class ChatPrevention extends Prevention
{

    public ChatPrevention()
    {
        super("chat", AntiGuest.getInstance());
    }

    @Override
    public ConfigurationSection getDefaultConfig()
    {
        ConfigurationSection config = super.getDefaultConfig();

        config.addDefault("message", "&4You are not allowed to chat!");

        return config;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void handle(PlayerChatEvent event)
    {
        prevent(event, event.getPlayer());
    }
}
