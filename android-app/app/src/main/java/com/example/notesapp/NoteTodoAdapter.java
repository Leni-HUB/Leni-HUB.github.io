package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteTodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NOTE = 1;
    private static final int VIEW_TYPE_TODO = 2;

    private List<Object> items;

    public NoteTodoAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Note) {
            return VIEW_TYPE_NOTE;
        } else if (items.get(position) instanceof Todo) {
            return VIEW_TYPE_TODO;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_NOTE) {
            View view = inflater.inflate(R.layout.list_item_note, parent, false);
            return new NoteViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.list_item_todo, parent, false);
            return new TodoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_NOTE) {
            Note note = (Note) items.get(position);
            ((NoteViewHolder) holder).bind(note);
        } else {
            Todo todo = (Todo) items.get(position);
            ((TodoViewHolder) holder).bind(todo);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView content;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.note_content);
        }

        public void bind(Note note) {
            content.setText(note.getContent());
        }
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private CheckBox checkBox;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.todo_content);
            checkBox = itemView.findViewById(R.id.todo_checkbox);
        }

        public void bind(Todo todo) {
            content.setText(todo.getContent());
            // Remove any existing listener to prevent unexpected behavior
            checkBox.setOnCheckedChangeListener(null);
            // Set the checkbox state based on the Todo item
            checkBox.setChecked(todo.isDone());
            // Set a new listener to update the Todo item when the checkbox is clicked
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                todo.setDone(isChecked);
            });
        }
    }
}
