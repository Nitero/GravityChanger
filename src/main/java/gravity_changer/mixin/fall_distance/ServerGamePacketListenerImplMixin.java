package gravity_changer.mixin.fall_distance;

import gravity_changer.api.GravityChangerAPI;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {

	@Redirect(
			method = "handleMovePlayer",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/server/level/ServerPlayer;resetFallDistance()V"
			)
	)
	private void redirect_resetFallDistance(ServerPlayer player) {
		Direction gravity = GravityChangerAPI.getGravityDirection(player);

		if (gravity == Direction.DOWN)
			player.resetFallDistance();
	}
}
