package GU.blocks.containers.BlockCanvas;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockCanvas extends ContainerBase {

    public Icon inner;
    public Icon[] icons = new Icon[16];
    private String folder = ":canvasConnected";

    public BlockCanvas(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileCanvas.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegistry) {
        super.registerIcons(iconRegistry);

        inner = iconRegistry.registerIcon(Reference.MODDID + ":BlockCanvasOutline");

        icons[0] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connectedRegular");
        icons[1] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_d");
        icons[2] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_u");
        icons[3] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_l");
        icons[4] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_r");
        icons[5] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_h");
        icons[6] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_v");
        icons[7] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_dl");
        icons[8] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_dr");
        icons[9] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_ul");
        icons[10] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_ur");
        icons[11] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_d");
        icons[12] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_u");
        icons[13] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_l");
        icons[14] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_r");
        icons[15] = iconRegistry.registerIcon(Reference.MODDID + folder + "/blank");
    }

    @Override
    public int getRenderType() {

        return CanvasRenderer.canvasRenderID;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileCanvas();
    }
}
