package com.example.githubreposearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // Create an edit text variable
    EditText searchBoxEditText;
    TextView urlDisplayTextView;
    TextView searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBoxEditText = findViewById(R.id.et_search_box);
        urlDisplayTextView = findViewById(R.id.urlDisplay);
        searchResults = findViewById(R.id.githubSearchResultsJson);
    }
}
