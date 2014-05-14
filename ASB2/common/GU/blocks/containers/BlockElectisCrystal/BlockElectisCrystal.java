package GU.blocks.containers.BlockElectisCrystal;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilItemStack;
import GU.blocks.containers.BlockContainerBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockElectisCrystal extends BlockContainerBase {
    
    public BlockElectisCrystal(Material material) {
        super(material);
        this.registerTile(TileElectisCrystal.class);
        this.registerTile(TileType1Crystal.class);
        this.registerTile(TileType2Crystal.class);
        this.registerTile(TileType3Crystal.class);
        this.setLightOpacity(0);
        this.setLightLevel(.3f);
    }
    
    @Override
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, ItemBlockElectisCrystal.class, entry);
    }
    
    @Override
    public void postInit() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElectisCrystal.class, ElectisCrystalRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ElectisCrystalRenderer.instance);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        if (!world.isRemote) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof TileElectisCrystal && player.getHeldItem() == null) {
                
                UtilEntity.sendChatToPlayer(player, "PowerStored: " + ((TileElectisCrystal) tile).powerManager.getStoredPower());
                UtilEntity.sendChatToPlayer(player, "CrystalType: " + ((TileElectisCrystal) tile).getCrystalType());
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        
        return side;
    }
    
    @Override
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Electis Crystal";
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        super.setBlockBoundsBasedOnState(world, x, y, z);
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
        return world.isSideSolid(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction);
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side).getOpposite();
        return world.isSideSolid(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.getOpposite());
    }
    
    @Override
    public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_) {
        
        return false;
    }
    
    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        
        return false;
    }
    
    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        
        return false;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        
        if (entity instanceof EntityLivingBase) {
            
            ((EntityLivingBase) entity).attackEntityFrom(DamageSource.magic, 1.0F);
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        
        for (EnumElectisCrystalType type : EnumElectisCrystalType.values()) {
            
            if (type != EnumElectisCrystalType.BROKEN) {
                
                ItemStack stack = new ItemStack(this, 1, 0);
                UtilItemStack.setNBTTagInt(stack, "crystalType", type.ordinal());
                list.add(stack);
            }
        }
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        ItemStack stack = new ItemStack(this);
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileElectisCrystal) {
            
            EnumElectisCrystalType type = ((TileElectisCrystal) tile).getCrystalType();
            UtilItemStack.setNBTTagInt(stack, "crystalType", type.ordinal());
        }
        return stack;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        
        par3List.add("Crystal Type: " + EnumElectisCrystalType.values()[UtilItemStack.getNBTTagInt(stack, "crystalType")].toString());
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int meta) {
        
        return new TileElectisCrystal();
    }
}
