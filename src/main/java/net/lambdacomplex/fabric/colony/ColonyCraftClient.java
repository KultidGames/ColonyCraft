package net.lambdacomplex.fabric.colony;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.lambdacomplex.fabric.colony.block.ModBlocks;
import net.lambdacomplex.fabric.colony.entity.ModEntities;
import net.minecraft.client.render.RenderLayer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lambdacomplex.fabric.colony.entity.client.NooseKnotEntityRenderer;

public class ColonyCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARLIC_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILD_GARLIC, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.NOOSE_ENTITY, NooseKnotEntityRenderer::new);
    }
}
