package com.jelly.MightyMiner.baritone.automine.pathing.config;

import com.jelly.MightyMiner.baritone.automine.config.MineBehaviour;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

import java.util.List;

public class PathBehaviour{
    List<Block> forbiddenMiningBlocks;
    List<Block> allowedMiningBlocks;
    int maxY;
    int minY;
    int searchRadius;

    public PathBehaviour(List<Block> forbiddenMiningBlocks, List<Block> allowedMiningBlocks, int maxY, int minY, int searchRadius) {
        this.forbiddenMiningBlocks = forbiddenMiningBlocks;
        this.allowedMiningBlocks = allowedMiningBlocks;
        this.maxY = maxY;
        this.minY = minY;
        this.searchRadius = searchRadius;
    }



    public List<Block> getForbiddenMiningBlocks() {
        return forbiddenMiningBlocks;
    }

    public List<Block> getAllowedMiningBlocks() {
        return allowedMiningBlocks;
    }


    public int getMaxY() {
        return maxY;
    }

    public int getMinY() {
        return minY;
    }

    public int getSearchRadius() {
        return searchRadius;
    }

}
