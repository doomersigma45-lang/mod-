package net.mcreator.alien_craft;

import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/** The selector moves the player between the overworld and the Alien Planet. */
public class AlienPlanetSelectorBlock extends Block {
    public static final ResourceKey<Level> ALIEN_PLANET = ResourceKey.create(
            Registries.DIMENSION, new ResourceLocation(AlienCraft.MOD_ID, "alien_planet"));

    private static final String RETURN_DIMENSION = "alien_craft_return_dimension";
    private static final String RETURN_X = "alien_craft_return_x";
    private static final String RETURN_Z = "alien_craft_return_z";
    private static final String RETURN_YAW = "alien_craft_return_yaw";
    private static final double ALIEN_LANDING_X = 8192.0D;
    private static final double ALIEN_LANDING_Z = 8192.0D;
    private static final int LANDING_FLOOR_Y = 63;
    private static final int LANDING_PLAYER_Y = LANDING_FLOOR_Y + 1;

    public AlienPlanetSelectorBlock(Properties properties) { super(properties); }

    private static void prepareLandingPad(ServerLevel level, int centerX, int centerZ) {
        // Force the landing chunk to exist before placing the pad. This also covers
        // flat or void worldgen settings that fail to provide terrain.
        level.getChunk(centerX >> 4, centerZ >> 4);
        BlockState floor = AlienCraft.ALIENFLOOR.get().defaultBlockState();
        BlockState foundation = AlienCraft.ASTEROIDBLOCK.get().defaultBlockState();
        for (int x = centerX - 2; x <= centerX + 2; x++) {
            for (int z = centerZ - 2; z <= centerZ + 2; z++) {
                level.setBlock(new BlockPos(x, LANDING_FLOOR_Y, z), floor, Block.UPDATE_ALL);
                for (int y = LANDING_FLOOR_Y - 1; y >= LANDING_FLOOR_Y - 3; y--) {
                    level.setBlock(new BlockPos(x, y, z), foundation, Block.UPDATE_ALL);
                }
                level.setBlock(new BlockPos(x, LANDING_FLOOR_Y - 4, z),
                        Blocks.BEDROCK.defaultBlockState(), Block.UPDATE_ALL);
                for (int y = LANDING_PLAYER_Y; y <= LANDING_PLAYER_Y + 3; y++) {
                    level.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        if (!(player instanceof ServerPlayer serverPlayer)) return InteractionResult.PASS;

        boolean returning = serverPlayer.serverLevel().dimension().equals(ALIEN_PLANET);
        CompoundTag playerData = serverPlayer.getPersistentData();
        ServerLevel target;
        double x;
        double z;
        float yaw;

        if (returning) {
            String dimensionId = playerData.getString(RETURN_DIMENSION);
            ResourceKey<Level> returnDimension = dimensionId.isEmpty()
                    ? Level.OVERWORLD
                    : ResourceKey.create(Registries.DIMENSION, new ResourceLocation(dimensionId));
            target = serverPlayer.getServer().getLevel(returnDimension);
            if (target == null) target = serverPlayer.getServer().getLevel(Level.OVERWORLD);
            x = playerData.contains(RETURN_X) ? playerData.getDouble(RETURN_X) : serverPlayer.getX();
            z = playerData.contains(RETURN_Z) ? playerData.getDouble(RETURN_Z) : serverPlayer.getZ();
            yaw = playerData.contains(RETURN_YAW) ? playerData.getFloat(RETURN_YAW) : serverPlayer.getYRot();
            playerData.remove(RETURN_DIMENSION);
            playerData.remove(RETURN_X);
            playerData.remove(RETURN_Z);
            playerData.remove(RETURN_YAW);
        } else {
            target = serverPlayer.getServer().getLevel(ALIEN_PLANET);
            playerData.putString(RETURN_DIMENSION, serverPlayer.serverLevel().dimension().location().toString());
            playerData.putDouble(RETURN_X, serverPlayer.getX());
            playerData.putDouble(RETURN_Z, serverPlayer.getZ());
            playerData.putFloat(RETURN_YAW, serverPlayer.getYRot());
            x = ALIEN_LANDING_X;
            z = ALIEN_LANDING_Z;
            yaw = serverPlayer.getYRot();
        }

        if (target == null) {
            serverPlayer.displayClientMessage(Component.literal("Alien Planet não está disponível neste mundo."), true);
            return InteractionResult.FAIL;
        }

        if (!returning) {
            prepareLandingPad(target, BlockPos.containing(x, 0.0D, z).getX(),
                    BlockPos.containing(x, 0.0D, z).getZ());
        }
        serverPlayer.teleportTo(target, x + 0.5D, returning ? target.getSharedSpawnPos().getY() : LANDING_PLAYER_Y,
                z + 0.5D, Set.of(), yaw, serverPlayer.getXRot());
        serverPlayer.displayClientMessage(Component.literal(returning
                ? "Você voltou ao seu mundo."
                : "Você chegou ao Planeta Alienígena."), true);
        return InteractionResult.CONSUME;
    }
}
