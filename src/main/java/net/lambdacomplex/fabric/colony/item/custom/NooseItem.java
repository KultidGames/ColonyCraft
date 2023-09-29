package net.lambdacomplex.fabric.colony.item.custom;

import net.lambdacomplex.fabric.colony.entity.decoration.NooseKnotEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import java.util.List;
import net.minecraft.text.Text;

public class NooseItem extends Item {
    public NooseItem(Settings settings) {super(settings);}

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity playerEntity = null;
        if (blockState.isIn(BlockTags.FENCES)) {
            playerEntity = context.getPlayer();
            if (!world.isClient && playerEntity != null) {
                playerEntity.sendMessage(Text.literal("bye"));
                return attachHeldMobsToBlock(playerEntity, world, blockPos);
            }

            return ActionResult.success(world.isClient);
        } else {
        //    playerEntity.sendMessage(Text.literal("hi")); // Send a chat message to the player
            return ActionResult.PASS;
        }
    }

    public static ActionResult attachHeldMobsToBlock(PlayerEntity player, World world, BlockPos pos) {
        player.sendMessage(Text.literal("attachHeldMobsToBlock called."), false); // Debug message
        NooseKnotEntity leashKnotEntity = null;
        boolean bl = false;
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        List<MobEntity> list = world.getNonSpectatingEntities(MobEntity.class, new Box((double)i - 7.0, (double)j - 7.0, (double)k - 7.0, (double)i + 7.0, (double)j + 7.0, (double)k + 7.0));
        for (MobEntity mobEntity : list) {
            if (mobEntity.getHoldingEntity() == player) {
                if (leashKnotEntity == null) {
                    leashKnotEntity = (NooseKnotEntity) NooseKnotEntity.getOrCreate(world, pos);
                    leashKnotEntity.onPlace();
                    player.sendMessage(Text.literal("LeashKnotEntity created."), false); // Debug message
                }

                mobEntity.attachLeash(leashKnotEntity, true);
                bl = true;
                player.sendMessage(Text.literal("Leash attached to mob."), false); // Debug message
            }
        }

        if (bl) {
            world.emitGameEvent(GameEvent.BLOCK_ATTACH, pos, GameEvent.Emitter.of(player));
            player.sendMessage(Text.literal("Block attached to mob."), false); // Debug message
        } else {
            player.sendMessage(Text.literal("No mobs found."), false); // Debug message
        }

        return bl ? ActionResult.SUCCESS : ActionResult.PASS;
    }


}

