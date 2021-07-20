package me.kaloyankys.sizzle.init;

import me.kaloyankys.sizzle.item.PizzaItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SItems {

public static final Item TOMATO = register("tomato", new Item(new FabricItemSettings().food(SFoods.BASE_VEGETABLE).group(ItemGroup.FOOD)));
public static final Item SLICED_TOMATO = register("sliced_tomato", new Item(new FabricItemSettings().group(ItemGroup.MISC)));
public static final Item CUCUMBER = register("cucumber", new Item(new FabricItemSettings().food(SFoods.BASE_VEGETABLE).group(ItemGroup.FOOD)));
public static final Item SLICED_CUCUMBER = register("sliced_cucumber", new Item(new FabricItemSettings().group(ItemGroup.MISC)));
public static final Item TOMATO_SAUCE_BOTTLE = register("tomato_sauce_bottle", new Item(new FabricItemSettings().food(SFoods.BASE_VEGETABLE).group(ItemGroup.FOOD)));
public static final Item CHEESE = register("cheese", new Item(new FabricItemSettings().food(SFoods.BASE_VEGETABLE).group(ItemGroup.FOOD)));

    public SItems() {
        new PizzaItems();
    }

    private static Item register (String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("sizzle", id), item);
    }
}
