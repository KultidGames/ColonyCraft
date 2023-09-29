package net.lambdacomplex.fabric.colony.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lambdacomplex.fabric.colony.ColonyCraft;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    //Change Icon Eventually
    public static final ItemGroup COLONYGROUP = FabricItemGroup.builder(new Identifier(ColonyCraft.MODID, "colony_group")).icon(() -> new ItemStack(ModItems.VENISON)).build();

}
