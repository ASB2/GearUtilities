package GU.blocks.containers.BlockCanvas;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.ContainerBase;
import GU.color.EnumVinillaColor;
import GU.info.Reference;
import GU.utils.UtilDirection;
import GU.color.*;

public class BlockCanvas extends ContainerBase {

    public Icon inner;
    public Icon[] icons = new Icon[16];
    private String folder = ":canvasConnected";

    public BlockCanvas(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileCanvas.class);
    }

    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
    
        int id = UtilDirection.translateDirectionToBlockId(world, ForgeDirection.getOrientation(side), x, y, z);
        
        if(id == 0 || (!Block.blocksList[id].isOpaqueCube() && id != this.blockID)) {
        
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }

    @Override
    public boolean isBlockNormalCube(World world, int x, int y, int z) {

        return true;
    }

    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof IColorable) {

            if(player.getHeldItem() != null && EnumVinillaColor.isItemDye(player.getHeldItem())) {

                ((IColorable)tile).setColor(EnumVinillaColor.getRGBValue(EnumVinillaColor.getItemColorValue(player.getHeldItem())), ForgeDirection.getOrientation(side));
            }
        }
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
