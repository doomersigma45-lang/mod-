package net.mcreator.alien_craft;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.level.Level;

public class UfoEntity extends FlyingMob {
    public UfoEntity(EntityType<? extends UfoEntity> type, Level level) {
        super(type, level);
        moveControl = new FlyingMoveControl(this, 20, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return FlyingMob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.FLYING_SPEED, 0.25D);
    }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 12.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override protected FlyingPathNavigation createNavigation(Level level) { return new FlyingPathNavigation(this, level); }
    @Override protected SoundEvent getAmbientSound() { return AlienCraft.UFO_VOICE.get(); }
    @Override protected SoundEvent getHurtSound(DamageSource source) { return net.minecraft.sounds.SoundEvents.ANVIL_PLACE; }
    @Override protected SoundEvent getDeathSound() { return net.minecraft.sounds.SoundEvents.ANVIL_BREAK; }
}
