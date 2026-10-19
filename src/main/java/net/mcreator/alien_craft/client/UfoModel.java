package net.mcreator.alien_craft.client;

import net.mcreator.alien_craft.AlienCraft;
import net.mcreator.alien_craft.UfoEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

/** Saucer-shaped replacement for the Phantom model used by the earlier port. */
public class UfoModel extends EntityModel<UfoEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(AlienCraft.MOD_ID, "ufo"), "main");
    private final ModelPart saucer;

    public UfoModel(ModelPart root) { saucer = root.getChild("saucer"); }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition saucer = root.addOrReplaceChild("saucer", CubeListBuilder.create()
                .texOffs(0, 54).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F)
                .texOffs(0, 30).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 2.0F, 10.0F)
                .texOffs(42, 0).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F)
                .texOffs(0, 0).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(0, 74).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F), PartPose.offset(0.0F, 19.0F, 0.0F));
        return LayerDefinition.create(mesh, 100, 100);
    }

    @Override public void setupAnim(UfoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        saucer.yRot = netHeadYaw * ((float)Math.PI / 180F);
        saucer.xRot = (float)Math.sin(ageInTicks * 0.08F) * 0.04F;
    }
}
