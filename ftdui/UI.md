# 面舌诊SDK

## ftd_ui 使用说明

### 简介

为保证开发者能够快速集成面舌诊服务，我们推出了轻量简介的UI库，ftd_ui(以下简称：UI)。

注意：**UI中已经集成了ftd_core，开发者无需单独集成ftd_core!**

为保证充分的灵活性，我们把UI的代码开源，开发者可以直接把UI作为一个library集成到自己项目里，也可以在UI的基础上修改，以适配自己项目的风格。

### 使用

#### 集成

首先下载demo源码，把demo中的UI以library的形式集成到项目中。

然后添加UI库的依赖：

```groovy
implementation project(':ftdui')
```

#### 配置

在 `AndroidManifest.xml` 中的 `<Application>` 标签内加入以下内容：

```xml
<meta-data android:name="laiKang.appId" android:value="${value}" />
<meta-data android:name="laiKang.appCode" android:value="${value}" />
<meta-data android:name="laiKang.appKey" android:value="${value}" />
<meta-data android:name="laiKang.appSecret" android:value="${value}" />
<meta-data android:name="laiKang.companyCode" android:value="${value}" />
<meta-data android:name="laiKang.companyId" android:value="${value}" />
<meta-data android:name="laiKang.companyPid" android:value="${value}" />
<meta-data android:name="laiKang.phrAppKey" android:value="${value}" />
<meta-data android:name="laiKang.phrAppSecret" android:value="${value}" />
```

注意： `<meta-data>` 标签内的name不要写错，value请与我方对接人员获取！

#### 初始化

```java
FtdUi.init(getApplicationContext());
```

#### 登录/启动

```java
FtdUi.login(mobile, context, new FtdUILoginCallback() {
    @Override
    public void onSuccess() {
        pb.setEnabled(true);
        pb.setVisibility(View.GONE);
    }

    @Override
    public void onError(FtdException e) {
        pb.setEnabled(true);
        Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
    }
});
```

如上代码，传入用户手机号，登录面舌诊服务，登录成功后会自动启动，并跳转至UI页面，至此面舌诊业务逻辑被UI接管，下面的事情开发者就无需关注了！

### UI用到的第三方组件

1. com.github.bumptech.glide:glide:4.9.0
    这个不用讲了，众所周知的glide图片加载库，之所以用这个而不用Fresico，是因为glide更加轻量和灵活。
2. 'com.shuhart.stepview:stepview:1.5.0'
    一个不错的步进图组件。
3. org.xclcharts:lib:2.4
    一个强大的图表组件库，非常推荐。
4. JTCameraView
    这是我个人写的一个相机组件库，目前功能还比较少，后期会逐渐添加。开发者也可以使用其他相机组件库替换，或自己实现相机功能。Google官方开源了一个相机库CameraView，该库在android 5.0以上系统会自动切换到camera2实现，但是现阶段camera2并没有真正普及，很容易出现兼容性问题，所以不推荐使用camera2。
5. com.william:zhibiaoview:1.1.3
    这也是我为这个项目单独写的两个图表组件。