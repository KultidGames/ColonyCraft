package net.lambdacomplex.fabric.colony.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lambdacomplex.fabric.colony.ColonyCraft;
import net.lambdacomplex.fabric.colony.block.ModBlocks;
import net.lambdacomplex.fabric.colony.item.custom.NooseItem;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    //Name string must be lowercase, Add a Model and Texture in Assets

    //Item Registry Starts Here=============================]
    public static final Item ITEMICON = registerItems("itemicon", new Item(new FabricItemSettings()), ModItemGroup.COLONYGROUP);
    public static final Item VENISON = registerItems("venison", new Item(new FabricItemSettings().food(ModFoodComponents.VENISON)), ModItemGroup.COLONYGROUP);
    public static final Item GARLIC = registerItems("garlic", new AliasedBlockItem(ModBlocks.GARLIC_CROP, new FabricItemSettings().food(ModFoodComponents.GARLIC)), ModItemGroup.COLONYGROUP);
    public static final Item NOOSE = registerItems("noose", new NooseItem(new FabricItemSettings()), ModItemGroup.COLONYGROUP);

    //Item Registry Ends Here===============================]





    private static Item registerItems(String name, Item item, ItemGroup group){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(ColonyCraft.MODID, name), item);
    }

    public static void registerModItems(){
        ColonyCraft.LOGGER.debug("Registering Mod Items for " + ColonyCraft.MODID);
    }


}
