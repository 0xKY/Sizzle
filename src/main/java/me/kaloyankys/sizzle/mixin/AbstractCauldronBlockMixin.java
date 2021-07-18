package me.kaloyankys.sizzle.mixin;

import me.kaloyankys.sizzle.init.SBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin {

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void onCauldronUsed(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
    if (player.getStackInHand(hand).getItem() == Items.MILK_BUCKET) {
        player.swingHand(hand);
        player.setStackInHand(hand, new ItemStack(Items.BUCKET));
        world.setBlockState(pos, SBlocks.CHEESE_CAULDRON.getDefaultState());
        }
    }
}

