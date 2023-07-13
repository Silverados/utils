package com.wyw.game_utils;

import lombok.Data;

import java.util.Map;
import java.util.Optional;

/**
 * 升级一般有两种方式：
 * 1. 经验达到当前等级上限，升级
 * 2. 升级，消耗经验
 *
 * 实际上都是消耗经验 升级
 */
public class LevelUpUtils {
    // K是当前 V是升级所需要的经验
    // 最大是4级
    public static final Map<Integer, Integer> expToLevelUpConfig = Map.of(1, 100, 2, 1000, 3, 10000);

    public static void main(String[] args) {
        Player player = new Player();
        addExp(player, 1000);
        addExp(player, 1000);
        addExp(player, 10000);
    }

    private static void addExp(Player player, int exp) {
        int beforeLevel = player.getLevel();
        int beforeExp = player.getExp();

        if (beforeLevel == getMaxLevel()) {
            log("max limit");
            return;
        }

        // 需要注意 添加的经验 可能足够升好几级
        while (player.getLevel() < getMaxLevel() && exp > 0) {
            // 当前等级的上限
            int levelExpLimit = expToLevelUpConfig.get(player.getLevel());
            // 当前升级所需的经验
            int cost = levelExpLimit - player.getExp();
            if (cost <= exp) {
                player.setLevel(player.getLevel() + 1);
                // 如果满级 经验条设置为0 / 最大值
                if (player.getLevel() == getMaxLevel()) {
                    player.setExp(levelExpLimit);
                } else {
                    player.setExp(0);
                }
            } else {
                player.setExp(player.getExp() + exp);
            }
            exp -= cost;
        }

        log(String.format("beforeLevel: %d, beforeExp: %d, afterLevel: %d, afterExp: %d", beforeLevel, beforeExp, player.getLevel(), player.getExp()));
    }

    private static int getMaxLevel() {
        Optional<Integer> max = expToLevelUpConfig.keySet().stream().max(Integer::compareTo);
        return max.orElse(3) + 1;
    }

    public static void log(String str) {
        System.out.println(str);
    }

    @Data
    public static class Player {
        private int level = 1;
        private int exp;
    }
}
