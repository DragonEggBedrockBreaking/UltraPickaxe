package uk.debb.ultra_pickaxe.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class UltraPickaxeMaterial implements ToolMaterial {
    public static final UltraPickaxeMaterial INSTANCE = new UltraPickaxeMaterial();

    private UltraPickaxeMaterial() {}

    @Override
    public int getDurability() {
        return 1;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 100.0f;
    }

    @Override
    public float getAttackDamage() {
        return 6;
    }

    @Override
    public int getMiningLevel() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
