# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
# Preserve generic type information
-keepattributes Signature
# With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 # R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

 # With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response
# Keep Jetpack Compose classes
-keep class androidx.compose.** { *; }
-keep class androidx.activity.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.navigation.** { *; }

# Keep all classes and methods in your package
-keep class com.gebeya.order_optima_restaurant.** { *; }

# Keep classes with specific annotations (if any)
-keep @interface com.gebeya.order_optima_restaurant.**

# Prevent obfuscation of classes used by reflection
-keepnames class com.gebeya.order_optima_restaurant.** { *; }

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata { *; }

# Keep data classes
-keepclassmembers class * {
    *** toJson();
    *** fromJson();
}
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