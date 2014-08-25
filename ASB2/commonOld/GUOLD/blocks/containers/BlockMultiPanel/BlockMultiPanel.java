package GUOLD.blocks.containers.BlockMultiPanel;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilItemStack;
import GUOLD.GearUtilities;
import GUOLD.blocks.containers.ContainerBase;
import GUOLD.info.Gui;

public class BlockMultiPanel extends ContainerBase {
    
    public BlockMultiPanel(Material material) {
        super(material);
        
        this.useStandardRendering = false;
        this.registerTile(TileMultiPanel.class);
        setLightOpacity(0);
        maxHeight = .65F;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        
        ArrayList<ItemStack> array = new ArrayList<ItemStack>();
        
        return array;
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileMultiPanel) {
            
            ItemStack stack = new ItemStack(this);
            UtilItemStack.setNBTTagInt(stack, "mode", ((TileMultiPanel) tile).getMode());
            return stack;
        }
        return new ItemStack(this);
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems) {
        
        ItemStack stack = new ItemStack(this);
        
        UtilItemStack.setNBTTagInt(stack, "mode", 0);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 1);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 2);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 3);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 4);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 5);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 6);
        subItems.add(stack.copy());
        
        UtilItemStack.setNBTTagInt(stack, "mode", 7);
        subItems.add(stack.copy());
    }
    
    @Override
    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
        
        return ForgeDirection.VALID_DIRECTIONS;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) {
        
        if (!player.isSneaking()) {
            
            player.openGui(GearUtilities.instance, Gui.MULTI_PANEL, world, x, y, z);
            return true;
        }
        else {
            
            // try {
            //
            // File outputfile = new File("Noise.png");
            // outputfile.createNewFile();
            //
            // ImageIO.write( NoiseManager.iconTexture.finalImage, "png",
            // outputfile);
            // } catch (IOException e) {
            // }
            
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TileMultiPanel();
    }
}
