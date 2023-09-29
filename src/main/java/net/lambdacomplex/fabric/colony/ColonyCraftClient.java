package net.lambdacomplex.fabric.colony;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.lambdacomplex.fabric.colony.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class ColonyCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARLIC_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILD_GARLIC, RenderLayer.getCutout());
    }
}
