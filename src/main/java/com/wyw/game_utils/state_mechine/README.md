# 状态机

状态机可以视为 因为发生了什么 由A状态 转化到了B状态。 这里可能的的动作有 转化过程做的，A退出做的，B进入做的。

怪物 划分以下几种状态：
- 空闲: IdleState
- 巡逻: PatrolState
- 追击: ChaseState
- 攻击: AttackState
- 返回: ReturnState
- 死亡: DeadState



unit订阅了几种事件，事件驱动了状态的变更