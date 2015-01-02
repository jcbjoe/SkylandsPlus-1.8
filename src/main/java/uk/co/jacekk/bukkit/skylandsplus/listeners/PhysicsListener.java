package uk.co.jacekk.bukkit.skylandsplus.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

import uk.co.jacekk.bukkit.skylandsplus.SkylandsPlus;
import uk.co.jacekk.bukkit.skylandsplus.generation.ChunkGenerator;

public class PhysicsListener
  extends uk.co.jacekk.bukkit.baseplugin.event.BaseListener<SkylandsPlus> implements Listener
{
  public PhysicsListener(SkylandsPlus plugin)
  {
    super(plugin);
  }
  
  @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
  public void onBlockPhysics(BlockPhysicsEvent event)
  {
    Material changed = event.getChangedType();
    if (((changed == Material.SAND) || (changed == Material.GRAVEL)) && 
      ((event.getBlock().getWorld().getGenerator() instanceof ChunkGenerator))) {
      event.setCancelled(true);
    }
  }
}