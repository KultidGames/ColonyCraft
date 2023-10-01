package net.lambdacomplex.fabric.colony;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.lambdacomplex.fabric.colony.block.ModBlocks;
import net.lambdacomplex.fabric.colony.entity.ModEntities;
import net.lambdacomplex.fabric.colony.item.ModItems;
import net.lambdacomplex.fabric.colony.registry.ModConfiguredRegistry;
import net.lambdacomplex.fabric.colony.registry.PlacementModifiersRegistry;
import net.lambdacomplex.fabric.colony.entity.decoration.NooseKnotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

public class ColonyCraft implements ModInitializer {

	public static String MODID = "colonycraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);


	public static String getMODID(){
		return MODID;
	}



	@Override
	public void onInitialize() {
	//	ModConfiguredFeatures.registerConfiguredFeatures();

		//Everything else after

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerAll();
		ModConfiguredRegistry.registerAll();
		PlacementModifiersRegistry.registerAll();
		registerLootTables();
	//	FabricDefaultAttributeRegistry.register(ModEntities.NOOSE_ENTITY, NooseKnotEntity.);


		registerBiomeModifications();
		LOGGER.info(MODID + " successfully loaded!!");
	}



	protected void registerLootTables(){
		//TODO Remove Later
		//ADD CUSTOM MEATS & SECONDARY DROPS HERE-- THIS IS JUST A TEST
		Set<Identifier> scavengingEntityIdList = Set.of(
		EntityType.FOX.getLootTableId());




		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			Identifier injectId = new Identifier(ColonyCraft.MODID, "inject/" + id.getPath());
			if (scavengingEntityIdList.contains(id)) {
				tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(injectId)).build());
			}
		});
	}

	protected void registerBiomeModifications() {
			BiomeModifications.addFeature(context -> context.getBiome().getTemperature() > .0f && context.getBiome().getTemperature() < .3f,
					GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredRegistry.PATCH_WILD_GARLIC.key());

		BiomeModifications.addFeature(context -> context.getBiome().getTemperature() > .0f && context.getBiome().getTemperature() < .3f,
				GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredRegistry.PATCH_WILD_SWEETPOTATO.key());
	}





}
