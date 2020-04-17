package net.electron.endreborn.world;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ObsidianOreFeature extends Feature<OreFeatureConfig> {
	public ObsidianOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> config) {
        super(config);
	}

	@Override
	   public boolean generate(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, OreFeatureConfig oreFeatureConfig) {
	      float f = random.nextFloat() * 3.1415927F;
	      float g = (float)oreFeatureConfig.size / 8.0F;
	      int i = MathHelper.ceil(((float)oreFeatureConfig.size / 16.0F * 2.0F + 1.0F) / 2.0F);
	      double d = (double)((float)blockPos.getX() + MathHelper.sin(f) * g);
	      double e = (double)((float)blockPos.getX() - MathHelper.sin(f) * g);
	      double h = (double)((float)blockPos.getZ() + MathHelper.cos(f) * g);
	      double j = (double)((float)blockPos.getZ() - MathHelper.cos(f) * g);
	      boolean k = true;
	      double l = (double)(blockPos.getY() + random.nextInt(3) - 2);
	      double m = (double)(blockPos.getY() + random.nextInt(3) - 2);
	      int n = blockPos.getX() - MathHelper.ceil(g) - i;
	      int o = blockPos.getY() - 2 - i;
	      int p = blockPos.getZ() - MathHelper.ceil(g) - i;
	      int q = 2 * (MathHelper.ceil(g) + i);
	      int r = 2 * (2 + i);

	      for(int s = n; s <= n + q; ++s) {
	         for(int t = p; t <= p + q; ++t) {
	            if (o >= iWorld.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, s, t)) {
	               return this.generateVeinPart(iWorld, random, oreFeatureConfig, d, e, h, j, l, m, n, o, p, q, r);
	            }
	         }
	      }

	      return false;
	   }

	   protected boolean generateVeinPart(IWorld world, Random random, OreFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
		   int i = 0;
		      BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
		      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		      double[] adouble = new double[config.size * 4];
		      for(int j = 0; j < config.size; ++j) {
		         float f = (float)j / (float)config.size;
		         double d0 = MathHelper.lerp((double)f, p_207803_4_, p_207803_6_);
		         double d2 = MathHelper.lerp((double)f, p_207803_12_, p_207803_14_);
		         double d4 = MathHelper.lerp((double)f, p_207803_8_, p_207803_10_);
		         double d6 = random.nextDouble() * (double)config.size / 16.0D;
		         double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
		         adouble[j * 4 + 0] = d0;
		         adouble[j * 4 + 1] = d2;
		         adouble[j * 4 + 2] = d4;
		         adouble[j * 4 + 3] = d7;
		      }
		      for(int l2 = 0; l2 < config.size - 1; ++l2) {
		         if (!(adouble[l2 * 4 + 4] <= 0.0D)) {
		            for(int j3 = l2 + 1; j3 < config.size; ++j3) {
		               if (!(adouble[j3 * 4 + 3] <= 0.0D)) {
		                  double d12 = adouble[l2 * 4 + 0] - adouble[j3 * 4 + 0];
		                  double d13 = adouble[l2 * 4 + 1] - adouble[j3 * 4 + 1];
		                  double d14 = adouble[l2 * 4 + 2] - adouble[j3 * 4 + 2];
		                  double d15 = adouble[l2 * 4 + 3] - adouble[j3 * 4 + 3];
		                  if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
		                     if (d15 > 0.0D) {
		                        adouble[j3 * 4 + 3] = -1.0D;
		                     } else {
		                        adouble[l2 * 4 + 3] = -1.0D;
		                     }
		                  }
		               }
		            }
		         }
		      }
		      for(int i3 = 0; i3 < config.size; ++i3) {
		         double d11 = adouble[i3 * 4 + 3];
		         if (!(d11 < 0.0D)) {
		            double d1 = adouble[i3 * 4 + 0];
		            double d3 = adouble[i3 * 4 + 1];
		            double d5 = adouble[i3 * 4 + 2];
		            int k = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
		            int k3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
		            int l = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
		            int i1 = Math.max(MathHelper.floor(d1 + d11), k);
		            int j1 = Math.max(MathHelper.floor(d3 + d11), k3);
		            int k1 = Math.max(MathHelper.floor(d5 + d11), l);
		            for(int l1 = k; l1 <= i1; ++l1) {
		               double d8 = ((double)l1 + 0.5D - d1) / d11;
		               if (d8 * d8 < 2.0D) {
		                  for(int i2 = k3; i2 <= j1; ++i2) {
		                     double d9 = ((double)i2 + 0.5D - d3) / d11;
		                     if (d8 * d8 + d9 * d9 < 2.0D) {
		                        for(int j2 = l; j2 <= k1; ++j2) {
		                           double d10 = ((double)j2 + 0.5D - d5) / d11;
		                           if (d8 * d8 + d9 * d9 + d10 * d10 < 2.0D) {
		                              int k2 = l1 - p_207803_16_ + (i2 - p_207803_17_) * p_207803_19_ + (j2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
		                              if (!bitset.get(k2)) {
		                                 bitset.set(k2);
		                                 blockpos$mutable.set(l1, i2, j2);
		                                 
		                                 if (world.getBlockState(blockpos$mutable).getBlock() == Blocks.OBSIDIAN)
		                                 {
		                                    world.setBlockState(blockpos$mutable, config.state, 2);
		                                    ++i;
		                                 }
		                              }
		                           }
		                        }
		                     }
		                  }
		               }
		            }
		         }
		      }
		      return i > 0;
		   }
	}