package com.electron.endreborn.world;

import com.electron.endreborn.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.function.Function;

public class EndMossFeature extends Feature<BushConfig> {
	   public EndMossFeature(Function<Dynamic<?>, ? extends BushConfig> p_i49908_1_) {
		      super(p_i49908_1_);
		   }

		   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BushConfig config) {
		      int i = 0;
		      BlockState brickstate = ModBlocks.MOSSY_END_STONE.get().getDefaultState();
		      BlockState blockstate = ModBlocks.END_MOSS.get().getDefaultState();
		      for(int j = 0; j < 1024; ++j) {
		         BlockPos blockpos = pos.add(rand.nextInt(16) - rand.nextInt(12), pos.getY(), rand.nextInt(16) - rand.nextInt(8));
		         if (worldIn.getBlockState(blockpos).getBlock() == Blocks.END_STONE && blockstate.isValidPosition(worldIn, blockpos) && !worldIn.isAirBlock(blockpos) && worldIn.isAirBlock(blockpos.up())) {
		         	worldIn.setBlockState(blockpos, blockstate, 2);
		         	++i;
				 }
		         if (worldIn.getBlockState(blockpos).getBlock() == Blocks.END_STONE_BRICKS && blockstate.isValidPosition(worldIn, blockpos) && !worldIn.isAirBlock(blockpos)) {
		         	worldIn.setBlockState(blockpos, brickstate, 2);
		         	++i;
				}
		   }
		return i > 0;
	 }
}