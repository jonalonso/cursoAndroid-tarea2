package com.stvansolano.cursoandroid;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.*;
import com.stvansolano.cursoandroid.modelos.Usuario;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NuevoElementoActivity extends AppCompatActivity {

    public static final int GUARDADO_EXITOSO = 100;

    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_elemento);

        Button botonGuardar = (Button) findViewById(R.id.botonGuardarNuevo);
        Button botonMensaje = (Button) findViewById(R.id.btn_sendMessage);
        Intent llamadoIntent = getIntent();
        if(llamadoIntent.hasExtra("usuario")){
            Button btn = (Button) findViewById(R.id.botonGuardarNuevo);
            btn.setVisibility(View.GONE);
            btn = (Button) findViewById(R.id.btn_sendMessage);
            btn.setVisibility(View.VISIBLE);
        }

        final EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);

        final EditText editTextApellidos = (EditText) findViewById(R.id.editTextApellidos);

        final EditText editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);

        if(llamadoIntent.hasExtra("usuario")){
            user = (Usuario) llamadoIntent.getSerializableExtra("usuario");
            Button btn = (Button) findViewById(R.id.botonGuardarNuevo);
            btn.setVisibility(View.GONE);
            btn = (Button) findViewById(R.id.btn_sendMessage);
            btn.setVisibility(View.VISIBLE);
            editTextNombre.setText(user.getNombre());
            editTextNombre.setEnabled(false);
            editTextApellidos.setText(user.getApellido());
            editTextApellidos.setEnabled(false);
            editTextTelefono.setText(user.getTelefono());
            editTextTelefono.setEnabled(false);
        }

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Usuario nuevo = new Usuario();

                nuevo.setNombre(editTextNombre.getText().toString());
                nuevo.setApellido(editTextApellidos.getText().toString());
                nuevo.setTelefono(editTextTelefono.getText().toString());

                intent.putExtra(Usuario.class.toString(), nuevo);
                setResult(GUARDADO_EXITOSO, intent);
                finish();
            }
        });

        botonMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NuevoElementoActivity.this);
                builder.setTitle("Msj Whatsapp");

                final EditText input = new EditText(NuevoElementoActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PackageManager pm=getPackageManager();
                        try {

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String text = input.getText().toString();
                            String url = "https://api.whatsapp.com/send?phone=506"+ user.getTelefono() +"&text=" + URLEncoder.encode(text, "UTF-8");
                            intent.setPackage("com.whatsapp");
                            intent.setData(Uri.parse(url));
                            startActivity(intent);

                        }  catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });

                builder.show();
            }
        });

        Log.d("TAG", "onCreate: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();


        System.out.println("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("onStop");
    }
}
