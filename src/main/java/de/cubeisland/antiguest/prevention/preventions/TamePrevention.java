package de.cubeisland.antiguest.prevention.preventions;

import de.cubeisland.antiguest.prevention.Prevention;
import de.cubeisland.antiguest.prevention.PreventionPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityTameEvent;

/**
 * Prevents taming
 *
 * @author Phillip Schichtel
 */
public class TamePrevention extends Prevention
{

    public TamePrevention(PreventionPlugin plugin)
    {
        super("tame", plugin);
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void tame(EntityTameEvent event)
    {
        if (event.getOwner() instanceof Player)
        {
            checkAndPrevent(event, (Player)event.getOwner());
        }
    }
}