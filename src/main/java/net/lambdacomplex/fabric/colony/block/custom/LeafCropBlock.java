package net.lambdacomplex.fabric.colony.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class LeafCropBlock extends Block implements Fertilizable {

    public static int MAX_AGE; //Maximum Age
    public static IntProperty AGE; //Current Age
    public static DirectionProperty FACING;
    public static Block GROWABLE;
    public LeafCropBlock(Settings settings, int mAge, Block soil) {
        super(settings);
        MAX_AGE = mAge;
        GROWABLE = soil;
        AGE = IntProperty.of("age", 0, MAX_AGE); // Define AGE property here
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0)); // Set initial age in the constructor
    }

    // CUSTOM CODE STARTS HERE

    public boolean hasRandomTicks(BlockState state) {
        return (Integer)state.get(AGE) < 2;
    } //CHANGE ME IF ISSUE

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextInt(5) == 0) {
            int i = (Integer) state.get(AGE);
            if (i < 2) {
                world.setBlockState(pos, (BlockState) state.with(AGE, i + 1), 2); //CHANGE ME IF ISSUE
            }
        }
    }



    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(Direction.UP));
        return world.getBlockState(pos.up()).isOf(GROWABLE);
    }


    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        return (BlockState)((BlockState)super.getPlacementState(ctx)).with(AGE, 4); //THIS MIGHT ALSO BE AN ISSUE
    }


    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.UP && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }





    // BELOW HERE ARE REQUIRED FOR IMPLEMENTS FERTILIZABLE
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return (Integer)state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, (BlockState)state.with(AGE, (Integer)state.get(AGE) + 1), 2); //CHANGE THIS TO MAX_AGE IF THERE ARE ERRORS!!
    }
}
