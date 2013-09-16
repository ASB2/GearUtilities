package GU.blocks.containers.BlockConduitInterface;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.network.INetwork;
import GU.api.network.INetworkInterface;
import GU.api.power.IPowerMisc;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.network.UniversalConduitNetwork;

public class TileConduitInterface extends TileBase implements INetworkInterface {

    UniversalConduitNetwork network;
    boolean[] importing = new boolean[7];

    public TileConduitInterface() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new UniversalConduitNetwork();
    }

    public void updateEntity() {

        waitTimer.update();

        if(this.getNetwork() != null) {

            if(!this.getNetwork().getAvaliableConductors().contains(this)) {

                this.getNetwork().addConductor(worldObj, new Vector3(this));
            }
        }
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(importing[side]) {

            importing[side] = false;
            return;
        }
        else {

            importing[side] = true;
            return;
        }
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile instanceof IInventory) {

                    ((UniversalConduitNetwork)this.getNetwork()).addAvaliableInventory(new Vector3(tile));
                }

                if(tile instanceof IFluidHandler) {

                    ((UniversalConduitNetwork)this.getNetwork()).addAvaliableTank(new Vector3(tile));
                } 

                if(tile instanceof IPowerMisc) {

                    ((UniversalConduitNetwork)this.getNetwork()).postPowerRequest(new Vector3(tile));
                }
            }
        }
    }

    @Override
    public boolean setNetwork(INetwork network) {

        if(network instanceof UniversalConduitNetwork) {

            this.network = (UniversalConduitNetwork)network;
            return true;
        }
        return false;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }

    @Override
    public int[] getCoords() {

        return new int[]{xCoord, yCoord, zCoord};
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        for(int i = 0; i < importing.length; i++) {       

            importing[i] = tag.getBoolean("importing" + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        for(int i = 0; i < importing.length; i++) {       

            tag.setBoolean("importing" + i, importing[i]);
        }
    }
}
