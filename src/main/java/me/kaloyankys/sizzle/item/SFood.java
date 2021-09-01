package me.kaloyankys.sizzle.item;

import net.minecraft.item.Item;

public class SFood extends Item {

    public static Item choppedItem;

    public SFood(Settings settings, Item slices) {
        super(settings);
        choppedItem = slices;
    }
}
