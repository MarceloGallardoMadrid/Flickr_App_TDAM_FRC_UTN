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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.nahue.flickrapp.Entidades.EntidadDetalle;
import com.nahue.flickrapp.Entidades.Photo;
import com.nahue.flickrapp.Entidades.PostDetalleDirectorio;
import com.nahue.flickrapp.Entidades.RootObject;

import java.util.ArrayList;


public class DetalleActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private final static boolean SAVE_IMAGE_ON_DISK = true;

    RecyclerView recyclerViewDet;
    //ArrayList<EntidadDetalle> listadetalle;
    ArrayList<PostDetalleDirectorio> listadetalle;

    private ImageView image;
    private Gson gson;
    private ImageFileUtil imageFileUtil;

    ArrayList<PostDetalleDirectorio> ListadoPhoto;
    //RecyclerView.Adapter adapter;
    DetalleAdapter adapter;

    String root = Environment.getExternalStorageDirectory().toString();
    String sdirectorio = root + "/fotos";
    String sarchivo = "";

    String url_album;
    String titulo;
    TextView TituloDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        TituloDetalle = (TextView) findViewById(R.id.tvTituloDetalle);
        url_album=getIntent().getStringExtra("url_album");
        titulo = getIntent().getStringExtra("titulo");
        TituloDetalle.setText(titulo);
        listadetalle = new ArrayList<>();
        recyclerViewDet = (RecyclerView) findViewById(R.id.recyclerViewDetalle);
        recyclerViewDet.setHasFixedSize(true);
        recyclerViewDet.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewD.setLayoutManager(new GridLayoutManager(this, 2));
        gson = new Gson();
        imageFileUtil = new ImageFileUtil();
        ListadoPhoto = new ArrayList<>();

        loadPostDetalle();

        //adapter = new DetalleAdapter(listadetalle, new OnItemClickListener() {
        adapter = new DetalleAdapter( new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getApplicationContext(), "Hice un clic: "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),FotoFinalActivity.class);

                intent.putExtra("titulo", listadetalle.get(position).getTitle());
                intent.putExtra("url_b", listadetalle.get(position).getUrl_b());
                intent.putExtra("uri_foto", listadetalle.get(position).getUri().toString());
                //PostDetalleDirectorio pdd = (PostDetalleDirectorio) listadetalle.get(recyclerViewDet.getChildAdapterPosition(v));
                intent.putExtra("photo_id", listadetalle.get(position).getId().toString());
                /* no pasa el objeto
                PostDetalleDirectorio pdd = (PostDetalleDirectorio) listadetalle.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objeto", pdd);
                intent.putExtras(bundle);
                                 */
                startActivity(intent);
            }
        });
        recyclerViewDet.setAdapter(adapter);
    }

    private void loadPostDetalle() {
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
                sarchivo = post.getId().toString() + "_b.png";
                post.setPath(sdirectorio + "/"+ sarchivo);
                //siempre la voy a buscar a la foto.
                loadImage(post.getUrl(),sdirectorio,sarchivo);
                Uri uri = imageFileUtil.getBitmapImageUri(sdirectorio, sarchivo);
                post.setUri(uri);
                //insertar en la base siempre y pisar.
                //levantar registros desde la base y ah?? llenar el listadetalle.

                listadetalle.add(post);
                adapter.addlista(post);

                //ListadoPhoto.add(post);
                //lamada a la obtenci??n de esa foto.
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

 /*
    private void cargarDetalle() {
        listadetalle.add(new EntidadDetalle("Nueva Foto",R.drawable.foto1));
        listadetalle.add(new EntidadDetalle("Disfrutando el paisaje",R.drawable.foto2));
        listadetalle.add(new EntidadDetalle("Disfrutando la vida",R.drawable.foto3));
        listadetalle.add(new EntidadDetalle("Haciendo ejemplos",R.drawable.foto4));
        listadetalle.add(new EntidadDetalle("Meti??ndole a full para que salga",R.drawable.foto5));
        listadetalle.add(new EntidadDetalle("Ver que pasa si es una descripci??n muy larga que no entra",R.drawable.foto6));
        listadetalle.add(new EntidadDetalle("Bien",R.drawable.foto7));
        listadetalle.add(new EntidadDetalle("Excelente",R.drawable.foto8));
    }

  */
}