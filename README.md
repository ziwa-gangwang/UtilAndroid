# UtilAndroid
**usage:**

    allprojects {
    	repositories {
    		jcenter()
    		maven { url "https://jitpack.io" }
    	}
    }


compile 'com.github.djun100:UtilAndroid:SNAPSHOT'

depends on which you should compile :

    'com.android.support:support-v4:+'

if you use UtilBase64_Codec,please compile below:

    'commons-codec:commons-codec:1.8'

增加测试数据生成功能
# 附：
## 1、log工具研究

log显示打印出处只需要这样

Log.w("(" + className + ".java:" + lineNumber + ")", msg);

一次log调用最多显示一次可点击并跳转到源码的链接。

链接可以显示在tag位置。