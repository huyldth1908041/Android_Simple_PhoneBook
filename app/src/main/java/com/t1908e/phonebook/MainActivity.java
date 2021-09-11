package com.t1908e.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnItemClickListener {
    private ListView listViewContact;
    private List<Contact> contacts = new ArrayList<>();
    private ContactAdapter contactAdapter;
    private ImageView imageViewUserAvatar;
    private TextView txtUsername;
    private TextView txtUserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init static element
        listViewContact = findViewById(R.id.listviewContact);
        imageViewUserAvatar = (ImageView) findViewById(R.id.userAvatar);
        txtUsername = (TextView) findViewById(R.id.userName);
        txtUserPhone = (TextView) findViewById(R.id.userPhone);
        //init data
        initData();
        contactAdapter = new ContactAdapter(this, contacts);
        listViewContact.setAdapter(contactAdapter);
        //set event listener
        listViewContact.setOnItemClickListener((adapterView, view, i, l) -> {
            Contact contact = contacts.get(i);
            txtUsername.setText(contact.getName());
            imageViewUserAvatar.setImageResource(contact.getAvatar());
            txtUserPhone.setText(contact.getPhone());
        });
    }

    private void initData() {
        contacts.add(new Contact("HUY", "0333237860", R.drawable.ic_avatar1));
        contacts.add(new Contact("HUY 2", "0333237860", R.drawable.ic_avatar2));
        contacts.add(new Contact("HUY 3", "0333237860", R.drawable.ic_avatar3));
        contacts.add(new Contact("HUY 4", "0333237860", R.drawable.ic_avatar4));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactAdapter.unRegisterChildItemClick();
    }

    @Override
    public void phoneBtnClick(int position) {
        Contact contact = contacts.get(position);

    }
}