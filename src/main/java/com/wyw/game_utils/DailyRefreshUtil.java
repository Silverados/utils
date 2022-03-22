package com.wyw.game_utils;

import com.wyw.utils.DateTimeUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DailyRefreshUtil {
    /**
     * 几个属性可以放到一个新的对象中
     */
    @Data
    public static class Player {
        Map<EventId, DailyInfo> dailyInfoMap = new HashMap<>();
    }

    @Data
    public static class DailyInfo {
        private int todayCount;
        private LocalDateTime lastTime;
        // 有时重置时间是按刷新时间计算的 例如当天的11点 ~ 第二天的11点为一个区间
        private LocalDateTime lastResetTime;
    }

    public enum EventId {
        DRAW;
    }

    public static void main(String[] args) {
        Player player = new Player();
        doSomething(player, EventId.DRAW, true);
    }

    /**
     * 这里的写法是getter/setter，逻辑不太清晰
     * @param player 玩家
     * @param eventId 事件id
     * @param resetByField 是否根据属性重置
     */
    public static void doSomething(Player player, EventId eventId, boolean resetByField) {
        DailyInfo dailyInfo = player.dailyInfoMap.get(eventId);
        if (dailyInfo == null) {
            dailyInfo = new DailyInfo();
        }
        
        if (dailyInfo.getLastTime() == null) {
            dailyInfo.setTodayCount(0);
        } else if (resetByField) {
            if (DateTimeUtils.durationDays(dailyInfo.getLastResetTime().toLocalDate()) >= 1) {
                dailyInfo.setTodayCount(0);
            }
        } else if (DateTimeUtils.notToday(dailyInfo.getLastTime())) {
            dailyInfo.setTodayCount(0);
        }

        // do something
        if (resetByField && dailyInfo.getTodayCount() == 0) {
            dailyInfo.setLastResetTime(LocalDateTime.now());
        }
        dailyInfo.setTodayCount(dailyInfo.getTodayCount() + 1);
        dailyInfo.setLastTime(LocalDateTime.now());
        player.dailyInfoMap.put(eventId, dailyInfo);
    }
}
