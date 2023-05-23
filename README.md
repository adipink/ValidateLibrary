# Validate Library
Library that help validate user's information:
- Email
- Phone number
- Full name
- Age's format / Legal age
- Password's strength
- Date's format
- Birth's date

## Setup
Step 1. Add it in your root build.gradle at the end of repositories:
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


Step 2. Add the dependency:
```java
dependencies {
	        implementation 'com.github.adipink:ValidateLibrary:1.00.01'
	}
```


## Usage
Example of using the library:
```java
private void checkIfEmail() {
        String mail = main_EDT_email.getText().toString();
        if(ValidateClass.checkEmail(mail)){
            Toast.makeText(MainActivity.this, "Valid Email", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
        }
    }
```


