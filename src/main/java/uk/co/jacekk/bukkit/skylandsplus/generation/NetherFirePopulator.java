package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class NetherFirePopulator
  extends BlockPopulator
{
  private Random random;
  
  public NetherFirePopulator(World world)
  {
    this.random = new Random(world.getSeed());
  }
  
  private double getDistanceSquared(double x, double z)
  {
    return x * x + z * z;
  }
  
  private void ignight(Block block)
  {
    if ((block.getType() == Material.AIR) && (block.getRelative(BlockFace.DOWN).getType() == Material.NETHERRACK) && (this.random.nextInt(100) < 25)) {
      block.setType(Material.FIRE);
    }
  }
  
  public void populate(World world, Random rand, Chunk chunk)
  {
    if (this.random.nextInt(100) < 25)
    {
      Integer x = Integer.valueOf(5 + this.random.nextInt(6));
      Integer z = Integer.valueOf(5 + this.random.nextInt(6));
      
      int radius = (int)Math.ceil(6.5D);
      int radiusSq = radius * radius;
      for (int sx = 0; sx < radius; sx++) {
        for (int sz = 0; sz < radius; sz++) {
          if (getDistanceSquared(sx, sz) < radiusSq) {
            for (int y = 100; y > 30; y--)
            {
              ignight(chunk.getBlock(x.intValue() + sx, y, z.intValue() + sz));
              ignight(chunk.getBlock(x.intValue() + sx, y, z.intValue() - sz));
              ignight(chunk.getBlock(x.intValue() - sx, y, z.intValue() + sz));
              ignight(chunk.getBlock(x.intValue() - sx, y, z.intValue() - sz));
            }
          }
        }
      }
    }
  }
}