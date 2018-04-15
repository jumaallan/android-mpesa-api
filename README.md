# Android M-Pesa Daraja SDK [![CircleCI](https://circleci.com/gh/twigaeng/android-mpesa-sdk/tree/master.svg?style=shield)](https://circleci.com/gh/twigaeng/android-mpesa-sdk/tree/master)
This is a simple Android M-PESA SDK to allow you to integrate Safaricom M-PESA API dubbed Daraja with your Android App with ease without knowing a thing. It’s easy. We promise.

> This version only offers the STKPush Support

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

Daraja requires minSdkVersion Level 16 

## Permissions
If you are using this Library, this means your Application is using Internet, so don't forget the following permission also:

```
<uses-permission android:name="android.permission.INTERNET"/>
```

That's it, you have set up the required permissions and ready to go!

## How do I use Daraja?

Simple use cases with Daraja will look something like this:

```java
//For Sandbox Mode
Daraja.with(CONSUMER_KEY, CONSUMER_SECRET, Env.SANDBOX);

//For Production Mode
Daraja.with(CONSUMER_KEY, CONSUMER_SECRET, Env.PRODUCTION);
```
This initializes Daraja and also generates a `Token` to be used for further requests. This should be done in your Application `onCreate Method`, to allow Daraja generate the Authorization Token as early as possible.

With the Token generated, we can now request an STKPush with ease. Just call the `sendSTKPush` as shown here:

```java
//For Sandbox Mode
 Daraja.sendSTKPush(
                Env.SANDBOX,
                BUSINESS_SHORT_CODE,
                PASS_KEY,
                AMOUNT,
                PARTY_A,
                PARTY_B,
                PHONE_NUMBER,
                CALLBACK_URL,
                ACCOUNT_REFERENCE,
                TRANSACTION_DESCRIPTION
        );

//For Production Mode
 Daraja.sendSTKPush(
                Env.PRODUCTION,
                BUSINESS_SHORT_CODE,
                PASS_KEY,
                AMOUNT,
                PARTY_A,
                PARTY_B,
                PHONE_NUMBER,
                CALLBACK_URL,
                ACCOUNT_REFERENCE,
                TRANSACTION_DESCRIPTION
        );
```

This sanitizes all the data, as required by Safaricom before making a request for the STKPush. You only need to pass the parameters and Daraja will do the rest!

## Lipa na M-Pesa Online Payment API

The following table highlights the requirements needed by Daraja, as described in the [Safaricom Developer API Page](https://developer.safaricom.co.ke/lipa-na-m-pesa-online/apis/post/stkpush/v1/processrequest)

| Name                  | Description           | Parameter Type    | Possible Values |
| -------------         |:--------------------: | ----------------: | ---------------:|
| BusinessShortCode     | The organization shortcode used to receive the transaction        | Numeric             | Shortcode (6 digits)           |
| Passkey     |        |              |           |
| Amount     | The amount to be transacted      | Numeric             | 100           |
| PartyA     | The entity sending the funds        | Numeric             | MSISDN (12 digits)          |
| PartyB     | The organization receiving the funds        | Numeric             | Shortcode (6 digits)           |
| PhoneNumber     | The MSISDN sending the funds        | Numeric             | MSISDN (12 digits)          |
| CallBackURL     | Call Back URL        | URL             | https://ip or domain:port/path           |
| AccountReference     | Account Reference        | Alpha-Numeric	             | Any combinations of letters and numbers |
| TransactionDesc     | Description of the transaction        | String             | any string of less then 20 characters          |

## Contributing
We’re glad you’re interested in Daraja, and we’d love to see where you take it. If you would like to contribute code to this project you can do so through GitHub by Forking the Repository and creating a Pull Request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible. We look forward to you submitting a Pull Request.

Thanks, and please do take it for a joyride!

`Twiga Tech Team`

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