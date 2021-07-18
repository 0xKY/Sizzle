package me.kaloyankys.sizzle.init;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class STags {
    public static final Tag<Item> OVEN_COOKABLES = register("oven_cookables", TagRegistry::item);

    private static <E>Tag<E> register(String path, Function<Identifier, Tag<E>> tags) {
        return tags.apply(new Identifier("sizzle", path));
    }
}
