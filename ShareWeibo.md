## 最近两门考试，进度会很慢，一点一点的更新


###  2017年11月27日

***

完成了：
- BottomNavigationView
- navigation

下一步：
- 写Recylerview和adapter
- 增加下拉刷新的功能

需要学习的东西：
- 微博文档的使用

### 2017年11月28日

***

- 试了一下OAuth授权
- 试了一下postman和获取当前登录用户及其所关注（授权）用户的最新微博
- 图片的准备

下一步：
- 开发所有的接口
- 界面合理
- 代码结构MVP

#### 2017年11月29日

***

- 尝试着添加第一个fragment
- 现在获取个别内容进行实验

下一步：
- 开发全部微博的fragment
- 原形头像的学习
- 开发用户信息界面

#### 2017年11月30日

***

- 原形头像的显示
- 内容格式化
- 自定义View，优雅的显示多图片

下一步：

- 考试在即，准备复习
- 完成全部微博界面的完整功能

遇到的坑：

正则表达式匹配时没有 find();坑了好长时间，气氛


#### 2017年12月3日

***

- GridLayout动态展示addView
- DrawerLayout的主要代码实现

下一步：

- 继续完成全部微博界面
- 界面元素保存到本地内存中去
- 加上视频的View
- 完成fragment的可选择功能

遇到的坑：

动态添加imageView时忘记设置width和height
界面不好看

#### 2017年12月4日

***

- json存储到本地文件
- 加一个登陆多账号切换activity

遇到的坑：

- 没有debug也就暂时没有遇到坑

下一步：
- 完成 **登陆多账号切换activity**


#### 12月6号

- 完成了全部微博里的retweeted_status的内容
- 完成我的微博的界面（和全部微博的界面一样）
- debug 保存json内容到手机（手机没root导致不能看是否保存成功）

下一步
- 完成 分组信息功能 
- 完成 **登陆多账号切换activity** 
- *root*手机

坑：
- 手机没root不能看json是否储存成功

要注意的点：
- MVP的学习

#### 12月8日

周日有考试，无法写太多

添加CommentActivity

#### 12月9日

学习到的东西：

- butterKnife
- 格式化格式

#### 12月12日

完成:
- view的bind替换成ButterKnife
- 重新分一下包
- 格式化代码
- debug模式不输出Log信息
- 尝试MVP
- 学习自定义Dialog

坑：

- radioGroup中`onCheckedChanged(RadioGroup radioGroup, int i)`中的i是resourceId不是位置i;

#### 12月13日

MVP：
- View 层：视图层，包含界面的相关的功能。例如各种Activity、Fragment、View、Adapter等。
- Presenter层:逻辑控制层
- Model层:封装各种数据来源。例如远程网络数据，本地数据库数据，对Presenter层提供简单易用的数据接口。

> - https://github.com/googlesamples/android-architecture
> - https://github.com/googlesamples/android-architecture/tree/todo-mvp/todoapp

BuildConfig里添加AppKey重要信息

