package net.mcreator.alien_craft.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mcreator.alien_craft.AlienCraft;
import net.mcreator.alien_craft.AlienEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class AlienCraftClientEvents {
    private AlienCraftClientEvents() { }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AlienCraft.ALIEN_ENTITY.get(), context -> new AlienRenderer(context, "alienskin"));
        event.registerEntityRenderer(AlienCraft.ALIENCIVIL_ENTITY.get(), context -> new AlienRenderer(context, "alien3"));
        event.registerEntityRenderer(AlienCraft.LONGALIEN_ENTITY.get(), context -> new AlienRenderer(context, "alienskin"));
        event.registerEntityRenderer(AlienCraft.LONGLAIENGUARD_ENTITY.get(), context -> new AlienRenderer(context, "aliensoldier3"));
        event.registerEntityRenderer(AlienCraft.ZOMBIEALIEN_ENTITY.get(), context -> new AlienRenderer(context, "zombiealien"));
    }

    private static final class AlienRenderer extends MobRenderer<AlienEntity, ZombieModel<AlienEntity>> {
        private final ResourceLocation texture;

        private AlienRenderer(EntityRendererProvider.Context context, String textureName) {
            super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
            texture = new ResourceLocation(AlienCraft.MOD_ID, "textures/" + textureName + ".png");
        }

        @Override
        public ResourceLocation getTextureLocation(AlienEntity entity) {
            return texture;
        }
    }
}
