package net.mcreator.alien_craft;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class AlienCraftEntityEvents {
    private AlienCraftEntityEvents() { }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        AttributeSupplier attributes = AlienEntity.createAttributes().build();
        event.put(AlienCraft.ALIEN_ENTITY.get(), attributes);
        event.put(AlienCraft.ALIENCIVIL_ENTITY.get(), attributes);
        event.put(AlienCraft.LONGALIEN_ENTITY.get(), attributes);
        event.put(AlienCraft.LONGLAIENGUARD_ENTITY.get(), attributes);
        event.put(AlienCraft.ZOMBIEALIEN_ENTITY.get(), attributes);
        event.put(AlienCraft.UFO_ENTITY.get(), UfoEntity.createAttributes().build());
    }
}
