package GU.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilVector;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockFurnace extends MultiBlockInventory implements IFluidHandler {
    
    public FluidTank fluidTank = new FluidTank(1000);
    
    public MultiBlockFurnace(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
        fluidTank.setCapacity((size.getX() - 1) * (size.getY() - 1) * (size.getZ() - 1) * 16 * FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    public MultiBlockFurnace(World world) {
        super(world);
        
        fluidTank.setCapacity(16 * FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.RED;
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return fluidTank.fill(resource, doFill);
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (fluidTank != null) {
            
            if (fluid != null) {
                
                if (fluidTank.getFluid() != null) {
                    
                    if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {
                        
                        return true;
                    }
                }
                else {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {
            
            return null;
        }
        
        return fluidTank.drain(resource.amount, doDrain);
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        return fluidTank.drain(maxDrain, doDrain);
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (this.fluidTank.getFluid() != null) {
            
            if (fluidTank.getFluidAmount() > 0) {
                
                if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        return new FluidTankInfo[] { fluidTank.getInfo() };
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("TankData", fluidTank.writeToNBT(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        fluidTank.readFromNBT(tag.getCompoundTag("TankData"));
        super.load(tag);
    }
    
    @Override
    public void update(Object... objects) {
        
        if (isConstructing) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        
                        if (x == 1 && y == 1 && z == 1) {
                            
                            if (!placeRenderBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y == 0 || y == size.getY())) || ((y == 0 || y == size.getY()) && (z == 0 || z == size.getZ())) || ((x == 0 || x == size.getX()) && (z == 0 || z == size.getZ()))) {
                            
                            if (!placeEdgeBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (y == (int) (size.getY() / 2)) {
                            
                            if (!placeEdgeBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x == 0 || x == size.getX()) && (y != 0 && y != size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y == 0 || y == size.getY())) && (z != 0 && z != size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (((x != 0 && x != size.getX()) && (y != 0 && y != size.getY())) && (z == 0 || z == size.getZ())) {
                            
                            if (!placeGlassBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (!placeAirBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                    }
                }
            }
            isConstructing = false;
            isValid = true;
        }
        else if (forceLoad) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        checkBlock(positionRelativeTo.subtract(x, y, z));
                    }
                }
            }
            forceLoad = false;
            isValid = true;
        }
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            if (size.getX() >= 2 && size.getY() >= 4 && size.getZ() >= 2) {
                
                isConstructing = true;
                
                TileEntity tile = UtilVector.getTileAtPostion(world, updater);
                
                if (tile != null && tile instanceof IMultiBlockPart) {
                    
                    return ((IMultiBlockPart) tile).addMultiBlock(this);
                }
            }
        }
        return false;
    }
    
    @Override
    public void render(double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(x, y, z);
        
        GL11.glBegin(GL11.GL_QUADS); // Draw The Cube Using quads
        GL11.glColor3f(0.0f, 1.0f, 0.0f); // Color Blue
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Top)
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Quad (Top)
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Quad (Top)
        GL11.glColor3f(1.0f, 0.5f, 0.0f); // Color Orange
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Top Right Of The Quad (Bottom)
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Top Left Of The Quad (Bottom)
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad
                                              // (Bottom)
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad
                                             // (Bottom)
        GL11.glColor3f(1.0f, 0.0f, 0.0f); // Color Red
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Front)
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Front)
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Quad (Front)
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Quad (Front)
        GL11.glColor3f(1.0f, 1.0f, 0.0f); // Color Yellow
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Right Of The Quad (Back)
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Left Of The Quad (Back)
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Bottom Left Of The Quad (Back)
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Bottom Right Of The Quad (Back)
        GL11.glColor3f(0.0f, 0.0f, 1.0f); // Color Blue
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Left)
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Left)
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad (Left)
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Quad (Left)
        GL11.glColor3f(1.0f, 0.0f, 1.0f); // Color Violet
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Right)
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Right)
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Quad (Right)
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad (Right)
        GL11.glEnd();
        
        GL11.glPopMatrix();
    }
}
