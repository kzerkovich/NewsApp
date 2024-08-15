// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
	alias(libs.plugins.androidApplication) apply false
	alias(libs.plugins.jetbrainsKotlinAndroid) apply false
	alias(libs.plugins.androidLibrary) apply false
	alias(libs.plugins.compose.compiler) apply false
	id("com.google.dagger.hilt.android") version "2.52" apply false
	`kotlin-dsl`
}