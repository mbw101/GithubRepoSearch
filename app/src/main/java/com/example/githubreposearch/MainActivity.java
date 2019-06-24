package com.example.githubreposearch;

import android.content.Context;
import android.net.Network;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import com.example.githubreposearch.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    // Create an edit text variable
    EditText searchBoxEditText;

    // create url display and search results text views
    TextView urlDisplayTextView;
    TextView searchResultsTextView;

    TextView errorMessageTextView;
    ProgressBar progressBar; // reference to a progress bar variable

    // github query task that uses asynctask
    public class GithubQueryTask extends AsyncTask<URL, Void, String>
    {
        @Override
        protected void onPreExecute() {
            // Sett the loading indicator to visible
            progressBar.setVisibility(View.VISIBLE);
        }

        // this doInBackground has to be rewritten
        // for the async class to work
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String githubSearchResults = null;

            try
            {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            // hide the loading indicator
            progressBar.setVisibility(View.INVISIBLE);
            if (s != null && !s.equals(""))
            {
                // Call showJsonDataView if we have valid, non-null results
                showJsonDataView();
                searchResultsTextView.setText(s);
            }
            else
            {
                // showErrorMessage if the result is null in onPostExecute
                showErrorMessage();
            }
        }
    }

    // show the data and hide the error
    public void showJsonDataView()
    {
        errorMessageTextView.setVisibility(View.INVISIBLE);
        searchResultsTextView.setVisibility(View.VISIBLE);
    }

    // show the error and hide the data
    public void showErrorMessage()
    {
        errorMessageTextView.setVisibility(View.VISIBLE);
        searchResultsTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // text views
        searchBoxEditText = findViewById(R.id.et_search_box);
        urlDisplayTextView = findViewById(R.id.urlDisplay);
        searchResultsTextView = findViewById(R.id.githubSearchResultsJson);
        errorMessageTextView = findViewById(R.id.errorMessageDisplay);

        // progress bar
        progressBar = findViewById(R.id.loadingIndicatorProgressBar);
    }

    public void makeGithubSearchQuery() {
        String githubQuery = searchBoxEditText.getText().toString();
        URL githubSearchURL = NetworkUtils.buildUrl(githubQuery);
        urlDisplayTextView.setText(githubSearchURL.toString());

        new GithubQueryTask().execute(githubSearchURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();

        // check to see if the search button was the item pressed
        if (menuItemThatWasSelected == R.id.action_search)
        {
            //Context context = MainActivity.this;
            //String message = "Search Clicked";

            // show a toast
            //Toast.makeText(context, message, Toast.LENGTH_LONG).show();

            // Make the github search
            makeGithubSearchQuery();
        }

        return super.onOptionsItemSelected(item);
    }
}
