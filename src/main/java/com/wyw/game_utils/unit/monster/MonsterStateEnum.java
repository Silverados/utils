package com.wyw.game_utils.unit.monster;

import lombok.Getter;

public enum MonsterStateEnum {
    IdleState(new IdleState()),
    //    PatrolState,
//    ChaseState,
//    AttackState,
//    ReturnState,
    DeadState(new DeadState()),
    ;

    @Getter final MonsterState instance;

    MonsterStateEnum(MonsterState monsterState) {
        instance = monsterState;
    }
}
