package com.kriyatma.nodesocket;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by malli on 5/21/2016.
 */
public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    String name;
    private Button btnRegister;
    // private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private ProgressDialog pDialog;
    //private SessionManager session;
    private SQLiteHandler db;
    //  private ProgressDialog pDialog;
 //   private FloatingActionButton fab;
    JSONParser jsonParser;
    // url to create new product
    // String url_create_product = "http://192.168.1.5:3000/login";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
   /* ConnectionDetector cd;
    private CoordinatorLayout coordinatorLayout;
   */
    public LoginActivity() throws IOException, JSONException {
        jsonParser = new JSONParser();
    }
    private Socket socket;
    {
        try{
            socket = IO.socket("http://192.168.1.3:3000");
        }catch(URISyntaxException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    /*    coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
      */  // Edit Text
        inputFullName = (EditText) findViewById(R.id.name);
        //  inputEmail = (EditText) findViewById(R.id.email);


        db = new SQLiteHandler(getApplicationContext());

        // Create button
        Button btnlogin = (Button) findViewById(R.id.btnLogin);
        //fab = (FloatingActionButton) findViewById(R.id.fab);
        Button btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // button click event
     /*   btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
*/


        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                //view = this.getCurrentFocus();
           /*     if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
*/
  //              isInternetPresent = cd.isConnectingToInternet();

                // check for Internet status
    //            if (isInternetPresent) {
      if(true){
                    // Internet Connection is Present
                    // make HTTP requests
                    /*showAlertDialog(this, "Internet Connection",
                            "You have internet connection", true);
                */
                    Log.d("internet connection", "u hve internet connection");
                } else {
               /*     Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);

                    // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);

                    snackbar.show();
*/                }
                String name = inputFullName.getText().toString();



                if (!name.isEmpty()) {
                    new CreateNewProduct().execute();

                }else{

  /*                  Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Please Enter Your Credentials!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);

                    // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);

                    snackbar.show();
    */            }

                // creating new product in background thread
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Validating.....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
             name = inputFullName.getText().toString();

            Log.d("clicked","yes"+name);
            Log.d("name",name);

            if (name.isEmpty()) {
                Log.d("going",name);
                db.addUser(name);
               // socket.connect();
//                socket.on("message", handleIncomingMessages);
                Log.d("name123", name);

            //    socket.on("message", handleIncomingMessages);

/*
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Please Enter Your Credentials!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.RED);

                // Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                snackbar.show();
*/
            }

            /*JSONObject json = null;
            try {
                json = jsonParser.makeHttpRequest2(AppConfig.url_create_product,
                        "POST", name, password);
            } catch (IOException e) {
                e.printStackTrace();
            }



            Log.d("json after json parser", String.valueOf(json));

            if(json != null){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("message", "raipur");
                startActivity(i);

            }
*/
            return null;

        }




        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            db.addUser(name);
            Log.d("username123",name);
            Intent intent=new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        }

    }

}

