package com.jelly.MightyMiner.baritone.automine.config;

import com.jelly.MightyMiner.utils.BlockUtils;

import java.util.Arrays;

public class WalkBaritoneConfig extends BaritoneConfig{

    public WalkBaritoneConfig(int minY, int maxY, int restartTimeThreshold){
        super(MiningType.NONE,
                false,
                false,
                false,
                200,
                restartTimeThreshold,
                null,
                Arrays.asList(BlockUtils.walkables),
                maxY,
                minY);
    }
}