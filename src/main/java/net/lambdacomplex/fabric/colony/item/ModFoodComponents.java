package net.lambdacomplex.fabric.colony.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent VENISON = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).meat().build();
    public static final FoodComponent GARLIC = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).build();

}
