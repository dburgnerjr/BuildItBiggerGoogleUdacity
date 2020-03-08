package com.danielburgnerjr.builditbiggergoogleudacity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
public class MainActivity extends AppCompatActivity {

    String strPackName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        strPackName = getApplicationContext().getPackageName();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File myDir = new File(getApplicationContext().getExternalFilesDir(null) + "/");
        String strPath = myDir.getPath();
        // below line displays the path
        Toast.makeText(getApplicationContext(), "MainActivity: " +  strPath, Toast.LENGTH_LONG).show();

        /*private bundle = Bundle()
        bundle.putString("path", myDir.toString())
        // set MainActivityFragment Arguments
        val fragobj = MainActivityFragment()
        fragobj.arguments = bundle

        val manager = supportFragmentManager

        // Begin the fragment transition using support fragment manager
        val transaction = manager.beginTransaction()

        // add the fragment on container
        transaction.add(R.id.main_fragment, fragobj)

        // Finishing the transition
        transaction.commit()*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
    }


}