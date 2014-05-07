package GU.info;

import net.minecraft.util.DamageSource;
import ASB2.CustomDamageSource;

public class GUDamageSource {

    public static DamageSource entityFinderCollision = (new CustomDamageSource("clusterCollision")).setDeathMessage("You dead sucker").setDamageBypassesArmor().setDifficultyScaled();
}
