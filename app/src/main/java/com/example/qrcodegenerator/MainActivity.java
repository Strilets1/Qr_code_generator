package com.example.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    private String inputValue;

    private ImageView mImageView;
    private EditText mEditText;
    private Bitmap mBitmap;
    private QRGEncoder mQRGEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.submit_btn);
        mImageView = findViewById(R.id.qr_image_iv);
        mEditText = findViewById(R.id.data_et);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValue = mEditText.getText().toString().trim();

                if (inputValue.length()>0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimension = width < height ? width : height;
                    dimension = dimension * 3 / 4;

                    mQRGEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, dimension);
                    // Getting QR-Code as Bitmap
                    mBitmap = mQRGEncoder.getBitmap();
                    // Setting Bitmap to ImageView
                    mImageView.setImageBitmap(mBitmap);

                } else {
                    mEditText.setError(getResources().getString(R.string.error_no_data_entered));
                }

            }
        });

    }
}
