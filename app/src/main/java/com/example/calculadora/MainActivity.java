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

    //Função para pegar os numeros
    public void getValue(View view) {
    TextView result = (TextView) findViewById(R.id.resultado);
    TextView historico = (TextView) findViewById(R.id.historico);
    String btn = ((Button) view).getText().toString();
    Button btnid = ((Button) view);
    //Verificando se ja existe um calculoa feito na Array
    if(arr.size() == 1) {
        valor += btn;
        result.setText("");
        result.setText(result.getText().toString() + btn);
        historico.setText("");
        historico.setText(historico.getText().toString() + btn);
        arr.clear();
    } else {
        //Adicionando o numero a String 'valor' para poder mandar para a Array com alguns tratamentos
        if(btnid.getId() == R.id.button0){
            if(valor == ""){
                valor += btn;
                result.setText(result.getText().toString() + btn);
                historico.setText(historico.getText().toString() + btn);
            }
            if(valor.charAt(0) != '0'){
                result.setText(result.getText().toString() + btn);
                historico.setText(historico.getText().toString() + btn);
                valor += btn;
            } else {
                if(valor.length() > 1) {
                    result.setText(result.getText().toString() + btn);
                    historico.setText(historico.getText().toString() + btn);
                    valor += btn;
                }
            }
        } else {
            valor += btn;
            result.setText(result.getText().toString() + btn);
            historico.setText(historico.getText().toString() + btn);
        }
        }
        System.out.println(valor);
    }

    //Função para pegar os operadores matematicos
    public void getOperator(View view) {
        TextView result = (TextView)findViewById(R.id.resultado);
        TextView historico = (TextView) findViewById(R.id.historico);
        if(valor != ""){
            arr.add(valor);
            valor="";
        }
        System.out.println("ultimo "+arr.get(arr.size() - 1));
        //Verificando se ja foi inserido algum valor
        if(arr.size() == 0){
            Toast.makeText(this, "Insira primeiro os valores", Toast.LENGTH_SHORT).show();
        } else {
            //Adicionando na Array com alguns tratamentos e verificações
            String btn = ((Button)view).getText().toString();
            if (isFloat(arr.get(arr.size() - 1))) {
                arr.add(btn);
                result.setText(result.getText().toString() + " " + btn + " ");
                historico.setText(historico.getText().toString() + " " + btn + " ");
            } else {
                String substring = result.getText().toString().substring(0, result.getText().toString().length() - 2);
                arr.set(arr.size()-1, btn);
                result.setText(substring + arr.get(arr.size() - 1) + " ");
                historico.setText(substring + arr.get(arr.size() - 1) + " ");
            }
            System.out.println("array " + arr);
        }
    }

    //Função de calculo
    public void calc(View view) {
        if(valor != ""){
            arr.add(valor);
            valor="";
        }
        TextView result = (TextView)findViewById(R.id.resultado);
        if(!isFloat(arr.get(arr.size() - 1))) {
            System.out.println("aqui " + arr.get(arr.size() - 1));
            Toast.makeText(this, "Insira os proximos numeros", Toast.LENGTH_SHORT).show();
        } else {
            if(!isFloat(arr.get(arr.size() - 1))) {
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

//                        if (Objects.equals(arr.get(i), "^")) {
//                            double num1 = Double.parseDouble(arr.get(i - 1));
//                            double num2 = Double.parseDouble(arr.get(i + 1));
//                            if(Objects.equals(arr.get(i + 1), "0")){
//                                arr.set(i, "1");
//                                arr.remove(i + 1);
//                                arr.remove(i - 1);
//                                i = 0;
//                            } else {
//                                double resultado = elevado(num1, num2);
//                                arr.set(i, String.valueOf(resultado));
//                                arr.remove(i + 1);
//                                arr.remove(i - 1);
//                                i = 0;
//                            }
//                        }
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
            }
        }
        result.setText(arr.get(0));
        System.out.println(arr);
    }

    //Funções de operadores matematicos
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

    public static double elevado(double num1, double num2){
        double result = 1;
        for(int i = 0; i < num2; i++){
            result *= num1;
        }
        return result;
    }

    //Função para verificar se um numero é float ou não
    public static boolean isFloat(String num){
        try{
            Float numFloat = Float.parseFloat(num);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //Função para limpar todos os valores
    public void clear(View view) {
        TextView result = (TextView)findViewById(R.id.resultado);
        TextView historico = (TextView) findViewById(R.id.historico);
        result.setText("");
        historico.setText("");
        valor = "";
    }
}