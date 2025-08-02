package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ITEM_REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private NoteTodoAdapter adapter;
    private List<Object> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        items = InMemoryDataSource.getInstance().getItems();
        adapter = new NoteTodoAdapter(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String itemType = data.getStringExtra(AddItemActivity.EXTRA_ITEM_TYPE);
            String itemContent = data.getStringExtra(AddItemActivity.EXTRA_ITEM_CONTENT);

            if (itemType != null && itemContent != null) {
                if (itemType.equals(AddItemActivity.ITEM_TYPE_NOTE)) {
                    InMemoryDataSource.getInstance().addItem(new Note(itemContent));
                } else {
                    InMemoryDataSource.getInstance().addItem(new Todo(itemContent));
                }
                adapter.notifyItemInserted(0);
                recyclerView.scrollToPosition(0);
            }
        }
    }
}
