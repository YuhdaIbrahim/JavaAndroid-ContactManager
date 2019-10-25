package com.example.contactmanager;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contactmanager.adapter.RecyleViewAdapter;
import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

 private RecyclerView recyclerView;
 private RecyleViewAdapter recyleViewAdapter;
  private ArrayList<Contact> contactArrayList;
  private ArrayAdapter<String> arrayAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recycle_view1);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));


    contactArrayList = new ArrayList<>();
    DatabaseHandler db = new DatabaseHandler(MainActivity.this);
    Log.d("count", "onCreate: " + db.getCount());

    List<Contact> contactList = db.getAllContact();
    for (Contact contact: contactList) {
      contactArrayList.add(contact);
    }
    recyleViewAdapter = new RecyleViewAdapter(MainActivity.this,contactArrayList);
    recyclerView.setAdapter(recyleViewAdapter);


  }
}
//    listView = findViewById(R.id.show_view);
//    arrayAdapter = new ArrayAdapter<>(this,
//        android.R.layout.simple_list_item_1,
//        contactArrayList
//        );
//    listView.setAdapter(arrayAdapter);
//
//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.d("list", "onItemClick: " + contactArrayList.get(position));
//      }
//    });
//    db.addContact(new Contact("Abyadh","666"));
//    db.addContact(new Contact("Khannah","1-000-000-0000"));
//    db.addContact(new Contact("Laqnis","090-4444-4444"));
//    db.addContact(new Contact("Khinzab","20 20 20 20"));
//    db.addContact(new Contact("Haffaf","0888 888 888"));
//    db.addContact(new Contact("Murrah","999-9999"));
//    db.addContact(new Contact("Dasim","1999"));
//    db.addContact(new Contact("Miswat","7823667"));
//    db.addContact(new Contact("Sabrum","778321"));
//    db.addContact(new Contact("Watin","9871287"));
//    db.addContact(new Contact("Masud","8263722"));
//    db.addContact(new Contact("Walahan","173727"));
//    db.addContact(new Contact("Zaktabur","23721762"));
//    db.addContact(new Contact("Lakhus","461527172"));
//    db.addContact(new Contact("A’awar","873727663"));


//  db.addContact(Dajjal);
//    Contact c = db.getContact(3);
//    c.setName("Iblis");
//    c.setPhoneNumber("98292");
//    int updateRow = db.updateContact(c);
//    Log.d("update", "onCreate: " + updateRow);

//    db.deleteContact(c);