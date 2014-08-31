package GU.multiblock;

import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import GU.multiblock.construction.ConstructionManager;
import GU.multiblock.construction.TankConstructionManager;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockGrinder extends MultiBlockInventory {
    
    public MultiBlockGrinder(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        // TODO Auto-generated constructor stub
    }
    
    public MultiBlockGrinder(World world) {
        super(world);
        // TODO Auto-generated constructor stub
    }
    
    public Color4i getDefaultBlockColor() {
        
        return Color4i.RUST;
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new TankConstructionManager(world, this, positionRelativeTo, size);
    }
    
    @Override
    public void logicUpdate() {
        
        // final int distance = 100;
        //
        // for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
        //
        // int xMax = 0, yMax = 0, zMax = 0;
        // int xMin = 0, yMin = 0, zMin = 0;
        //
        // switch (direction) {
        //
        // case UP: {
        //
        // int xHalfed = (this.size.getX() + 1) / 2, zHalfed = (this.size.getZ()
        // + 1) / 2;
        //
        // xMin = this.getCore().getX();
        // yMin = this.getCore().getY() + 1;
        // zMin = this.getCore().getZ();
        //
        // for (int pos = 0; pos <= distance; pos++) {
        //
        // int modX = this.getCore().getX() - xHalfed, modY =
        // this.getCore().getY() + size.getY() + pos, modZ =
        // this.getCore().getZ() - zHalfed;
        //
        // Block block = world.getBlock(modX, modY, modZ);
        //
        // if (block == BlockRegistry.ELECTIS_POLYHEDRON) {
        //
        // xMax = xMin + size.getX();
        // yMax = yMin + pos + this.size.getY();
        // zMax = zMin + size.getZ();
        // break;
        // } else {
        //
        // // world.setBlock(modX, modY, modZ, Blocks.stone);
        // }
        // }
        // break;
        // }
        // }
        //
        // // for (int x = xMin; x < xMax; x++) {
        // //
        // // for (int y = yMin; y < yMax; y++) {
        // //
        // // for (int z = zMin; z < zMax; z++) {
        // //
        // // world.setBlock(x, y, z, Blocks.stone);
        // // // UtilBlock.breakAndAddToInventory(this.inventory,
        // // // world, x, y, z, true);
        // // }
        // // }
        // // }
        //
        // for (int x = xMax; x < xMin; x++) {
        //
        // for (int y = yMax; y < yMin; y++) {
        //
        // for (int z = zMax; z < zMin; z++) {
        //
        // world.setBlock(x, y, z, Blocks.stone);
        // // UtilBlock.breakAndAddToInventory(this.inventory,
        // // world, x, y, z, true);
        // }
        // }
        // }
        // }
    }
    
    @Override
    public void onSetSize() {
        
        if (inventory != null && inventory.getSizeInventory() == 0)
            inventory.setSizeInventory((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    public boolean startCreation() {
        
        if (size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2) {
            
            if (size.getX() + 1 % 2 != 0 && size.getY() + 1 % 2 != 0 && size.getZ() + 1 % 2 != 0) {
                
                return super.startCreation();
            }
        }
        return false;
    }
}
