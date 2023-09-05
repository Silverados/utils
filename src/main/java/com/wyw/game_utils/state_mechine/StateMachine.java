package com.wyw.game_utils.state_mechine;

public class StateMachine<T> {

    public BaseFSMState<T> currentState;
    public T t;

    public StateMachine(T t) {
        this.t = t;
    }

    public void update() {
        for (FSMTransition<T> transition : currentState.transitionList) {
            if (transition.isValid(t)) {
                transition.onTransition(t);
                currentState.onExit(t);
                BaseFSMState<T> nextState = transition.nextState();
                nextState.onEnter(t);
                currentState = nextState;
                break;
            }
        }

        currentState.onUpdate(t);
    }
}
