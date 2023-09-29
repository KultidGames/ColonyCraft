package net.lambdacomplex.fabric.colony.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lambdacomplex.fabric.colony.entity.decoration.NooseKnotEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LeashKnotEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NooseKnotEntityRenderer extends EntityRenderer<NooseKnotEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/lead_knot.png");
    private final NooseKnotEntityModel<NooseKnotEntity> model;

    public NooseKnotEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new NooseKnotEntityModel(context.getPart(EntityModelLayers.LEASH_KNOT));
    }

    public void render(NooseKnotEntity leashKnotEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.setAngles(leashKnotEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(leashKnotEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(NooseKnotEntity entity) {
        return null;
    }

    public Identifier getTexture(LeashKnotEntity leashKnotEntity) {
        return TEXTURE;
    }
}
