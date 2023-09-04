package com.wyw.game_utils.unit.monster;

import com.wyw.game_utils.state_mechine.BaseFSMState;
import com.wyw.game_utils.state_mechine.FSMTransition;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MonsterState extends BaseFSMState<Monster> {
    protected MonsterStateEnum stateEnum;
    public MonsterState(MonsterStateEnum stateEnum) {
        super();
        this.stateEnum = stateEnum;
        initTransitions();
    }

    public void initTransitions() {}


    void addTransitions(Predicate<Monster> valid, Consumer<Monster> transition, MonsterStateEnum nextState) {
        transitionList.add(new FSMTransition<>() {
            @Override
            public boolean isValid(Monster monster) {
                return valid.test(monster);
            }

            @Override
            public void onTransition(Monster monster) {
                transition.accept(monster);
            }

            @Override
            public BaseFSMState<Monster> nextState() {
                return nextState.instance;
            }
        });
    }
}
