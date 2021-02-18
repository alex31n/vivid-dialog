[ ![Download](https://api.bintray.com/packages/alex31n/vivid-dialog/vivid-dialog/images/download.svg) ](https://bintray.com/alex31n/vivid-dialog/vivid-dialog/_latestVersion)

# Vivid Dialog
A beautiful and advanced material dialog library for android

## Gradle
```
dependencies {
    implementation 'com.ornach.vividdialog:vivid-dialog:<latest_version>'
}
```

## Features 
- A advanced dialog library based on Material design
- Multi theming - NORMAL, DARK, SUCCESS, WARNING, INFORMATION
- Highly customizable - text, button, color, layout


## Usage

#### Standard Dialog
```java
	VividStandardDialog dialog= new VividStandardDialog.Builder(this)  
	        .setTitle("This is Title")  
	        .setMessage("this is simple text message. this is simple text message. ")  
	        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                dialog.dismiss();  
	  }  
	        })  
	        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	  
	            }  
	        })  
	        .build();
	        
	dialog.show();
```

#### Input Dialog
```java
	VividInputDialog dialog = new VividInputDialog.Builder(this)  
	                .setHeaderText("Customer Name Edit")  
	                .setHeaderBackgroundColor(Color.BLUE)  
	                .setMessage("Enter customer name")  
	  .setSubmitButtonListener("Save", new DialogInterface.OnSubmitListener() {  
	                    @Override  
	                    public void onSubmit(DialogInterface dialog, String text) {  
	                        Toast.makeText(MainActivity.this, "text: "+text, Toast.LENGTH_SHORT).show();  
	  }  
	                })  
	                .setSubmitButtonListener("Submit", new DialogInterface.OnSubmitListener() {  
	                    @Override  
	                    public void onSubmit(DialogInterface dialog, String text) {  
	                        Toast.makeText(MainActivity.this, "Input Text: "+ text, Toast.LENGTH_SHORT).show();  
	  }  
	                })  
	                .build();  
	  
	  dialog.show();
```

##### Customize Dialog

```java
	VividCustomDialog dialog = new VividCustomDialog.Builder(this)  
	        .setView(R.layout.content_custom_dialog)  
	        .setHeaderText("Login Validation")  
	        .setPositiveButton("Next", new DialogInterface.OnClickListener() {  
	            @Override  
	  public void onClick(DialogInterface dialog, int which) {  
	  
	                View view = ((VividCustomDialog)dialog).getView();  
	  String userName = ((TextView)view.findViewById(R.id.usernameText)).getText().toString();  
	  String password = ((TextView)view.findViewById(R.id.passwordText)).getText().toString();  
	  
	  Toast.makeText(MainActivity.this, "Username: "+ userName+"\nPassword: "+ password, Toast.LENGTH_SHORT).show();  
	  }  
	        })  
	        .setNegativeButton("Cancel", null)  
	        .build();  
	dialog.show();
```

## Attributes
|        Attribute                      |         Default Value        |        Definations          |
| ------------------------------------- | ---------------------------- | --------------------------- |
| setThemeType(ThemeType themeType)  | ThemeType.NORMAL | Style of dialog box |
| setBackgroundColor(@ColorInt int color) | Color.WHITE |  The background color. set from color int|
| setBackgroundColorRes(@ColorRes int colorRes) | | The background color. set from color resource| 
| setHeaderEnabled(boolean headerEnabled) | true | Show/hide header of the Dialog|
| setHeaderText(String headerText) | | The text fo the header |
| setHeaderTextColor(@ColorInt int color)  |  Color.WHITE | Color of header text. set from color int |
| setHeaderTextColorRes(@ColorRes int colorRes) | | Color of header text. set from color resouce |
| setHeaderBackgroundColor(@ColorInt int color) | | The bcakground color of header, set from color int |
| setHeaderBackgroundColorRes(@ColorRes int colorRes) | R.color.colorPrimary | The bcakground color of header, set from color resource  | 
| setIcon(int icon) | | The icon of dialog, set from drawable resource|
| setIconColor(@ColorInt int iconColor) |  Color.WHITE | The color of icon. |
| setIconColorRes(@ColorRes int iconColorRes) | | The color of icon. |
| setTitle(String title) | | The text of message title |
| setMessage(String message) | | The text of dialog message |
| setInputText(String text) | | The text of Input filed (It only working for VividInputDialog) |
| setInputTextColor(@ColorInt int color) | The color of Input filed text color |
| setInputTextSize(int textSize) | | The size of input text |
| setInputHintText(String text) | | The text of input hint |
| setInputHintColor(@ColorInt int color) | | The color of input hint text |
| setInputRadius(int radius) | | The radius of input box |
| setInputBackgroundColor(@ColorInt int color) | | The background color of input box |
| setInputBorderColor(@ColorInt int color) | | The border color of input box |
| setInputBorderWidth(int size) | | The border size of input box |
| setInputStyle(InputStyle inputStyle) | | The custom input box style |
| setNegativeButton(String text, @Nullable OnClickListener listener) | | Button text & The listener of Negetive button | 
| setNegativeButtonStyle(ButtonStyle negativeButtonStyle) | Default \| ButtonStyle | The style of Negetive button |
| setPositiveButton(String text, @Nullable OnClickListener listener) | | Button text & The listener of Positive button | 
| setPositiveButtonStyle(ButtonStyle positiveButtonStyle) | Default \| ButtonStyle | The style of Positive button
| setView(View view) | | The custom view of the dialog |
| setView(int layoutResId) | | The custom view of the dialog. Set from layout resource |
| setAutoDismissible(boolean dismissible) | true | Auto dismiss dialog when user click a button |
| setCancelable(boolean cancelable) | true | Auto cancel dialog when user click outside of the dialog |
| setOnDismissListener(OnDismissListener onDismissListener) | | Call the listener when dialog dismiss |
| setOnShowListener(OnShowListener onShowListener) | | Call the listener when dialog show |


## Used Library in this project
[NoboButton](https://github.com/alex31n/NoboButton)
[RichText](https://github.com/alex31n/RichText)

## License
```

    Copyright 2017-2018 Alex Beshine

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

```

