package com.wyw.game_utils.unit.monster;

public class IdleState extends MonsterState {
    public IdleState() {
        super(MonsterStateEnum.IdleState);
    }

    public IdleState(MonsterStateEnum state) {
        super(MonsterStateEnum.IdleState);
    }

    @Override
    public void initTransitions() {
        super.initTransitions();

        addTransitions((monster) -> {
            return true;
        }, (monster) -> {
            // doSomething
        }, MonsterStateEnum.DeadState);
    }
}
