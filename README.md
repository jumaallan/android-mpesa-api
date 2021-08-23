# Android M-Pesa Daraja SDK   [ ![Download](https://api.bintray.com/packages/jumaallan/android-daraja-sdk/daraja/images/download.svg) ](https://bintray.com/jumaallan/android-daraja-sdk/daraja/1.0.0)
This is a simple Android M-PESA SDK to allow you to integrate Safaricom M-PESA API dubbed ***Daraja*** in your Android App with ease without knowing a thing. It’s easy. I promise. :rocket:

> This version only offers the MPESA Express (STKPush) Support.

> If you were using `1.0.0`, be warned that version `1.0.2` ***breaks the code***. Look at the updated example

## Getting Started
These instructions will help you set up this library easily on your current project and working in no time. You only need a few configurations to start working!

## Setting Up
To be able to use the following library, you will need to add the following gradle dependency in your build.gradle(module:app) file

```gradle
dependencies {
  implementation 'com.androidstudy:daraja:1.0.2'
}
```

That is the basic set up needed to be able to use the library in your applications! 

Daraja requires minSdkVersion Level 14 (Min Supported by Android Studio) 

## Permissions
If you are using this Library,don't forget the following permission:

```
<uses-permission android:name="android.permission.INTERNET"/>
```

That's it, you have the required permissions and ready to go!

## How do I use Daraja?
Simple use cases with MPESA Express (STKPush) will look something like this:

Have a Daraja singleton instance 

```java
 class Payment{
    @Provides
    @Singleton
    Daraja providesDaraja() {
        return Daraja.Builder(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .setBusinessShortCode(Config.BUSINESS_SHORTCODE)
                .setPassKey(AppUtils.getPassKey())
                .setTransactionType(Config.ACCOUNT_TYPE)
                .setCallbackUrl(Config.CALLBACK_URL)
                .setEnvironment(Environment.SANDBOX)
                .build();
    }
    
    void getToken(){
        daraja.getAccessToken(accessTokenDarajaResult -> {
        if(accessTokenDarajaResult instanceof DarajaResult.Success){
            AccessToken accessToken = ((DarajaResult.Success<AccessToken>) accessTokenDarajaResult).getValue();
            makePayment(accessToken);
        }else{
            DarajaResult.Failure  failure = (DarajaResult.Failure) accessTokenDarajaResult;
            DarajaException darajaException = failure.getDarajaException();
            boolean isNetworkError = failure.isNetworkError();
        }
        return null;
    });
}
   void makePayment(){
       daraja.initiatePayment("token", "phoneNumber", "amount", "", "Payment", paymentResultDarajaResult -> {
       if (paymentResultDarajaResult instanceof DarajaResult.Success) {
           PaymentResult paymentResult = ((DarajaResult.Success<PaymentResult>) paymentResultDarajaResult).getValue();
           toast(paymentResult.getResponseDescription());

       } else {
           DarajaResult.Failure failure = (DarajaResult.Failure) paymentResultDarajaResult;
           DarajaException darajaException = failure.getDarajaException();
           boolean isNetworkError = failure.isNetworkError();
       }
       return null;
   });

   }
       
}
        
```


```kotlin
     @Inject lateinit var daraja : Daraja
        
     private fun getToken() {
        daraja.getAccessToken { darajaResult ->
            when(darajaResult ){
                is DarajaResult.Success ->{
                    makePaymentRequest(token)
                }
                is  DarajaResult.Failure ->{
                    val darajaException = darajaResult.darajaException
                    toast(darajaException?.message?:"An error occurred!")
                }
            }

        }
   
    }

    private fun makePaymentRequest(token : String) {
        var phoneNumber = ""
        var amount = "100"
        var accountReference = "2343de"
        var description = "Payment for airtime"

        daraja.initiatePayment(token, phoneNumber, amount, accountReference, description){ darajaResult ->
            when (darajaResult) {
                is DarajaResult.Success -> {
                    val result = darajaResult.value
                    toast(result.ResponseDescription)
                }
                is DarajaResult.Failure -> {
                    val exception = darajaResult.darajaException
                    if(darajaResult.isNetworkError){
                        toast(exception?.message?:"Network error!")
                    }else {
                        toast(exception?.message ?: "Payment failed!")
                    }
                }
            }

        }
    }
```

## Lipa na M-Pesa Online Payment API

The following table highlights the requirements needed by Daraja, as described in the [Safaricom Developer API Page](https://developer.safaricom.co.ke/lipa-na-m-pesa-online/apis/post/stkpush/v1/processrequest)

| Name                  | Description           | Parameter Type    | Possible Values |
| -------------         |:--------------------: | ----------------: | ---------------:|
| BusinessShortCode     | The organization shortcode used to receive the transaction        | Numeric             | Shortcode (6 digits)           |
| Passkey     | Lipa Na Mpesa Online PassKey       | Alpha-Numeric              |           | 
| Amount     | The amount to be transacted      | Numeric             | 100           |
| PhoneNumber     | The MSISDN sending the funds        | Numeric             | MSISDN (12 digits)          |
| CallBackURL     | Call Back URL        | URL             | https://ip or domain:port/path           |
| AccountReference     | Account Reference        | Alpha-Numeric	             | Any combinations of letters and numbers |
| TransactionDesc     | Description of the transaction        | String             | any string of less then 20 characters          |

> Get the Pass Key Here : https://developer.safaricom.co.ke/test_credentials


The whole process looks similar to these screenshots:

<img src="https://github.com/jumaallan/AndroidMPesaAPI/blob/master/screenshots/daraja-user-interface.jpg" width="320"/> <img src="https://github.com/jumaallan/AndroidMPesaAPI/blob/master/screenshots/daraja-success-interface.jpg" width="320"/>

## Contributing
I am glad you’re interested in Daraja, and I’d love to see where you take it. If you would like to contribute code to this project you can do so through GitHub by Forking the Repository and creating a Pull Request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible. I look forward to you submitting a Pull Request.

Thanks, and please do take it for a joyride!

`Juma Allan`

## License

```text
MIT License

Copyright (c) 2018 Juma Allan 

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
