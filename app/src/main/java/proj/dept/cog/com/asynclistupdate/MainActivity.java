package proj.dept.cog.com.asynclistupdate;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    LazyListAsync fireList;

    private static String[] sitems = new String[] { "Linux-RH", "Windows", "Tizen", "Suse",
            "Ubuntu", "Red Hat", "Solaris", "Android", "iOS", "VxWorks",
            "Debian", "RHEL", "Kitkat", "Android M", "Mac OS", "Win"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Normal app init code...
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lazyList);
        listView.setAdapter(new ArrayAdapter<String>( this,android.R.layout.simple_expandable_list_item_1,new ArrayList<String>() ));


         fireList = new LazyListAsync(this,listView,sitems);
          fireList.execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag","Main Activity on Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag","Main Activity on Pause STATUS OF ASYNC "+ fireList.getStatus());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag","Main Activity on Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Activity IS no more alive..", Toast.LENGTH_SHORT).show();
        Log.d("tag","Main Activity is dead..");
        // Though it works its not the right way to cancle a thread...
        // think??????
//        if(fireList != null) {
//            fireList.cancel(true);
//        }

    }
}
