package uk.co.jacekk.bukkit.skylandsplus.generation;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.Blocks;
import net.minecraft.server.v1_8_R1.WorldGenLakes;
import net.minecraft.server.v1_8_R1.WorldGenReed;

import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.generator.BlockPopulator;

public class LakePopulator
  extends BlockPopulator
{
  private Random random;
  
  public LakePopulator(org.bukkit.World world)
  {
    this.random = new Random(world.getSeed());
  }
  public void populate(org.bukkit.World world, Random random, Chunk chunk)
  {
    net.minecraft.server.v1_8_R1.World mcWorld = ((CraftWorld)world).getHandle();
    


    int worldChunkX = chunk.getX() * 16;
    int worldChunkZ = chunk.getZ() * 16;
    if (this.random.nextInt(4) == 0)
    {
      int x = worldChunkX + this.random.nextInt(16) + 8;
      int z = worldChunkZ + this.random.nextInt(16) + 8;
      if (!Arrays.asList(new Biome[] { Biome.JUNGLE, Biome.JUNGLE_HILLS }).contains(world.getBiome(x, z)))
      {
        int y = world.getHighestBlockYAt(x, z) + 2;
        if (this.random.nextInt(100) < 85)
        {
        	new WorldGenLakes(Blocks.WATER).generate(mcWorld, random, new BlockPosition(x,y,z));
            new WorldGenReed().generate(mcWorld, random, new BlockPosition(x,y,z));
        }
        else
        {
        	new WorldGenLakes(Blocks.LAVA).generate(mcWorld, random, new BlockPosition(x,y,z));
        }
      }
    }
  }
}