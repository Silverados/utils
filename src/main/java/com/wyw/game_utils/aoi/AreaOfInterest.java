package com.wyw.game_utils.aoi;

import com.wyw.utils.MathUtils;

public class AreaOfInterest {
    public final int width;
    public final int height;
    public final int gridRange;

    public final int xGridSize;
    public final int yGridSize;

    public final Grid[][] grids;


    public AreaOfInterest(int width, int height, int gridRange) {
        this.width = width;
        this.height = height;
        this.gridRange = gridRange;

        xGridSize = MathUtils.ceilDiv(width, gridRange);
        yGridSize = MathUtils.ceilDiv(height, gridRange);
        grids = new Grid[xGridSize][yGridSize];
    }
}
