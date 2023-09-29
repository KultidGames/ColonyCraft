package net.lambdacomplex.fabric.colony.block.custom;

import net.lambdacomplex.fabric.colony.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class GarlicCropBlock extends CropBlock {

    public static final IntProperty AGE = IntProperty.of("age", 0, 6);

    public GarlicCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.GARLIC;
    }

    @Override
    public int getMaxAge(){
        return 6;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(AGE);
    }

}
