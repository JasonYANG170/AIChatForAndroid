package com.YANG.Chat;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        delay(4000);
        Toast.makeText(MainActivity.this,"Wellcome to YANG Chat",Toast.LENGTH_LONG).show();
        Intent intent = new Intent("A");
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_Item:
                Toast.makeText(this,"add",Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_Item:
                Toast.makeText(this,"remove",Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }

    private void delay(int ms) {
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}