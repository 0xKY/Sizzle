package me.kaloyankys.sizzle.init;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class SBiomes {

    public static final RegistryKey<Biome> SALT_CAVERNS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("sizzle", "salt_caverns"));
    private static final Biome SALT_CAVERNS = createSaltCaverns();

    private static Biome createSaltCaverns() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        GenerationSettings.Builder generationSettings = (new GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        generationSettings.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addClayOre(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addDripstone(generationSettings);

        return (new net.minecraft.world.biome.Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.UNDERGROUND)
                .depth(0.5F)
                .scale(0.5F)
                .temperature(0.5F)
                .downfall(0.5F)
                .effects((new net.minecraft.world.biome.BiomeEffects.Builder()).
                        waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(12638463)
                        .build()).spawnSettings(builder.build()).generationSettings(generationSettings.build()).build();
    }
    public SBiomes() {
        Registry.register(BuiltinRegistries.BIOME, SALT_CAVERNS_KEY.getValue(), SALT_CAVERNS);
        OverworldBiomes.addContinentalBiome(SALT_CAVERNS_KEY, OverworldClimate.TEMPERATE, 2000D);
    }
}
