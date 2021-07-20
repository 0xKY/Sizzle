package me.kaloyankys.sizzle.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PizzaItems {

    public static final Item MARGHERITA = registerPizzas("margherita", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item CAPRICOSA = registerPizzas("capricosa", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item AMERICAN = registerPizzas("american", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item HAWAIIAN = registerPizzas("hawaiian", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));

    public static final Item COOKED_MARGHERITA = registerPizzas("cooked_margherita", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item COOKED_CAPRICOSA = registerPizzas("cooked_capricosa", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item COOKED_AMERICAN = registerPizzas("cooked_american", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item COOKED_HAWAIIAN = registerPizzas("cooked_hawaiian", new Item(new FabricItemSettings().group(ItemGroup.FOOD)));

    private static Item registerPizzas(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("sizzle", id), item);
    }
}
