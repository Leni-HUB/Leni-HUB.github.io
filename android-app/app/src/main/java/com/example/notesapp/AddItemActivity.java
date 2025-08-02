package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_TYPE = "com.example.notesapp.EXTRA_ITEM_TYPE";
    public static final String EXTRA_ITEM_CONTENT = "com.example.notesapp.EXTRA_ITEM_CONTENT";
    public static final String ITEM_TYPE_NOTE = "NOTE";
    public static final String ITEM_TYPE_TODO = "TODO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        EditText editTextContent = findViewById(R.id.edit_text_content);
        RadioGroup radioGroupType = findViewById(R.id.radio_group_type);
        Button buttonSave = findViewById(R.id.button_save);

        buttonSave.setOnClickListener(view -> {
            String content = editTextContent.getText().toString().trim();
            if (content.isEmpty()) {
                // In a real app, we might show a Toast message.
                // For now, we just cancel.
                setResult(RESULT_CANCELED);
                finish();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_ITEM_CONTENT, content);

            int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
            if (selectedTypeId == R.id.radio_button_note) {
                resultIntent.putExtra(EXTRA_ITEM_TYPE, ITEM_TYPE_NOTE);
            } else {
                resultIntent.putExtra(EXTRA_ITEM_TYPE, ITEM_TYPE_TODO);
            }

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
