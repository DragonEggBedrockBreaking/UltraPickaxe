package uk.debb.ultra_pickaxe.tools;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class UltraPickaxeMaterial implements Tier {
    public static final UltraPickaxeMaterial INSTANCE = new UltraPickaxeMaterial();

    private UltraPickaxeMaterial() {}

    @Override
    public int getUses() {
        return 1;
    }

    @Override
    public float getSpeed() {
        return 0.8f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 6;
    }

    @Override
    public int getLevel() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
