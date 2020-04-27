package proj.dept.cog.com.asynclistupdate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by admin on 8/1/2016.
 *
 */

public class LazyListAsync extends AsyncTask<String,String,String>
{

    private Context myContext;
    ListView listView ;
    String[] items ;

    public LazyListAsync(MainActivity mainActivity, ListView listView,String[] items) {

        myContext = mainActivity;
        this.listView = listView;
        this.items = items ;


    }

    ArrayAdapter<String> listAdapter;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        listAdapter = (ArrayAdapter<String>)listView.getAdapter();
    }

    @Override
    protected String doInBackground(String... values) {

        // Change the orientation and watch the memory leak.. by clicking on logcat
        // when the orientation changes VIEW is no more percisting
        // But te following loop goes onnn....

        // Press back button Main activity gets killed still Async task  is at work.. gets killed..
        // Remember you closed the mainActivity not the os so the allocated memory is still lingering around for your thread to suffice.


        for(String OSOwners : items)
        {

            Log.d("tag"," Names : "+OSOwners);
            /// This will go onnnn with the data since this thread is not aware of the death of the Activity


            publishProgress(OSOwners);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return("Task Completed..... .. .");

    }




    @Override
    protected void onProgressUpdate(String... values) {
        // super.onProgressUpdate(values);
        Log.d("tag","Receiving : "+values[0]);
        listAdapter.add(values[0]);
    }

    @Override
    protected void onPostExecute(String jobStatus ) {
        // super.onPostExecute(aVoid);
        Toast.makeText(myContext, jobStatus, Toast.LENGTH_SHORT).show();
    }
}