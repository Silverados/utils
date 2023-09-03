package com.wyw.game_utils.state_mechine;

public interface FSMState<T> {
    void onEnter();

    void onUpdate();

    void onExit();
}
