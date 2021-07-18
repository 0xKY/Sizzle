package me.kaloyankys.sizzle.block;

import me.kaloyankys.sizzle.init.SItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class UncookedPizzaBlock extends Block {

    public static final IntProperty STEP = IntProperty.of("step", 1, 3);
    public static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);

    public UncookedPizzaBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.stateManager.getDefaultState().with(STEP, 1));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STEP);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = player.getStackInHand(hand).getItem();
        if (item == SItems.TOMATO_SAUCE_BOTTLE && state.get(STEP) == 1) {
            player.swingHand(hand);
            world.setBlockState(pos, state.with(STEP, 2));
        }
        if (item == SItems.CHEESE && state.get(STEP) == 2) {
            player.swingHand(hand);
            world.setBlockState(pos, state.with(STEP, 3));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}

