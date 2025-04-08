

package com.example.thltud_tuan2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvInput;
    private String current = "", result = "";
    private boolean isOperator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);

        int[] numberIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        };

        int[] operatorIds = {
                R.id.btnAdd, R.id.btnSub, R.id.btnMul,
                R.id.btnDiv, R.id.btnPercent
        };

        View.OnClickListener numberListener = view -> {
            Button b = (Button) view;
            current += b.getText().toString();
            tvInput.setText(current);
            isOperator = false;
        };

        View.OnClickListener operatorListener = view -> {
            if (!isOperator && !current.isEmpty()) {
                Button b = (Button) view;
                current += " " + b.getText().toString() + " ";
                tvInput.setText(current);
                isOperator = true;
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(operatorListener);
        }

        findViewById(R.id.btnDel).setOnClickListener(v -> {
            current = "";
            result = "";
            tvInput.setText("0");
        });

        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            result = calculate(current);
            tvInput.setText(result);
            current = result;
        });
    }

    private String calculate(String input) {
        try {
            String[] tokens = input.split(" ");
            double num1 = Double.parseDouble(tokens[0]);
            String op = tokens[1];
            double num2 = Double.parseDouble(tokens[2]);

            switch (op) {
                case "+": return String.valueOf(num1 + num2);
                case "-": return String.valueOf(num1 - num2);
                case "X": return String.valueOf(num1 * num2);
                case "/": return num2 != 0 ? String.valueOf(num1 / num2) : "Error";
                case "%": return String.valueOf(num1 % num2);
                default: return "Error";
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}
