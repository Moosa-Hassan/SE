package com.example.lab5_starter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CityDialogFragment.CityDialogListener {

    private Button addCityButton;
    private Button deleteCityButton;
    private Boolean delete = Boolean.FALSE ;
    private ListView cityListView;

    private FirebaseFirestore db;
    private CollectionReference citiesRef;
    private ArrayList<City> cityArrayList;
    private ArrayAdapter<City> cityArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set views
        addCityButton = findViewById(R.id.buttonAddCity);
        cityListView = findViewById(R.id.listviewCities);
        deleteCityButton = findViewById(R.id.buttonDeleteCity);

        // create city array
        cityArrayList = new ArrayList<>();
        cityArrayAdapter = new CityArrayAdapter(this, cityArrayList);
        cityListView.setAdapter(cityArrayAdapter);

//        addDummyData();

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
        if (firebaseApp != null) {
            db = FirebaseFirestore.getInstance();
            citiesRef = db.collection("cities");

            citiesRef.addSnapshotListener((value,error)->{
               if (error != null){
                   Log.e("Firestore", error.toString());
               }
               if (value != null  && !value.isEmpty()){
                   cityArrayList.clear();
                   for (QueryDocumentSnapshot snapshot : value){
                       String name = snapshot.getString("name");
                       String province = snapshot.getString("province");
                       cityArrayAdapter.add(new City(name,province));
                   }
                   cityArrayAdapter.notifyDataSetChanged();
               }
            });
        } else {
            Log.e("Firebase", "Firebase initialization failed. Firestore features are disabled.");
        }

        // set listeners
        addCityButton.setOnClickListener(view -> {
            CityDialogFragment cityDialogFragment = new CityDialogFragment();
            cityDialogFragment.show(getSupportFragmentManager(),"Add City");
        });

        deleteCityButton.setOnClickListener(view->{
            delete = Boolean.TRUE;
        });

        cityListView.setOnItemClickListener((adapterView, view, i, l) -> {
            City city = cityArrayAdapter.getItem(i);
            if (delete) {
                citiesRef.document(city.getName()).delete();
                cityArrayAdapter.remove(city);
                cityArrayAdapter.notifyDataSetChanged();
            }
            else {
                CityDialogFragment cityDialogFragment = CityDialogFragment.newInstance(city);
                cityDialogFragment.show(getSupportFragmentManager(), "City Details");
            }
            delete = Boolean.FALSE;
        });

    }

    @Override
    public void updateCity(City city, String title, String year) {
        city.setName(title);
        city.setProvince(year);
        cityArrayAdapter.notifyDataSetChanged();

        // Updating the database using delete + addition
    }

    @Override
    public void addCity(City city){
        cityArrayList.add(city);
        cityArrayAdapter.notifyDataSetChanged();

        if (citiesRef != null) {
            DocumentReference doc = citiesRef.document(city.getName());
            doc.set(city);
        }

    }

    public void addDummyData(){
        City m1 = new City("Edmonton", "AB");
        City m2 = new City("Vancouver", "BC");
        cityArrayList.add(m1);
        cityArrayList.add(m2);
        cityArrayAdapter.notifyDataSetChanged();
    }
}