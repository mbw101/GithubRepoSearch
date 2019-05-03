package com.example.githubreposearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // Create an edit text variable
    EditText searchBoxEditText;
    TextView urlDisplayTextView;
    TextView searchResults;

    final static String GIT_HUB_BASE_URL =
            "https://api.github.com/search/repositories";
    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBoxEditText = findViewById(R.id.et_search_box);
        urlDisplayTextView = findViewById(R.id.urlDisplay);
        searchResults = findViewById(R.id.githubSearchResultsJson);
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
            Context context = MainActivity.this;
            String message = "Search Clicked";

            // show a toast
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
