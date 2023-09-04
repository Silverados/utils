package com.wyw.game_utils.unit.monster;

import com.wyw.game_utils.state_mechine.StateMachine;
import com.wyw.game_utils.unit.FightUnit;

public class Monster extends FightUnit {
    public Monster() {
        stateMachine = new StateMachine<Monster>(this);
    }
}
