package uic.cs478.jaykumarkakkad.myapplication.feb2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    // define the buttons
protected Button dial;  // for dial button
protected Button message; // for message button
    protected EditText numberText; // for storing text field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind the interface elements to corresponding fields
        dial = (Button) findViewById(R.id.button);
        message = (Button) findViewById(R.id.button2);
        numberText = (EditText) findViewById(R.id.editTextText);

        // set up listeners for the two buttons
        dial.setOnClickListener(dialListener);
        message.setOnClickListener(messageListener);



    }

    // create instances of onclicklistener interface of View class.
    // since it is functional interface it has only none class - onClick
    // hence we can create an instance of the interface and change the onClick as below
public View.OnClickListener dialListener = v -> {
        //dial.setBackgroundColor(getResources().getColor(R.color.dark_blue));
        boolean a;
        a = validateNumber();
        if (a){
            // set an intent: data is the edittext, action is dial, category

            String pNumber = numberText.getText().toString();
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pNumber));

        // start activity with that intent
            startActivity(dialIntent);
        }
        else {
            //display toast message
            phoneInvalidToastMessage();
        }

    };
    // Similar to dialListener, create a messageListener using the View Interface
    public View.OnClickListener messageListener = v -> {
        boolean a;
        a = validateNumber();
        if (a){
            // set an intent: data is numberText as phone number + message : "Hello Jaykumar Kakkad"
            // action is open message app, category
            String pNumber = numberText.getText().toString();
            Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + pNumber));
            messageIntent.putExtra("sms_body", "Hello Jaykumar Kakkad");

            // start activity with that intent
            startActivity(messageIntent);
        }
        else {
            //display toast message : phone number {numberText} is not valid
            phoneInvalidToastMessage();
        }

    };

    // This method is used to validate if the number in the EditText is in the required format
public boolean validateNumber()
        {  // Only two valid strings : (111) 111-1111 , 1231231234
            // return false for all other types.
            String pNumber = numberText.getText().toString();
            // Check lengths match:
            if (pNumber.length()==10 || pNumber.length()==14 || pNumber.length()==13){
                // if length is 10 then all values to be digits.
                if (pNumber.length()==10){
                    for (int i = 0; i < 10; i++) {
                        if (!Character.isDigit(pNumber.charAt(i))){
                            return false; // if any character is not digit, then return False
                        }

                    }
                return true; // all characters are digit
                }
                else if (pNumber.length()==14){
                    // if length is 14 , we need 4 additional checks
                    if (pNumber.charAt(0)=='(' && Character.isDigit(pNumber.charAt(1)) && Character.isDigit(pNumber.charAt(2)) &&
                            Character.isDigit(pNumber.charAt(3)) && Character.isDigit(pNumber.charAt(6)) && Character.isDigit(pNumber.charAt(7))
                            && Character.isDigit(pNumber.charAt(8)) && Character.isDigit(pNumber.charAt(10)) && Character.isDigit(pNumber.charAt(11))
                            && Character.isDigit(pNumber.charAt(12)) && Character.isDigit(pNumber.charAt(13))
                            && pNumber.charAt(4)==')' && pNumber.charAt(5)==' ' && pNumber.charAt(9)=='-'
                            ){return true;}
                    else{return false;}

                }
                else {if (pNumber.charAt(0)=='(' && Character.isDigit(pNumber.charAt(1)) && Character.isDigit(pNumber.charAt(2)) &&
                        Character.isDigit(pNumber.charAt(3)) && Character.isDigit(pNumber.charAt(5)) && Character.isDigit(pNumber.charAt(6))
                        && Character.isDigit(pNumber.charAt(7)) && Character.isDigit(pNumber.charAt(9)) && Character.isDigit(pNumber.charAt(10))
                        && Character.isDigit(pNumber.charAt(11)) && Character.isDigit(pNumber.charAt(12))
                        && pNumber.charAt(4)==')' && pNumber.charAt(8)=='-'
                ){return true;}
                else{return false;}}
            }
            // if lengths don't match to 10 or 14, return false
            else {
                return false;
        }


        }

    // Method to display a toast message
    private void phoneInvalidToastMessage() {
        // needs message, context and duration
        String pNumber = numberText.getText().toString();
        String message = pNumber +" is invalid entry";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        // using makeText and show methods of Toast class - as per specs
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }



}