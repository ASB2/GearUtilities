package GU.blocks.containers.BlockElectisCrystal;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class ElectisCrystalRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static ElectisCrystalRenderer instance = new ElectisCrystalRenderer();
    
    public ElectisCrystalRenderer() {
        
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        if (!EnumElectisCrystalType.getHasLoaded()) {
            
            EnumElectisCrystalType.generateDisplayList();
        }
        EnumElectisCrystalType crystalType = ((TileElectisCrystal) tileentity).getCrystalType();
        
        crystalType.renderBlockDisplayList(((TileElectisCrystal) tileentity), x, y, z);
        
        // GL11.glPushMatrix();
        //
        // for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> crystal
        // : ((TileElectisCrystal) tileentity).powerHandlers) {
        //
        // GL11.glBegin(GL11.GL_LINES);
        // GL11.glColor3f(1, 1, 0);
        // GL11.glVertex3d(x + .5, y + .5, z + .5);
        // GL11.glColor3f(1, 0, 0);
        // GL11.glVertex3d((crystal.getKey().getX() -
        // Minecraft.getMinecraft().thePlayer.posX) + .5,
        // (crystal.getKey().getY() -
        // Minecraft.getMinecraft().thePlayer.posY) + .5,
        // (crystal.getKey().getZ() -
        // Minecraft.getMinecraft().thePlayer.posZ) + .5);
        //
        // GL11.glEnd();
        // }
        //
        // GL11.glPopMatrix();
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
        
        EnumElectisCrystalType.getCrystalType(item).renderItemDisplayList(type, item, data);
    }
}