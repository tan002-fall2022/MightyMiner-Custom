package com.jelly.MightyMiner.macros.macros;

import com.jelly.MightyMiner.MightyMiner;
import com.jelly.MightyMiner.baritone.automine.AutoMineBaritone;
import com.jelly.MightyMiner.baritone.automine.config.AutoMineType;
import com.jelly.MightyMiner.baritone.automine.config.BaritoneConfig;
import com.jelly.MightyMiner.handlers.KeybindHandler;
import com.jelly.MightyMiner.handlers.MacroHandler;
import com.jelly.MightyMiner.macros.Macro;
import com.jelly.MightyMiner.utils.DrawUtils;
import com.jelly.MightyMiner.utils.LogUtils;
import com.jelly.MightyMiner.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MithrilMacro extends Macro {

    AutoMineBaritone baritone;

    List<Block> priorityBlocks = new ArrayList<Block>(){
        {
            add(Blocks.stained_hardened_clay);
            add(Blocks.prismarine);
            add(Blocks.wool);
        }
    };



    @Override
    public boolean isPaused() {
        return !enabled;
    }

    @Override
    public void Pause() {
        KeybindHandler.resetKeybindState();
        toggle();
    }

    @Override
    public void Unpause() {
        toggle();
    }

    @Override
    protected void onEnable() {
        if (isPaused()) {
            Unpause();
        }
        LogUtils.debugLog("Enabled Mithril macro checking if player is near");

        if(MightyMiner.config.mithPlayerFailsafe) {
            if(PlayerUtils.isNearPlayer(MightyMiner.config.mithPlayerRad)){
                LogUtils.addMessage("Didnt start macro since therese a player near");
                this.enabled = false;
                onDisable();
                return;
            }
        }
        LogUtils.debugLog("Didnt find any players nearby, continuing");
        baritone = new AutoMineBaritone(getMineBehaviour());

    }


    @Override
    public void onTick(TickEvent.Phase phase) {

        if(phase != TickEvent.Phase.START)
            return;

        if(MightyMiner.config.mithPlayerFailsafe) {
            if(PlayerUtils.isNearPlayer(MightyMiner.config.mithPlayerRad)){
                PlayerUtils.warpBackToIsland();
                MacroHandler.disableScript();
            }
        }

        if(MightyMiner.config.mithShiftWhenMine)
            KeybindHandler.setKeyBindState(KeybindHandler.keyBindShift, true);

        switch(baritone.getState()){
            case IDLE: case FAILED:
                baritone.mineFor(priorityBlocks.get(MightyMiner.config.mithPriority1), priorityBlocks.get(MightyMiner.config.mithPriority2), priorityBlocks.get(MightyMiner.config.mithPriority3));
                break;
        }

        useMiningSpeedBoost();
    }



    @Override
    protected void onDisable() {
        if(baritone != null) baritone.disableBaritone();
        KeybindHandler.resetKeybindState();
    }

    private BaritoneConfig getMineBehaviour(){
        return new BaritoneConfig(
                AutoMineType.STATIC,
                MightyMiner.config.mithShiftWhenMine,
                true,
                true,
                MightyMiner.config.mithRotationTime,
                MightyMiner.config.mithRestartTimeThreshold,
                null,
                null,
               256,
                0
        );
    }
}
