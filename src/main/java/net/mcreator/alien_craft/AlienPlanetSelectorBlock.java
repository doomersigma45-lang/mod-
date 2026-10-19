package net.mcreator.alien_craft;

import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.BlockHitResult;

/** Right-clicking the selector travels between the Overworld and Alien Planet. */
public class AlienPlanetSelectorBlock extends Block {
    public static final ResourceKey<Level> ALIEN_PLANET = ResourceKey.create(
            Registries.DIMENSION, new ResourceLocation(AlienCraft.MOD_ID, "alien_planet"));

    public AlienPlanetSelectorBlock(Properties properties) { super(properties); }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        if (!(player instanceof ServerPlayer serverPlayer)) return InteractionResult.PASS;
        ServerLevel target = serverPlayer.serverLevel().dimension().equals(ALIEN_PLANET)
                ? serverPlayer.getServer().getLevel(Level.OVERWORLD)
                : serverPlayer.getServer().getLevel(ALIEN_PLANET);
        if (target == null) {
            serverPlayer.displayClientMessage(Component.literal("Alien Planet não está disponível."), true);
            return InteractionResult.FAIL;
        }
        int y = target.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 0, 0) + 1;
        if (target.isEmptyBlock(new BlockPos(0, y, 0))) {
            target.getStructureManager().get(new ResourceLocation(AlienCraft.MOD_ID, "alienbase3")).ifPresent(template ->
                    template.placeInWorld(target, new BlockPos(0, y, 0), new BlockPos(0, y, 0),
                            new StructurePlaceSettings(), RandomSource.create(), Block.UPDATE_CLIENTS));
        }
        serverPlayer.teleportTo(target, 0.5D, y, 0.5D, Set.of(), serverPlayer.getYRot(), serverPlayer.getXRot());
        return InteractionResult.CONSUME;
    }
}
