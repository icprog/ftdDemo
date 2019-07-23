# 面舌诊SDK

## ftd_core 使用说明

### 简介

ftd_core为面舌诊SDK服务核心功能，如若使用面舌诊功能，则必须集成ftd_core。

### 集成

#### 集成ftd_core

首先在build.gradle中集成ftd_core：

``` groovy
implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
```

#### 集成依赖库

ftd_core的网络传输能录依赖其他liabrary，所以需要集成以下library(如已集成可跳过)

```groovy
implementation 'com.squareup.retrofit2:retrofit:2.6.0'
implementation 'com.squareup.retrofit2:converter-gson:2.6.0'
implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'io.reactivex.rxjava2:rxjava:2.2.10'
implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
```

### 配置

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

### 使用

#### 初始化

在使用本服务之前，需要先初始化，如果已经在AndroidManifest.xml填写了配置，则可在 `Application` 或 `Activity` 中调用：

```java
FtdClient.getInstance().init(getApplicationContext()); 
```

如果不想在AndroidManifest.xml中配置，而是希望**动态**的传入配置参数，也可调用：

```java
FtdClient.getInstance().init(InitParam param)
```

#### 登录面舌诊服务

在初始化完成之后，使用面舌诊服务之前，**必须先登录**，否则无法正常使用面舌诊服务。登录调用如下：

```java
FtdClient.getInstance().login(phone, new FtdLoginCallback() {
    @Override
    public void onSuccess() {
    }

    @Override
    public void onError(FtdException e) {

    }
}); 
```

其中 `login` 方法第一个参数是用户的手机号。登录成功之后就可以拍照上传了。

#### 上传图片并分析

picUpload方法需一次性上传三张图片，参数为 `File` 类型，依次是**面部照片、舌面照片、舌底照片，顺序不能乱**。图片全都上传分析成功后，可以进入问诊了。

```java
FtdClient.getInstance().picUpload(FaceImg, TongueTopImg, TongueBottomImg, new FtdPicUploadCallback() {
    @Override
    public void onSuccess(Conclusion result) {

    }

    @Override
    public void onError(FtdException e) {
        
    }
}); 
```

#### 获取问诊题目

问诊的题目是依据上传的三张图片分析结果对应给出，所以每个人的体质不一样，问诊的题目也都不一样，所以只有全部图片上传成功后，才能获取问诊的题目列表：

```java
FtdClient.getInstance().getQuestion(new FtdQuestionListCallback() {
    @Override
    public void onSuccess(AskBean bean) {
        
    }

    @Override
    public void onError(FtdException e) {

    }
}); 
```

返回的AskBean中，包含了该用户体质对应的题目，和体证对应的题目：

* bean.getCardInfo()为获取体证；
* bean.getConstitutionInfo()为获取体质。

#### 提交问诊答案

提交问诊答案时，要注意：**如果没有适合的选项，用户可以一个都不选，也可以选择的体证和体质答案各不能超过4个！**

```java
FtdClient.getInstance().submitAnswer(questionList1, questionList2, traceId1, traceId2, new FtdSubmitCallback(){

    @Override
    public void onSuccess(AskBean bean) {

    }

    @Override
    public void onError(FtdException e) {

    }
}); 
```

如果提交成功，就可以在返回的 `AskBean` 对象中获取到 `seqNo` 字段，这个字段是后面用来查询用户报告的，所以要缓存。

如果提交成功，返回的 `AskBean` 对象就是本次的诊断结果：

```java
public class AskBean  {

    private CardInfoBean cardInfo;
    private CardInfoBean constitutionInfo;

    public CardInfoBean getCardInfo() {
        return cardInfo; 
    }

    public void setCardInfo(CardInfoBean cardInfo) {
        this.cardInfo = cardInfo; 
    }

    public CardInfoBean getConstitutionInfo() {
        return constitutionInfo; 
    }

    public void setConstitutionInfo(CardInfoBean constitutionInfo) {
        this.constitutionInfo = constitutionInfo; 
    }
}
```

其中 `cardInfo` 是本次诊断出的体证结果， `constitutionInfo` 是体质结果。这两字段都是 `CardInfoBean` 类型，核心字段如下：

```java
public class CardInfoBean {

    private String traceId;//诊断跟踪标识，问诊时需要此值作为参数
    private ImagesBean images;//用户上传的照片
    private long seqNo;//诊断号，用来查询用户特定的诊断报告
    private boolean asking;
    private String createTime;
    private EvaluationScoreBean evaluationScore;//评分
    private ArrayList<QuotaInfoListBean> quotaInfoList;//体质列表
    private ArrayList<QuestionBean> askingItemList;//问题列表
    private ArrayList<DiseaseListBean> diseaseList;//疾病列表
    private ArrayList<AbnormityQuotaInfoListBean> //异常指标结果集abnormityQuotaInfoList;

    private SixDiseaseResultBean sixDiseaseResult;//6种疾病列表
    private ArrayList<DiseaseListBean> possibleDiseaseList;//可能疾病列表
}
```

其中 `seqNo` 是诊断的唯一标志，体质和体证都有自己对应的 `seqNo` , 用来查询对应的报告，所以建议缓存一下。

```
long seqNo = bean.getCardInfo().getSeqNo(); 
```

#### 根据seqNo查询检测报告

检测报告，就像我们去医院做检查，机器打印出来的检测结果，我们自己看不懂，需要拿给医生去解读和诊断。

诊断报告分为体质报告和体证报告，传入体质的seqNo就可以查询体质的报告，当然传入体质的seqNo查到的就是体质。

```java
FtdClient.getInstance().getRecordBySeqNo(seqNo, new FtdLastReportCallback(){
    @Override
    public void onSuccess(ReportBean bean) {

    }

    @Override
    public void onError(FtdException e) {

    }
})
```

返回的ReportBean类对象，包含了一种报告下的所有数据：

```java
public class ReportBean{
    private FaceDiagnoseBean faceDiagnose;
    private String checkName;//诊断空间对应的中文描述
    private String seqNo;//个人档案诊断编号，即PHR提供的此次诊断的唯一标识
    private int dataType;
    private String typeCode;//诊断空间
    private double score;//K值分值
    private EightBean eight;//八纲得分
    private Object uwr;
    private String evaluate;//医生建议
    private Object objJson;
    private String faceImg;//面部照片
    private String tongueImg;//舌面照片
    private String userName;//用户名
    private String createTime;
    private String baseOfTongueUrl;
    private List<MossBean> moss;
    private List<QuotaInfoListBean> quotaInfoList;
    private List<UqeBean> uqe;
    private List<?> otherUqo;
    private List<UrBean> ur;
    private List<QmBean> qm;
    private List<?> signUqo;
    private List<TongueBean> tongue;
    private List<FaceBean> face;
}
```
|参数名称|	是否必须	|类型|	描述|
|-----|-------|-------|--------|
success	|true|	|boolean|	返回结果是否正常
|code	|true|	string|	返回结果编码，20000为正常；否则为异常|
|message	|true|	string|	返回结果信息，具体消息内容|
|data	|true|	object|	返回数据|
|  seqNo	|true|	|string|	个人档案诊断编号，即PHR提供的此次诊断的唯一标识|
|  userName	|true|	string	|用户名
|  typeCode	|true|	string	|诊断空间
|  checkName	|true|	string	|诊断空间对应的中文描述
|  score	|true|	double|	K值分值
|  createTime	|true|	string|	诊断时间
|  evaluate	|true|	string|	医生建议
|  qm	|true|	array\[object\]|	异常指标列表
|    code	|true|	string|	指标编码
|    value	|true|	string|	指标值
|    normal	|true|	int|	是否正常，0:正常;1:异常
|    name	|true|	string	|指标名称
|    text	|true|	string	|指标值对应中文描述
|    unit	|true|	string	|单位
|    minValue	|true|	double|	最小值
|    maxValue	|true|	double	|最大值
|    detectionRangeMin	|true|	string	|检测范围最小值
|    detectionRangeMax	|true|	string	|检测范围最大值
|    normalValue	|true|	string	|正常值
|    quotaType	|true|	int|	指标类型
|    causeList	|true|	object|	原因列表
|      expression	|true|	string|	原因表达式
|      cause	|true|	string|	原因
|  ur	|true|	array\[object\]|	检测结果列表
|    diseaseId	|true|	string|	疾病编码
|    diseaseName	|true|	string|	疾病名称
|    medical	|true|	int|	疾病类型，1:中医;2:西医
|  uwr	|true|	array\[object\]|	用户结果预警，目前未使用
|  uqe	|true|	array\[object\]|	用户所选问题列表
|    id	|true|	string	|记录ID
|    userHistorySeqNo	|true|	string	|个人档案诊断编号，即PHR提供的此次诊断的唯一标识
|    userId	|true|	string	|用户ID
|    questionCode	|true|	string	|问题编码
|    questionContent	|true|	string	|问题内容
|    flag	|true|	int|	标志，暂时未发现作用
|    updated	|true|	string|	更新时间
|    created	|true|	string|	创建时间
|  signUqo	|true|	array\[object\]	|未知，暂时未发现作用
|  otherUqo	|true|	array\[object\]	|未知，暂时未发现作用
|  face	|true|	array\[object\]	|面
|    id	|true|	string	|记录ID
|    medical	|true|	string	|指标类型，1:中医;2:西医
|    userHistorySeqNo	|true|	string	|个人档案诊断编号，即PHR提供的此次诊断的唯一标识
|    userId	|true|	string	|用户ID
|    quotaCatCode	|true|	string	|指标大分类编码
|    quotaName	|true|	string	|指标名称
|    quotaCode	|true|	string	|指标编码
|    quotaValue	|true|	string	|指标值
|    quotaText	|true|	string	|指标值对应中文描述
|    quotaUnit	|true|	string	|指标单位
|    quotaIndex	|true|	int	|指标排序索引
|    minValue	|true|	double	|指标最小值
|    maxValue	|true|	double	|指标最大值
|    quotaNormal	|true|	string	|是否正常值，2:正常;1:异常
|    flag	|true|	int	|标志，暂时未发现作用
|    updated	|true|	string	|记录更新时间
|    created	|true|	string	|记录创建时间
|    detectionRangeMin	|true|	string	|检测范围最小值
|    detectionRangeMax	|true|	string	|检测范围最大值
|    quotaType	|true|	int	|指标类型
|    quotaIcon	|true|	string	|指标图标地址
|    checkDate	|true|	string	|检测时间
|    deviceSn	|true|	string	|暂时未发现作用
|    deviceNo	|true|	string	|暂时未发现作用
|    dataSource	|true|	string	|暂时未发现作用
|  tongue	|true|	array\[object\]	|舌质
|    id	|true|	string	|记录ID
|    medical	|true|	string	|指标类型，1:中医;2:西医
|    userHistorySeqNo	|true|	string	|个人档案诊断编号，即PHR提供的此次诊断的唯一标识
|    userId	|true|	string	|用户ID
|    quotaCatCode	|true|	string	|指标大分类编码
|    quotaName	|true|	string	|指标名称
|    quotaCode	|true|	string	|指标编码
|    quotaValue	|true|	string	|指标值
|    quotaText	|true|	string	|指标值对应中文描述
|    quotaUnit	|true|	string	|指标单位
|    quotaIndex	|true|	int	|指标排序索引
|    minValue	|true|	double	|指标最小值
|    maxValue	|true|	double	|指标最大值
|    quotaNormal	|true|	string	|是否正常值，0:正常;1:异常
|    flag	|true|	int	|标志，暂时未发现作用
|    updated	|true|	string	|记录更新时间
|    created	|true|	string	|记录创建时间
|    detectionRangeMin	|true|	string	|检测范围最小值
|    detectionRangeMax	|true|	string	|检测范围最大值
|    quotaType	|true|	int	|指标类型
|    quotaIcon	|true|	string	|指标图标地址
|    checkDate	|true|	string	|检测时间
|    deviceSn	|true|	string	|暂时未发现作用
|    deviceNo	|true|	string	|暂时未发现作用
|    dataSource	|true|	string	|暂时未发现作用
|  moss	|true|	array\[object\]	|舌苔
|    id	|true|	string	|记录ID
|    medical	|true|	string	|指标类型，1:中医;2:西医
|    userHistorySeqNo	|true|	string	|个人档案诊断编号，即PHR提供的此次诊断的唯一标识
|    userId	|true|	string	|用户ID
|    quotaCatCode	|true|	string	|指标大分类编码
|    quotaName	|true|	string	|指标名称
|    quotaCode	|true|	string	|指标编码
|    quotaValue	|true|	string	|指标值
|    quotaText	|true|	string	|指标值对应中文描述
|    quotaUnit	|true|	string	|指标单位
|    quotaIndex	|true|	int	|指标排序索引
|    minValue	|true|	double	|指标最小值
|    maxValue	|true|	double	|指标最大值
|    quotaNormal	|true|	string	|是否正常值，0:正常;1:异常
|    flag	|true|	int	|标志，暂时未发现作用
|    updated	|true|	string	|记录更新时间
|    created	|true|	string	|记录创建时间
|    detectionRangeMin	|true|	string	|检测范围最小值
|    detectionRangeMax	|true|	string	|检测范围最大值
|    quotaType	|true|	int	|指标类型
|    quotaIcon	|true|	string	|指标图标地址
|    checkDate	|true|	string	|检测时间
|    deviceSn	|true|	string	|暂时未发现作用
|    deviceNo	|true|	string	|暂时未发现作用
|    dataSource	|true|	string	|暂时未发现作用
|  faceDiagnose	|true|	object	|面舌分析描述
|    face	|true|	string	|面分析描述
|    tongue	|true|	string	|舌质分析描述
|    moss	|true|	string	|舌苔分析描述
|    pulse	|true|	string	|暂时未发现作用
|  quotaInfoList	|true|	array\[object\]	|指标信息列表
|  faceImg	|true|	string	|面诊图片地址
|  tongueImg	|true|	string	|舌诊图片地址
|  eight	|true|	object	|八纲图信息
|    id	|true|	string	记录ID
|    userHistorySeqNo	|true|	string	|个人档案诊断编号，即PHR提供的此次诊断的唯一标识
|    userId	|true|	string	|用户ID
|    totalScore	|true|	double	|最终得分
|    sixDiseaseList	|true|	string	|表里虚实寒热）疾病数据
|    yinScore	|true|	double	|阴分
|    yangScore	|true|	double	|阳分
|    created	|true|	string	|创建时间


#### 获取诊断结果

<span id="jump"/>

诊断结果，是医生根据根据检测情况下的结论。当然一个好的医生不会这么武断，一般都会给我们多条建议，所以在这个方法中，我们接收到的实际上是一个集合：

```java
FtdClient.getInstance().getAnalyzer(bean.getUr(), new FtdGetAnaylzerCallback(){
    @Override
    public void onSuccess(AnalyzeResultBean bean) {
    }

    @Override
    public void onError(FtdException e) {

    }
}); 
```

返回的 `AnalyzeResultBean` 是个list，每个item的字段如下：

* code: 对应的疾病编码
* intro: 疾病的简介
* name: 疾病名称
* remark: 症状表现
* opinion: 疾病对应的建议

#### 获取趋势分析

趋势分析是用户最近7天内身体状况的得分走势。这个方法不需要传递任何参数，因为core中已经自动处理了，只要前面的 `登录` 方法调用成功，这里就可以正常获取到正确的数据。

```java
FtdClient.getInstance().getTendency(new FtdTendencyCallback(){
    @Override
    public void onSuccess(TendencyResult result) {
        
    }

    @Override
    public void onError(FtdException e) {
    }
}); 
```

既然是趋势，就意味着一定存在多条数据，所以我们也用集合的形式来接收
，所以返回的TendencyResult中我们主要看result字段就可以了，这个字段是一个list, 每个item的字段如下：

```java
public  class ResultBean {
    private String date;//检测日期
    private double score;//检测得分
    private String faceUrl;//面部图片
    private String disease;//疾病
    private String seqNo;//检测标识
    private String tongueUrl;//舌面图片
}
```

#### 获取健康微语

健康微语是健康相关的小贴士，从中医角度，对用户生活中的不良习惯、细节做个提醒，以提升大家的健康水平。

```java
FtdClient.getInstance().getMicroTip(new FtdMicroTipCallback() {
    @Override
    public void onSuccess(MicroTipBean bean) {
    }

    @Override
    public void onError(FtdException e) {
    }
});
```

返回的MicroTipBean也很简单：

```java
public class MicroTipBean {
    private String name;//标题
    private String analysis;//内容
}
```

#### 五养

「五养」是我司依据中医理论，推出的养生建议，可以根据面舌诊的结果，有针对性的为用户提供食、术、动、心、居五方面的合理疗养建议。

五养暂以Web的形式推出，所以暂未集成于core中，需要开发者手动跳转。地址为：

```java
private String reg = "%s%s?reportCode=%s&nextPageId=0&appkey=%s";

String url = String.format(reg, 参数1, 参数2, 参数3, 参数4);
```

参数解释：

1. 参数1：为统一的地址字符串：<http://lk-m-questionnaire-qa.op.laikang.com/#/klgMulRaiseTemp/>
2. 参数2：五养标识，用来区分五养：
    - 食养：foodRaiseTemp
    - 术养：kungfuRaiseTemp
    - 动养：actionRaiseTemp
    - 心养：heartRaiseTemp
    - 居养：liveRaiseTemp
3. 参数3：疾病的code，diseaseId，从[获取诊断结果](#jump)获取诊断结果</a>中获取到。
4. 参数4：appKey，可以直接`FtdClient.getInstance().getAppKey()`获取。

WebViewSettings:

```java
WebSettings settings = wv.getSettings();
settings.setJavaScriptEnabled(true);
settings.setBuiltInZoomControls(true);
settings.setUseWideViewPort(true);
settings.setLoadWithOverviewMode(true);
settings.setSupportZoom(true);
settings.setDomStorageEnabled(true);
settings.setDatabaseEnabled(true);
settings.setJavaScriptCanOpenWindowsAutomatically(true);
settings.setAllowFileAccessFromFileURLs(true);
settings.setAllowUniversalAccessFromFileURLs(true);
```

### FAQ

1. 配置信息从哪获取？

    请与我司的对接人员获取。

2. 怎样避免内存泄露？

    一般的SDK都不具备处理内存泄露的能力，需要集成者自己进行处理。但由于core的网络能力采用Retrofit + RxJava的形式，所以集成方能够很方便的处理内存泄露，这里以健康微语为例：

    ```java
    Disposable tipDisposable = FtdClient.getInstance().getMicroTip(new FtdMicroTipCallback() {
            @Override
            public void onSuccess(MicroTipBean bean) {
                tvTitle.setText(bean.getName());
                tvContent.setText(bean.getAnalysis());
                pbSub.setVisibility(View.GONE);
            }

            @Override
            public void onError(FtdException e) {
                pbSub.setVisibility(View.GONE);
                tvContent.setText(e.getMsg());
            }
        });
    ```

    core中的每个业务方法都会返回一个Disposable类对象，我们需要重写Activity的onDestroy方法，在方法中释放Disposable类对象即可避免内存泄露。如：

    ```java
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable != null && !mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }
    ```

3. 不想自己写UI?

    面舌诊SDK提供了一套现成的、简洁的UI框架[FTD-UI](../ftdui/UI.md),可供开发者快速集成面舌诊服务。
