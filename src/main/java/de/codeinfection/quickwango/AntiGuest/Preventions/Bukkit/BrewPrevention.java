package de.codeinfection.quickwango.AntiGuest.Preventions.Bukkit;

import de.codeinfection.quickwango.AntiGuest.AntiGuestBukkit;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Prevents brewing
 *
 * @author Phillip Schichtel
 */
public class BrewPrevention extends Prevention
{
    public BrewPrevention()
    {
        super("brew", AntiGuestBukkit.getInstance());
    }

    @Override
    public ConfigurationSection getDefaultConfig()
    {
        ConfigurationSection config = super.getDefaultConfig();

        config.set("message", "&4You are not allowed to access brewing stands!");

        return config;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void handle(InventoryOpenEvent event)
    {
        if ("container.brewing".equals(event.getInventory().getName()))
        {
            if (event.getPlayer() instanceof Player)
            {
                prevent(event, (Player)event.getPlayer());
            }
        }
    }
}