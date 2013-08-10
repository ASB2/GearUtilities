package GU.blocks.containers.BlockConduit;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.conduit.ConduitLogic;
import GU.api.conduit.IConduitNetwork;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.utils.UtilDirection;

public class TileConduit extends TileBase implements IConduitNetwork {

    ConduitLogic conduitInfo;
    
    public TileConduit() {

        this.tileItemStacks = new ItemStack[1];
        
        //conduitInfo = new ConduitLogic(color, new ItemStack[0], null, null);
        waitTimer = new Wait(10, this, 0);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
        }
    }

    @Override
    public ConduitLogic getConduitInfo() {
        
        return conduitInfo;
    }
    
    public boolean renderDirection(ForgeDirection direction) {

        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

        if(tile != null) {

            if(tile instanceof TileConduit) {

                return true;
            }

            if(tile instanceof ISidedInventory && ((ISidedInventory)tile).getSizeInventory() > 0) {

                return true;
            }

            if(tile instanceof IInventory && ((IInventory)tile).getSizeInventory() > 0) {

                return true;
            }
            
            if(tile instanceof IFluidHandler) {

                return true;
            }
        }
        return false;
    }

    @Override
    public void trigger(int id) {
    }
}
