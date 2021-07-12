package me.kaloyankys.sizzle.item;

import net.minecraft.item.Item;

public class PizzaItem extends Item {
    public boolean meat;
    public boolean mushrooms;
    public boolean basil;
    public int crunch;
    public int taste;
    public int nourishment;

    public PizzaItem(Settings settings) {
        super(settings);
    }
}
