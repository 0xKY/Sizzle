package me.kaloyankys.sizzle.init;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.floatprovider.TrapezoidFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.*;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.BiasedToBottomHeightProvider;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.heightprovider.HeightProviderType;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class SWorldGen {
    public static final ConfiguredCarver<?> SALT_RIFT_CARVER = RavineCarver.RAVINE.configure(
            new RavineCarverConfig(0.1F,
                    BiasedToBottomHeightProvider.create(YOffset.fixed(5), YOffset.fixed(67), 6),
                    ConstantFloatProvider.create(3.0F), YOffset.aboveBottom(5), false,
                    CarverDebugConfig.create(false, Blocks.WARPED_BUTTON.getDefaultState()),
                    UniformFloatProvider.create(-0.125F, 0.5F),
                    new RavineCarverConfig.Shape(UniformFloatProvider.create(0.75F, 1.0F),
                            TrapezoidFloatProvider.create(0.0F, 3.0F, 3.0F), 3,
                            UniformFloatProvider.create(0.75F, 1.0F), 1.5F, 0.0F)));

    public static final ConfiguredCarver<?> SALT_RIFT_CARVER1 = CaveCarver.CAVE.configure(
            new CaveCarverConfig(0.7F,
                    BiasedToBottomHeightProvider.create(YOffset.fixed(32), YOffset.fixed(127), 8),
                    ConstantFloatProvider.create(0.7F),
                    YOffset.aboveBottom(1), false,
                    CarverDebugConfig.create(false, Blocks.CRIMSON_BUTTON.getDefaultState()),
                    ConstantFloatProvider.create(1.5F),
                    ConstantFloatProvider.create(1.0F),
                    ConstantFloatProvider.create(-0.7F)));


    public static final ConfiguredFeature<?, ?> SALT_WALLS = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    SBlocks.SALT_ROCK.getDefaultState(),
                    64))
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64))))
            .spreadHorizontally()
            .repeat(128);

    public SWorldGen(){
        RegistryKey<ConfiguredCarver<?>> saltRiftCarver = RegistryKey.of(Registry.CONFIGURED_CARVER_KEY,
                new Identifier("sizzle", "salt_rift"));
        Registry.register(BuiltinRegistries.CONFIGURED_CARVER, saltRiftCarver.getValue(), SALT_RIFT_CARVER);
        BiomeModifications.addCarver(BiomeSelectors.includeByKey(SBiomes.SALT_CAVERNS_KEY), GenerationStep.Carver.AIR, saltRiftCarver);

        RegistryKey<ConfiguredFeature<?, ?>> saltWalls = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("sizzle", "salt_walls"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, saltWalls.getValue(),  SALT_WALLS);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(SBiomes.SALT_CAVERNS_KEY), GenerationStep.Feature.UNDERGROUND_DECORATION, saltWalls);

        RegistryKey<ConfiguredCarver<?>> saltRiftCarver1 = RegistryKey.of(Registry.CONFIGURED_CARVER_KEY,
                new Identifier("sizzle", "salt_rift1"));
        Registry.register(BuiltinRegistries.CONFIGURED_CARVER, saltRiftCarver1.getValue(), SALT_RIFT_CARVER1);
        BiomeModifications.addCarver(BiomeSelectors.includeByKey(SBiomes.SALT_CAVERNS_KEY), GenerationStep.Carver.AIR, saltRiftCarver1);
    }
}
