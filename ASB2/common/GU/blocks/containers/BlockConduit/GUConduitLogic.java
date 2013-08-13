package GU.blocks.containers.BlockConduit;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;
import GU.api.color.EnumColor;
import GU.api.conduit.ConduitLogic;
import GU.api.conduit.EnumConduitType;
import GU.api.power.PowerProvider;

public class GUConduitLogic extends ConduitLogic {

    public GUConduitLogic(TileEntity tile, EnumColor color, ItemStack[] tileItemStacks, FluidTank fluidTank, PowerProvider powerProvider) {
        super(tile, color, tileItemStacks, fluidTank, powerProvider);
    }

    @Override
    public EnumSet<EnumConduitType> getConductorType() {

        return EnumSet.of(EnumConduitType.ITEM, EnumConduitType.GUU, EnumConduitType.LIQUID);
    }
    
    @Override
    public void updateConduit() {
        
        
    }
}
