package net.mcreator.alien_craft.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mcreator.alien_craft.AlienCraft;
import net.mcreator.alien_craft.AlienEntity;

/** Geometry reconstructed from Alien Craft 1.4.1 for Minecraft 1.14.4. */
public final class AlienModel extends EntityModel<AlienEntity> {
    public enum Kind { ALIEN, CIVIL, LONG, GUARD, ZOMBIE }
    public static final ModelLayerLocation ALIEN_LAYER = layer("alien");
    public static final ModelLayerLocation CIVIL_LAYER = layer("aliencivil");
    public static final ModelLayerLocation LONG_LAYER = layer("longalien");
    public static final ModelLayerLocation GUARD_LAYER = layer("longlaienguard");
    public static final ModelLayerLocation ZOMBIE_LAYER = layer("zombiealien");
    private static ModelLayerLocation layer(String name) { return new ModelLayerLocation(new ResourceLocation(AlienCraft.MOD_ID, name), "main"); }
    private final ModelPart bone, rightArm, leftArm, rightLeg, leftLeg, head;
    private final Kind kind;

    public AlienModel(ModelPart root, Kind kind) {
        this.bone = root.getChild("bone"); this.kind = kind;
        this.rightArm = bone.getChild("Rarm"); this.leftArm = bone.getChild("Larm");
        this.rightLeg = bone.getChild("Rleg"); this.leftLeg = bone.getChild("Lleg"); this.head = bone.getChild("Head");
    }

    public static LayerDefinition createAlienLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(7, 1).addBox(1.0F, -19.0F, -1.0F, 1F, 6F, 2F)
                .texOffs(0, 1).addBox(-1.0F, -13.0F, -3.0F, 4F, 5F, 6F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rarm = bone.addOrReplaceChild("Rarm", CubeListBuilder.create()
                .texOffs(27, 29).addBox(0.0F, 7.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(26, 30).addBox(-1.0F, 7.0F, 1.0F, 2F, 1F, 0F)
                .texOffs(17, 11).addBox(-1.0F, 0.0F, 0.0F, 2F, 7F, 1F), PartPose.offsetAndRotation(1.0F, -13.0F, 3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Larm = bone.addOrReplaceChild("Larm", CubeListBuilder.create()
                .texOffs(21, 10).addBox(-1.0F, 0.0F, -1.0F, 2F, 7F, 1F)
                .texOffs(26, 30).addBox(-1.0F, 7.0F, -1.0F, 2F, 1F, 0F)
                .texOffs(27, 29).addBox(0.0F, 7.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -13.0F, -3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rleg = bone.addOrReplaceChild("Rleg", CubeListBuilder.create()
                .texOffs(18, 11).addBox(-1.0F, 0.0F, 0.0F, 2F, 8F, 1F)
                .texOffs(27, 30).addBox(-2.0F, 7.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(27, 29).addBox(-2.0F, 7.0F, 1.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -8.0F, 1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Lleg = bone.addOrReplaceChild("Lleg", CubeListBuilder.create()
                .texOffs(16, 4).addBox(-1.0F, 0.0F, -1.0F, 2F, 8F, 1F)
                .texOffs(27, 30).addBox(-2.0F, 7.0F, -1.0F, 1F, 1F, 0F)
                .texOffs(27, 30).addBox(-2.0F, 7.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -8.0F, -1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Head = bone.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(3, 12).addBox(-4.0F, 0.0F, -2.0F, 4F, 2F, 4F)
                .texOffs(2, 13).addBox(-4.0F, -7.0F, -3.0F, 6F, 7F, 6F)
                .texOffs(8, 6).addBox(-3.0F, -8.0F, -2.0F, 4F, 1F, 4F), PartPose.offsetAndRotation(1.0F, -17.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }
    public static LayerDefinition createCivilLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(17, 15).addBox(1.0F, -27.0F, -1.0F, 1F, 6F, 2F)
                .texOffs(0, 0).addBox(-1.0F, -21.0F, -3.0F, 4F, 5F, 6F)
                .texOffs(1, 2).addBox(0.0F, -16.0F, -2.0F, 3F, 4F, 4F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rarm = bone.addOrReplaceChild("Rarm", CubeListBuilder.create()
                .texOffs(13, 4).addBox(-1.0F, 0.0F, 0.0F, 2F, 10F, 1F)
                .texOffs(26, 30).addBox(-1.0F, 10.0F, 1.0F, 2F, 1F, 0F)
                .texOffs(27, 29).addBox(0.0F, 10.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -20.0F, 3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Larm = bone.addOrReplaceChild("Larm", CubeListBuilder.create()
                .texOffs(13, 4).addBox(-1.0F, 0.0F, -1.0F, 2F, 10F, 1F)
                .texOffs(26, 30).addBox(-1.0F, 10.0F, -1.0F, 2F, 1F, 0F)
                .texOffs(27, 29).addBox(0.0F, 10.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -20.0F, -3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rleg = bone.addOrReplaceChild("Rleg", CubeListBuilder.create()
                .texOffs(13, 4).addBox(0.0F, 0.0F, 0.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(27, 29).addBox(-1.0F, 11.0F, 1.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, 1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Lleg = bone.addOrReplaceChild("Lleg", CubeListBuilder.create()
                .texOffs(13, 4).addBox(0.0F, 0.0F, -1.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, -1.0F, 1F, 1F, 0F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, -1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Head = bone.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(3, 12).addBox(-4.0F, 0.0F, -2.0F, 4F, 2F, 4F)
                .texOffs(2, 13).addBox(-4.0F, -7.0F, -3.0F, 6F, 7F, 6F)
                .texOffs(11, 16).addBox(-3.0F, -8.0F, -2.0F, 4F, 1F, 4F), PartPose.offsetAndRotation(1.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }
    public static LayerDefinition createLongLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(7, 1).addBox(1.0F, -27.0F, -1.0F, 1F, 6F, 2F)
                .texOffs(0, 1).addBox(-1.0F, -21.0F, -3.0F, 4F, 5F, 6F)
                .texOffs(0, 1).addBox(0.0F, -16.0F, -2.0F, 3F, 4F, 4F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rarm = bone.addOrReplaceChild("Rarm", CubeListBuilder.create()
                .texOffs(27, 29).addBox(0.0F, 10.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(26, 30).addBox(-1.0F, 10.0F, 1.0F, 2F, 1F, 0F)
                .texOffs(17, 11).addBox(-1.0F, 0.0F, 0.0F, 2F, 10F, 1F), PartPose.offsetAndRotation(1.0F, -20.0F, 3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Larm = bone.addOrReplaceChild("Larm", CubeListBuilder.create()
                .texOffs(21, 10).addBox(-1.0F, 0.0F, -1.0F, 2F, 10F, 1F)
                .texOffs(26, 30).addBox(-1.0F, 10.0F, -1.0F, 2F, 1F, 0F)
                .texOffs(27, 29).addBox(0.0F, 10.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -20.0F, -3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rleg = bone.addOrReplaceChild("Rleg", CubeListBuilder.create()
                .texOffs(18, 11).addBox(0.0F, 0.0F, 0.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(27, 29).addBox(-1.0F, 11.0F, 1.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, 1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Lleg = bone.addOrReplaceChild("Lleg", CubeListBuilder.create()
                .texOffs(16, 4).addBox(0.0F, 0.0F, -1.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, -1.0F, 1F, 1F, 0F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, -1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Head = bone.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(3, 12).addBox(-4.0F, 0.0F, -2.0F, 4F, 2F, 4F)
                .texOffs(2, 13).addBox(-4.0F, -7.0F, -3.0F, 6F, 7F, 6F)
                .texOffs(8, 6).addBox(-3.0F, -8.0F, -2.0F, 4F, 1F, 4F), PartPose.offsetAndRotation(1.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }
    public static LayerDefinition createGuardLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(17, 15).addBox(1.0F, -27.0F, -1.0F, 1F, 6F, 2F)
                .texOffs(0, 0).addBox(-1.0F, -21.0F, -3.0F, 4F, 5F, 6F)
                .texOffs(1, 2).addBox(0.0F, -16.0F, -2.0F, 3F, 4F, 4F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rarm = bone.addOrReplaceChild("Rarm", CubeListBuilder.create()
                .texOffs(0, 27).addBox(-13.0F, 10.0F, 0.0F, 11F, 1F, 1F)
                .texOffs(12, 7).addBox(-2.0F, 10.0F, 0.0F, 3F, 1F, 1F)
                .texOffs(16, 8).addBox(-2.0F, 9.0F, 0.0F, 0F, 3F, 1F)
                .texOffs(11, 4).addBox(-2.0F, 10.0F, -1.0F, 0F, 1F, 3F)
                .texOffs(13, 4).addBox(-1.0F, 0.0F, 0.0F, 2F, 10F, 1F), PartPose.offsetAndRotation(1.0F, -20.0F, 3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Larm = bone.addOrReplaceChild("Larm", CubeListBuilder.create()
                .texOffs(13, 4).addBox(-1.0F, 0.0F, -1.0F, 2F, 10F, 1F)
                .texOffs(26, 30).addBox(-1.0F, 10.0F, -1.0F, 2F, 1F, 0F)
                .texOffs(27, 29).addBox(0.0F, 10.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -20.0F, -3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rleg = bone.addOrReplaceChild("Rleg", CubeListBuilder.create()
                .texOffs(13, 4).addBox(0.0F, 0.0F, 0.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(27, 29).addBox(-1.0F, 11.0F, 1.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, 1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Lleg = bone.addOrReplaceChild("Lleg", CubeListBuilder.create()
                .texOffs(13, 4).addBox(0.0F, 0.0F, -1.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, -1.0F, 1F, 1F, 0F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, -1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Head = bone.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(3, 12).addBox(-4.0F, 0.0F, -2.0F, 4F, 2F, 4F)
                .texOffs(2, 13).addBox(-4.0F, -7.0F, -3.0F, 6F, 7F, 6F)
                .texOffs(11, 16).addBox(-3.0F, -8.0F, -2.0F, 4F, 1F, 4F), PartPose.offsetAndRotation(1.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }
    public static LayerDefinition createZombieLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create()
                .texOffs(17, 15).addBox(1.0F, -27.0F, -1.0F, 1F, 6F, 2F)
                .texOffs(0, 0).addBox(-1.0F, -21.0F, -3.0F, 4F, 5F, 6F)
                .texOffs(18, 5).addBox(0.0F, -16.0F, -2.0F, 3F, 4F, 4F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rarm = bone.addOrReplaceChild("Rarm", CubeListBuilder.create()
                .texOffs(13, 4).addBox(-1.0F, 0.0F, 0.0F, 2F, 10F, 1F)
                .texOffs(26, 30).addBox(-1.0F, 10.0F, 1.0F, 2F, 1F, 0F)
                .texOffs(27, 29).addBox(0.0F, 10.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -20.0F, 3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Larm = bone.addOrReplaceChild("Larm", CubeListBuilder.create()
, PartPose.offsetAndRotation(1.0F, -20.0F, -3.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Rleg = bone.addOrReplaceChild("Rleg", CubeListBuilder.create()
                .texOffs(13, 4).addBox(0.0F, 0.0F, 0.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F)
                .texOffs(27, 29).addBox(-1.0F, 11.0F, 1.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, 1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Lleg = bone.addOrReplaceChild("Lleg", CubeListBuilder.create()
                .texOffs(13, 4).addBox(0.0F, 0.0F, -1.0F, 2F, 12F, 1F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, -1.0F, 1F, 1F, 0F)
                .texOffs(27, 30).addBox(-1.0F, 11.0F, 0.0F, 1F, 1F, 0F), PartPose.offsetAndRotation(1.0F, -12.0F, -1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition Head = bone.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(3, 12).addBox(-4.0F, 0.0F, -2.0F, 4F, 2F, 4F)
                .texOffs(2, 13).addBox(-4.0F, -7.0F, -3.0F, 6F, 7F, 6F)
                .texOffs(11, 16).addBox(-3.0F, -8.0F, -2.0F, 4F, 1F, 4F), PartPose.offsetAndRotation(1.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override public void setupAnim(AlienEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.yRot = netHeadYaw * ((float)Math.PI / 180.0F); head.xRot = headPitch * ((float)Math.PI / 180.0F);
        leftArm.zRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
        leftLeg.zRot = -Mth.cos(limbSwing) * limbSwingAmount;
        rightLeg.zRot = Mth.cos(limbSwing) * limbSwingAmount;
        rightArm.zRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        if (kind == Kind.ZOMBIE) rightArm.zRot += 0.4363F;
    }
    @Override public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) { bone.render(pose, buffer, light, overlay, red, green, blue, alpha); }
}
