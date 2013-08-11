package GU.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.blocks.containers.BlockTestTank.TileTestTank;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class TestTankPacket extends GUPacketBase {

    int x;
    int y;
    int z;
    int liquidId;
    int liquidAmount;
    boolean itWorked = false;
    
    public TestTankPacket(int x, int y, int z, int liquidId, int liquidAmount) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.liquidId = liquidId;
        this.liquidAmount = liquidAmount;  
    }

    public TestTankPacket() {

    }

    @Override
    protected void write(ByteArrayDataOutput out) {

        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
        out.writeInt(liquidId);
        out.writeInt(liquidAmount);
    }

    @Override
    protected void read(ByteArrayDataInput in) throws ProtocolException {

        try {
            x = in.readInt();
            y = in.readInt();
            z = in.readInt();
            liquidId = in.readInt();
            liquidAmount = in.readInt();
            itWorked = true;
        }
        catch (IllegalStateException e) {

            //e.printStackTrace();
            return;
        }
    }

    @Override
    protected void execute(EntityPlayer player, Side side) throws ProtocolException {

        if(itWorked && side == Side.CLIENT) {

            if(player.worldObj.getBlockTileEntity(x, y, z) != null && player.worldObj.getBlockTileEntity(x, y, z) instanceof TileTestTank) {

                TileTestTank tank = (TileTestTank)player.worldObj.getBlockTileEntity(x, y, z);

                if(liquidId == 0) {

                    tank.fluidTank.setFluid(null);
                }
                tank.fluidTank.setFluid(new FluidStack(FluidRegistry.getFluid(liquidId), liquidAmount));
                player.worldObj.markBlockForRenderUpdate(x, y, z);
            }
        }
    }    
}
