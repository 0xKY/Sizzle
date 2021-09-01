package me.kaloyankys.sizzle;

import me.kaloyankys.sizzle.init.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.Tag;

import java.util.List;

public class Sizzle implements ModInitializer {

    @Override
    public void onInitialize() {
        new SItems();
        new SFoods();
        new SBlocks();
        new SBlockEntities();
        new SWorldGen();
        new SBiomes();
    }
}
