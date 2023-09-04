package com.wyw.game_utils.unit.monster;

import com.wyw.game_utils.state_mechine.BaseFSMState;
import com.wyw.game_utils.state_mechine.FSMTransition;

public class DeadState extends MonsterState{
    public DeadState() {
        super(MonsterStateEnum.DeadState);
    }
    public DeadState(MonsterStateEnum stateEnum) {
        super(MonsterStateEnum.DeadState);
    }
}
