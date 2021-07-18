package me.kaloyankys.sizzle.block;

import me.kaloyankys.sizzle.block.entity.OvenBlockEntity;
import me.kaloyankys.sizzle.init.SBlockEntities;
import me.kaloyankys.sizzle.init.STags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class OvenBlock extends BlockWithEntity {

    public static final BooleanProperty LIT = Properties.LIT;
    public OvenBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(LIT, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OvenBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SBlockEntities.OVEN_BLOCK_ENTITY, OvenBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (itemStack.isIn(STags.OVEN_COOKABLES)) {
            if (state.get(LIT)) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof OvenBlockEntity ovenBlockEntity) {
                    Optional<CampfireCookingRecipe> optional = ovenBlockEntity.findMatchingRecipe(itemStack);
                    if (optional.isPresent()) {
                        if (ovenBlockEntity.addItem(player.getAbilities().creativeMode ? itemStack.copy() : itemStack, 400)) {
                            player.incrementStat(Stats.INTERACT_WITH_CAMPFIRE);
                            return ActionResult.SUCCESS;
                        }
                        return ActionResult.CONSUME;
                    } else {
                        if (item instanceof ShovelItem) {
                            extinguish(state, world, pos);
                            return ActionResult.SUCCESS;
                        } else if (item == Items.WATER_BUCKET) {
                            extinguish(state, world, pos);
                            if (!player.isCreative()) {
                               player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                            }
                            return ActionResult.SUCCESS;
                            }
                        }
                    }
                }
            } else {
                if (item instanceof FlintAndSteelItem) {
                    world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.f, 1.0f);
                    world.setBlockState(pos, state.with(LIT, Boolean.TRUE));
                    itemStack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(LIT, true);
    }

    @Override
    @Environment(value = EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(CampfireBlock.LIT)) {
            double dx = (double) pos.getX() + 0.5d;
            double dy = pos.getY();
            double dz = (double) pos.getZ() + 0.5d;
            if (random.nextInt(10) == 0) {
                world.playSound(dx, dy, dz, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.f, 1.f, false);
            }
            world.addParticle(ParticleTypes.SMOKE, dx, dy, dz, 0.0d, 0.0d, 0.0d);
            world.addParticle(ParticleTypes.FLAME, dx, dy, dz, 0.0d, 0.0d, 0.0d);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof OvenBlockEntity) {
                ItemScatterer.spawn(world, pos, ((OvenBlockEntity) blockEntity).getInventory());
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    private void extinguish(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(LIT, false));
        double dx = (double) pos.getX() + .5d;
        double dy = pos.getY();
        double dz = (double) pos.getZ() + .5d;
        world.playSound(dx, dy, dz, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, .5f, 2.6f, false);
    }
}