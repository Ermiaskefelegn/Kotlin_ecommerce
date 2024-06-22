# Preserve generic type information
-keepattributes Signature

# With R8 full mode, preserve Continuation and retrofit2 classes
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# Keep Retrofit interfaces with annotations
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# Keep Jetpack Compose classes
-keep class androidx.compose.** { *; }
-keep class androidx.activity.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.navigation.** { *; }

# Keep all classes and methods in your package
-keep class com.gebeya.order_optima_restaurant.** { *; }
-keep class com.auth0.** { *; }
# Keep classes with specific annotations
-keep @interface com.gebeya.order_optima_restaurant.**

# Prevent obfuscation of classes used by reflection
-keepnames class com.gebeya.order_optima_restaurant.** { *; }

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata { *; }

# Keep data classes and their serialization methods
-keepclassmembers class * {
    *** toJson();
    *** fromJson();
}

# Enable R8 specific optimizations
-repackageclasses ''
-allowaccessmodification

# General ProGuard optimizations to reduce memory usage
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*