package GU.blocks.containers.BlockGeothermalGenerator;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.GearUtilities;
import GU.blocks.containers.ContainerBase;
import GU.info.Gui;
import GU.info.Reference;
import GU.utils.UtilDirection;

public class BlockGeothermalGenerator extends ContainerBase {

    Icon front;
    Icon top;

    public BlockGeothermalGenerator(int id, Material material) {
        super(id, material);
        this.registerTile(TileGeothermalGenerator.class);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        front = iconRegister.registerIcon(Reference.MODDID + ":BlockGeothermalGeneratorFront");
        top = iconRegister.registerIcon(Reference.MODDID + ":BlockGeothermalGeneratorTop");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {

        int roatation = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        //South 0 = rotation
        //West 1 = rotation
        //North 2 = rotation
        //East 3 = rotation 

        if (roatation == 0) {

            world.setBlockMetadataWithNotify(x, y, z, UtilDirection.translateDirectionToNumber(ForgeDirection.NORTH), 2);
        }

        if (roatation == 1) {

            world.setBlockMetadataWithNotify(x, y, z, UtilDirection.translateDirectionToNumber(ForgeDirection.EAST), 2);
        }

        if (roatation == 2) {

            world.setBlockMetadataWithNotify(x, y, z, UtilDirection.translateDirectionToNumber(ForgeDirection.SOUTH), 2);
        }

        if (roatation == 3) {

            world.setBlockMetadataWithNotify(x, y, z, UtilDirection.translateDirectionToNumber(ForgeDirection.WEST), 2);
        }
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        if(side == 1) {

            return top;
        }

        if(side == metadata ) {

            return front;
        }
        return super.getIcon(side, metadata);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) 
    {
        if(!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.GEOTHERMAL_GENERATOR, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileGeothermalGenerator();
    }

}
