package GU.blocks.containers.BlockConduitInterface;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilDirection;
import ASB2.utils.UtilRender;
import ASB2.vector.Vector3;
import GU.api.network.IConductor;
import GU.api.network.INetworkInterface;
import GU.api.power.IPowerMisc;
import GU.info.Models;
import GU.info.Textures;
import GU.info.Variables;

public class ConduitInterfaceRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {

        boolean[] importing = ((TileConduitInterface)tileEntity).importing;
        
        GL11.glPushMatrix();
        GL11.glTranslated(x + .5f, y + .5, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);
        
        int adjacent = getAdjacent(tileEntity.worldObj, new Vector3(tileEntity));

        UtilRender.renderTexture(Textures.UNIVERSAL_CONDUIT);

        if(adjacent == 0) {

            GL11.glPushMatrix();
            GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 1F, 1F, 1F);
            Models.ModelOctogon.renderAll();
            GL11.glPopMatrix();
        }
        else {
            
            GL11.glPushMatrix();
            GL11.glScalef(.5f, .5f, .5f);
            GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 1F, 1F, 1F);
            Models.ModelOctogon.renderAll();
            GL11.glPopMatrix();
            
            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                GL11.glPushMatrix();
                
                TileEntity tile = UtilDirection.translateDirectionToTile(tileEntity, tileEntity.worldObj, direction);

                if(tile != null) {

                    if(tile instanceof IInventory || tile instanceof IFluidHandler || tile instanceof IPowerMisc) {

                        switch (direction) {

                            case UP: {
                                
                                GL11.glTranslated(0, .9, 0);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1F, 0);
                                break;
                            }
                            case DOWN: {
                                
                                GL11.glTranslated(0, -.9, 0);
                                GL11.glRotatef(180F, 1F, 0F, 0F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1F, 0);
                                break;
                            }
                            case NORTH: {
                                
                                GL11.glTranslated(0, 0, -.9);
                                GL11.glRotatef(-90F, 1F, 0F, 0F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            case SOUTH: {

                                GL11.glTranslated(0, 0, .9);
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            case WEST: {

                                GL11.glTranslated(-0.9, 0, 0);
                                GL11.glRotatef(90F, 0F, 0F, 1F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            case EAST: {

                                GL11.glTranslated(0.9, 0, 0);
                                GL11.glRotatef(-90F, 0F, 0F, 1F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                        
                        GL11.glPushMatrix();

                        if(importing[direction.ordinal()]) {
                            
                            UtilRender.renderTexture(Textures.CONDUIT_INTERFACE_IMPORTING);
                        }
                        else {
                            
                            UtilRender.renderTexture(Textures.CONDUIT_INTERFACE_EXPORTING);
                        }
                            
                        Models.ModelOctogon.renderAll();

                        GL11.glPopMatrix();
                    }
                    else if(tile instanceof IConductor) {
                        
                        switch (direction) {

                            case UP: {
                                
                                GL11.glTranslated(0, .9, 0);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1F, 0);
                                break;
                            }
                            case DOWN: {
                                
                                GL11.glTranslated(0, -.9, 0);
                                GL11.glRotatef(180F, 1F, 0F, 0F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1F, 0);
                                break;
                            }
                            case NORTH: {
                                
                                GL11.glTranslated(0, 0, -.9);
                                GL11.glRotatef(-90F, 1F, 0F, 0F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            case SOUTH: {

                                GL11.glTranslated(0, 0, .9);
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            case WEST: {

                                GL11.glTranslated(-0.9, 0, 0);
                                GL11.glRotatef(90F, 0F, 0F, 1F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            case EAST: {

                                GL11.glTranslated(0.9, 0, 0);
                                GL11.glRotatef(-90F, 0F, 0F, 1F);
                                GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0, 1, 0);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                        
                        GL11.glPushMatrix();
                        GL11.glScalef(.5f, .5f, .5f);
                        Models.ModelOctogon.renderAll();

                        GL11.glPopMatrix();
                    }
                }
                GL11.glPopMatrix();
            }
        }

        GL11.glPopMatrix();
    }


    public int getAdjacent(World world, Vector3 position) {

        int amount = 0;

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(position.getTileEntity(world), world, direction);

            if(tile != null) {

                if(tile instanceof IInventory || tile instanceof IFluidHandler || tile instanceof IPowerMisc || tile instanceof IConductor || tile instanceof INetworkInterface) {

                    amount++;
                }
            }
        }
        return amount;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {

            case ENTITY: {

                renderItemSwitched(item, type, 0f, .5f, 0f, 1f);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(item, type, 0f, 0f - .1f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .9f, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        UtilRender.renderTexture(Textures.SOLAR_FOCUS_TOP);
        Models.ModelSolarFocus.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}