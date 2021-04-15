package com.example.android_team_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ResultPageActivity extends Activity {

    private TextView title;
    private TextView description;
    private ImageView imageView;
    private String review;
    private String imgURL;
    private String price;
    private ArrayList<Comment> commentArrayList = new ArrayList<Comment>();
    private CommentAdapter commentAdapter;
    private ListView commentsListView;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_page);

        // REFERENCES TO UI
        title = (TextView) findViewById(R.id.resultsTitleTxtV);
        description = (TextView) findViewById(R.id.resultsDescriptionTextV);
        imageView = (ImageView) findViewById(R.id.resultsImageView);
        commentsListView = (ListView) findViewById(R.id.resultsCommentsListView);

        Intent intent = getIntent();
        String nameData;
        nameData = intent.getStringExtra("name");
        String descriptionData;
        descriptionData = intent.getStringExtra("description");

        // IMAGE URL, RATING, PRICE
        review = intent.getStringExtra("review");
        imgURL = intent.getStringExtra("imageURL");
        price = intent.getStringExtra("price");

        Comment c = new Comment(review, price);
        commentArrayList.add(c);
        commentAdapter = new CommentAdapter(this, commentArrayList);
        commentsListView.setAdapter(commentAdapter);

        getImage();
        title.setText(nameData);
        description.setText(descriptionData);

    }

    public void getImage (){
        new Thread() { // retrieving the image on another thread
            public void run() {
                try {
                    //Step 1: Create and send a HTTP request to a URL.
                    Message msg = Message.obtain();
                    InputStream inputStream = null;
                    URL url = new URL(imgURL);
                    URLConnection urlConn = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection)  urlConn;
                    httpConnection.setRequestMethod("GET");
                    httpConnection.connect();
                    inputStream = httpConnection.getInputStream();

                    //Step 2: Once the server replies, receive the response and parse it.
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmap", bitmap);
                    msg.setData(bundle);
                    inputStream.close();

                    //Step 3: Using the main thread, update the UI
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage (Message msg){
            imageView.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
        }
    };
}
