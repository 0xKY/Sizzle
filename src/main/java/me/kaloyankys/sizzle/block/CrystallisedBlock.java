package me.kaloyankys.sizzle.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class CrystallisedBlock extends SaltBlock {

    public BlockState crystalState;

    public CrystallisedBlock(Settings settings, Block crystal) {
        super(settings);
        crystalState = crystal.getDefaultState();
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt() == 0 && world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
            world.setBlockState(pos.up(), crystalState);
        }
        super.randomTick(state, world, pos, random);
    }

}
