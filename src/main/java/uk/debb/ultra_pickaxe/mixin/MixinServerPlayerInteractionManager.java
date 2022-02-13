package uk.debb.ultra_pickaxe.mixin;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.ultra_pickaxe.UltraPickaxe;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class MixinServerPlayerInteractionManager {
    @Shadow protected ServerWorld world;
    @Shadow @Final protected ServerPlayerEntity player;

    private static final Map<Block, ItemEntity> blockToItemMap = new HashMap<Block, ItemEntity>();
    private void addBlocksAndItemsToMap(BlockPos pos) {
        blockToItemMap.put(Blocks.BARRIER, new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BARRIER, 1)));
        blockToItemMap.put(Blocks.BEDROCK, new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BEDROCK, 1)));
        blockToItemMap.put(Blocks.END_PORTAL_FRAME, new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.END_PORTAL_FRAME, 1)));
    }

    @Inject(method = "finishMining", at = @At("HEAD"), cancellable = true)
    private void dropBlock(BlockPos pos, PlayerActionC2SPacket.Action action, String reason, CallbackInfo ci) {
        if (this.player.getStackInHand(Hand.MAIN_HAND).getItem() == UltraPickaxe.ULTRA_PICKAXE) {
            blockToItemMap.clear();
            addBlocksAndItemsToMap(pos);
            if (blockToItemMap.containsKey(this.world.getBlockState(pos).getBlock())) {
                this.world.spawnEntity(blockToItemMap.get(this.world.getBlockState(pos).getBlock()));
            }
        }
    }
}
