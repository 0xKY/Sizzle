package me.kaloyankys.sizzle.block;

import net.minecraft.block.FallingBlock;

import java.util.Random;

public class SaltBlock extends FallingBlock {

    public Random random = new Random();

    public SaltBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected int getFallDelay() {
        return random.nextInt();
    }
}