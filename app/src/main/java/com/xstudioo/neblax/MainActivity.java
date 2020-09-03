package net.xstudioo.neblaxman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    net.xstudioo.neblaxman.Adapter adapter;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // get the list of stories titles and contents in string array
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        String[] titles = getResources().getStringArray(R.array.stories_title);
        String[] contents = getResources().getStringArray(R.array.story_content);

        recyclerView = findViewById(R.id.storiesListsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new net.xstudioo.neblaxman.Adapter(this,titles,contents); // our adapter takes two string array
        recyclerView.setAdapter(adapter);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.exit)
        {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        }

        if(menuItem.getItemId() == R.id.share){
            Intent shareintent = new Intent();
            shareintent.setAction(Intent.ACTION_SEND);
            shareintent.putExtra(Intent.EXTRA_TEXT,"https:play.google.com/store/apps/details?id=net.xstudioo.neblaxman");
            shareintent.setType("text/plain");
            startActivity(Intent.createChooser(shareintent,"Share Now"));
        }
        if(menuItem.getItemId() == R.id.rateus)
        {
            Intent rateus = new Intent(Intent.ACTION_VIEW);
            rateus.setData(Uri.parse("https:play.google.com/store/apps/details?id=net.xstudioo.neblaxman"));
            startActivity(rateus);
        }


if(menuItem.getItemId() == R.id.home)
        {
            Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
        }




        return true;
    }
}
