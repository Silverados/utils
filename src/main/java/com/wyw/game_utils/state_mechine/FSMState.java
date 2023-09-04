package com.wyw.game_utils.state_mechine;

public interface FSMState<T> {
    void onEnter(T t);

    void onUpdate(T t);

    void onExit(T t);
}
