package net.mcreator.alien_craft.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
        event.registerEntityRenderer(AlienCraft.ALIEN_ENTITY.get(), context -> new AlienRenderer(context, AlienModel.ALIEN_LAYER, AlienModel.Kind.ALIEN, "alienskin"));
        event.registerEntityRenderer(AlienCraft.ALIENCIVIL_ENTITY.get(), context -> new AlienRenderer(context, AlienModel.CIVIL_LAYER, AlienModel.Kind.CIVIL, "alien3"));
        event.registerEntityRenderer(AlienCraft.LONGALIEN_ENTITY.get(), context -> new AlienRenderer(context, AlienModel.LONG_LAYER, AlienModel.Kind.LONG, "alienskin"));
        event.registerEntityRenderer(AlienCraft.LONGLAIENGUARD_ENTITY.get(), context -> new AlienRenderer(context, AlienModel.GUARD_LAYER, AlienModel.Kind.GUARD, "aliensoldier3"));
        event.registerEntityRenderer(AlienCraft.ZOMBIEALIEN_ENTITY.get(), context -> new AlienRenderer(context, AlienModel.ZOMBIE_LAYER, AlienModel.Kind.ZOMBIE, "zombiealien"));
        event.registerEntityRenderer(AlienCraft.UFO_ENTITY.get(), UfoRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AlienModel.ALIEN_LAYER, AlienModel::createAlienLayer);
        event.registerLayerDefinition(AlienModel.CIVIL_LAYER, AlienModel::createCivilLayer);
        event.registerLayerDefinition(AlienModel.LONG_LAYER, AlienModel::createLongLayer);
        event.registerLayerDefinition(AlienModel.GUARD_LAYER, AlienModel::createGuardLayer);
        event.registerLayerDefinition(AlienModel.ZOMBIE_LAYER, AlienModel::createZombieLayer);
        event.registerLayerDefinition(UfoModel.LAYER, UfoModel::createLayer);
    }

    private static final class AlienRenderer extends MobRenderer<AlienEntity, AlienModel> {
        private final ResourceLocation texture;

        private AlienRenderer(EntityRendererProvider.Context context, net.minecraft.client.model.geom.ModelLayerLocation layer, AlienModel.Kind kind, String textureName) {
            super(context, new AlienModel(context.bakeLayer(layer), kind), 0.5F);
            texture = new ResourceLocation(AlienCraft.MOD_ID, "textures/" + textureName + ".png");
        }

        @Override
        public ResourceLocation getTextureLocation(AlienEntity entity) {
            return texture;
        }
    }

    private static final class UfoRenderer extends MobRenderer<net.mcreator.alien_craft.UfoEntity, UfoModel> {
        private static final ResourceLocation TEXTURE = new ResourceLocation(AlienCraft.MOD_ID, "textures/ufo.png");
        private UfoRenderer(EntityRendererProvider.Context context) { super(context, new UfoModel(context.bakeLayer(UfoModel.LAYER)), 0.6F); }
        @Override public ResourceLocation getTextureLocation(net.mcreator.alien_craft.UfoEntity entity) { return TEXTURE; }
    }
}
