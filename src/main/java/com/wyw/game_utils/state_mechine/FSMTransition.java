package com.wyw.game_utils.state_mechine;

public interface FSMTransition<T> {
    boolean isValid(T t);
    void onTransition(T t);
    BaseFSMState<T> nextState();
}
