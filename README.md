# Android M-Pesa Daraja SDK     [![CircleCI](https://circleci.com/gh/twigaeng/android-daraja-sdk.svg?style=shield)](https://circleci.com/gh/twigaeng/android-daraja-sdk)      [ ![Download](https://api.bintray.com/packages/twigatech/android-daraja-sdk/daraja/images/download.svg) ](https://bintray.com/twigatech/android-daraja-sdk/daraja/0.0.1)
This is a simple Android M-PESA SDK to allow you to integrate Safaricom M-PESA API dubbed ***Daraja*** in your Android App with ease without knowing a thing. It’s easy. We promise. :rocket:

> This version only offers the MPESA Express (STKPush) Support.

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
Simple use cases with MPESA Express (STKPush) will look something like this:

```java
//For Sandbox Mode
Daraja daraja = Daraja.with(CONSUMER_KEY, CONSUMER_SECRET, new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(MainActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e(MainActivity.this.getClass().getSimpleName(), error);
            }
        });

//For Production Mode
Daraja daraja = Daraja.with(CONSUMER_KEY, CONSUMER_SECRET, Env.PRODUCTION, new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(MainActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e(MainActivity.this.getClass().getSimpleName(), error);
            }
        });
```

Notice the `Env.SANDBOX` is `OPTIONAL`. Daraja uses `SANDBOX` as the Default Mode. To switch to Production Mode, pass the `Env.PRODUCTION` and let Daraja do the rest!

This initializes Daraja and also generates a `Token` to be used for further requests. This should be done in your Application `onCreate Method`, to allow Daraja generate the Authorization Token as early as possible.

With the Token generated, create a `LNMExpress Object`, to be able to pass it to the `sendSTKPush` method as shown below. Replace with actual values.

```java
LNMExpress lnmExpress = new LNMExpress(
                "BUSINESS_SHORT_CODE",
                "PASS_KEY",
                "AMOUNT",
                "PARTY_A",
                "PARTY_B",
                "PHONE_NUMBER",
                "CALLBACK_URL",
                "ACCOUNT_REFERENCE",
                "TRANSACTION_DESCRIPTION"
        );
```

You can now request an STKPush with ease.Just call the `sendSTKPush` as shown here:

```java
//For both Sandbox and Production Mode
button.setOnClickListener(v -> daraja.sendSTKPush(lnmExpress,
                new DarajaListener<LNMResult>() {
                    @Override
                    public void onResult(@NonNull LNMResult lnmResult) {
                        Log.i(MainActivity.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                    }

                    @Override
                    public void onError(String error) {
                        Log.i(MainActivity.this.getClass().getSimpleName(), error);
                    }
                }
        ));
```

This sanitizes all the data, as required by Safaricom before making a request for the STKPush. You only need to pass the parameters and Daraja will do the rest!

## Lipa na M-Pesa Online Payment API

The following table highlights the requirements needed by Daraja, as described in the [Safaricom Developer API Page](https://developer.safaricom.co.ke/lipa-na-m-pesa-online/apis/post/stkpush/v1/processrequest)

| Name                  | Description           | Parameter Type    | Possible Values |
| -------------         |:--------------------: | ----------------: | ---------------:|
| BusinessShortCode     | The organization shortcode used to receive the transaction        | Numeric             | Shortcode (6 digits)           |
| Passkey     | Lipa Na Mpesa Online PassKey       | Alpha-Numeric              |           | 
| Amount     | The amount to be transacted      | Numeric             | 100           |
| PartyA     | The entity sending the funds        | Numeric             | MSISDN (12 digits)          |
| PartyB     | The organization receiving the funds        | Numeric             | Shortcode (6 digits)           |
| PhoneNumber     | The MSISDN sending the funds        | Numeric             | MSISDN (12 digits)          |
| CallBackURL     | Call Back URL        | URL             | https://ip or domain:port/path           |
| AccountReference     | Account Reference        | Alpha-Numeric	             | Any combinations of letters and numbers |
| TransactionDesc     | Description of the transaction        | String             | any string of less then 20 characters          |

> Get the Pass Key Here : https://developer.safaricom.co.ke/test_credentials

## Daraja Example
Make a simple Daraja request as shown in the code sample below. First, make sure your import these dependencies (or let Android Studio auto-import), and initialize a few configurations

```java
import com.twigafoods.daraja.Daraja;
import com.twigafoods.daraja.DarajaListener;
import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.model.LNMResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextPhoneNumber)
    EditText editTextPhoneNumber;
    @BindView(R.id.sendButton)
    Button sendButton;

    //Declare Daraja :: Global Variable
    Daraja daraja;

    String phoneNumber;

```
Inside your `onCreate Method`, set up `Initialize Daraja` as shown below:

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Init Daraja
        //TODO :: REPLACE WITH YOUR OWN CREDENTIALS  :: THIS IS SANDBOX DEMO
        daraja = Daraja.with("EG6TJgOCyuVGHKUeJs0uLXKsLCANRjbo", "4F3tAlRZ4OzNZt0Y", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(MainActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
                Toast.makeText(MainActivity.this, "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Log.e(MainActivity.this.getClass().getSimpleName(), error);
            }
        });
```
Make the `Daraja STKPush` request with ease now:
```java
//TODO :: THIS IS A SIMPLE WAY TO DO ALL THINGS AT ONCE!!! DON'T DO THIS :)
        sendButton.setOnClickListener(v -> {

            //Get Phone Number from User Input
            phoneNumber = editTextPhoneNumber.getText().toString().trim();

            if (TextUtils.isEmpty(phoneNumber)) {
                editTextPhoneNumber.setError("Please Provide a Phone Number");
                return;
            }

            //TODO :: REPLACE WITH YOUR OWN CREDENTIALS  :: THIS IS SANDBOX DEMO
            LNMExpress lnmExpress = new LNMExpress(
                    "174379",
                    "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                    "100",
                    "254708374149",
                    "174379",
                    phoneNumber,
                    "http://mycallbackurl.com/checkout.php",
                    "001ABC",
                    "Goods Payment"
            );

            daraja.requestMPESAExpress(lnmExpress,
                    new DarajaListener<LNMResult>() {
                        @Override
                        public void onResult(@NonNull LNMResult lnmResult) {
                            Log.i(MainActivity.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                        }

                        @Override
                        public void onError(String error) {
                            Log.i(MainActivity.this.getClass().getSimpleName(), error);
                        }
                    }
            );
        });
```

The whole process looks similar to these screenshots:

<img src="https://github.com/twigaeng/android-daraja-sdk/blob/master/screenshots/daraja-error-page.png" width="320"/> <img src="https://github.com/twigaeng/android-daraja-sdk/blob/master/screenshots/daraja-mpesa-working.png" width="320"/>    

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
