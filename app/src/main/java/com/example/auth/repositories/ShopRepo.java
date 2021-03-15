package com.example.auth.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.auth.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ShopRepo {

        private MutableLiveData<List<Product>> mutableProductList;

        public LiveData<List<Product>> getProducts() {
            if (mutableProductList == null) {
                mutableProductList = new MutableLiveData<>();
                loadProducts();
            }
            return mutableProductList;
        }

        private void loadProducts() {
            List<Product> productList = new ArrayList<>();
            productList.add(new Product(UUID.randomUUID().toString(), "Eliquis", 19, true, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fimages%204.jpg?alt=media&token=41bf6b4d-25fb-4a90-a2bf-5d31d4f3e2c9" ));
            productList.add(new Product(UUID.randomUUID().toString(), "Protien Shake", 19, true, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fimages%20(2).jpg?alt=media&token=b1d1ae28-17d1-4a7c-8b26-14f0fa4703e3"));
            productList.add(new Product(UUID.randomUUID().toString(), "BP Monitor", 19, true, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fimages%20(1).jpg?alt=media&token=3ed6db27-9846-4e37-ac23-5b908ad4fe69"));
            productList.add(new Product(UUID.randomUUID().toString(), "Covifor", 10, false, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fimages%205.jpg?alt=media&token=1e85e771-bdd8-448f-8a9a-72d2319a3d99"));
            productList.add(new Product(UUID.randomUUID().toString(), "TG cream", 15, true, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fimages%207.jpg?alt=media&token=f5b9b0a3-4ece-40ae-bc04-88097a3a61da"));
            productList.add(new Product(UUID.randomUUID().toString(), "VelpaClear", 19, true, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fpharma%202.jpg?alt=media&token=4d384eaa-e303-4fd1-9ffd-248d34bafda3"));
            productList.add(new Product(UUID.randomUUID().toString(), "Doxysina", 3, false, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fpharma.jpg?alt=media&token=b6c5a2b1-8e89-4844-8115-af0eed62224c"));
            productList.add(new Product(UUID.randomUUID().toString(), "Colchichine", 99, true, "https://firebasestorage.googleapis.com/v0/b/auth-b13f0.appspot.com/o/pictures%2Fpharma3.jpg?alt=media&token=7a4bc3c1-c91b-44fd-b6b0-15d57c33942b"));

            mutableProductList.setValue(productList);
        }
    }


