package uk.co.jacekk.bukkit.skylandsplus;

import java.io.File;
import uk.co.jacekk.bukkit.skylandsplus.listeners.MobSpawnListener;
import uk.co.jacekk.bukkit.skylandsplus.listeners.PhysicsListener;

public class SkylandsPlus
  extends uk.co.jacekk.bukkit.baseplugin.BasePlugin
{
  public void onEnable()
  {
    super.onEnable(true);
    
    this.config = new uk.co.jacekk.bukkit.baseplugin.config.PluginConfig(new File(this.baseDirPath + File.separator + "config.yml"), Config.class, this.log);
    if (this.config.getBoolean(Config.PREVENT_SAND_FALLING)) {
      this.getServer().getPluginManager().registerEvents(new PhysicsListener(this), this);
    }
    if (this.config.getBoolean(Config.RESTRICT_MOB_SPAWNING)) {
      this.getServer().getPluginManager().registerEvents(new MobSpawnListener(this), this);
    }
  }
  
  public org.bukkit.generator.ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
  {
    return new uk.co.jacekk.bukkit.skylandsplus.generation.ChunkGenerator();
  }
}