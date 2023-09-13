package com.wyw.game_utils.aoi;

import com.wyw.game_utils.unit.BaseUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tower {
    private final int xNo;
    private final int yNo;
    private final int gridRange;
    private final int height;
    private final int range;

    Set<BaseUnit> watchers;

    public Tower(int xNo, int yNo, int gridRange, int height, int range) {
        this.xNo = xNo;
        this.yNo = yNo;
        this.gridRange = gridRange;
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
