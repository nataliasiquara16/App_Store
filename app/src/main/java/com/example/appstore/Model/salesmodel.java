package com.example.appstore.Model;

import android.provider.ContactsContract;

import com.example.appstore.setting.setFirebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
// classe para declarar os métodos set e get das variaveis.
    public class salesmodel {

        String Buyer,Product, Value;

        public String getBuyer() {
            return Buyer;
        }

        public String getProduct() {
            return Product;
        }

        public String getValue() {
            return Value;
        }

        public void setBuyer(String buyer) {
            Buyer = buyer;
        }

        public void setProduct(String product) {
            Product = product;
        }

        public void setValue(String value) {
            Value = value;
        }
    }


