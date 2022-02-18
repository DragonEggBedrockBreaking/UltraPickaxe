package uk.debb.ultra_pickaxe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import uk.debb.ultra_pickaxe.tools.CustomPickaxeItem;
import uk.debb.ultra_pickaxe.tools.UltraPickaxeMaterial;

public class UltraPickaxe implements ModInitializer {
	public static TieredItem ULTRA_PICKAXE = new CustomPickaxeItem(UltraPickaxeMaterial.INSTANCE, 6, 0.1f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));

	private static MinecraftServer server;
    public static MinecraftServer getServer() {
        return server;
    }

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new ResourceLocation("ultra_pickaxe", "ultra_pickaxe"), ULTRA_PICKAXE);

		ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
            UltraPickaxe.server = minecraftServer;
        });
	}
}
