package com.handsome.dat.intentex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1, imageView2;
    String[] arrayImages;
    public static ArrayList<String> arrayName;
    int REQUEST_CODE_CHOOSE = 123;
    String Image = "";
    int score = 100;
    TextView text;
    SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        text = (TextView) findViewById(R.id.text);

        share = getSharedPreferences("score", MODE_PRIVATE);

        score = share.getInt("score1", 100);

        text.setText(score + "");
        arrayImages = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(arrayImages));
        
        Collections.shuffle(arrayName);
        Image = arrayName.get(1);
        int IdImages = getResources().getIdentifier(arrayName.get(1), "drawable", getPackageName());

        imageView1.setImageResource(IdImages);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListImage.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK && data != null) {
            String Image2 = data.getStringExtra("images");
            int idImages = getResources().getIdentifier(Image2, "drawable", getPackageName());
            imageView2.setImageResource(idImages);
            if (Image.equals(Image2)) {
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                score += 10;
                text.setText(score + "");
                SaveScore();
            } else {
                Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                score -= 5;
                text.setText(score + "");
                SaveScore();
            }
        }
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "You wanna see that again?", Toast.LENGTH_SHORT).show();
            score -= 10;
            text.setText(score + "");
            SaveScore();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void SaveScore() {
        SharedPreferences.Editor editor = share.edit();
        editor.putInt("score1", score);
        editor.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Setting:
                Toast.makeText(MainActivity.this, "You Choose Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Share:
                Toast.makeText(MainActivity.this, "You Choose Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Refresh:
                Collections.shuffle(arrayName);
                Image = arrayName.get(1);
                int idImages = getResources().getIdentifier(arrayName.get(1), "drawable", getPackageName());
                imageView1.setImageResource(idImages);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
