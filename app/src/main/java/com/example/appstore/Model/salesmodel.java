package com.example.appstore.Model;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

    public class salesmodel {
        private String id;
        private String buyer;
        private String product;
        private String value;
        private Object DatabaseReference;

        public salesmodel() {
        }

        public salesmodel(String id, String buyer, String product, String value) {
            this.id = id;
            this.buyer = buyer;
            this.product = product;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBuyer() {
            return buyer;
        }

        public void setBuyer(String buyer) {
            this.buyer = buyer;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {this.value = value;}


    }
