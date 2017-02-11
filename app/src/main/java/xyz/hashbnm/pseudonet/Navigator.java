package xyz.hashbnm.pseudonet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Navigator extends AppCompatActivity implements View.OnClickListener {
    ImageView hotel,busstand,airport,atm,restaurant,
            petrol,railwaystations,banks,hospitals,search,translate,news,direction,weather,wikipedia,
    busbook,trains,flights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        hotel = (ImageView) findViewById(R.id.hotel);
        busstand = (ImageView) findViewById(R.id.bus);
        airport = (ImageView) findViewById(R.id.airport);
        atm = (ImageView) findViewById(R.id.atm);
        restaurant = (ImageView) findViewById(R.id.foodpoint);
        petrol = (ImageView) findViewById(R.id.petrol);
        railwaystations = (ImageView) findViewById(R.id.railway);
        banks = (ImageView) findViewById(R.id.bank);
        hospitals = (ImageView) findViewById(R.id.hospital);
        search = (ImageView) findViewById(R.id.search);
        translate = (ImageView) findViewById(R.id.translate);
        news = (ImageView) findViewById(R.id.news);
        direction = (ImageView) findViewById(R.id.directions);
        weather = (ImageView) findViewById(R.id.weather);
        wikipedia = (ImageView) findViewById(R.id.wikipedia);
        busbook = (ImageView) findViewById(R.id.Bus);
        trains = (ImageView) findViewById(R.id.railways);
        flights = (ImageView) findViewById(R.id.airports);
        hotel.setOnClickListener(this);busstand.setOnClickListener(this);airport.setOnClickListener(this);
        atm.setOnClickListener(this);restaurant.setOnClickListener(this);
        petrol.setOnClickListener(this);railwaystations.setOnClickListener(this);
        banks.setOnClickListener(this);hospitals.setOnClickListener(this);search.setOnClickListener(this);
        translate.setOnClickListener(this);news.setOnClickListener(this);direction.setOnClickListener(this);
        weather.setOnClickListener(this);wikipedia.setOnClickListener(this);
        busbook.setOnClickListener(this);trains.setOnClickListener(this);flights.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case (R.id.hotel):
                 intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Hotels");
                startActivity(intent);
                break;

            case (R.id.Bus):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Bus Stands");
                startActivity(intent);
                break;

            case (R.id.airport):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Airports");
                startActivity(intent);
                break;

            case (R.id.atm):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","ATMs");
                startActivity(intent);
                break;

            case (R.id.foodpoint):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Restaurants");
                startActivity(intent);
                break;

            case (R.id.petrol):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Petrol Pumps");
                startActivity(intent);
                break;

            case (R.id.railway):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Railway Stations");
                startActivity(intent);
                break;

            case (R.id.bank):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Banks");
                startActivity(intent);
                break;

            case (R.id.hospital):
                intent = new Intent(this,nearBy.class);
                intent.putExtra("type","Hospitals");
                startActivity(intent);
                break;

            case (R.id.search):
                break;

            case (R.id.translate):
                break;

            case (R.id.news):
                break;

            case (R.id.directions):
                intent = new Intent(this,News.class);
                intent.putExtra("type","News");
                startActivity(intent);
                break;

            case (R.id.weather):
                break;


            case (R.id.wikipedia):
                break;

            case (R.id.bus):
                break;

            case (R.id.railways):
                break;

            case (R.id.airports):
                break;


        }
    }
}