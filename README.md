# Swing_Server
Java写的游戏服务器
部署顺序
1:src/Mysql_operate/BaseDao.java配置mysql数据库
2:加载resouce/sql/game.sql数据
3:src/GameStart/ServerStart.java 启动就好了

# 过程
然后在3月份，用这个毕设面试近了一家游戏公司，专门做服务器开发。大概在5月份就要答辩，开始赶代码、赶美工。虽然很辛苦，但是过程学到了很多东西。

# 总结
项目开始时也是犹豫了java开发游戏的美观度，导师也建议再想想看。后来也是考虑到时间的问题和对java的熟悉度才决定用java开发。

1：通信就是很原始的TCP协议，一个连接一个线程。（后来了解到Netty协议，很不错）

2：并发用synchronized关键字修饰。

3：数据库连接当时也不知道怎么想的，没有用任何框架，冒泡了。。。（用mybatis工厂其实是个不错的选择）

4：信息的传输用的拼接数据，就是数据加分隔符那种，脑子又冒泡了。。。（应该用json或者直接用序列化的方式）

5：客户端的界面用java的Swing界面做。至于怎么让图片动起来，就是帧的方式，多张图片连续刷新，哈哈，很古老。说起来我写的游戏没有用任何框架，导致开发周期很长，但是也是认识到了很多问题。

6：有个小问题，因为东西太多，以至于玩家在创建角色时候，要重置 的数据也很大，而又为了满足毕设的时间要求（实则偷懒），所以做了假数据展示。

# 图片展示

* 登录界面

* ![1](https://github.com/xiaobaobao007/Swing_Server/blob/master/resouce/img/show/login.png)

* 选择英雄界面

* ![1](https://github.com/xiaobaobao007/Swing_Server/blob/master/resouce/img/show/choose.png)

  进入关卡前的界面

* ![1](https://github.com/xiaobaobao007/Swing_Server/blob/master/resouce/img/show/loading.png)

* 战斗界面1

* ![1](https://github.com/xiaobaobao007/Swing_Server/blob/master/resouce/img/show/fighting1.png)

* 战斗界面2

* ![1](https://github.com/xiaobaobao007/Swing_Server/blob/master/resouce/img/show/fighting2.png)

* 背包界面

* ![1](https://github.com/xiaobaobao007/Swing_Server/blob/master/resouce/img/show/menu.png)

* 
