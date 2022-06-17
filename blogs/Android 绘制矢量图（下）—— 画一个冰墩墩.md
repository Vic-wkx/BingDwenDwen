# Android 绘制矢量图（下）—— 画一个冰墩墩

在上篇文章中，我们学习了 Android 中 SVG 的基本语法。本篇文章我们就来实战一下，用 SVG 的语法绘制一个冰墩墩。顺便助力“一人一墩”的梦想！

![One man, one dwen](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/21fc036b96b14cc9b00236d584d29233~tplv-k3u1fbpfcp-watermark.image?)

先看下效果图：

![冰墩墩](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b3fbdb64043c445b92503467df696453~tplv-k3u1fbpfcp-watermark.image?)

# 一、通过 Photoshop 的钢笔工具获得贝塞尔曲线坐标

在实战中，我们用得最多的是贝塞尔曲线，我们要做的第一件事就是计算出每条曲线的坐标。这件事可以通过 PS 的钢笔工具完成。需要足够的耐心，好在网上已经有大佬做过这件事了，本文直接使用其计算结果。

![冰墩墩贝塞尔曲线](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/49e5ead2f139407f9033ece7460e83bd~tplv-k3u1fbpfcp-watermark.image?)

> 注：坐标来源在本文底部的参考文章中可以找到

# 二、绘制轮廓

轮廓的绘制：

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="200dp"
    android:height="200dp"
    android:viewportWidth="2000"
    android:viewportHeight="2000">
    <!--Shape-->
    <path
        android:fillColor="#FFFFFF"
        android:pathData="
        M497,462
        C452, 380, 497, 184, 666, 297
        C792, 255, 921, 261, 1017, 278
        C1127, 155, 1227, 305, 1183, 404
        C1208, 443, 1238, 488, 1254, 544
        C1251, 421, 1503, 398, 1472, 577
        C1407, 758, 1336, 789, 1279, 876
        C1270, 924, 1255, 1044, 1147, 1222
        C1098, 1372, 1211, 1454, 1031, 1457
        C877, 1469, 892, 1434, 901, 1376
        C924, 1313, 783, 1324, 802, 1378
        C822, 1432, 819, 1467, 691, 1469
        C571, 1473, 569, 1448, 571, 1332
        C572, 1218, 530, 1226, 464, 1038
        C386, 1244, 233, 1115, 272, 1017
        C306, 916, 365, 845, 407, 777
        C433, 669, 449, 545, 497, 462
        z"
        android:strokeWidth="3"
        android:strokeColor="#000000" />
</vector>
```

效果图：

![Shape](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ff369850d4814cddaf23c7d62c538b0c~tplv-k3u1fbpfcp-watermark.image?)

填充色指定为白色，边框色指定为黑色。

pathData 中，首先用 M 符号移动到起点 (497,462)，这个点在左耳的底部。然后通过几条三阶贝塞尔曲线完成整个轮廓。

最后的 z 表示闭合整个曲线，这里的 z 可写可不写，因为最后一条贝塞尔曲线已经回到了起点 (497,462)。这里写上的原因是明确地表示这是一个闭合的形状。

这里解释一下 width、height、viewportWidth、viewportHeight 的区别：

* width、height：当使用这张矢量图的 View 的宽高是 wrap_content 时，这两个属性用来指定 view 的宽高
* viewportWidth、viewportHeight：指定画布的宽高，画布是一个虚拟空间，仅用于编辑 pathData 中的参数

# 三、两只耳朵

耳朵的绘制：

```xml
<!--Left ear-->
<path
    android:fillColor="#000000"
    android:pathData="
    M526, 437
    C498, 263, 667, 325, 641, 329
    Q600, 343, 526, 437
    z" />
<!--Right ear-->
<path
    android:fillColor="#000000"
    android:pathData="
    M1050, 285
    C1144, 232, 1167, 342, 1162, 387
    Q1119, 317, 1050, 285
    z" />
```

效果图：

![Ears](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/209ceff3071949fdbb36758899851114~tplv-k3u1fbpfcp-watermark.image?)

耳朵的填充色指定为黑色，路径中，先用 M 移动到指定坐标，然后通过两条贝塞尔曲线完成耳朵的轮廓，同样使用 z 表示这个形状是闭合的。

# 四、两只小手，两只小脚

双手和双脚的绘制：

```xml
<!-- Left hand-->
<path
    android:fillColor="#000000"
    android:pathData="
    M417,804
    C430,837,435,914,457,968
    C445,1016,440,1022,428,1053
    C396,1142,307,1112,304,1048
    Q300,987,418,803
    z" />
<!-- Right hand-->
<path
    android:fillColor="#000000"
    android:pathData="
    M1267,593
    C1275,584,1279,574,1280,555
    C1282,448,1480,477,1429,575
    C1403,621,1374,689,1287,757
    Q1291,693,1267,594
    z" />
<!-- Left foot-->
<path
    android:fillColor="#000000"
    android:pathData="
    M585,1231
    C626,1261,776,1297,792,1336
    C756,1387,838,1427,710,1428
    C505,1431,644,1381,585,1231
    z" />
<!--Right foot-->
<path
    android:fillColor="#000000"
    android:pathData="
    M910,1342
    C981,1318,938,1293,1125,1226
    C1087,1370,1172,1404,1014,1420
    C875,1425,959,1403,910,1342
    z" />
```

效果图：


![hands and feet](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1d99e5c30be343c0b8b4293a6cc43727~tplv-k3u1fbpfcp-watermark.image?)

与耳朵的绘制是类似的。

> - 为什么一样的东西还要一步步地贴效果图呢？
> - 因为 —— 就像捏泥人很快乐呀！

# 五、彩色围巾

有人说冰墩墩脖子上是能量圈，但我感觉应该是围巾。

```xml
<!-- Muffler-->
<path
    android:pathData="
    M497,772
    C425,371,1145,80,1262,699
    C1294,945,1105,1031,907,1040
    C716,1049,519,962,497,772
    z"
    android:strokeWidth="7"
    android:strokeColor="#73fd94" />
<path
    android:pathData="
    M515,794
    C405,421,1093,119,1242,646
    C1316,881,1130,1001,898,1003
    C732,1005,562,961,515,794
    z"
    android:strokeWidth="5"
    android:strokeColor="#f97dfe" />
<path
    android:pathData="
    M611,909
    C301,602,878,185,1137,487
    C1495,981,840,1066,611,909
    z"
    android:strokeWidth="9"
    android:strokeColor="#ecea87" />
<path
    android:pathData="
    M611,909
    C281,592,878,200,1137,487
    C1495,1001,840,1076,611,909
    z"
    android:strokeWidth="7"
    android:strokeColor="#9ad6ff" />
<path
    android:pathData="
    M515,794
    C405,421,1053,109,1242,646
    C1316,911,1150,1001,898,1023
    C732,1025,562,971,515,794
    z"
    android:strokeWidth="5"
    android:strokeColor="#9ad6ff" />
<path
    android:pathData="
    M545,674
    C673,289,1265,370,1215,773
    C1177,1083,453,1010,545,674
    z"
    android:strokeWidth="7"
    android:strokeColor="#d2fbe5" />
<path
    android:pathData="
    M549,752
    C548,421,1037,320,1191,640
    C1309,1058,597,1021,549,752
    z"
    android:strokeWidth="7"
    android:strokeColor="#4a46be" />
<path
    android:pathData="
    M549,752
    C548,441,1057,300,1191,640
    C1319,1048,567,1021,549,752
    z"
    android:strokeWidth="5"
    android:strokeColor="#b5e7fe" />
```

效果图：


![Muffler](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bcaa2d75f0df4005ac3f3bf883104918~tplv-k3u1fbpfcp-watermark.image?)

这里绘制了一条 8 种颜色的围巾，绘制围巾时不再使用填充的方式（fillColor），而是使用绘制边框的形式（strokeColor）。同样使用贝塞尔曲线，不同围巾的宽度略有区别。

# 六、眼睛

```xml
<!--Left eye socket-->
<path
    android:fillColor="#000000"
    android:pathData="
    M806,552
    C706,492,512,681,603,777
    C738,882,896,600,806,552
    z" />
<!--Left eye-->
<path
    android:fillColor="#ffffff"
    android:pathData="
    M749,595
    C798,592,829,709,743,712
    C659,707,686,593,749,595
    z" />
<path android:pathData="
    M699,655
    C696,596,782,574,783,653
    C775,735,694,699,699,655
    z">
    <aapt:attr name="android:fillColor">
        <gradient
            android:centerX="742"
            android:centerY="652"
            android:gradientRadius="50"
            android:type="radial">
            <item
                android:color="#857343"
                android:offset="0.3" />
            <item
                android:color="#000000"
                android:offset="1.0" />
        </gradient>
    </aapt:attr>
</path>
<path
    android:fillColor="#000000"
    android:pathData="
    M719,655
    C716,633,760,609,762,657
    C755,691,723,676,719,655
    z" />
<!--Left eye highlight-->
<path
    android:fillColor="#FFFFFF"
    android:pathData="
    M743,636
    A13,13,0,1,0,742,636
    z" />
<path
    android:fillColor="#5fc2ba"
    android:pathData="
    M732,682
    A7,7,0,1,0,731,682
    z" />
<!--Right eye socket-->
<path
    android:fillColor="#000000"
    android:pathData="
    M989,541
    C1080,477,1251,684,1168,768
    C1077,837,893,607,989,541
    z" />
<!--Right eye-->
<path
    android:fillColor="#ffffff"
    android:pathData="
    M988,630
    C997,569,1091,548,1087,647
    C1079,719,976,710,988,630
    z" />
<path android:pathData="
    M995,634
    C993,584,1077,559,1077,641
    C1068,707,993,689,995,634
    z">
    <aapt:attr name="android:fillColor">
        <gradient
            android:centerX="1040"
            android:centerY="635"
            android:gradientRadius="50"
            android:type="radial">
            <item
                android:color="#857343"
                android:offset="0.3" />
            <item
                android:color="#000000"
                android:offset="1.0" />
        </gradient>
    </aapt:attr>
</path>
<path
    android:fillColor="#000000"
    android:pathData="
    M1022,621
    C1055,596,1065,650,1042,659
    C1027,662,1002,646,1022,621
    z" />
<!--Right eye highlight-->
<path
    android:fillColor="#FFFFFF"
    android:pathData="
    M1036,618
    A13,13,0,1,0,1035,618
    z" />
<path
    android:fillColor="#5fc2ba"
    android:pathData="
    M1024,666
    A7,7,0,1,0,1023,666
    z" />
```

效果图：


![Eyes](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/70e3a759c928414aaf78952ea280ee2f~tplv-k3u1fbpfcp-watermark.image?)

画墩点睛，眼睛的绘制较为复杂。我们拆开来看：

* 眼圈、眼眶、瞳孔的部分和之前的耳朵绘制是类似的，用贝塞尔曲线组成一个闭合的图形
* 比较复杂的是眼珠，这里用到了一个渐变色，我们稍后讲解
* 然后是眼睛高光，绘制了两个圆形

## 6.1. 渐变色的绘制

一张最简单的渐变色 SVG 图片代码如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="100dp"
    android:height="100dp"
    android:viewportWidth="100"
    android:viewportHeight="100">
    <path android:pathData="M0,0 H100 V100 H-100z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:startColor="#FFFFFF"
                android:endColor="#000000"
                android:startX="0"
                android:startY="0"
                android:endX="100"
                android:endY="100"
                android:type="linear" />
        </aapt:attr>
    </path>
</vector>
```

效果图：


![linear gradient](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ff799490af5e4f0bb88cb4f52526134a~tplv-k3u1fbpfcp-watermark.image?)

通过 aapt:attr 指定需要注入属性，name 指定注入的属性名，这里设置为 android:fillColor，表示需要注入的属性是填充色。

然后编写 gradient 标签，设置开始颜色 startColor，结束颜色 endColor，这里指定为从白色到黑色。

然后设置起始点和终点的坐标，这里定义的是从 (0,0) 到 (100,100)，也就是从画布左上角到右下角，这条路径的含义是渐变色的变化方向。

为了更清晰地解释渐变色的方向，不妨将起始点和终点修改为 (0,0) 到 (0,100)，也就是将这里的 endX 改为 0，效果如下：

![vertical_gradient](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8b229a6004904c81bf84a26f6c5649fd~tplv-k3u1fbpfcp-watermark.image?)

可以很明显的看出，渐变色的方向由左上到左下变成了从上到下，这就是渐变色方向的含义。

type 属性用于指定渐变色的渐变类型，常用的类型有 linear、radial、sweep，分别表示线型、发散型、扫描型。

如果渐变色只能指定 startColor、endColor，那就只能处理两种颜色之间的渐变，那么多色渐变做起来就会很复杂。所以还有另一种方式指定渐变色的颜色变化，就是给 gradient 标签内部添加 item 标签：

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="100dp"
    android:height="100dp"
    android:viewportWidth="100"
    android:viewportHeight="100">
    <path android:pathData="M0,0 H100 V100 H-100z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:startX="0"
                android:startY="0"
                android:endX="100"
                android:endY="100"
                android:type="linear">
                <item
                    android:color="#FFFFFF"
                    android:offset="0" />
                <item
                    android:color="#0000FF"
                    android:offset="0.5" />
                <item
                    android:color="#000000"
                    android:offset="1" />
            </gradient>
        </aapt:attr>
    </path>
</vector>
```

效果如下：

![gradient with item](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4d02e77ba7414a23a53ad22b00d1467f~tplv-k3u1fbpfcp-watermark.image?)

每个 item 标签代表一种颜色，offset 表示这个颜色处于整个渐变区域的哪个位置。取值范围是 0~1，表示整个渐变区域的百分比。

在设置了 item 之后，startColor 和 endColor 就不会起作用了。也就是说 startColor/endColor 和 item 标签是不能混用的。

通过 item 标签，我们将 offset 0 的区域指定为 白色，offset 0.5 的区域指定为蓝色，offset 1 的区域指定为 黑色，就形成了上图中的效果。

再看一个发散型渐变色的例子：

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="100dp"
    android:height="100dp"
    android:viewportWidth="100"
    android:viewportHeight="100">
    <path android:pathData="M0,0 H100 V100 H-100z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:startColor="#FFFFFF"
                android:endColor="#000000"
                android:centerX="50"
                android:centerY="50"
                android:gradientRadius="50"
                android:type="radial" />
        </aapt:attr>
    </path>
</vector>
```

效果图：


![radial gradient](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/415d847e58ee4094a42a7f0c2c1f73d7~tplv-k3u1fbpfcp-watermark.image?)

> 是不是有种黑洞照片的科幻感 =,=

在这个例子中，type 指定为 radial，表示发散型渐变色。发散型渐变色需要的参数和线型渐变色的参数略有不同。

startColor 和 endColor 表示起始颜色和结束颜色，这一点和线型渐变色是相同的。并且，同样可以通过 item 标签指定多种渐变色。

然后通过 centerX、centerY 指定圆心的坐标，通过 gradientRadius 指定渐变色的半径。这样就能完成上图的效果。

接下来再看一个扫描型渐变色的例子：

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt"
    android:width="100dp"
    android:height="100dp"
    android:viewportWidth="100"
    android:viewportHeight="100">
    <path android:pathData="M0,0 H100 V100 H-100z">
        <aapt:attr name="android:fillColor">
            <gradient
                android:startColor="#FFFFFF"
                android:endColor="#000000"
                android:centerX="50"
                android:centerY="50"
                android:type="sweep">
            </gradient>
        </aapt:attr>
    </path>
</vector>
```

效果图：


![sweep gradient](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9fb8b42316bf4e88915d64f731307f39~tplv-k3u1fbpfcp-watermark.image?)

在这个例子中，type 指定为 sweep，表示扫描型渐变色。可以看到，扫描型渐变色和发散型渐变色的写法比较类似。

同样地，startColor 和 endColor 表示起始颜色和结束颜色。并且，也可以通过 item 标签指定多种渐变色。

然后再通过 centerX、centerY 指定圆心的坐标即可。

OK，学会了渐变色的绘制，我们再回到冰墩墩的眼睛上，冰墩墩的眼珠绘制过程用到了渐变色，绘制代码如下：

```xml
<path android:pathData="
    M699,655
    C696,596,782,574,783,653
    C775,735,694,699,699,655
    z">
    <aapt:attr name="android:fillColor">
        <gradient
            android:centerX="742"
            android:centerY="652"
            android:gradientRadius="50"
            android:type="radial">
            <item
                android:color="#857343"
                android:offset="0.3" />
            <item
                android:color="#000000"
                android:offset="1.0" />
        </gradient>
    </aapt:attr>
</path>
```

这个渐变色的效果图如下：


![bing dwen dwen gradient](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1ba866f2b0a142139c9699313842c676~tplv-k3u1fbpfcp-watermark.image?)

这里用到的是一个射线型的从棕色变成黑色的渐变色。

> 注：线型、射线型、扫描型的名字是我自己翻译的，我没有找到这三种渐变色类型的官方中文译名。建议读者直接使用英文名交流 linear、radial、sweep。

> 再注：在 Android Studio 中编辑渐变色时，没有代码提示、自动补全的功能，我暂时没有找到解决办法，只能手打一个个属性。

## 6.2. 圆形的绘制

眼睛高光的部分用了两个圆形，绘制圆形的代码是：

```xml
<!--Left eye highlight-->
<path
    android:fillColor="#FFFFFF"
    android:pathData="
    M743,636
    A13,13,0,1,0,742,636
    z" />
<path
    android:fillColor="#5fc2ba"
    android:pathData="
    M732,682
    A7,7,0,1,0,731,682
    z" />
```

效果图：

![highlight](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8f38808da2f143529a80bf9d26785f3a~tplv-k3u1fbpfcp-watermark.image?)

圆形的绘制使用的是 A 符号，前文中讲过，A 是用来绘制弧线的（arc）。在 SVG 中，并没有专门用于绘制圆形的符号。所以要么通过一条几乎闭合的弧线绘制圆形、要么用两条弧线绘制圆形。

读者可能会有疑问，为什么说是几乎闭合呢？如果将弧线的起点和终点设置成一样，不就能绘制一条完整的圆形了吗？

实际上 A 符号的起点和终点一样时，不会触发 SVG 的绘制。这可能是个 bug，（产品经理小声说道：这其实是个 feature）。或许 A 符号出于安全检查的考虑，为了避免做无用的绘制，将起点和终点一致的弧线直接忽略了。

所以这里的 pathData 中，先移动到 (743,636) 坐标准备绘制，而弧线的终点坐标是 (742,636)，终点和起点的横坐标相差 1，目的就是避免 A 符号不触发绘制。所以这里的圆严格意义上来讲不是一个正圆，但肉眼是分辨不出来的。

如果用两条弧线绘制圆形的话，可以保证绘制出的是一个正圆，具体方法是每条弧线只画一个半圆，最后拼接成一个正圆。这种方式稍显复杂，所以我没有采用。

# 七、鼻子

鼻子的绘制：

```xml
<!--Nose-->
<path
    android:fillColor="#000000"
    android:pathData="
        M914,646
        C863,646,867,682,901,698
        C920,706,927,704,941,694
        C970,668,961,644,914,646
        z" />
<path android:pathData="
        M886,666
        C887,648,945,644,944,666
        C944,686,886,683,886,666
        z">
    <aapt:attr name="android:fillColor">
        <gradient
            android:endColor="#000000"
            android:endX="910"
            android:endY="675"
            android:startColor="#FFFFFF"
            android:startX="910"
            android:startY="650"
            android:type="linear" />
    </aapt:attr>
</path>
```

效果图：


![nose](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/08ffe23d5af6441492dca2966ee03059~tplv-k3u1fbpfcp-watermark.image?)

鼻子的绘制同样使用了渐变色。采用的是白色到黑色的垂直线型渐变。

# 八、嘴巴

嘴巴的绘制：

```xml
<!--Mouse-->
<path
    android:fillColor="#000000"
    android:pathData="
    M824,728
    C895,754,939,740,982,726
    C935,782,861,764,824,728
    z" />
<path
    android:fillColor="#e5482d"
    android:pathData="
    M870,750
    C876,746,939,745,945,749
    C910,764,872,755,870,750
    z" />
```
效果图：

![mouse](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a9a852e4bef044ba8b0256a89af3ef07~tplv-k3u1fbpfcp-watermark.image?)

嘴巴的绘制也是和耳朵类似的，由贝塞尔曲线组成的两个闭合图形。这里我们也看到了熊猫全身上下唯一彩色的地方：红色的舌头 =，=

# 九、五环

五环的绘制：

```xml
<!--Five Circles-->
<path
    android:pathData="M886,1260A15,15,0,1,0,885,1260"
    android:strokeWidth="3"
    android:strokeColor="#ebcb44" />
<path
    android:pathData="M921,1260A15,15,0,1,0,920,1260"
    android:strokeWidth="3"
    android:strokeColor="#2bb459" />
<path
    android:pathData="M871,1245A15,15,0,1,0,870,1245"
    android:strokeWidth="3"
    android:strokeColor="#5398db" />
<path
    android:pathData="M906,1245A15,15,0,1,0,905,1245"
    android:strokeWidth="3"
    android:strokeColor="#2c2e2e" />
<path
    android:pathData="M941,1245A15,15,0,1,0,940,1245"
    android:strokeWidth="3"
    android:strokeColor="#f53e59" />
```

效果图：


![five circles](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6e9475c0b01743ed92db40f6d3300815~tplv-k3u1fbpfcp-watermark.image?)

五环的绘制需要五个圆形。同上文讲的一样，通过 A 符号绘制圆形，并且弧线的起点和终点的坐标有一点区别，目的是保证触发弧线的绘制。

# 十、画墩点“心”

冰墩墩爱心发射：

```xml
<!--Heart-->
<path
    android:fillColor="#e5482d"
    android:pathData="
    M1364,545
    C1359,525,1300,508,1331,595
    C1338,615,1349,607,1356,605
    C1394,587,1420,532,1364,545
    z" />
```

效果图：


![heart](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9bc5288d2e4e46bb87199798f34206df~tplv-k3u1fbpfcp-watermark.image?)

注入灵魂。

这样就完成了整个冰墩墩的绘制，可以看到效果还是不错的。

在白底背景下，冰墩墩更好看：

![冰墩墩](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b3fbdb64043c445b92503467df696453~tplv-k3u1fbpfcp-watermark.image?)

完整代码已上传至 github：https://github.com/wkxjc/BingDwenDwen/blob/master/app/src/main/res/drawable/bing_dwen_dwen.xml

# 小结

本文通过实战的方式练习了上篇文章介绍的 SVG 语法，画出了一只可爱的冰墩墩。其中用得最多的就是三阶贝塞尔曲线，还有少量的二阶贝塞尔曲线。

在绘制眼睛和鼻子时，用到了 SVG 中的渐变色。我们顺便学习了三种渐变色的绘制：linear、radial、sweep。

在绘制眼神中的高光和身上的五环时，用到了 A 符号绘制圆形的技巧。

最后画墩点心，一只可爱的冰墩墩就诞生了！为“人手一墩”的梦想成功助力。

如果觉得还不错的话，可以给可爱的冰墩墩点个赞吗？

![like](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/cae77ccbf0c04b4b84b544a07946a1cf~tplv-k3u1fbpfcp-watermark.image?)

# 参考文章

[通过HTML+JavaScript绘制冰墩墩](https://bulbul559.cn/2022/02/09/80/)

[Android矢量图(一)--VectorDrawable基础](https://www.jianshu.com/p/0972a0d290e9)

[Android矢量图(二)--VectorDrawable所有属性全解析](https://www.jianshu.com/p/89efdbe01ac9)

[Android矢量图(三)--VectorDrawable渐变色](https://www.jianshu.com/p/553dfdf4f5c5)
