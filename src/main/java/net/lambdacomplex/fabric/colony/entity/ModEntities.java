package net.lambdacomplex.fabric.colony.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.lambdacomplex.fabric.colony.ColonyCraft;
import net.lambdacomplex.fabric.colony.entity.decoration.NooseKnotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<NooseKnotEntity> NOOSE_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier(ColonyCraft.MODID, "noose_entity"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, NooseKnotEntity::new).build());


}
