package com.example.android.justjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String emailText;
    private static final String whippedCreamYes = "Whipped cream added.";
    private static final String whippedCreamNo = "Without whipped cream.";
    private static final String chocolateYes = "Chocolate added.";
    private static final String chocolateNo = "Without chocolate.";

    private static final String quantity = "Quantity: ";
    private static final String total = "Total: ";
    private static final String closeThankYou = "Thank You!";

    private int mNumberOfCoffees = 0;
    private static final int PRICE_PER_CUP = 5;
    private static final int PAPER_CUP_CHARGE = 2;
    private int originalColor;
    private CheckBox mCheckBoxWhippedCream;
    private CheckBox mCheckBoxChocolate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View.OnClickListener resetButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButton();
            }
        };

        final View.OnClickListener sendOrderButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrderByEmail();
            }
        };

        View.OnClickListener orderButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalOrder();
            }
        };


        View.OnClickListener incrementButton = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                increment();
            }
        };

        View.OnClickListener decrementButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        };

        Button buttonResetOrder = findViewById(R.id.reset_order_button);
        buttonResetOrder.setOnClickListener(resetButton);

        Button buttonSendOrder = findViewById(R.id.send_order_button);
        buttonSendOrder.setOnClickListener(sendOrderButton);

        mCheckBoxChocolate = findViewById(R.id.CheckBox_Chocolate);

        mCheckBoxWhippedCream = findViewById(R.id.CheckBox_WhippedCream);

        Button buttonOrder = findViewById(R.id.preview_order_button);
        buttonOrder.setOnClickListener(orderButton);

        Button buttonIncrement = findViewById((R.id.button_increment));
        buttonIncrement.setOnClickListener(incrementButton);

        Button buttonDecrement = findViewById(R.id.button_decrement);
        buttonDecrement.setOnClickListener(decrementButton);

        TextView priceTextView = findViewById(R.id.price_text_view);
        originalColor = priceTextView.getCurrentTextColor();

    }

    public void resetButton() {
        mCheckBoxChocolate.setChecked(false);
        mCheckBoxWhippedCream.setChecked(false);
        mNumberOfCoffees = 0;
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(NumberFormat.getInstance().format(mNumberOfCoffees));
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setTextColor(Color.BLACK);
        priceTextView.setText(NumberFormat.getCurrencyInstance(new Locale("us", "US")).format(mNumberOfCoffees));
    }

    public void increment() {
        mNumberOfCoffees++;
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(NumberFormat.getInstance().format(mNumberOfCoffees));
        Log.w("Hey", "Enough Coffee!! " + mNumberOfCoffees);
    }

    public void decrement() {
        if (mNumberOfCoffees > 0) {
            mNumberOfCoffees--;
            TextView quantityTextView = findViewById(R.id.quantity_text_view);
            quantityTextView.setText(NumberFormat.getInstance().format(mNumberOfCoffees));
        }
    }

    public int calculatePrice(int quantity) {
        int whippedCream = 0;
        int chocolate = 0;
        if (mCheckBoxWhippedCream.isChecked()) {
            whippedCream = 1;
        }
        if (mCheckBoxChocolate.isChecked()) {
            chocolate = 2;
        }
        int price = 0;
        if (quantity > 0) {
            price = quantity * ((PRICE_PER_CUP + PAPER_CUP_CHARGE + whippedCream + chocolate));
        }
        return price;
    }

    public void finalOrder() {
        if (mNumberOfCoffees <= 0) {
            displayPrice(0);
        } else {
            displayPrice(calculatePrice(mNumberOfCoffees));
        }
    }

    @SuppressLint("SetTextI18n")
    public void createOrderSummary(TextView textView, int number) {
        EditText editView = findViewById(R.id.user_name);
        String creamAdded;
        String chocolateAdded;
        if (mCheckBoxWhippedCream.isChecked()) {
            creamAdded = whippedCreamYes;
        } else {
            creamAdded = whippedCreamNo;
        }

        if (mCheckBoxChocolate.isChecked()) {
            chocolateAdded = chocolateYes;
        } else {
            chocolateAdded = chocolateNo;
        }

        emailText = "Name: " + editView.getText() + "\n" +
                creamAdded + "\n" +
                chocolateAdded + "\n" +
                quantity + mNumberOfCoffees + "\n" +
                total + NumberFormat.getCurrencyInstance(new Locale("us", "US")).format(number) + "\n" +
                closeThankYou;

        textView.setText(emailText);
    }

    public void sendOrderByEmail() {

        if (mNumberOfCoffees > 0) {
            displayPrice(calculatePrice(mNumberOfCoffees));
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_TEXT, emailText);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kanavishe@gmail.com"});
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else {
            TextView priceTextView = findViewById(R.id.price_text_view);
            priceTextView.setTextColor(Color.RED);
            priceTextView.setText(NumberFormat.getCurrencyInstance(new Locale("us", "US")).format(mNumberOfCoffees));
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        if (number > 0) {
            priceTextView.setTextColor(originalColor);
            createOrderSummary(priceTextView, number);
        } else {
            priceTextView.setTextColor(Color.RED);
            priceTextView.setText(NumberFormat.getCurrencyInstance(new Locale("us", "US")).format(number));
        }
    }
}