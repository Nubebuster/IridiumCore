package com.iridium.iridiumcore.multiversion;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.*;
import org.bukkit.block.Block;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for working with methods that were changed during an update by Spigot.
 */
public class MultiVersion_V1_11_R1 implements MultiVersion {

    /**
     * Returns the material at a position in a chunk.
     *
     * @param chunk The snapshot of the chunk where the position is in
     * @param x The relative x position of the block in the chunk
     * @param y The relative y position of the block in the chunk
     * @param z The relative z position of the block in the chunk
     * @return The material at the provided position in the chunk
     */
    @Override
    @SuppressWarnings("deprecation")
    public XMaterial getMaterialAtPosition(ChunkSnapshot chunk, int x, int y, int z) {
        return XMaterial.matchXMaterial(Material.getMaterial(chunk.getBlockTypeId(x, y, z)));
    }

    @Override
    public boolean isPassable(Block block) {
        return block.getType().equals(Material.AIR);
    }

    @Override
    public CompletableFuture<Chunk> getChunkAt(World world, int x, int z) {
        CompletableFuture<Chunk> completableFuture = new CompletableFuture<>();
        completableFuture.complete(world.getChunkAt(x, z));
        return completableFuture;
    }

    @Override
    public CompletableFuture<Chunk> getChunkAt(Location location) {
        return getChunkAt(location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

}
