package vip.floatationdevice.survivalsetspawnerlimiter;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * When a survival mode player try to use spawner egg to set the spawner,
 * this plugin will check if the player has the permission to do so.
 */
public final class SurvivalSetSpawnerLimiter extends JavaPlugin implements Listener
{

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable()
    {
        // do nothing
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSpawnerSet(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK  // right-click at block?
                && event.getClickedBlock().getType() == Material.SPAWNER // spawner clicked?
                && event.getPlayer().getGameMode() == GameMode.SURVIVAL // player in survival mode?
                && event.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("SPAWN_EGG") // item is spawner egg?
                && !event.getPlayer().hasPermission("survivalsetspawnerlimiter.bypass") // player has the permission?
        )
        {
            event.setCancelled(true);
            //getLogger().info(event.getPlayer().getName() + " failed to set spawner because of lack of permission");
        }
    }
}
