package uk.debb.ultra_pickaxe.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.ultra_pickaxe.UltraPickaxe;

@Mixin(BlockBehaviour.class)
public abstract class MixinBlockBehaviour {
    @Inject(method = "getDestroyProgress", at = @At("HEAD"), cancellable = true)
    private void modifyDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (state.getDestroySpeed(world, pos) == -1.0 && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == UltraPickaxe.ULTRA_PICKAXE) {
            BlockState newState = Blocks.OBSIDIAN.defaultBlockState();
            cir.setReturnValue(player.getDestroySpeed(newState) / newState.getDestroySpeed(world, pos) / (float)(player.hasCorrectToolForDrops(newState) ? 30 : 100));
        }
    }
}