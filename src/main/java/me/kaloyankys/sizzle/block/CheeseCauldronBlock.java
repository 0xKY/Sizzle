package me.kaloyankys.sizzle.block;

import me.kaloyankys.sizzle.init.SItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("deprecation")
public class CheeseCauldronBlock extends Block {

    public static final BooleanProperty MATURE = BooleanProperty.of("mature");

    public CheeseCauldronBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.stateManager.getDefaultState().with(MATURE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MATURE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(MATURE)) {
            player.swingHand(hand);
            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
            ItemScatterer.spawn(world, pos.getX(), pos.getY() + 0.5, pos.getZ(), new ItemStack(SItems.CHEESE));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(2) == 0) {
            world.setBlockState(pos, state.with(MATURE, true));
        }
    }
}

