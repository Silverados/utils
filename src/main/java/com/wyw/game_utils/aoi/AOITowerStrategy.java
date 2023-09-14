package com.wyw.game_utils.aoi;

import com.wyw.game_utils.unit.BaseUnit;
import com.wyw.utils.MathUtils;
import lombok.Getter;

import java.util.List;
import java.util.function.Consumer;

/**
 * <a href="https://iyichen.xyz/2020/04/talk-about-aoi/">...</a>
 * 以玩家移动，进入，离开为例：
 * 进入：根据目标玩家坐标计算出玩家所属灯塔，将玩家添加到该灯塔管理的玩家列表，如果灯塔上绑定了观察者，则通知观察者有玩家进入。然后找出玩家视野内的所有灯塔，将自身绑定为灯塔的观察者，同时将灯塔的观察者列表发送给玩家，添加至玩家维护的玩家列表。
 * 离开：根据目标玩家坐标计算出玩家所属灯塔，将玩家从该灯塔管理的玩家列表中删除，如果灯塔上绑定了观察者，则通知观察者有玩家离开。然后找出玩家视野内的所有灯塔，解除灯塔的观察者绑定，同时将灯塔的观察者列表发送给玩家，从玩家维护的玩家列表中移除。
 * 移动：
 * 玩家视野内的灯塔没有变动：不做灯塔操作，对玩家维护的玩家列表发送移动消息
 * 玩家视野内的灯塔发生变动：对旧灯塔执行对象离开逻辑，对新灯塔执行对象进入逻辑，前后视野交集内的灯塔不需要执行解绑和绑定操作，仅发送移动消息
 * 可以看到灯塔的视野越小，在碰撞检测时过滤的无效对象就越多，整张地图灯塔的数目也就越多，消耗的内存就越大，而且对象进出灯塔的计算量就越多，而灯塔的视野越大，在碰撞检测时能过滤的无效对象就越少，如果只有一个灯塔的情况，那就是全局查找方式了。
 * 云风的博客中还提到了另一种优化方式，使用六边形灯塔代替方形灯塔，在这种情况下，每个玩家最多只会被3个灯塔观察。
 */
public class AOITowerStrategy implements AOIStrategy<BaseUnit> {
    @Getter
    private final int width;
    @Getter
    private final int height;
    @Getter
    private final int towerRange;

    public final int xTowerSize;
    public final int yTowerSize;

    public final Tower[][] towers;

    public AOITowerStrategy(int width, int height, int towerRange) {
        this.width = width;
        this.height = height;
        this.towerRange = towerRange;

        xTowerSize = MathUtils.ceilDiv(width, towerRange);
        yTowerSize = MathUtils.ceilDiv(height, towerRange);
        towers = new Tower[xTowerSize][yTowerSize];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                towers[i][j] = new Tower(i, j, width, height, towerRange);
            }
        }
    }

    public Tower getTower(float x, float y) {
        return towers[MathUtils.floorDiv(x, towerRange)][MathUtils.floorDiv(y, towerRange)];
    }

    public Tower getTower(Vector2 pos) {
        return getTower(pos.x, pos.y);
    }

    public void towersWork(BaseUnit unit, Vector2 curPos, Consumer<Tower> task) {
        Edges edges = getEdges(unit, curPos);
        Tower leftBottom = getTower(new Vector2(edges.left, edges.bottom));
        Tower rightTop = getTower(new Vector2(edges.right, edges.top));

        // 这里是用位置求tower 减少除法
        for (var xNo = leftBottom.getXNo(); xNo <= rightTop.getXNo(); xNo++) {
            for (var yNo = leftBottom.getYNo(); yNo <= rightTop.getYNo(); yNo++) {
                Tower tower = towers[xNo][yNo];
                task.accept(tower);
            }
        }
    }

    private Edges getEdges(BaseUnit unit, Vector2 curPos) {
        float halfWidth = unit.viewWidth / 2;
        float halfHeight = unit.viewHeight / 2;
        var left = Math.max(curPos.x - halfWidth, 0);
        var right = Math.min(curPos.x + halfHeight, width);
        var top = Math.min(curPos.y + halfHeight, height);
        var bottom = Math.max(curPos.y - halfWidth, 0);
        return new Edges(left, right, top, bottom);
    }

    public void towersWorkBak(BaseUnit unit, Vector2 curPos, Consumer<Tower> task) {
        Edges edges = getEdges(unit, curPos);

        // 这里是用位置求tower
        for (var x = edges.left; x <= edges.right; ) {
            for (var y = edges.bottom; y <= edges.top; ) {
                Tower tower = getTower(x, y);
                task.accept(tower);
                y += edges.top - y < towerRange ? edges.top - y : towerRange;
            }
            x += edges.right - x < towerRange ? edges.right - x : towerRange;
        }
    }

    @Override
    public void enter(BaseUnit unit, Vector2 pos) {
        towersWork(unit, pos, Tower::notifyWatchers);
    }

    @Override
    public void move(BaseUnit unit, Vector2 fromPos, Vector2 toPos) {

    }

    @Override
    public void exit(BaseUnit unit, Vector2 pos) {

    }

    private static class Edges {
        public final float left;
        public final float right;
        public final float top;
        public final float bottom;

        public Edges(float left, float right, float top, float bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }
    }
}
