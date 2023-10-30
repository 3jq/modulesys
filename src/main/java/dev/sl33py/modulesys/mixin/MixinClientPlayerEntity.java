package dev.sl33py.modulesys.mixin;

import dev.sl33py.modulesys.ModuleSys;
import dev.sl33py.modulesys.api.wrapper.Wrapper;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity implements Wrapper {
    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        ModuleSys.getModuleManager().getModules().forEach(module -> {
            if (module.isEnabled()) module.onUpdate();
        });
    }
}
