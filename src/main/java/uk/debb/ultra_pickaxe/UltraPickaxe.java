package uk.debb.ultra_pickaxe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.debb.ultra_pickaxe.tools.CustomPickaxeItem;
import uk.debb.ultra_pickaxe.tools.UltraPickaxeMaterial;

public class UltraPickaxe implements ModInitializer {
	public static ToolItem ULTRA_PICKAXE = new CustomPickaxeItem(UltraPickaxeMaterial.INSTANCE, 6, 0.1f, new Item.Settings().group(ItemGroup.TOOLS));

	private static MinecraftServer server;
    public static MinecraftServer getServer() {
        return server;
    }

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("ultra_pickaxe", "ultra_pickaxe"), ULTRA_PICKAXE);

		ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
            UltraPickaxe.server = minecraftServer;
        });
	}
}
