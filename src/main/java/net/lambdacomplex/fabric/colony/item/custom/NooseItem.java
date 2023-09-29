package net.lambdacomplex.fabric.colony.item.custom;

import net.lambdacomplex.fabric.colony.entity.NoosedEntity;
import net.lambdacomplex.fabric.colony.entity.decoration.NooseKnotEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NooseItem extends Item {
    public NooseItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        World world = player.getWorld();
        NooseKnotEntity nooseKnotEntity = new NooseKnotEntity(player.getWorld(), player.getBlockPos());
        if (!world.isClient) {
            ((NoosedEntity) entity).attachNoose(nooseKnotEntity, true);
            player.sendMessage(Text.literal("attached!"));
        }

        return ActionResult.SUCCESS;
    }
}

