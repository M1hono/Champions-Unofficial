package top.theillusivec4.champions.client.affix;

import net.minecraft.client.player.Input;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import top.theillusivec4.champions.Champions;
import top.theillusivec4.champions.common.registry.ChampionsRegistry;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Champions.MODID)
public class ClientAffixEventsHandler {

  @SubscribeEvent
  public static void handleJailing(MovementInputUpdateEvent evt) {
    if (evt.getEntity().hasEffect(ChampionsRegistry.PARALYSIS_EFFECT_TYPE.get())) {
      Input input = evt.getInput();
      input.shiftKeyDown = false;
      input.jumping = false;
      input.forwardImpulse = 0;
      input.leftImpulse = 0;
    }
  }
}
