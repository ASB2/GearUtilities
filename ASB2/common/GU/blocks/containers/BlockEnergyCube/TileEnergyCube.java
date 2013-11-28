package GU.blocks.containers.BlockEnergyCube;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerHelper;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileEnergyCube extends TileBase implements IPowerMisc {

    boolean[] importing = new boolean[ForgeDirection.values().length];

    public TileEnergyCube() {

        this.powerProvider = new PowerProvider(4000, PowerClass.LOW, State.OTHER);
        this.waitTimer = new Wait(10, this, 0);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(!isSneaking) {

            if(importing[side]) {

                importing[side] = false;
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

        this.updateClients();

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null && tile instanceof IPowerMisc) {

                if(!importing[direction.ordinal()]) {
                    
                    PowerHelper.moveEnergy(this.getPowerProvider(), ((IPowerMisc) tile).getPowerProvider(), direction, direction.getOpposite(), true);
                }
                else {
                    
                    PowerHelper.moveEnergy(((IPowerMisc) tile).getPowerProvider(), this.getPowerProvider(), direction, direction.getOpposite(), true);
                }
            }
        }
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
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
}
