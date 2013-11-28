package GU.blocks.containers.BlockGyro.BlockSolarGyro;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import ASB2.utils.UtilRender;
import GU.blocks.containers.ContainerBase;
import GU.entity.FXBase;
import GU.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSolarGyro extends ContainerBase {

    public static Icon particle;

    public BlockSolarGyro(int id, Material material) {
        super(id, material);

        this.registerTile(TileSolarGyro.class);
        this.useStandardRendering = false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        particle = iconRegister.registerIcon(Reference.MODDID + ":GearBlock");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldObj, int xCoord, int yCoord, int zCoord, Random par5Random) {

        if(worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && worldObj.isDaytime()) {

            UtilRender.renderFX(new FXBase(worldObj, xCoord + .5, yCoord + .5, zCoord + .5, worldObj.rand.nextFloat(), worldObj.rand.nextFloat(), worldObj.rand.nextFloat(), 20 * 5, Block.bedrock.getIcon(0, 0), Color.WHITE));
            UtilRender.renderFX(new FXBase(worldObj, xCoord + .5, yCoord + .5, zCoord + .5, worldObj.rand.nextFloat(), worldObj.rand.nextFloat(), worldObj.rand.nextFloat(), 20 * 5, Block.bedrock.getIcon(0, 0), Color.WHITE));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileSolarGyro();
    }
}
