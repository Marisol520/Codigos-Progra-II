package com.example.parcial1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etMetrosConsumidos;
    Button btnCalcularCosto;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar TabHost
        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        // Pestaña 1: Calculadora de costo de agua potable
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Tab 1");
        tab1.setContent(R.id.Valorpagar);
        tab1.setIndicator("Pestaña 1");
        tabHost.addTab(tab1);

        etMetrosConsumidos = findViewById(R.id.et_metros_consumidos);
        btnCalcularCosto = findViewById(R.id.btn_calcular_costo);
        tvResultado = findViewById(R.id.tv_resultado);

        btnCalcularCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String metrosConsumidosStr = etMetrosConsumidos.getText().toString();
                if (!metrosConsumidosStr.isEmpty()) {
                    int metrosConsumidos = Integer.parseInt(metrosConsumidosStr);
                    double costo = calcularCostoAgua(metrosConsumidos);
                    tvResultado.setText("Costo del agua potable: $" + costo);
                } else {
                    tvResultado.setText("Por favor, ingrese los metros consumidos.");
                }
            }
        });
    }

    public double calcularCostoAgua(int metrosConsumidos) {
        double costoTotal = 0.0;

        if (metrosConsumidos >= 1 && metrosConsumidos <= 18) {
            costoTotal = 6.0; // Cuota fija
        } else if (metrosConsumidos >= 19 && metrosConsumidos <= 28) {
            costoTotal = 6.0 + 0.45 * (metrosConsumidos - 18);
        } else if (metrosConsumidos >= 29) {
            costoTotal = 17.0 + 0.65 * (metrosConsumidos - 28) + 0.45 * 10; // 10 es la diferencia entre 28 y 18
        }

        return costoTotal;
    }
}