# Android MPESA SDK [![CircleCI](https://circleci.com/gh/twigaeng/android-mpesa-sdk/tree/master.svg?style=shield)](https://circleci.com/gh/twigaeng/android-mpesa-sdk/tree/master)
This is a simple Android MPESA SDK to allow you to integrate Safaricom MPESA API dubbed Daraja with your Android App with ease. 
> This version only offer the STKPush Support

## Getting Started
These instructions will help you set up this library easily on your current project and working in no time. You only need a few configurations to start working!

## Setting Up
To be able to use the following library, you will need to add the following gradle dependency in your build.gradle(module:app) file

```gradle
dependencies {
  implementation 'com.twigafoods.daraja:mpesa-daraja:0.0.1'
}
```

That is the basic set up needed to be able to use the library in your applications!

## Permissions
If you are using this Library, this means your Application is using Internet, so don't forget the following permission also:

```
<uses-permission android:name="android.permission.INTERNET"/>
```

That's it, you have set up the required permissions and ready to go!

## How do I use Daraja?

Simple use cases with Daraja will look something like this:

```java
Daraja.with(CONSUMER_KEY, CONSUMER_SECRET, Env.SANDBOX);
```
This initializes Daraja and also generates a Token to be used for further requests.