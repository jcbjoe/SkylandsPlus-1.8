package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.generator.BlockPopulator;

public class EndTowerPopulator
  extends BlockPopulator
{
  private Random random;
  
  public EndTowerPopulator(World world)
  {
    this.random = new Random(world.getSeed());
  }
  
  public void populate(World world, Random rand, Chunk chunk)
  {
    if (this.random.nextInt(100) < 10)
    {
      int x = this.random.nextInt(8) + 4;
      int z = this.random.nextInt(8) + 4;
      int y = world.getHighestBlockYAt(chunk.getX() * 16 + x, chunk.getZ() * 16 + z) - 4;
      int height = this.random.nextInt(30) + 20;
      if (y < 80) {
        height += 80 - y;
      }
      if (chunk.getBlock(x, y, z).getType() == Material.ENDER_STONE)
      {
        for (int i = 0; i < height; i++)
        {
          chunk.getBlock(x, y + i, z).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x + 1, y + i, z).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 1, y + i, z).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x, y + i, z + 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x, y + i, z - 1).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x + 1, y + i, z - 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 1, y + i, z - 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x + 1, y + i, z + 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 1, y + i, z + 1).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x + 2, y + i, z).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 2, y + i, z).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x, y + i, z + 2).setType(Material.OBSIDIAN);
          chunk.getBlock(x, y + i, z - 2).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x + 1, y + i, z - 2).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 1, y + i, z - 2).setType(Material.OBSIDIAN);
          chunk.getBlock(x + 1, y + i, z + 2).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 1, y + i, z + 2).setType(Material.OBSIDIAN);
          
          chunk.getBlock(x - 2, y + i, z + 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x - 2, y + i, z - 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x + 2, y + i, z + 1).setType(Material.OBSIDIAN);
          chunk.getBlock(x + 2, y + i, z - 1).setType(Material.OBSIDIAN);
        }
        chunk.getBlock(x, y + height, z).setType(Material.BEDROCK);
        chunk.getBlock(x, y + height + 1, z).setType(Material.FIRE);
        world.spawn(chunk.getBlock(x, y + height, z).getLocation().add(0.5D, 0.0D, 0.5D), EnderCrystal.class);
        if (this.random.nextInt(100) < 10) {
          world.spawn(chunk.getBlock(x, y + height + 10, z).getLocation(), EnderDragon.class);
        }
      }
    }
  }
}