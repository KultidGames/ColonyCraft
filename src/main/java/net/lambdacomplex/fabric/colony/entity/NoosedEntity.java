package net.lambdacomplex.fabric.colony.entity;

import net.lambdacomplex.fabric.colony.entity.decoration.NooseKnotEntity;
import net.lambdacomplex.fabric.colony.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class NoosedEntity extends MobEntity {
    @Nullable
    private NbtCompound nooseNbt;
    private Entity holdingEntity;

    protected NoosedEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.updateNoose();
            if (this.age % 5 == 0) {
                this.updateGoalControls();
            }
        }
    }
 /*   public boolean canBeNoosedBy(PlayerEntity player) {
        return !this.isNoosed() && !(this instanceof Monster);
    }
    public boolean isNoosed() {
        return this.holdingEntity != null;
    } */
    protected void updateNoose() {
        if (this.nooseNbt != null) {
            this.readNooseNbt();
        }

        if (this.holdingEntity != null) {
            if (!this.isAlive() || !this.holdingEntity.isAlive()) {
                this.detachNoose(true, true);
            }

        }
    }

    public void detachNoose(boolean sendPacket, boolean dropItem) {
        if (this.holdingEntity != null) {
            this.holdingEntity = null;
            this.nooseNbt = null;
            if (!this.world.isClient && dropItem) {
                this.dropItem(Items.LEAD);
            }

            if (!this.world.isClient && sendPacket && this.world instanceof ServerWorld) {
                ((ServerWorld)this.world).getChunkManager().sendToOtherNearbyPlayers(this, new EntityAttachS2CPacket(this, null));
            }
        }
    }

    public void attachNoose(Entity entity, boolean sendPacket) {
        this.holdingEntity = entity;
        this.nooseNbt = null;
        if (!this.world.isClient && sendPacket && this.world instanceof ServerWorld) {
            ((ServerWorld)this.world).getChunkManager().sendToOtherNearbyPlayers(this, new EntityAttachS2CPacket(this, this.holdingEntity));
        }
        if (this.hasVehicle()) {
            this.stopRiding();
        }
    }
    private void readNooseNbt() {
        if (this.nooseNbt != null && this.world instanceof ServerWorld) {
            if (this.nooseNbt.containsUuid("UUID")) {
                UUID uUID = this.nooseNbt.getUuid("UUID");
                Entity entity = ((ServerWorld) this.world).getEntity(uUID);
                if (entity != null) {
                    this.attachNoose(entity, true);
                    return;
                }
            } else if (this.nooseNbt.contains("X", 99) && this.nooseNbt.contains("Y", 99) && this.nooseNbt.contains("Z", 99)) {
                BlockPos blockPos = NbtHelper.toBlockPos(this.nooseNbt);
                this.attachNoose(NooseKnotEntity.getOrCreate(this.world, blockPos), true);
                return;
            }

            if (this.age > 100) {
                this.dropItem(ModItems.NOOSE);
                this.nooseNbt = null;
            }
        }
    }
    @Override
    protected void removeFromDimension() {
        super.removeFromDimension();
        this.detachNoose(true, false);
        this.getItemsEquipped().forEach((stack) -> stack.setCount(0));
    }


}
