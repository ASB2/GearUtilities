package GU.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilInventory;
import GU.ItemRegistry;
import GU.info.Reference;

public class BlockBloodPlant extends FlowerBase {

    static final int FULLY_GROWN = 4;
    String iconString = "BlockBloodPlantStage";
    Icon[] icons = new Icon[FULLY_GROWN];

    public BlockBloodPlant(int id, Material material) {
        super(id, material);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        for(int i = 0; i < FULLY_GROWN; i++) {

            icons[i] = iconRegister.registerIcon(Reference.MODDID + ":" + iconString + i);
        }
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        return icons[metadata];
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {

        if(world.getBlockMetadata(x, y, z) == FULLY_GROWN - 1) {

            ItemStack copy = ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard.copy();
            copy.stackSize = world.rand.nextInt(5) + 1;

            if(UtilInventory.addItemStackToInventoryAndSpawnExcess(world, entityplayer.inventory, copy, x, y, z)) {

                world.setBlockMetadataWithNotify(x, y, z, 0, 3);
                return true;
            }
        }
        else {

            if(entityplayer.capabilities.isCreativeMode) {

                world.setBlockMetadataWithNotify(x, y, z, FULLY_GROWN - 1, 3);
                return true;
            }
        }
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        this.setBlockBounds(0, 0, 0, 1, 1, 1);
        return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        if(!world.isRemote) {

            if(random.nextInt(25) == 1) {

                if(world.getBlockMetadata(x, y, z) < FULLY_GROWN - 1) {

                    world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 3);
                }
                else {

                    UtilBlock.placeBlockInAir(world, x, y + 1, z, blockID, 0);
                }
            }
        }
    }

    @Override
    public int getRenderType() {

        return 0;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return 0;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune) {

        ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();

        itemStacks.add(new ItemStack(this, 0));

        if(meta == FULLY_GROWN - 1) {

            ItemStack copy = ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard.copy();
            copy.stackSize += world.rand.nextInt(5);
            itemStacks.add(copy);
        }
        return itemStacks;
    }

    @Override
    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event) {

        if(event.world.getBlockId(event.X, event.Y, event.Z) == this.blockID) {

            this.updateTick(event.world, event.X, event.Y, event.Z, event.world.rand);
            event.setResult(Result.ALLOW);
        }
    }
}
