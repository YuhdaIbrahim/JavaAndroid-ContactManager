package com.example.contactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

  private TextView detsName,detsPhone;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);

    detsName = findViewById(R.id.textView);
    detsPhone = findViewById(R.id.textView2);

    Bundle bundle = getIntent().getExtras();
    if(bundle != null){
      String name = bundle.getString("name") ;
      String number = bundle.getString("number");

      detsName.setText(name);
      detsPhone.setText(number);
    }
  }
}
