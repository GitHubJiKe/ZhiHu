# ZhiHu （Author  GitHubJiKe）#
****
**为了得到一个在《知乎》团队实习和工作的机会，我做了这个Demo，鉴于个人能力有限和设备的局限性（基本上是每20分钟重启一次的节奏），我已经尽力的去实现足够多的模块，但是依然和要求相差甚远。**

**这个Demo只是简单地实现了UI布局和界面之间简单地切换，以及一些点击事件的响应。**

**没有数据源，没有联网，没有任何实用价值，只作参考。**

****

**版本支持**

* 最低android 5.0

**主要实现：**

* ViewPager实现新闻图片的滑动切换，新起线程，实现ViewPager的自动滑动
* RecycleView实现新闻条目的显示，简单实用系统分割线，自定义Adapter，添加点击事件的回调接口
* Title导航栏使用LinearLayout和RelativeLayout实现多屏幕适配
* ListPopUpWindow实现弹出式菜单（夜间模式和日间模式可以通过自定义属性和主题实现，但是由于设备问题和时间原因没有实现）
* SlidingMenu实现侧滑菜单，菜单内部通过ListView显示不同的新闻主题，和原版不同的是，原版的侧滑菜单是可以整体上下滑动的，而我实现的相对简单只是新闻主题的滑动，上方的Title保持固定
* 用户头像通过自定义ImageCircleView实现（比着原版大了一些，个人觉得原版的用户头像太小了，看着不太清晰）
* AsyncHttpClient访问网络请求数据，fast-json解析返回的数据

**使用的Jar包和Library：**

* commons-views-1.0.0.jar:ImageCircleView jar包
* SlidingMenuLibrary: 侧滑菜单Library
* fast-json:Json解析
* android-async-http-1.4.8.jar：异步请求网络
* commons-codec-1.6.jar、commons-logging-1.1.1.jar、fluent-hc-4.2.5.jar、httpclient-4.2.5.jar、httpclient-cache-4.2.5.jar、httpcore-4.2.4.jar、httpmime-4.2.5.jar：辅助 android-async-http-1.4.8.jar

#总结

* 大部分布局模仿原版的《知乎日报》，有些许的改动
* 自己对知识的掌握不够系统，零零碎碎的东西一到用的时候就无法信手拈来，还要查阅资料


#关于《知乎日报》
* 好的方面：

 * 进入主界面的欢迎界面和每个主题内的主题动图展示很炫酷
 * ImageViewLoader加载图片，有缓存机制
 * RecycleView的运用
 * 侧滑菜单的整体滑动效果很不错
 * 布局的简洁明朗
 * 配色

* 有待完善的方面：

  * 模式选择和设置选项的Title只在首页显示，用户每次更改都要从正在浏览的界面或主题返回到首页，有点繁琐
  * 侧滑菜单内的用户头像显示太小，看不太清晰
  * 侧滑菜单的整体滑动效果虽然很炫，但是有点多余，上方的用户头像和我的收藏、离线下载保持固定状态，用户的观感应该更佳（个人之见）
  * 关注主题的点击效果不是很好，因为添加图标的不够大，导致每次点击的时候都会先进入该主题，而不是关注


* 我的见解：
  * 增加检索功能，给用户带去更直接的新闻搜索体验
  
