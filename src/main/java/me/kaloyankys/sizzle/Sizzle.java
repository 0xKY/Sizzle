package me.kaloyankys.sizzle;

import me.kaloyankys.sizzle.init.SBlocks;
import me.kaloyankys.sizzle.init.SFoods;
import me.kaloyankys.sizzle.init.SItems;
import net.fabricmc.api.ModInitializer;

public class Sizzle implements ModInitializer {
    @Override
    public void onInitialize() {
        new SItems();
        new SFoods();
        new SBlocks();
    }
}
