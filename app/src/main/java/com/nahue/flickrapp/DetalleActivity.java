package com.nahue.flickrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DetalleActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private final static boolean SAVE_IMAGE_ON_DISK = true;

    RecyclerView recyclerViewDet;
    ArrayList<EntidadDetalle> listadetalle;

    private ImageView image;
    private Gson gson;
    private ImageFileUtil imageFileUtil;

    ArrayList<PostDetalleDirectorio> ListadoPhoto;
    RecyclerView.Adapter adapter;

    String root = Environment.getExternalStorageDirectory().toString();
    String sdirectorio = root + "/fotos";
    String sarchivo = "";
    //String archivo = "fotos/test.png";

    String url_album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        url_album=getIntent().getStringExtra("url_album");
        listadetalle = new ArrayList<>();
        recyclerViewDet = (RecyclerView) findViewById(R.id.recyclerViewDetalle);
        recyclerViewDet.setHasFixedSize(true);
        recyclerViewDet.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewD.setLayoutManager(new GridLayoutManager(this, 2));
        gson = new Gson();
        imageFileUtil = new ImageFileUtil();
        ListadoPhoto = new ArrayList<>();

        //File myDir = new File(root + "/" + folderPath);

        loadPostDetalle();
        //cargarDetalle();
        //loadPostDetalle();

        //AdaptadorDetalle adapter = new AdaptadorDetalle(listadetalle);

        adapter = new DetalleAdapter(listadetalle, new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getApplicationContext(), "Hice un clic: "+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),FotoFinalActivity.class));
            }
        });
        recyclerViewDet.setAdapter(adapter);
    }

    private void loadPostDetalle() {
        //URL de un album hardcodeda, deberia pasar la por parametro la activity anterior
        //String url = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=80e44d9e765e01bef6d4e1294caaf54d&photoset_id=72157720019424378&user_id=193985255%40N05&format=json&nojsoncallback=1";
        String url=url_album;
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        MyApplication.getSharedQueue().add(request);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            String pngarchivo;
            RootObject ro = gson.fromJson(response, RootObject.class);
            for(Photo p : ro.getPhotoset().getPhoto()) {

                PostDetalleDirectorio post = new PostDetalleDirectorio(p);
                sarchivo = post.getId().toString() + "_w.png";
                //pngarchivo = post.getId().toString() + "_w.png";
                //post.setPath(sdirectorio + "/"+ sarchivo);
                post.setPath(sdirectorio + "/"+ sarchivo);
                //siempre la voy a buscar a la foto.
                loadImage(post.getUri(),sdirectorio,sarchivo);
                //post.setpath = "";
                //listadetalle.add(new EntidadDetalle(post.getTitle(), R.drawable.foto1));
                Uri uri = imageFileUtil.getBitmapImageUri(sdirectorio, sarchivo);
                listadetalle.add(new EntidadDetalle(post.getTitle(), uri));

                ListadoPhoto.add(post);
                //lamada a la obtención de esa foto.
                //guarda foto
                //actqualizar el post. con  path
            }
            adapter.notifyDataSetChanged(); //actualizar el adapter mientras vaya trayendo datos.
        }
    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println(error.getMessage());
            Toast.makeText( getApplicationContext(),"Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                   ///////// loadImageImpl();
                }else{
                    Toast.makeText(this, "Unable to get image and then save it", Toast.LENGTH_LONG).show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void loadImage(String url, String directorio, String archivo) {
        if (SAVE_IMAGE_ON_DISK) {
            loadImageFromDisk(url, directorio, archivo);
        } else {
            loadImageImpl(url, directorio, archivo);
        }
    }

    private void loadImageFromDisk(String url, String directorio, String archivo) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                loadImageImpl(url, directorio, archivo);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
            }
        }else{
            loadImageImpl(url, directorio, archivo);
        }
    }

    private void loadImageImpl(String url, String directorio, String archivo) {
        ImageLoader imageLoader = MyApplication.getImageLoader();
        //String url = "https://live.staticflickr.com/65535/51504317081_a59b2bb444_w.jpg";
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText( getApplicationContext(),"Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    if (SAVE_IMAGE_ON_DISK) {
                        saveImageToDisk(response, directorio, archivo);
                    }
                    else {
                        //image.setImageBitmap(response.getBitmap());
                    }
                }
            }
        });
    }

    private void saveImageToDisk(ImageLoader.ImageContainer response, String directorio, String filename) {
        //boolean result = imageFileUtil.saveBitmapImageToExternalStorage(response.getBitmap(), "Fotos", "test.png", Bitmap.CompressFormat.PNG);
        boolean result = imageFileUtil.saveBitmapImageToExternalStorage(response.getBitmap(), directorio, filename, Bitmap.CompressFormat.PNG);
        if (result) {
            //image.setImageURI(imageFileUtil.getBitmapImageUri("fotos", "test.png"));
            //ver que debe hacer
        } else {
            //text.setText("Error saving image");
            System.out.println("Error saving image");
            Toast.makeText( getApplicationContext(),"Error saving image", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarDetalle2() {
        for(int i=0; i < ListadoPhoto.size(); i++){
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