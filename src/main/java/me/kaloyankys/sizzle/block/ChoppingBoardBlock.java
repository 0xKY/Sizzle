package me.kaloyankys.sizzle.block;

import me.kaloyankys.sizzle.init.SBlockEntities;
import me.kaloyankys.sizzle.init.SItems;
import me.kaloyankys.sizzle.init.STags;
import me.kaloyankys.sizzle.item.SFood;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChoppingBoardBlock extends Block {

    public static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 17.0D, 2.0D, 17.0D);
    public ChoppingBoardBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        SFood slices = (SFood) itemStack.getItem();
        int itemCount = player.getStackInHand(hand).getCount();
        if (!world.isClient() && slices.getDefaultStack().isIn(STags.CHOPPABLE)) {
            player.swingHand(hand);
            itemStack.decrement(itemCount);
            while (itemCount > 0) {
                itemCount--;
                ItemScatterer.spawn(world, pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D, slices.choppedItem.getDefaultStack());
            }
        }
        super.onUse(state, world, pos, player, hand, hit);
        return ActionResult.CONSUME;
    }
}
