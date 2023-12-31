package net.lambdacomplex.fabric.colony.block;

import com.nhoryzon.mc.farmersdelight.block.WildCropBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lambdacomplex.fabric.colony.ColonyCraft;
import net.lambdacomplex.fabric.colony.block.custom.GarlicCropBlock;
import net.lambdacomplex.fabric.colony.block.custom.LeafCropBlock;
import net.lambdacomplex.fabric.colony.block.custom.SweetPotatoCropBlock;
import net.lambdacomplex.fabric.colony.item.ModItemGroup;
import net.lambdacomplex.fabric.colony.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //IF TRANSPARENT, DONT FORGET LINE IN COLONYCRAFTCLIENT!!!!
    //Blocks without Items require: public static final here, blockstates (if you want it to drop (?)), models, texture
    //Wild Crops require: public static final here, ModConfiguredRegistry, Block/Item Models, Data in Loot_Tables and World Gen, ColonyCraft Line too
    //Crate Crops require: public static final here, Block/Item Models, Data in Loot_Tables, Mineable in Data, Recipes, lang, blockstates


    //Block Registry Starts Here=============================]

    public static final Block GARLIC_CROP = registerBlockWithoutItem("garliccrop", new GarlicCropBlock(FabricBlockSettings.copy(Blocks.CARROTS)));
    public static final Block WILD_GARLIC = registerBlock("wildgarlic", new WildCropBlock(), ModItemGroup.COLONYGROUP);
    public static final Block CRATE_GARLIC = registerBlock("crategarlic", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).hardness(2f).resistance(3f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.COLONYGROUP);

    public static final Block SWEETPOTATO_CROP = registerBlockWithoutItem("sweetpotatocrop", new SweetPotatoCropBlock(FabricBlockSettings.copy(Blocks.POTATOES)));
    public static final Block WILD_SWEETPOTATO = registerBlock("wildsweetpotato", new WildCropBlock(), ModItemGroup.COLONYGROUP);
    public static final Block CRATE_SWEETPOTATO = registerBlock("cratesweetpotato", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).hardness(2f).resistance(3f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.COLONYGROUP);

    public static final Block APPLE_CROP = registerBlockWithoutItem("applecrop", new LeafCropBlock(FabricBlockSettings.copy(Blocks.COCOA), 2, Blocks.SPRUCE_LEAVES));
    //Block Registry Ends Here===============================]


    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(ColonyCraft.MODID, name),block);
    }

    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(ColonyCraft.MODID, name),block);
    }



    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(block));
        return Registry.register(Registries.ITEM, new Identifier(ColonyCraft.MODID, name), new BlockItem(block, new FabricItemSettings()));
    }



    public static void registerModBlocks(){
        ColonyCraft.LOGGER.debug("Registering ModBlocks for " + ColonyCraft.MODID);
    }

}
