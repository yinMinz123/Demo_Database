package sg.edu.rp.c346.id20039202.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayAdapter<Task> aa;
    ArrayList<Task> al;
    EditText etDescription, etDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertTask(etDescription.getText().toString(),
                        etDate.getText().toString());

            }
        });
         btnGetTasks.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DBHelper dbh = new DBHelper(MainActivity.this);
                 ArrayList<String> data = dbh.getTaskContent();

                 String txt = "";
                 for(int i=0; i<data.size(); i++){
                     txt += i + ". " + data.get(i) + "\n";
                 }
                 tvResults.setText(txt);

                al.clear();
                al.addAll( dbh.getTasks(true) );
                aa.notifyDataSetChanged();
                dbh.close();
             }
         });
    }
}