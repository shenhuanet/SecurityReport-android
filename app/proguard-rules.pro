# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Develop\SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# gson
-keep public class com.google.gson.**
-keep public class com.google.gson.** {public private protected *;}
-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keepattributes Signature
-keepattributes *Annotation*

# 保留bean变量
-keep public class com.project.mocha_patient.login.SignResponseData { private *; }
# 保留内部类
-keep class com.project.mocha_patient.login.FindForgotInfoActivity$ForgetResponse {*;}
-keep class com.project.mocha_patient.account_setting.ChangePasswordActivity$ChangePasswordResponse {*;}
# 不混淆内部类
-keepattributes InnerClasses

# bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

# butterknife
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
# OkHttp
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** {*;}
-keep interface com.squareup.okhttp.** {*;}
-dontwarn okio.**
-dontwarn javax.annotation.**
# otto混淆规则
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# 不混淆第三方引用的库
-dontskipnonpubliclibraryclasses
# 不做预校验
-dontpreverify
# 忽略警告
-ignorewarning
# 如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
# 如果引用了v4或者v7包
-dontwarn android.support.**
# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep public class * extends android.support.v4.app.Fragment
-dontwarn android.support.**