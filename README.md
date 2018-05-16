# Android M-Pesa Daraja SDK     [![CircleCI](https://circleci.com/gh/twigaeng/android-daraja-sdk.svg?style=shield)](https://circleci.com/gh/twigaeng/android-daraja-sdk)      [ ![Download](https://api.bintray.com/packages/twigatech/android-daraja-sdk/daraja/images/download.svg) ](https://bintray.com/twigatech/android-daraja-sdk/daraja/0.0.1)
This is a simple Android M-PESA SDK to allow you to integrate Safaricom M-PESA API dubbed ***Daraja*** in your Android App with ease without knowing a thing. It’s easy. We promise. :rocket:

> This version only offers the STKPush Support.

> We are building C2B, B2C and B2B Endpoints. Feel free to contribute :) 

> We will be having Kotlin support soon also, as well as examples!

## Getting Started
These instructions will help you set up this library easily on your current project and working in no time. You only need a few configurations to start working!

## Setting Up
To be able to use the following library, you will need to add the following gradle dependency in your build.gradle(module:app) file

```gradle
dependencies {
  implementation 'com.twigafoods:daraja:0.1.1'
}
```

That is the basic set up needed to be able to use the library in your applications! 

Daraja requires minSdkVersion Level 14 (Min Supported by Android Studio) 

## Permissions
If you are using this Library, this means your Application is using Internet, so don't forget the following permission also:

```
<uses-permission android:name="android.permission.INTERNET"/>
```

That's it, you have set up the required permissions and ready to go!

## How do I use Daraja?

To use Daraja in your Android Application, please follow the following [Wiki Guide](https://github.com/twigaeng/android-daraja-sdk/wiki).

## Contributing
We’re glad you’re interested in Daraja, and we’d love to see where you take it. If you would like to contribute code to this project you can do so through GitHub by Forking the Repository and creating a Pull Request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible. We look forward to you submitting a Pull Request.

Thanks, and please do take it for a joyride!

`Twiga Engineering Team`

## License

```text
MIT License

Copyright (c) 2018 Twiga Engineering 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
