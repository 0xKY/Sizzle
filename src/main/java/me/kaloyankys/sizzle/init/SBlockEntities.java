package me.kaloyankys.sizzle.init;

import me.kaloyankys.sizzle.block.entity.OvenBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class SBlockEntities {
    public static BlockEntityType<OvenBlockEntity> OVEN_BLOCK_ENTITY = register(
            "oven_block_entity",
            FabricBlockEntityTypeBuilder.create(OvenBlockEntity::new, SBlocks.OVEN).build(null));

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, "sizzle", type);
    }
}
