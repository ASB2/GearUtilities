package GU.blocks.containers.BlockCamoBlock;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;

public class GuiCamoBlock extends GuiBase {

    @SuppressWarnings("unused")
    private TileCamoBlock tileEntity;

    public GuiCamoBlock(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerCamoBlock(inventory, (TileCamoBlock) tile));

        tileEntity = (TileCamoBlock) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
    }
}
