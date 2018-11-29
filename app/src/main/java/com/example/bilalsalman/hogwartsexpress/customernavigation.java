package com.example.bilalsalman.hogwartsexpress;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.design.widget.TabLayout;
        import android.support.v4.view.ViewPager;
        import android.view.View;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.app.FragmentTransaction;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ImageView;
        import android.widget.TableLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.firebase.ui.storage.images.FirebaseImageLoader;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;

public class customernavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth auth;
    FirebaseDatabase db;
    FirebaseStorage stor;
    ImageView imageview;
    TextView custname,custemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customernavigation);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view1);
        View headerView = navView.getHeaderView(0);

        custname=(TextView) headerView.findViewById(R.id.custname);
        custemail=(TextView)headerView.findViewById(R.id.custemail);

        imageview=(ImageView)headerView.findViewById(R.id.profile_image1);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        stor = FirebaseStorage.getInstance();

        DatabaseReference ref = db.getReference("Users");

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser.equals(null))
        {
            custname.setText("No user signed in");
        }
        else{
            custemail.setText(currentUser.getEmail());

            ref.orderByChild("email").equalTo(currentUser.getEmail()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    UserDataForFireBase udata = dataSnapshot.getValue(UserDataForFireBase.class);
                    custname.setText(udata.firstName + " " + udata.lastNAme);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) { }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });



            StorageReference storef = stor.getReference().child(currentUser.getEmail());

            Glide.with(this).using(new FirebaseImageLoader()).load(storef).into(imageview);


        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs1);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager1);
        ctabsPager tabsPager= new ctabsPager(getSupportFragmentManager());


        viewPager.setAdapter(tabsPager);
        tabLayout.setupWithViewPager(viewPager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle("Hogwarts Express");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customernavigation, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs1);
        tabLayout.removeAllTabs();


        for(Fragment fragment:getSupportFragmentManager().getFragments()){

            getSupportFragmentManager().beginTransaction().hide(fragment).commit();
        }


        FragmentManager fragmentManager =getSupportFragmentManager();

        if (id == R.id.customerhome)
        {
            finish();

            Intent i = new Intent(getBaseContext(), customernavigation.class);
            startActivity(i);
        }
        else if (id == R.id.signoutcustomersidepannel)
        {
            Toast.makeText(getApplicationContext(), "Signing Out",
                    Toast.LENGTH_SHORT).show();
            auth.getInstance().signOut();
            finish();
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
        }
        else if (id == R.id.customersettings)
        {
            finish();
            Intent i = new Intent(getBaseContext(), cSettings.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }
}
