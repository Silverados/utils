package com.wyw.game_utils.state_mechine;

public interface FSMTransition<T> {
    boolean isValid();
    void onTransition();
    BaseFSMState<T> nextState();
}
