# AOI (Area of interest)



## 九宫格
将地图划分为一个格子。有的实现会叫灯塔。
九宫格划分

<a href="https://iyichen.xyz/2020/04/talk-about-aoi/">...</a>
以玩家移动，进入，离开为例：
进入：根据目标玩家坐标计算出玩家所属灯塔，将玩家添加到该灯塔管理的玩家列表，如果灯塔上绑定了观察者，则通知观察者有玩家进入。然后找出玩家视野内的所有灯塔，将自身绑定为灯塔的观察者，同时将灯塔的观察者列表发送给玩家，添加至玩家维护的玩家列表。
离开：根据目标玩家坐标计算出玩家所属灯塔，将玩家从该灯塔管理的玩家列表中删除，如果灯塔上绑定了观察者，则通知观察者有玩家离开。然后找出玩家视野内的所有灯塔，解除灯塔的观察者绑定，同时将灯塔的观察者列表发送给玩家，从玩家维护的玩家列表中移除。
移动：
玩家视野内的灯塔没有变动：不做灯塔操作，对玩家维护的玩家列表发送移动消息
玩家视野内的灯塔发生变动：对旧灯塔执行对象离开逻辑，对新灯塔执行对象进入逻辑，前后视野交集内的灯塔不需要执行解绑和绑定操作，仅发送移动消息
可以看到灯塔的视野越小，在碰撞检测时过滤的无效对象就越多，整张地图灯塔的数目也就越多，消耗的内存就越大，而且对象进出灯塔的计算量就越多，而灯塔的视野越大，在碰撞检测时能过滤的无效对象就越少，如果只有一个灯塔的情况，那就是全局查找方式了。
云风的博客中还提到了另一种优化方式，使用六边形灯塔代替方形灯塔，在这种情况下，每个玩家最多只会被3个灯塔观察。