package com.nahue.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.nahue.flickrapp.Entidades.Comment;
import com.nahue.flickrapp.Entidades.EntidadComentario;
import com.nahue.flickrapp.Entidades.Photo;
import com.nahue.flickrapp.Entidades.RootObjectComentario;
import com.nahue.flickrapp.databd.Root;
import com.nahue.flickrapp.databd.USER_DATA;

import java.util.ArrayList;

public class FotoFinalActivity extends AppCompatActivity {

    TextView tvTitulo;
    TextView textView;
    ImageView ivFoto;
    private Gson gson;

    RecyclerView recyclerViewComentario;
    RecyclerView.Adapter adapter;

    ArrayList<EntidadComentario> listacomentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_final);
        listacomentario = new ArrayList<>();
        gson = new Gson();

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);
        String titulo = getIntent().getStringExtra("titulo");
        //Long photo_id = getIntent().getLongExtra("photo_id", 0);
        String photo_id = getIntent().getStringExtra("photo_id");
        String Foto_uri = getIntent().getStringExtra("uri_foto");
        Uri fileUri = Uri.parse(Foto_uri);
        ivFoto.setImageURI(fileUri);

        tvTitulo.setText(titulo);

        recyclerViewComentario = (RecyclerView) findViewById(R.id.recyclerViewComentario);
        recyclerViewComentario.setHasFixedSize(true);
        recyclerViewComentario.setLayoutManager(new LinearLayoutManager(this));

        //cargarcomentario();
        loadComentarioList(photo_id);
        //ComentarioAdapter adapter = new RecyclerView(listacomentario);
        adapter = new ComentarioAdapter(listacomentario);
        recyclerViewComentario.setAdapter(adapter);
        //adapter.notifyDataSetChanged(); //actualizar el adapter mientras vaya trayendo datos.
    }

    private void loadComentarioList(String photo_id) {
        //String url = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=80e44d9e765e01bef6d4e1294caaf54d&photoset_id=72157720019424378&user_id=193985255%40N05&format=json&nojsoncallback=1";
        //String url_comentario = "https://www.flickr.com/services/rest/?method=flickr.photos.comments.getList&api_key=80e44d9e765e01bef6d4e1294caaf54d&photo_id=51631896121&format=json&nojsoncallback=1";
        String url_comentario = getUrlComentarios(photo_id);
        StringRequest request = new StringRequest(Request.Method.GET, url_comentario, onPostsLoaded, onPostsError);
        MyApplication.getSharedQueue().add(request);
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            RootObjectComentario ro =gson.fromJson(response,RootObjectComentario.class);
            for(Comment c : ro.getComments().getComment()){
                listacomentario.add(new EntidadComentario(c.get_content()));
            }
            adapter.notifyDataSetChanged();
        }
    };
        private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText( getApplicationContext(),"Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

    private void cargarcomentario(){
        listacomentario.add(new EntidadComentario("es muy buena la foto."));
        listacomentario.add(new EntidadComentario("es muy buena la foffddsg to."));
        listacomentario.add(new EntidadComentario("es muy buena la fotodsafsadfasdfasd."));
    }

    private String getUrlComentarios(String photo_id){
        return USER_DATA.URL_REQUEST
                +USER_DATA.COMENTARIOS_MET
                +USER_DATA.AND
                +USER_DATA.API_KEY
                +USER_DATA.AND
                +"photo_id="
                +photo_id
                +USER_DATA.AND
                +USER_DATA.FORMAT
                +USER_DATA.AND
                +"nojsoncallback=1";
    }
}