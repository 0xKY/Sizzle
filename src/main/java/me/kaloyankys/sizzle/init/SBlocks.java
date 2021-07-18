package me.kaloyankys.sizzle.init;

import me.kaloyankys.sizzle.block.CheeseCauldronBlock;
import me.kaloyankys.sizzle.block.ChoppingBoardBlock;
import me.kaloyankys.sizzle.block.OvenBlock;
import me.kaloyankys.sizzle.block.UncookedPizzaBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class SBlocks {

    //Chopping Board
    public static final Block CHOPPING_BOARD = register("chopping_board", new ChoppingBoardBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

    //Pizza
    public static final Block UNCOOKED_PIZZA = register("uncooked_pizza", new UncookedPizzaBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).hardness(0.3f)));
    public static final Block OVEN = register("oven", new OvenBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(state -> state.get(Properties.LIT) ? 13 : 0)));

    //Cheese
    public static final Block CHEESE_CAULDRON = registerNoGroup("cheese_cauldron", new CheeseCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).ticksRandomly()));

    public static Block register(String id, Block block) {
        Item blockItem = new BlockItem(block, new Item.Settings().group(ItemGroup.DECORATIONS));
        Registry.register(Registry.ITEM, new Identifier("sizzle", id), blockItem);
        return Registry.register(Registry.BLOCK, new Identifier("sizzle", id), block);
    }
    public static Block registerNoGroup(String id, Block block) {
        Item blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registry.ITEM, new Identifier("sizzle", id), blockItem);
        return Registry.register(Registry.BLOCK, new Identifier("sizzle", id), block);
    }
}
