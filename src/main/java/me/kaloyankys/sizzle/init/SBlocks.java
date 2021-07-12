package me.kaloyankys.sizzle.init;

import me.kaloyankys.sizzle.block.ChoppingBoardBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class SBlocks {

//Chopping Board
    public static final Block CHOPPING_BOARD = register("chopping_board", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

    public static Block register(String id, Block block) {
        Item blockItem = new BlockItem(block, new Item.Settings().group(ItemGroup.DECORATIONS));
        Registry.register(Registry.ITEM, new Identifier("sizzle", id), blockItem);
        return Registry.register(Registry.BLOCK, new Identifier("sizzle", id), block);
    }
}
