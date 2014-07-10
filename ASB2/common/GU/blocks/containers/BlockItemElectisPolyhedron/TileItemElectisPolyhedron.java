package GU.blocks.containers.BlockItemElectisPolyhedron;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilInventory;
import GU.blocks.containers.TileBase;
import GU.render.EnumInputIcon;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileItemElectisPolyhedron extends TileBase {
    
    Wait movementTimer;
    public EnumInputIcon state;
    
    boolean directional;
    
    public TileItemElectisPolyhedron() {
        
        movementTimer = new Wait(new MovementWait(), 5);
        state = EnumInputIcon.OUTPUT;
        directional = true;
    }
    
    @Override
    public void updateEntity() {
        
        movementTimer.update();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        directional = tag.getBoolean("directional");
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        tag.setBoolean("directional", directional);
        super.writeToNBT(tag);
    }
    
    private class MovementWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (state == EnumInputIcon.OUTPUT) {
                
                ForgeDirection direction = getOrientation().getOpposite();
                
                TileEntity modifyingTile = UtilDirection.translateDirectionToTile(worldObj, direction, xCoord, yCoord, zCoord);
                
                if (modifyingTile != null && modifyingTile instanceof IInventory) {
                    
                    IInventory found = null;
                    final int maxDistance = 8;
                    
                    if (directional) {
                        
                        for (ForgeDirection searchingDirection : ForgeDirection.VALID_DIRECTIONS) {
                            
                            if (searchingDirection != direction) {
                                
                                for (int currentDistance = 1; currentDistance <= maxDistance; currentDistance++) {
                                    
                                    Block block = worldObj.getBlock((searchingDirection.offsetX * currentDistance) + xCoord, (searchingDirection.offsetY * currentDistance) + yCoord, (searchingDirection.offsetZ * currentDistance) + zCoord);
                                    
                                    if (block == null) {
                                        
                                        continue;
                                    }
                                    
                                    TileEntity tile = worldObj.getTileEntity((searchingDirection.offsetX * currentDistance) + xCoord, (searchingDirection.offsetY * currentDistance) + yCoord, (searchingDirection.offsetZ * currentDistance) + zCoord);
                                    
                                    if (tile != null) {
                                        
                                        if (tile instanceof IInventory) {
                                            
                                            found = (IInventory) tile;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        
                    }
                    
                    if (modifyingTile != null && found != null) {
                        UtilInventory.moveEntireInventory((IInventory) modifyingTile, found);
                    }
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}
