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

    public BlockCanvas(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileCanvas.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        inner = iconRegister.registerIcon(Reference.MODDID
                + ":BlockCanvasOutline");
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
