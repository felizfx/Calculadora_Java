package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arr = new ArrayList<String>();
    String valor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getValue(View view) {
    TextView result = (TextView) findViewById(R.id.resultado);
    String btn = ((Button) view).getText().toString();
    Button btnid = ((Button) view);
    if(arr.size() == 1) {
        valor += btn;
        result.setText("");
        result.setText(result.getText().toString() + btn);
        arr.clear();
    } else {
        if(btnid.getId() == R.id.button0){
            if(valor == ""){
                valor += btn;
                result.setText(result.getText().toString() + btn);
            }
            if(valor.charAt(0) != '0'){
                result.setText(result.getText().toString() + btn);
            }
        } else {
            valor += btn;
            result.setText(result.getText().toString() + btn);
        }
        }
        System.out.println(valor);
    }

    public void getOperator(View view) {
        TextView result = (TextView)findViewById(R.id.resultado);
        if(result.getText().toString() == ""){
            Toast.makeText(this, "Insira primeiro os valores", Toast.LENGTH_SHORT).show();
        } else {
            if(valor != ""){
                arr.add(valor);
                valor="";
            }
            String btn = ((Button)view).getText().toString();
            if (isFloat(arr.get(arr.size() - 1))) {
                arr.add(btn);
                result.setText(result.getText().toString() + " " + btn + " ");
            } else {
            String substring = result.getText().toString().substring(0, result.getText().toString().length() - 2);
            arr.set(arr.size()-1, btn);
            result.setText(substring + arr.get(arr.size() - 1) + " ");
            System.out.println(arr.size());
            }
            System.out.println(arr);
        }
    }

    public void calc(View view) {
        if(valor != ""){
            arr.add(valor);
            valor="";
        }
        TextView result = (TextView)findViewById(R.id.resultado);
        if(result.getText().toString() == "" || arr.size() <= 1){
            Toast.makeText(this, "Insira todos o valores", Toast.LENGTH_SHORT).show();
        } else {
            if(!isFloat(arr.get(arr.size() - 1))) {
                System.out.println("aqui " + arr.get(arr.size() - 1));
                Toast.makeText(this, "Insira os proximos numeros", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println(arr);
                int i = 0;

                while (true) {
                    i++;
                    if (arr.contains("*") || arr.contains("/")) {
                        if (Objects.equals(arr.get(i), "*")) {
                            double num1 = Double.parseDouble(arr.get(i - 1));
                            double num2 = Double.parseDouble(arr.get(i + 1));
                            double resultado = multi(num1, num2);
                            arr.set(i, String.valueOf(resultado));
                            arr.remove(i + 1);
                            arr.remove(i - 1);
                            i = 0;
                        }

                        if (Objects.equals(arr.get(i), "/")) {
                            double num1 = Double.parseDouble(arr.get(i - 1));
                            double num2 = Double.parseDouble(arr.get(i + 1));
                            double resultado = divi(num1, num2);
                            if(Objects.equals(arr.get(i + 1), "0")){
                                Toast.makeText(this, "Não se pode dividir por 0", Toast.LENGTH_SHORT).show();
                                arr.set(i, String.valueOf(num1));
                                arr.remove(i + 1);
                                arr.remove(i - 1);
                                i = 0;
                                System.out.println(arr);
                            } else {
                                arr.set(i, String.valueOf(resultado));
                                arr.remove(i + 1);
                                arr.remove(i - 1);
                                i = 0;
                            }
                        }
                    } else {
                        if (Objects.equals(arr.get(i), "+")) {
                            double num1 = Double.parseDouble(arr.get(i - 1));
                            double num2 = Double.parseDouble(arr.get(i + 1));
                            double resultado = sum(num1, num2);
                            arr.set(i, String.valueOf(resultado));
                            arr.remove(i + 1);
                            arr.remove(i - 1);
                            i = 0;
                        }

                        if (Objects.equals(arr.get(i), "-")) {
                            double num1 = Double.parseDouble(arr.get(i - 1));
                            double num2 = Double.parseDouble(arr.get(i + 1));
                            double resultado = sub(num1, num2);
                            arr.set(i, String.valueOf(resultado));
                            arr.remove(i + 1);
                            arr.remove(i - 1);
                            i = 0;
                        }
                    }

                    if (arr.size() == 1) {
                        break;
                    }
                }
                if (Float.parseFloat(arr.get(0)) % 1 == 0) {
                    result.setText(String.format("%.0f", Float.parseFloat(arr.get(0))));
                } else {
                    result.setText(arr.get(0));
                }
            }
        }
    }

    public static double sum(double num1, double num2){
        return num1 + num2;
    }

    public static double sub(double num1, double num2){
        return num1 - num2;
    }

    public static double multi(double num1, double num2){
        return num1 * num2;
    }

    public static double divi(double num1, double num2){
        return num1 / num2;
    }

    public static boolean isFloat(String num){
        try{
            Float numFloat = Float.parseFloat(num);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void clear(View view) {
        TextView result = (TextView)findViewById(R.id.resultado);
        result.setText("");
        arr.clear();
        valor = "";
    }
}