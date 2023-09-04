package com.wyw.game_utils.state_mechine;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFSMState<T> implements FSMState<T>{

    protected List<FSMTransition<T>> transitionList;

    public BaseFSMState() {
        transitionList = new ArrayList<>();
    }

    @Override
    public void onEnter(T t) {

    }

    @Override
    public void onUpdate(T t) {

    }

    @Override
    public void onExit(T t) {

    }

}
