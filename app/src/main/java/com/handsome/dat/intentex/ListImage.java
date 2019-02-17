package com.handsome.dat.intentex;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class ListImage extends AppCompatActivity {
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableLayout.setBackgroundColor(Color.MAGENTA);
        int Line = 6;
        int Column = 3;

        Collections.shuffle(MainActivity.arrayName);

    for (int i = 1; i <= Line; i++) {
        TableRow tableRow = new TableRow(this);
        for(int j = 1; j <= Column; j++) {
            final ImageView imageView = new ImageView(this);

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(300, 300);
            imageView.setLayoutParams(layoutParams);

            final int location = Column * (i - 1) + j -1;
            int idImages = getResources().getIdentifier(MainActivity.arrayName.get(location), "drawable", getPackageName());
            imageView.setImageResource(idImages);
            tableRow.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("images", MainActivity.arrayName.get(location));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
        tableLayout.addView(tableRow);
    }


    }
}
