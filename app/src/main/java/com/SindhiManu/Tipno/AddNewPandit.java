package com.SindhiManu.Tipno;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class AddNewPandit extends AppCompatActivity {

//    public static final String TAG = "AddNewPandit";
    private EditText panditNameEdit;
    private EditText panditAddressEdit;
    private EditText panditPhoneMain;
    private EditText panditPhone2;
    private EditText panditPhone3;
    private EditText panditPhone4;
    private EditText panditEmailMain;
    private EditText panditEmail2;

    private EditText pinCode;


    private ArrayList<String> phoneNos = new ArrayList<>();
    private ArrayList<String> emails = new ArrayList<>();

    private NeumorphButton mSaveButton;

    TextView state_text_view;
    TextView city_spinner;
    ArrayList<String> listOfStates;
    ArrayList<String> listOfCities;
    Dialog dialog;

    @Override // Hardcoding the view so that phone's display settings do not interfere with UI
    protected void attachBaseContext(Context newBase) {
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1.0f;
        applyOverrideConfiguration(override);

        super.attachBaseContext(newBase);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_new_pandit);

        panditNameEdit = findViewById(R.id.pandit_name_edit_text);
        panditAddressEdit = findViewById(R.id.pandit_address_edit_text);

        panditPhoneMain = findViewById(R.id.pandit_phone_no_edit_text1);
        panditPhone2 = findViewById(R.id.pandit_phone_no_edit_text2);
        panditPhone3 = findViewById(R.id.pandit_phone_no_edit_text3);
        panditPhone4 = findViewById(R.id.pandit_phone_no_edit_text4);
        pinCode = findViewById(R.id.entered_pin);
        panditEmailMain = findViewById(R.id.pandit_email_edit_text1);
        panditEmail2 = findViewById(R.id.pandit_email_edit_text2);

        state_text_view = findViewById(R.id.state_dropdown_text_view);
        city_spinner = findViewById(R.id.city_dropdown_text_view);

        listOfStates = new ArrayList<>();

        listOfStates.add("Andhra Pradesh");
        listOfStates.add("Arunachal Pradesh");
        listOfStates.add("Assam");
        listOfStates.add("Bihar");
        listOfStates.add("Chhattisgarh");
        listOfStates.add("Goa");
        listOfStates.add("Gujarat");
        listOfStates.add("Haryana");
        listOfStates.add("Himachal Pradesh");
        listOfStates.add("Jharkhand");
        listOfStates.add("Karnataka");
        listOfStates.add("Kerala");
        listOfStates.add("Madhya Pradesh");
        listOfStates.add("Maharashtra");
        listOfStates.add("Manipur");
        listOfStates.add("Meghalaya");
        listOfStates.add("Mizoram");
        listOfStates.add("Nagaland");
        listOfStates.add("Odisha");
        listOfStates.add("Punjab");
        listOfStates.add("Rajasthan");
        listOfStates.add("Sikkim");
        listOfStates.add("Tamil Nadu");
        listOfStates.add("Telangana");
        listOfStates.add("Tripura");
        listOfStates.add("Uttarakhand");
        listOfStates.add("Uttar Pradesh");
        listOfStates.add("West Bengal");

        listOfStates.add("Andaman and Nicobar Islands");
        listOfStates.add("Chandigarh");
        listOfStates.add("Daman & Diu");
        listOfStates.add("The Government of NCT of Delhi");
        listOfStates.add("Jammu & Kashmir");
        listOfStates.add("Ladakh");
        listOfStates.add("Lakshadweep");
        listOfStates.add("Puducherry (Pondicherry)");

        state_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(AddNewPandit.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner_states);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                EditText editText=dialog.findViewById(R.id.edit_text_states);
                ListView listView=dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter=new ArrayAdapter<>(AddNewPandit.this, android.R.layout.simple_list_item_1,listOfStates);

                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        state_text_view.setText(adapter.getItem(position));
                        state_text_view.setTextColor(getColor(R.color.black));
                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });

        mSaveButton = findViewById(R.id.add_pandit_button);
        System.out.println("Creating new pandit record");

        DAOPandit daoPandit = new DAOPandit();

        mSaveButton.setOnClickListener(v -> {
            Pandit pandit;

            if(panditPhoneMain.getText().toString().length() == 10){ //collecting entered phone no in phoneNos arrayList
                System.out.println("Adding phone no." + panditPhoneMain.getText().toString());
                phoneNos.add(panditPhoneMain.getText().toString());
                if(panditPhone2.getText().toString().length() == 10){
                    phoneNos.add(panditPhone2.getText().toString());
                    System.out.println("Adding phone no." + panditPhone2.getText().toString());
                }
                if(panditPhone3.getText().toString().length() == 10){
                    phoneNos.add(panditPhone3.getText().toString());
                    System.out.println("Adding phone no." + panditPhone3.getText().toString());
                }
                if(panditPhone4.getText().toString().length() == 10){
                    phoneNos.add(panditPhone4.getText().toString());
                    System.out.println("Adding phone no." + panditPhone4.getText().toString());
                }
            }

            if(panditEmailMain.getText().toString().length() > 0){ //collecting entered emails in email ArrayList
                emails.add(panditEmailMain.getText().toString());
                if(panditEmail2.getText().toString().length() > 0){
                    emails.add(panditEmail2.getText().toString());
                }
            }

            //Validating data before Entry to Database
            if(validateData(panditNameEdit.getText().toString(),panditAddressEdit.getText().toString(),panditPhoneMain.getText().toString(),
                    panditPhone2.getText().toString(),panditPhone3.getText().toString(), panditPhone4.getText().toString(),
                    panditEmailMain.getText().toString(),panditEmail2.getText().toString(),pinCode.getText().toString())){


                    pandit= new Pandit(panditNameEdit.getText().toString(),panditAddressEdit.getText().toString(),
                            state_text_view.getText().toString(), city_spinner.getText().toString(),
                            pinCode.getText().toString(), phoneNos,emails);

                daoPandit.addPanditToDatabase(pandit).addOnSuccessListener(suc ->{
                    Toast.makeText(this, "Details added", Toast.LENGTH_SHORT).show();
                    clear();

                }).addOnFailureListener(er ->{
                    Toast.makeText(this, "Detail addition failed" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

//            else{
////                Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show();
//            }


        });

    }

    public void clear() {
        panditNameEdit.setText("");
        panditAddressEdit.setText("");
        panditPhoneMain.setText("");
        panditPhone2.setText("");
        panditPhone3.setText("");
        panditPhone4.setText("");
        panditEmailMain.setText("");
        panditEmail2.setText("");
    }

    public void clear(EditText et){
        et.setText("");
    }

    public boolean validateData(String name, String Address, String phoneNoMain, String p2, String p3, String p4,
                                String emailMain, String emailAlternate, String pin){

        if(name.length()==0 || !name.matches("/D*.$")){
            Toast.makeText(this, "Name should contain only alphabets and should not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Address.length()<10){
            Toast.makeText(this, "Address should not be empty", Toast.LENGTH_SHORT).show();
            return false;
            }

        String[] phoneNos = new String[4];
        phoneNos[0] = phoneNoMain;
        phoneNos[1] = p2;
        phoneNos[2] = p3;
        phoneNos[3] = p4;

        if(phoneNoMain.length()==0){
            Toast.makeText(this, "Please enter at least 1 Phone no", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(!phoneNoMain.matches("^[6789]\\d{9}$")){
                Toast.makeText(this, "Please enter valid phone no", Toast.LENGTH_SHORT).show();
                clear();
            }
            for(int i=1; i<phoneNos.length; i++){
                if(!phoneNos[i].isEmpty() && !phoneNos[i].matches("^[6789]\\d{9}$")){
                    Toast.makeText(this, "Phone no." + i+1 + " not valid", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        if(!emailMain.matches("^(.+)@(\\S+)$")){
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!emailAlternate.isEmpty() && !emailAlternate.matches("^(.+)@(\\S+)$")){
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;

        }

        if(pin.isEmpty() || !pin.matches("/d{6}")){
            Toast.makeText(this, "Please enter valid pin", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
