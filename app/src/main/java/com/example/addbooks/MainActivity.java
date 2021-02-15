package com.example.addbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

import static com.example.addbooks.R.*;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button addButton;
    EditText addName, addAuthor, addYear;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        listView = findViewById(id.list_book);
        addButton = findViewById(id.add_button);
        addName = findViewById(id.edit_name);
        addAuthor = findViewById(id.edit_author);
        addYear = findViewById(id.edit_year);
        linearLayout = findViewById(id.my_layout);

        final LinkedList<Book> bookList = new LinkedList<>();
        bookList.add(new Book("Война и мир", "Лев Толстой", "1997", drawable.book));
        bookList.add(new Book("Основание", "Айзэк Азимов", "2017", drawable.osnovanie));
        bookList.add(new Book("Преступление и наказание", "Федор Достоевсуий", "1876", drawable.prestuplenie));
        bookList.add(new Book("Шинел", "Гоголь", "1678", drawable.shinel));
        bookList.add(new Book("Зерцалия", "Евгений Гоглоев", "2000", drawable.zertsalia));
        bookList.add(new Book("Идиот", "Не знаю", "неизвестный", drawable.book));

        LinkedList<HashMap<String, Object>> mapBook = new LinkedList<>();
        for (int i = 0; i < bookList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", bookList.get(i).name);
            map.put("author", bookList.get(i).author);
            map.put("year", bookList.get(i).year);
            map.put("cover", bookList.get(i).cover);
            mapBook.add(map);
        }

        String [] keyFrom = {"name", "author", "year", "cover"};
        int[] idTo = {R.id.name, R.id.author, R.id.year, R.id.cover};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, mapBook, R.layout.list_item, keyFrom, idTo);
        listView.setAdapter(simpleAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), bookList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addName != null && addAuthor != null && addYear != null) {
                    Book book = new Book(addName.getText().toString(), addAuthor.getText().toString(), addYear.getText().toString(), drawable.book);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", book.name);
                    map.put("author", book.author);
                    map.put("year", book.year);
                    map.put("cover", book.cover);
                    mapBook.add(map);
                    SimpleAdapter Adapter = new SimpleAdapter(getApplicationContext(), mapBook, R.layout.list_item, keyFrom, idTo);
                    listView.setAdapter(Adapter);
                } else Toast.makeText(getApplicationContext(), "Заполните все данные", Toast.LENGTH_SHORT).show();
            }
        });
    }
}