package com.wyw.game_utils.aoi;

public class Tower {
    private final int xNo;
    private final int yNo;
    private final int gridRange;
    private final int height;
    private final int range;

    public Tower(int xNo, int yNo, int gridRange, int height, int range) {
        this.xNo = xNo;
        this.yNo = yNo;
        this.gridRange = gridRange;
        this.height = height;
        this.range = range;
    }
}
