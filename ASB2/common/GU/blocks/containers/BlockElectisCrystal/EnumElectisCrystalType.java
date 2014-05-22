package GU.blocks.containers.BlockElectisCrystal;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilItemStack;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import UC.color.Color4f;
import GU.render.noise.NoiseManager;

public enum EnumElectisCrystalType {
    
    BROKEN, TYPE1, TYPE2, TYPE3, TYPE4;
    
    private static boolean hasLoaded;
    
    int blockListID = -1, itemListID = -1;
    
    private EnumElectisCrystalType() {
        
    }
    
    public static void generateDisplayList() {
        
        if (!hasLoaded) {
            hasLoaded = true;
            for (EnumElectisCrystalType type : EnumElectisCrystalType.values()) {
                
                switch (type) {
                
                    case TYPE1: {
                        
                        {
                            GL11.glPushMatrix();
                            GL11.glNewList(type.getBlockDisplayListID(), GL11.GL_COMPILE);
                            
                            {
                                GL11.glPushMatrix();
                                float scale = .15f;
                                GL11.glScalef(scale, scale, scale);
                                
                                NoiseManager.bindImage();
                                Models.ModelCrystal2.renderPart("Crystal");
                                GL11.glPopMatrix();
                            }
                            
                            final float secondCrystalScale = .35f;
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(0, -0.1, .25F);
                                
                                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(.25, -0.1, -.025F);
                                
                                GL11.glRotatef(90F, 0F, 1F, 0F);
                                
                                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(0, -0.1, -.25F);
                                
                                GL11.glRotatef(180F, 0F, 1F, 0F);
                                
                                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(-.25, -0.1, .025F);
                                
                                GL11.glRotatef(270F, 0F, 1F, 0F);
                                
                                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            GL11.glEndList();
                            GL11.glPopMatrix();
                            
                            GL11.glPushMatrix();
                            GL11.glNewList(type.getItemListID(), GL11.GL_COMPILE);
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(0, -0.1, .35F);
                                
                                GL11.glScalef(.3f, .3f, .3f);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(.25, -0.1, -.035F);
                                
                                GL11.glRotatef(90F, 0F, 1F, 0F);
                                
                                GL11.glScalef(.3f, .3f, .3f);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(0, -0.1, -.35F);
                                
                                GL11.glRotatef(180F, 0F, 1F, 0F);
                                
                                GL11.glScalef(.3f, .3f, .3f);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            
                            {
                                GL11.glPushMatrix();
                                
                                GL11.glTranslated(-.25, -0.1, .035F);
                                
                                GL11.glRotatef(270F, 0F, 1F, 0F);
                                
                                GL11.glScalef(.3f, .3f, .3f);
                                
                                GL11.glRotatef(90F, 1F, 0F, 0F);
                                GL11.glRotatef(25F, 1F, 0F, 0F);
                                
                                Models.ModelFlameShard.renderAll();
                                GL11.glPopMatrix();
                            }
                            GL11.glEndList();
                            GL11.glPopMatrix();
                        }
                        break;
                    }
                    
                    case TYPE2: {
                        
                        GL11.glNewList(type.getBlockDisplayListID(), GL11.GL_COMPILE);
                        
                        GL11.glPushMatrix();
                        GL11.glScaled(.25, .25, .25);
                        NoiseManager.bindImage();
                        Models.ModelRhombicuboctahedron.renderPart("Rhombicuboctahedron");
                        GL11.glPopMatrix();
                        
                        GL11.glEndList();
                        break;
                    }
                    
                    case TYPE3: {
                        
                        break;
                    }
                    
                    case TYPE4: {
                        
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
    }
    
    public void renderBlockDisplayList(TileElectisCrystal tileentity, double x, double y, double z) {
        
        switch (this) {
        
            case TYPE1: {
                
                GL11.glPushMatrix();
                
                float translationAmount = .2f;
                
                switch (((TileBase) tileentity).getOrientation()) {
                
                    case UP: {
                        
                        GL11.glTranslated(x + 0.5F, y + translationAmount, z + .5F);
                        break;
                    }
                    case DOWN: {
                        
                        GL11.glTranslated(x + 0.5F, y + (1 - translationAmount), z + .5F);
                        GL11.glRotatef(180F, 1F, 0F, 0F);
                        break;
                    }
                    
                    case SOUTH: {
                        
                        GL11.glTranslated(x + 0.5F, y + .5F, z + translationAmount);
                        GL11.glRotatef(90F, 1F, 0F, 0F);
                        break;
                    }
                    case NORTH: {
                        
                        GL11.glTranslated(x + 0.5F, y + .5F, z + (1 - translationAmount));
                        GL11.glRotatef(-90F, 1F, 0F, 0F);
                        break;
                    }
                    case WEST: {
                        
                        GL11.glTranslated(x + (1 - translationAmount), y + .5F, z + .5F);
                        GL11.glRotatef(90F, 0F, 0F, 1F);
                        break;
                    }
                    case EAST: {
                        
                        GL11.glTranslated(x + translationAmount, y + .5F, z + .5F);
                        GL11.glRotatef(-90F, 0F, 0F, 1F);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                
                GL11.glCallList(this.getBlockDisplayListID());
                GL11.glPopMatrix();
                break;
            }
            
            case TYPE2: {
                
                GL11.glPushMatrix();
                
                GL11.glTranslated(x + 0.5, y + .5, z + .5);
                
                GL11.glCallList(this.getBlockDisplayListID());
                GL11.glPopMatrix();
                break;
            }
            
            case TYPE3: {
                
                GL11.glPushMatrix();
                
                GL11.glTranslated(x + .5, y + .5, z + .5);
                
                GL11.glScaled(.4, .4, .4);
                NoiseManager.bindImage();
                
                GL11.glPushMatrix();
                GL11.glColor3d(NoiseManager.instance.ITERATED_COLOR.getRed() / 255f, NoiseManager.instance.ITERATED_COLOR.getGreen() / 255f, NoiseManager.instance.ITERATED_COLOR.getBlue() / 255f);
                GL11.glRotated(Minecraft.getSystemTime() / 17, 1, 0, 0);
                GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 1, 0);
                GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 0, 1);
                GL11.glDisable(GL11.GL_CULL_FACE);
                Models.ModelRhombicuboctahedron.renderPart("Main_Faces");
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glPopMatrix();
                
                GL11.glScaled(.8, .8, .8);
                
                GL11.glPushMatrix();
                GL11.glColor3d(NoiseManager.instance.ITERATED_COLOR_INVERTED.getRed() / 255f, NoiseManager.instance.ITERATED_COLOR_INVERTED.getGreen() / 255f, NoiseManager.instance.ITERATED_COLOR_INVERTED.getBlue() / 255f);
                GL11.glRotated(-Minecraft.getSystemTime() / 17, 1, 0, 0);
                GL11.glRotated(-Minecraft.getSystemTime() / 17, 0, 1, 0);
                GL11.glRotated(-Minecraft.getSystemTime() / 17, 0, 0, 1);
                Models.ModelRhombicuboctahedron.renderPart("Rhombicuboctahedron");
                GL11.glPopMatrix();
                
                GL11.glPopMatrix();
                break;
            }
            
            case TYPE4: {
                
                GL11.glPushMatrix();
                
                float translationAmount = .07f;
                
                switch (((TileBase) tileentity).getOrientation()) {
                
                    case UP: {
                        
                        GL11.glTranslated(x + 0.5F, y + translationAmount, z + .5F);
                        break;
                    }
                    case DOWN: {
                        
                        GL11.glTranslated(x + 0.5F, y + (1 - translationAmount), z + .5F);
                        GL11.glRotatef(180F, 1F, 0F, 0F);
                        break;
                    }
                    
                    case SOUTH: {
                        
                        GL11.glTranslated(x + 0.5F, y + .5F, z + translationAmount);
                        GL11.glRotatef(90F, 1F, 0F, 0F);
                        break;
                    }
                    case NORTH: {
                        
                        GL11.glTranslated(x + 0.5F, y + .5F, z + (1 - translationAmount));
                        GL11.glRotatef(-90F, 1F, 0F, 0F);
                        break;
                    }
                    case WEST: {
                        
                        GL11.glTranslated(x + (1 - translationAmount), y + .5F, z + .5F);
                        GL11.glRotatef(90F, 0F, 0F, 1F);
                        break;
                    }
                    case EAST: {
                        
                        GL11.glTranslated(x + translationAmount, y + .5F, z + .5F);
                        GL11.glRotatef(-90F, 0F, 0F, 1F);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                
                GL11.glScaled(.05, .05, .05);
                NoiseManager.bindImage();
                Models.ModelCrystal2.renderAll();
                
                GL11.glPopMatrix();
                break;
            }
            default: {
                break;
            }
        }
    }
    
    public void renderItemDisplayList(ItemRenderType type, ItemStack item, Object... data) {
        
        float x, y, z, scale;
        
        switch (this) {
        
            case TYPE1: {
                
                switch (type) {
                
                    case ENTITY: {
                        
                        x = 0;
                        y = 0;
                        z = 0;
                        scale = .2f;
                        break;
                    }
                    
                    case EQUIPPED: {
                        
                        x = 0;
                        y = 1;
                        z = .5f;
                        scale = .2f;
                        break;
                    }
                    
                    case INVENTORY: {
                        
                        x = 0;
                        y = -.2f;
                        z = 0f;
                        scale = .2f;
                        break;
                    }
                    
                    case EQUIPPED_FIRST_PERSON: {
                        
                        x = -.5f;
                        y = 1;
                        z = .5f;
                        scale = .12f;
                        break;
                    }
                    
                    default: {
                        return;
                    }
                }
                
                GL11.glPushMatrix();
                
                GL11.glTranslatef(x, y, z);
                
                {
                    GL11.glPushMatrix();
                    GL11.glScalef(scale, scale, scale);
                    NoiseManager.bindImage();
                    Models.ModelCrystal2.renderPart("Crystal");
                    GL11.glPopMatrix();
                }
                
                GL11.glCallList(this.getItemListID());
                
                GL11.glPopMatrix();
                
                break;
            }
            
            case TYPE2: {
                
                switch (type) {
                
                    case ENTITY: {
                        
                        x = 0;
                        y = 0;
                        z = 0;
                        scale = .2f;
                        break;
                    }
                    
                    case EQUIPPED: {
                        
                        x = 0;
                        y = 1;
                        z = .5f;
                        scale = .2f;
                        break;
                    }
                    
                    case INVENTORY: {
                        
                        x = 0;
                        y = -.2f;
                        z = 0f;
                        scale = .5f;
                        break;
                    }
                    
                    case EQUIPPED_FIRST_PERSON: {
                        
                        x = -.5f;
                        y = 1;
                        z = .5f;
                        scale = .3f;
                        break;
                    }
                    
                    default: {
                        return;
                    }
                }
                
                GL11.glTranslatef(x, y, z);
                
                GL11.glPushMatrix();
                GL11.glScalef(scale, scale, scale);
                NoiseManager.bindImage();
                Models.ModelRhombicuboctahedron.renderPart("Rhombicuboctahedron");
                GL11.glPopMatrix();
                
                break;
            }
            
            case TYPE3: {
                
                switch (type) {
                
                    case ENTITY: {
                        
                        x = 0;
                        y = 0;
                        z = 0;
                        scale = .9f;
                        break;
                    }
                    
                    case EQUIPPED: {
                        
                        x = 0;
                        y = 1;
                        z = .5f;
                        scale = .5f;
                        break;
                    }
                    
                    case INVENTORY: {
                        
                        x = 0;
                        y = -.2f;
                        z = 0f;
                        scale = .5f;
                        break;
                    }
                    
                    case EQUIPPED_FIRST_PERSON: {
                        
                        x = -.5f;
                        y = 1;
                        z = .5f;
                        scale = .3f;
                        break;
                    }
                    
                    default: {
                        return;
                    }
                }
                
                GL11.glTranslatef(x, y, z);
                
                GL11.glPushMatrix();
                
                GL11.glScalef(scale, scale, scale);
                
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glRotated(Minecraft.getSystemTime() / 17, 1, 0, 0);
                GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 1, 0);
                GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 0, 1);
                NoiseManager.bindImage();
                GL11.glColor3d(NoiseManager.instance.ITERATED_COLOR.getRed() / 255f, NoiseManager.instance.ITERATED_COLOR.getGreen() / 255f, NoiseManager.instance.ITERATED_COLOR.getBlue() / 255f);
                Models.ModelRhombicuboctahedron.renderPart("Main_Faces");
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glPopMatrix();
                
                GL11.glScaled(.8, .8, .8);
                GL11.glRotated(-Minecraft.getSystemTime() / 17, 1, 0, 0);
                GL11.glRotated(-Minecraft.getSystemTime() / 17, 0, 1, 0);
                GL11.glRotated(-Minecraft.getSystemTime() / 17, 0, 0, 1);
                GL11.glColor3d(NoiseManager.instance.ITERATED_COLOR_INVERTED.getRed() / 255f, NoiseManager.instance.ITERATED_COLOR_INVERTED.getGreen() / 255f, NoiseManager.instance.ITERATED_COLOR_INVERTED.getBlue() / 255f);
                Models.ModelRhombicuboctahedron.renderPart("Rhombicuboctahedron");
                
                GL11.glPopMatrix();
                
                break;
            }
            
            case TYPE4: {
                
                switch (type) {
                
                    case ENTITY: {
                        
                        x = 0;
                        y = 0;
                        z = 0;
                        scale = .1f;
                        break;
                    }
                    
                    case EQUIPPED: {
                        
                        x = 0;
                        y = 1;
                        z = .5f;
                        scale = .1f;
                        break;
                    }
                    
                    case INVENTORY: {
                        
                        x = 0;
                        y = -.2f;
                        z = 0f;
                        scale = .3f;
                        break;
                    }
                    
                    case EQUIPPED_FIRST_PERSON: {
                        
                        x = -.5f;
                        y = 1;
                        z = .5f;
                        scale = .1f;
                        break;
                    }
                    
                    default: {
                        return;
                    }
                }
                GL11.glPushMatrix();
                
                GL11.glTranslatef(x, y, z);
                
                {
                    GL11.glPushMatrix();
                    GL11.glScalef(scale, scale, scale);
                    NoiseManager.bindImage();
                    Models.ModelCrystal2.renderPart("Crystal");
                    GL11.glPopMatrix();
                }
                
                GL11.glCallList(this.getItemListID());
                
                GL11.glPopMatrix();
                break;
            }
            
            default: {
                break;
            }
        }
    }
    
    public int getBlockDisplayListID() {
        
        if (this.ordinal() != 0 && blockListID == -1) {
            blockListID = GL11.glGenLists(1);
        }
        return blockListID;
    }
    
    public int getItemListID() {
        
        if (this.ordinal() != 0 && itemListID == -1) {
            itemListID = GL11.glGenLists(1);
        }
        return itemListID;
    }
    
    public Color4f getDefaultColor() {
        
        return Color4f.WHITE;
    }
    
    public CrystalLogic getNewCrystalLogicInstance(TileElectisCrystal crystal) {
        
        switch (this) {
        
            case TYPE1:
                return new Type1CrystalLogic(crystal);
            case TYPE2:
                return new Type2CrystalLogic(crystal);
            case TYPE3:
                return new Type3CrystalLogic(crystal);
            case TYPE4:
                return new Type4CrystalLogic(crystal);
            default:
                return null;
        }
    }
    
    public DefaultPowerManager getDefaultPowerManager() {
        
        return new DefaultPowerManager().setPowerMax(100);
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setInteger("crystalType", this.ordinal());
        return tag;
    }
    
    public ItemStack setCrystalType(ItemStack stack) {
        
        return UtilItemStack.setNBTTagInt(stack, "crystalType", this.ordinal());
    }
    
    public static EnumElectisCrystalType load(NBTTagCompound tag) {
        
        return EnumElectisCrystalType.values()[tag.getInteger("crystalType")];
    }
    
    public static ItemStack setCrystalType(ItemStack stack, EnumElectisCrystalType type) {
        
        return UtilItemStack.setNBTTagInt(stack, "crystalType", type.ordinal());
    }
    
    public static EnumElectisCrystalType getCrystalType(ItemStack stack) {
        
        return EnumElectisCrystalType.values()[UtilItemStack.getNBTTagInt(stack, "crystalType")];
    }
    
    public static boolean getHasLoaded() {
        
        return hasLoaded;
    }
}
