package com.nahue.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nahue.flickrapp.Entidades.Photo;
import com.nahue.flickrapp.Entidades.PostDetalleDirectorio;
import com.nahue.flickrapp.Entidades.RootObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    RecyclerView recyclerViewDet;
    ArrayList<EntidadDetalle> listadetalle;

    private ImageView image;
    private Gson gson;

    ArrayList<PostDetalleDirectorio> ListadoPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        listadetalle = new ArrayList<>();
        recyclerViewDet = (RecyclerView) findViewById(R.id.recyclerViewDetalle);
        recyclerViewDet.setHasFixedSize(true);
        recyclerViewDet.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewD.setLayoutManager(new GridLayoutManager(this, 2));
        gson = new Gson();

        loadPostDetalle();
        //cargarDetalle();
        //loadPostDetalle();
        //cargarDetalle2();
        //AdaptadorDetalle adapter = new AdaptadorDetalle(listadetalle);

        RecyclerView.Adapter adapter = new DetalleAdapter(listadetalle, new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getApplicationContext(), "Hice un clic: "+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),FotoFinalActivity.class));
            }
        });
        recyclerViewDet.setAdapter(adapter);
    }

    private void loadPostDetalle() {
        String url = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=80e44d9e765e01bef6d4e1294caaf54d&photoset_id=72157720019424378&user_id=193985255%40N05&format=json&nojsoncallback=1";
        //String url = "https://kylewbanks.com/rest/posts.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        MyApplication.getSharedQueue().add(request);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            RootObject ro = gson.fromJson(response, RootObject.class);
            for(Photo p : ro.getPhotoset().getPhoto()){
                Photo photo = new Photo(p.getId(), p.getSecret(),p.getServer(),p.getTitle(),p.getIsprimary());
                PostDetalleDirectorio post = new PostDetalleDirectorio(photo);
                ListadoPhoto.add(post);
            }
            //text.setText(builder.toString());
            //showText();
        }
    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //text.setText("Error: " + error.getMessage());
            //showText();
            System.out.println(error.getMessage());
            //Toast.makeText( getApplicationContext(),"Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void loadImage() {
        ImageLoader imageLoader = MyApplication.getImageLoader();

        String url = "https://live.staticflickr.com/65535/51504317081_a59b2bb444_w.jpg";
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //text.setText("Error: " + error.getMessage());
                //showText();
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                image.setImageBitmap(response.getBitmap());
                //showImage();
            }
        });
    }

    private void cargarDetalle2() {
        for(int i=0; i <= ListadoPhoto.size(); i++){
            listadetalle.add(new EntidadDetalle(ListadoPhoto.get(i).getTitle(), R.drawable.foto1));
        }
    }

    private void cargarDetalle() {
        listadetalle.add(new EntidadDetalle("Nueva Foto",R.drawable.foto1));
        listadetalle.add(new EntidadDetalle("Disfrutando el paisaje",R.drawable.foto2));
        listadetalle.add(new EntidadDetalle("Disfrutando la vida",R.drawable.foto3));
        listadetalle.add(new EntidadDetalle("Haciendo ejemplos",R.drawable.foto4));
        listadetalle.add(new EntidadDetalle("Metiéndole a full para que salga",R.drawable.foto5));
        listadetalle.add(new EntidadDetalle("Ver que pasa si es una descripción muy larga que no entra",R.drawable.foto6));
        listadetalle.add(new EntidadDetalle("Bien",R.drawable.foto7));
        listadetalle.add(new EntidadDetalle("Excelente",R.drawable.foto8));


    }
}