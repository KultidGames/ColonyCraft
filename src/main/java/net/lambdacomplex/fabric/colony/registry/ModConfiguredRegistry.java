package net.lambdacomplex.fabric.colony.registry;

import net.lambdacomplex.fabric.colony.ColonyCraft;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public enum ModConfiguredRegistry {

    PATCH_WILD_GARLIC("patch_wild_garlic"),
    PATCH_WILD_SWEETPOTATO("patch_wild_sweetpotato");

    private final Identifier featureIdentifier;
    private RegistryKey<ConfiguredFeature<?, ?>> ModConfiguredRegistryKey;
    private RegistryKey<PlacedFeature> featureRegistryKey;

    ModConfiguredRegistry(String featurePathName) {
        this.featureIdentifier = new Identifier(ColonyCraft.MODID, featurePathName);
    }

    public static void registerAll() {
        for (ModConfiguredRegistry value : values()) {
            value.ModConfiguredRegistryKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, value.featureIdentifier);
            value.featureRegistryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, value.featureIdentifier);
        }
    }

    public RegistryKey<ConfiguredFeature<? extends FeatureConfig, ?>> configKey() {
        return ModConfiguredRegistryKey;
    }

    public RegistryKey<PlacedFeature> key() {
        return featureRegistryKey;
    }

    public Identifier identifier() {
        return featureIdentifier;
    }

}