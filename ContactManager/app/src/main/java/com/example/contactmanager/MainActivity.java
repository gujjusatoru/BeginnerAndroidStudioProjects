package com.example.contactmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactmanager.adapter.ContactAdapter;
import com.example.contactmanager.data.DataBaseHelp;
import com.example.contactmanager.data.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ContactAdapter contactAdapter;
    private ArrayList<Contact> listContacts=new ArrayList<>();
    private RecyclerView recyclerView;
    private DataBaseHelp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact Manager");

        recyclerView=findViewById(R.id.recycle);

        db=new DataBaseHelp(this);

        listContacts.addAll(db.getAllData());

        contactAdapter=new ContactAdapter(this, listContacts,MainActivity.this);

        RecyclerView.LayoutManager layout= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactAdapter);

        FloatingActionButton button=findViewById(R.id.floatbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditContact(false,null,-1);
            }
        });
    }

    public void EditContact(final boolean b, final Contact contact, final int position) {
        LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
        View view=inflater.inflate(R.layout.add_new,null);
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
        alertDialog.setView(view);
        TextView title=view.findViewById(R.id.titlecont);
        final EditText name=view.findViewById(R.id.inputname);
        final EditText email=view.findViewById(R.id.inputemail);
        final EditText number=view.findViewById(R.id.inputnum);
        title.setText(!b ?"Add New Contact":"Edit Contact");
        if(b&&contact!=null){
            name.setText(contact.getName());
            email.setText(contact.getEmail());
            number.setText(contact.getNumber());
        }
        alertDialog.setCancelable(false).
                setPositiveButton(b ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(b? "Delete": "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(b){
                            DeleteContact(contact,position);
                        }
                        else{
                            dialog.cancel();
                        }
                    }
                });
        final AlertDialog alert=alertDialog.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else alert.dismiss();

                if(b&&contact!=null){
                    UpdateContact(name.getText().toString(), email.getText().toString(),number.getText().toString(),position);
                }
                else {
                    CreateContact(name.getText().toString(),email.getText().toString(),number.getText().toString());
                    
                }
            }
        });
    }

    public void CreateContact(String name, String email,String number) {
        long id=db.insertContact(name,email,number);
        Contact newContact=db.getData(id);
        if(newContact!=null){
            listContacts.add(0,newContact);
            contactAdapter.notifyDataSetChanged();
        }
    }

    public void UpdateContact(String name, String email, String number, int position) {
        Contact newContact= listContacts.get(position);
        newContact.setName(name);
        newContact.setEmail(email);
        newContact.setNumber(number);
        db.updateContact(newContact);
        listContacts.set(position,newContact);
        contactAdapter.notifyDataSetChanged();
    }

    public void DeleteContact(Contact contact, int position) {
        listContacts.remove(position);
        db.deleteContact(contact);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lay,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}