package co.aartea.baseballapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        PlayerSQLiteHelper helper = PlayerSQLiteHelper.getInstance(DetailActivity.this);

        int id = getIntent().getIntExtra("id",-1);

        if(id >= 0){
            String description = helper.getDescriptionById(id);
            TextView textView = (TextView)findViewById(R.id.last_name_text);
            textView.setText(description);
        }
    }
}
