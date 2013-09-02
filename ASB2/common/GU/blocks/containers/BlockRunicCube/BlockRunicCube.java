package GU.blocks.containers.BlockRunicCube;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilRender;
import GU.api.runes.IRuneItem;
import GU.blocks.containers.ContainerBase;
import GU.entity.FXBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRunicCube extends ContainerBase {

    public BlockRunicCube(int id, Material material) {
        super(id, material);
        this.useStandardRendering  = false;
        this.setTickRandomly(true);
        this.registerTile(TileRunicCube.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TileRunicCube rune = (TileRunicCube)world.getBlockTileEntity(x, y, z);

        if(!player.isSneaking()) {

            if(player.inventory.getCurrentItem() != null) {

                if(player.inventory.getCurrentItem().getItem() instanceof IRuneItem) {

                    if(UtilInventory.addItemStackToInventory(rune, player.inventory.getCurrentItem(), false)) {

                        if(UtilInventory.consumeItemStack(player.inventory, player.inventory.getCurrentItem(), 1)) {

                            ItemStack copy = player.inventory.getCurrentItem().copy();

                            copy.stackSize = 1;
                            return UtilInventory.addItemStackToInventory(rune, copy, true);
                        }
                    }
                }
            }
        }
        else {

            if(!rune.getRunes().isEmpty()) {

                return true;
            }
        }
        return false;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        
        return 5;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {

        for(int i = 0; i < 21; i++) {

            float xF = x + rand.nextFloat();
            float yF = y + rand.nextFloat();
            float zF = z + rand.nextFloat();

            float motion = -0.5f + rand.nextFloat();

            //            world.spawnParticle("portal", xF, yF, zF, motion, motion, motion);

            UtilRender.renderFX(new FXBase(world, xF, yF, zF, motion, motion, motion, this.getIcon(0, 0), Color.WHITE));
        }

    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {

        TileRunicCube rune = (TileRunicCube)world.getBlockTileEntity(x, y, z);

        for(ItemStack stack : rune.getRunes()) {

            if(((IRuneItem)stack.getItem()).shouldUpdate(world, rune, stack, x, y, z))
                ((IRuneItem)stack.getItem()).randomUpdate(world, rune, stack, x, y, z);
        }
    }

    @Override
    public int getRenderType() {

        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileRunicCube();
    }
}
