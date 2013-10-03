package GU.blocks.containers.BlockConduitInterface;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.network.IConductor;
import GU.api.network.INetwork;
import GU.api.network.INetworkInterface;
import GU.api.network.UniversalConduitNetwork;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileConduitInterface extends TileBase implements INetworkInterface {

    INetwork network;
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

        if(!isSneaking) {

            if(importing[side]) {

                importing[side] = false;

                if(this.getNetwork() != null) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, ForgeDirection.getOrientation(side));

                    if(tile != null) {

                        if(tile instanceof IInventory) {

                            this.getNetwork().removeAvaliableInventory(new Vector3(tile));
                        }

                        if(tile instanceof IFluidHandler) {

                            this.getNetwork().removeAvaliableTank(new Vector3(tile));
                        }

                        if(tile instanceof IPowerMisc) {

                            this.getNetwork().removePowerRequest(new Vector3(tile));
                        }
                    }
                }
                return;
            }
            else {

                importing[side] = true;
                return;
            }
        }
        else {

            side = ForgeDirection.getOrientation(side).getOpposite().ordinal();

            if(importing[side]) {

                importing[side] = false;

                if(this.getNetwork() != null) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, ForgeDirection.getOrientation(side));

                    if(tile != null) {

                        if(tile instanceof IInventory) {

                            this.getNetwork().removeAvaliableInventory(new Vector3(tile));
                        }

                        if(tile instanceof IFluidHandler) {

                            this.getNetwork().removeAvaliableTank(new Vector3(tile));
                        }

                        if(tile instanceof IPowerMisc) {

                            this.getNetwork().removePowerRequest(new Vector3(tile));
                        }
                    }
                }
                return;
            }
            else {

                importing[side] = true;
                return;
            }
        }
    }

    @Override
    public void trigger(int id) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null ) {

                if(tile instanceof IConductor) {

                    IConductor conduit = (IConductor)tile;

                    if(conduit.getNetwork() != null) {

                        if(conduit.getNetwork() != this.getNetwork()) {

                            conduit.getNetwork().mergeNetworks(worldObj, this.getNetwork().getAvaliableConductors());

                            for(Vector3 vector : conduit.getNetwork().getAvaliableConductors()) {

                                if(vector != null && vector.getTileEntity(worldObj) != null && vector.getTileEntity(worldObj) instanceof IConductor) {

                                    ((IConductor)vector.getTileEntity(worldObj)).setNetwork(this.getNetwork());
                                }
                            } 
                        }
                        else {

                            conduit.setNetwork(this.getNetwork());
                        }
                    }
                    else {

                        conduit.setNetwork(this.getNetwork());
                    }
                }
            }
        }

        updateClients();

        if(this.getNetwork() != null && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile instanceof IInventory) {

                    if(importing[direction.ordinal()]) {

                        this.getNetwork().addAvaliableInventory(new Vector3(this));
                    }
                    else {

                        for(Vector3 vector : this.getNetwork().getAvaliableInventorys()) {

                            if(vector.getTileEntity(worldObj) != null && vector.getTileEntity(worldObj) instanceof IConductor) {

                                for(ForgeDirection avaliableTileDirections: ForgeDirection.VALID_DIRECTIONS) {

                                    for(TileEntity avaliableTile : ((INetworkInterface)vector.getTileEntity(worldObj)).getAvaliableTileEntities(avaliableTileDirections)) {

                                        if(avaliableTile != null && avaliableTile instanceof IInventory) {

                                            if(avaliableTile != tile) {

                                                if(tile instanceof ISidedInventory) {

                                                    if(avaliableTile instanceof ISidedInventory) {

                                                        UtilInventory.moveEntireISidedInventory((ISidedInventory)avaliableTile, direction, avaliableTileDirections.getOpposite(), (ISidedInventory)tile);
                                                    }
                                                    else {

                                                        UtilInventory.moveEntireISidedInventory((IInventory)avaliableTile, direction, (ISidedInventory)tile);
                                                    }
                                                }
                                                else if(avaliableTile instanceof ISidedInventory) {

                                                    UtilInventory.moveEntireISidedInventory((ISidedInventory)avaliableTile, direction, (IInventory)tile);
                                                }
                                                else {

                                                    UtilInventory.moveEntireInventory((IInventory)avaliableTile, (IInventory)tile);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(tile instanceof IFluidHandler) {

                    if(importing[direction.ordinal()]) {

                        this.getNetwork().addAvaliableTank(new Vector3(this));
                    }
                    else {

                        for(Vector3 vector : this.getNetwork().getAvaliableTanks()) {

                            if(vector.getTileEntity(worldObj) != null && vector.getTileEntity(worldObj) instanceof IConductor) {

                                for(TileEntity avaliableTile : ((INetworkInterface)vector.getTileEntity(worldObj)).getAvaliableTileEntities(direction.getOpposite())) {

                                    if(avaliableTile != null && avaliableTile instanceof IFluidHandler) {

                                        UtilFluid.moveFluid((IFluidHandler)avaliableTile, direction, (IFluidHandler)tile, true);
                                    }
                                }
                            }
                        }
                    }
                }

                if(tile instanceof IPowerMisc) {

                    if(importing[direction.ordinal()]) {

                        this.getNetwork().addPowerRequest(new Vector3(this));
                    }
                    else {

                        for(Vector3 vector : this.getNetwork().getPowerRequests()) {

                            if(vector.getTileEntity(worldObj) != null && vector.getTileEntity(worldObj) instanceof IConductor) {

                                for(TileEntity avaliableTile : ((INetworkInterface)vector.getTileEntity(worldObj)).getAvaliableTileEntities(direction.getOpposite())) {

                                    if(avaliableTile != null && avaliableTile instanceof IPowerMisc) {

                                        PowerHelper.moveEnergy((IPowerMisc)avaliableTile, (IPowerMisc)tile, direction, true);
                                    }
                                }
                            }
                        }
                    }
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

            importing[i] = tag.getBoolean("importing " + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        for(int i = 0; i < importing.length; i++) {       

            tag.setBoolean("importing " + i, importing[i]);
        }
    }

    @Override
    public TileEntity[] getAvaliableTileEntities(ForgeDirection direction) {

        TileEntity[] tileList = new TileEntity[7];

        if(this.importing[direction.ordinal()]) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null && (tile instanceof IInventory || tile instanceof IFluidHandler || tile instanceof IPowerMisc)) {

                tileList[direction.ordinal()] = tile;
            }
        }
        return tileList;
    }
}
