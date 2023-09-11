package com.wyw.game_utils.aoi;

public interface AOIStrategy<T> {
    void enter(T unit, Vector2 pos);
    void move(T unit, Vector2 fromPos, Vector2 toPos);
    void exit(T unit, Vector2 pos);
}
