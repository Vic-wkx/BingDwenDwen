---
highlight: androidstudio
theme: github
---

持续创作，加速成长！这是我参与「掘金日新计划 · 6 月更文挑战」的第1天，[点击查看活动详情](https://juejin.cn/post/7099702781094674468 "https://juejin.cn/post/7099702781094674468")

Android 中支持矢量图，矢量图具有放大缩小时不变模糊的特性，因为矢量图是通过图片中路径的坐标绘制的。

矢量图由直线、弧线、贝塞尔曲线组成。

# 一、M、L、z 绘制最简单的矢量图

一个最简单的矢量图代码如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="200dp"
    android:height="200dp"
    android:viewportWidth="200"
    android:viewportHeight="200">
        <path
            android:fillColor="@color/black"
            android:pathData="M0,0L100,0L100,100z" />
</vector>
```

效果图：

![矢量图](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6d5e49d7bbd3443eb848a20968458ec3~tplv-k3u1fbpfcp-watermark.image?)

我们重点关注代码中的 pathData：`pathData="M0,0L100,0L100,100z"`

* M0,0 表示移动到 0,0 坐标（Move），对应 path.moveTo() 方法
* L100,0 表示移动到 100,0 坐标（LineTo），对应 path.lineTo() 方法
* L100,100 表示移动到 100,100 坐标（LineTo）
* z 表示绘制结束（close），对应 path.close() 方法

可以看出，路径都是以 M 开头，通常以 z 结束。中间可以通过 L 移动到指定位置。

如果路径不以 z 结束，则整个路径不会闭合，也就是终点不会和起点相连。

# 二、l、h、v 符号指定相对移动距离

除了 L，还有一些符号可以表示路径的绘制方式，比如：

```xml
<path
    android:fillColor="@color/black"
    android:pathData="M0,0l100,0l0,100z" />
```

其中：

* l 表示相对移动，即路径以当前位置为起点，向 x,y 方法分别移动的距离。(lineTo)，对应 path.rLineTo() 方法

> 注：这是小写的 L，不是大写的 I

除了 l 符号，还可以使用 h、v 符号：

```xml
 <path
     android:fillColor="@color/black"
     android:pathData="M0,0h100v100z" />
```

其中：

* h100 表示水平移动 100（horizon），对应 path.rLineTo(x,0)
* v100 表示垂直移动 100（vertical），对应 path.rLineTo(0,y)

这两条 path 也能绘制出和上文一样的图案，只是写法上的区别。

类似地：
* H 表示水平移动到绝对位置，对应 path.lineTo(x,y不变)
* V 表示垂直移动到绝对位置，对应 path.lineTo(x不变,y)

# 三、Q、C 绘制贝塞尔曲线

贝塞尔曲线是由法国工程师贝塞尔为了设计汽车主体所发明的，后来被广泛用于图像绘制领域。

一阶、二阶、三阶、四阶贝塞尔曲线示例：

![bezier.gif](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/cc084055cfe443c2b82429fb21e2ca2b~tplv-k3u1fbpfcp-watermark.image?)

通俗地解释一下贝塞尔曲线：

* 一阶贝塞尔曲线等于直线，从起点到终点，没有任何干扰。
* 二阶贝塞尔曲线可以看做由起点和终点组成的直线被一个点“扯弯了”
* 三阶贝塞尔曲线可以看做由起点和终点组成的直线被两个点扯弯了，距离这条直线越远的点，扯的力气越大
* 四阶贝塞尔曲线可以看做由起点和终点组成的直线被三个点扯弯了。

专业一点的解释可以看下图：

![二阶贝塞尔曲线](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8a4ce59f968a4dfa83ab6f9419a942c6~tplv-k3u1fbpfcp-watermark.image?)

以二阶贝塞尔曲线为例：在一条二阶贝塞尔曲线中，绘制路径上的每个点都满足 `DF : DE= AD : AB = BE : BC`。

更专业的解释需要用到一些数学知识，这里直接贴维基百科的公式：

![贝塞尔曲线计算方式](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4c949d79804846008b6e63790b096518~tplv-k3u1fbpfcp-watermark.image?)

公式比较复杂，但只要知道了起点、终点，以及将直线扯弯的“扯点”，就能很容易地用代码绘制出一条二阶贝塞尔曲线：

```kotlin
val path = Path()
val start = PointF(0f, 0f)
val end = PointF(100f, 0f)
val drag = PointF(50f, 50f)
// 移动到起点
path.moveTo(start.x, start.y)
// 二阶贝塞尔曲线：经过“扯点”到达终点
path.quadTo(drag.x, drag.y, end.x, end.y)
```

先将 path 移动到起点，再经由“扯点”到达终点，就形成了一条二阶贝塞尔曲线。

> 注：“扯点”是我胡扯的名字，但我认为这个名字可以使贝塞尔曲线更容易理解。

再看一个三阶贝塞尔曲线的例子：

```kotlin
val path = Path()
val start = PointF(0f, 0f)
val end = PointF(100f, 0f)
val drag1 = PointF(50f, 50f)
val drag2 = PointF(75f, 75f)
// 移动到起点
path.moveTo(start.x, start.y)
// 三阶贝塞尔曲线：经过两个“扯点”到达终点
path.cubicTo(drag1.x, drag1.y, drag2.x, drag2.y, end.x, end.y)
```

同样先将 path 移动到起点，再经由两个“扯点”到达终点，就形成了一条三阶贝塞尔曲线。

Android 中只支持二阶和三阶贝塞尔曲线，对应字母 Q、C：

* Q 表示二阶贝塞尔曲线（quadratic），对应 path.quadTo()
* C 表示三阶贝塞尔曲线（cubic），对应 path.cubicTo()

绘制一条最简单的二阶贝塞尔曲线的矢量图，路径如下：

```xml
<path
    android:fillColor="@color/black"
    android:pathData="M0,0 Q50,50 100,0z" />
```

这条路径表示从起点 0,0 经由扯点 50,50 到达终点 100,0。效果图如下：


![二阶贝塞尔曲线矢量图](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/60a48eaf636a4f7abc67f8a0edaf4360~tplv-k3u1fbpfcp-watermark.image?)


绘制一条最简单的三阶贝塞尔曲线的矢量图，路径如下：

```xml
<path
    android:fillColor="@color/black"
    android:pathData="M0,0 C50,50 75,75 100,0z" />
```

这条路径表示从起点 0,0 经由扯点 50,50 和扯点 75,75 到达终点 100,0。效果图如下：


![三阶贝塞尔曲线矢量图](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c5fff1d2361742199be2a0d98a39af77~tplv-k3u1fbpfcp-watermark.image?)


类似地：
* q、c 指的是扯点和终点以其和起点的相对位置表示，对应 path.rQuadTo()、path.rCubicTo()

# 四、fillColor 设置颜色，stroke 设置边框

通过 fillColor 可以设置矢量图的填充色，通过 strokeColor 可以设置矢量图的边框颜色，通过 strokeWidth 可以设置边框宽度：

```xml
<path
    android:fillColor="@color/black"
    android:strokeWidth="1"
    android:strokeColor="@android:color/holo_red_dark"
    android:pathData="M0,0L100,0L100,100z" />
```

这里我们指定填充色为黑色，边框颜色为红色，边框宽度为 1。效果图如下：


![填充色与边框](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6703b89d038a4c709cfc46eaa1f5f845~tplv-k3u1fbpfcp-watermark.image?)

# 五、通过 A 绘制弧线

弧线绘制涉及的参数较多，一条简单的弧线路径如下：

```xml
<path
    android:pathData="M50,100A50,50 0 0 1 100,150"
    android:strokeWidth="1"
    android:strokeColor="@color/black" />
```

效果图：

![弧线](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6949de3b908f4f02a8d0ce5bf2817eb2~tplv-k3u1fbpfcp-watermark.image?)

首先，`M50,100` 表示移动到坐标 50,100 的位置，接下来重点关注 `A50,50 0 0 1 100,150`，其中：
* 前两个数字 50,50 表示这段弧线在 x 轴方向的半径和 y 轴方向的半径。这两个值如果不一样，则会画出一条椭圆的弧线。这里我们将其都指定为 50，表示画一个正圆形的弧线。
* 第三个数字 0 有的文章中说表示 x 轴旋转角度，笔者暂时不理解其作用，将其设置成其他的值没有看出有什么变化。
* 第四个数字 0 表示取大段弧线还是小段弧线。从起点到终点的弧线可以有两种，0 表示小段弧，也就是上图显示的效果，如果将其设置为 1，效果如下：

![大段弧](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/05bbce3abef74f2983a18a7b029382e9~tplv-k3u1fbpfcp-watermark.image?)

* 第五个数字 1 表示顺时针还是逆时针，1 表示顺时针，也就是上图中显示的效果，如果将其设置为 0，效果如下：

![逆时针](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/90b8d51b515441279307836679bd56ee~tplv-k3u1fbpfcp-watermark.image?)

* 最后一个坐标表示终点的位置 100,150

# 六、小结

以上就是 Android 中矢量图的基本绘制语法，下篇文章我计划写一个 demo，通过矢量图的方式绘制一个冰墩墩，我也不知道多久能做好，拭目以待吧。

# 七、参考文章

[android SVG 常用语法](https://blog.csdn.net/weixin_41620505/article/details/107255168)

[Animated Bézier Curves](https://www.jasondavies.com/animated-bezier/)

[android高级UI之贝塞尔曲线<上>---基本概念、德卡斯特里奥算法](https://www.cnblogs.com/webor2006/p/12901271.html)

[维基百科-贝塞尔曲线](https://zh.wikipedia.org/zh-cn/%E8%B2%9D%E8%8C%B2%E6%9B%B2%E7%B7%9A)

[android矢量图vector的简单介绍](https://blog.csdn.net/huweijian5/article/details/107560477)
