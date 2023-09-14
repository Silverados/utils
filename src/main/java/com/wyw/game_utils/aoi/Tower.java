package com.wyw.game_utils.aoi;

import com.wyw.game_utils.unit.BaseUnit;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Tower {
    private final int xNo;
    private final int yNo;
    private final int width;
    private final int height;
    private final int range;

    Set<BaseUnit> watchers;

    public Tower(int xNo, int yNo, int width, int height, int range) {
        this.xNo = xNo;
        this.yNo = yNo;
        this.width = width;
        this.height = height;
        this.range = range;

        watchers = new HashSet<>();
    }

    public void addWatcher(BaseUnit unit) {
        watchers.add(unit);
    }

    public void removeWatcher(BaseUnit unit) {
        watchers.remove(unit);
    }

    public void notifyWatchers() {

    }

}
