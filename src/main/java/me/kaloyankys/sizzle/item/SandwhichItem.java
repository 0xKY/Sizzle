package me.kaloyankys.sizzle.item;

import net.minecraft.item.Item;

public class SandwhichItem extends Item {
    public boolean meat;
    public int ingredientsNumber;
    public int crunch;
    public int taste;
    public int nourishment;

    public SandwhichItem(Settings settings) {
        super(settings);
    }
}
