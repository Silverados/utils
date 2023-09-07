package com.wyw.game_utils.aoi;

import com.wyw.game_utils.unit.BaseUnit;
import com.wyw.utils.MathUtils;
import lombok.Getter;

public class AreaOfInterest {
    @Getter
    private final int width;
    @Getter
    private final int height;
    @Getter
    private final int towerRange;

    public final int xTowerSize;
    public final int yTowerSizer;

    public final Tower[][] towers;

    public AreaOfInterest(int width, int height, int towerRange) {
        this.width = width;
        this.height = height;
        this.towerRange = towerRange;

        xTowerSize = MathUtils.ceilDiv(width, towerRange);
        yTowerSizer = MathUtils.ceilDiv(height, towerRange);
        towers = new Tower[xTowerSize][yTowerSizer];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                towers[i][j] = new Tower(i, j, width, height, towerRange);
            }
        }
    }

    public Tower getTower(int x, int y) {
        return towers[MathUtils.ceilDiv(x, towerRange)][MathUtils.ceilDiv(y, towerRange)];
    }

    public Tower getTower(Vector2 pos) {
        return getTower((int)pos.x, (int)pos.y);
    }

    public void onEnter(BaseUnit unit) {}

    public void move(BaseUnit unit) {}

    public void onExit(BaseUnit unit) {}
}
