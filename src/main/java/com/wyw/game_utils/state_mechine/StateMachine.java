package com.wyw.game_utils.state_mechine;

public class StateMachine<T> {

    public BaseFSMState<T> currentState;

    public void tick() {
        for (FSMTransition<T> transition : currentState.transitionList) {
            if (transition.isValid()) {
                transition.onTransition();
                currentState.onExit();
                BaseFSMState<T> nextState = transition.nextState();
                nextState.onEnter();
                currentState = nextState;
                break;
            }
        }

        currentState.onUpdate();
    }
}
