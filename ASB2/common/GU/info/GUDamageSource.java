package GU.info;

import net.minecraft.util.DamageSource;
import ASB2.CustomDamageSource;

public class GUDamageSource {

    public static DamageSource clusterCollision = (new CustomDamageSource("clusterCollision")).setDeathMessage("").setDamageBypassesArmor().setDifficultyScaled();
}
