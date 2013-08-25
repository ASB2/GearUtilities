package GU.blocks;

import GU.info.Reference;
import GU.utils.UtilRender;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockConnectedGlass extends BlockBase {

    private Icon[] icons = new Icon[16];
    private String folder = ":glassConnected";

    public BlockConnectedGlass(int id, Material material) {
        super(id, material);
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }
    
    @Override
    public boolean isOpaqueCube() {

        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {

        return icons[0];
    }

    @Override
    public void registerIcons(IconRegister iconRegistry) {

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
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {

        return UtilRender.renderConnectedTexture(blockAccess, icons, this.blockID, x, y, z, side);
    }
}
