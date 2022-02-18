package uk.debb.ultra_pickaxe.mixin;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.ultra_pickaxe.UltraPickaxe;

@Mixin(ServerPlayerGameMode.class)
public abstract class MixinServerPlayerGameMode {
    @Shadow protected ServerLevel level;
    @Shadow @Final protected ServerPlayer player;

    private static final Map<Block, ItemEntity> blockToItemMap = new HashMap<Block, ItemEntity>();
    private void addBlocksAndItemsToMap(BlockPos pos) {
        blockToItemMap.put(Blocks.BARRIER, new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BARRIER, 1)));
        blockToItemMap.put(Blocks.BEDROCK, new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BEDROCK, 1)));
        blockToItemMap.put(Blocks.END_PORTAL_FRAME, new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.END_PORTAL_FRAME, 1)));
    }

    @Inject(method = "destroyAndAck", at = @At("HEAD"), cancellable = true)
    private void dropBlock(BlockPos pos, ServerboundPlayerActionPacket.Action action, String reason, CallbackInfo ci) {
        if (this.player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == UltraPickaxe.ULTRA_PICKAXE) {
            blockToItemMap.clear();
            addBlocksAndItemsToMap(pos);
            if (blockToItemMap.containsKey(this.level.getBlockState(pos).getBlock())) {
                this.level.addFreshEntity(blockToItemMap.get(this.level.getBlockState(pos).getBlock()));
            }
        }
    }
}
