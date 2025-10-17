package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Home_Page extends Activity {
    public static final String DATABASE_URL = "https://sse-project-67343-default-rtdb.firebaseio.com";

   private TextView mTextMessage;
    private Button Logout;
    private FirebaseAuth firebaseAuth;
    ViewFlipper v_flipper;
    private long backpressedtime;
    private  Toast backtoast;

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_prof)
                    startActivity(new Intent(Home_Page.this, ProfileActivity.class));
            else if (itemId == R.id.nav_work)
                    startActivity(new Intent(Home_Page.this,WorkForOTS.class));
            else if (itemId == R.id.nav_logout) {
                    startActivity(new Intent(Home_Page.this, LoginActivity.class));
                    firebaseAuth.signOut();
                    finish();
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        findViewById(R.id.itemSlid);
        findViewById(R.id.elect);
        findViewById(R.id.pumbl);
        findViewById(R.id.chefl);


        ImageView im=findViewById(R.id.im);
        ImageView img=findViewById(R.id.img);
        ImageView img1=findViewById(R.id.img1);
        ImageView img5=findViewById(R.id.img5);

        ImageView image=findViewById(R.id.image);
        ImageView image1=findViewById(R.id.image1);
        ImageView image2=findViewById(R.id.image2);
        ImageView image3=findViewById(R.id.image3);

        String ur ="https://media2.s-nbcnews.com/i/newscms/2017_41/2187641/171012-better-stock-house-cleaner-ew-531p_524663bd13e994184485cf04bf4a26e0.jpg";
        String url = "https://i.ytimg.com/vi/p-lo828o1dU/maxresdefault.jpg";
        String url1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzLpvVlT61RqQ7YgTnQV900k-DdSrdUe1veTLWKsux0TQ0kjHc";
        String url5 = "https://www.straitstimes.com/sites/default/files/articles/2017/10/11/bsgourmet111017.jpg";
        Picasso.with(this).load(ur).into(im);
        Picasso.with(this).load(url).into(img);
        Picasso.with(this).load(url1).into(img1);
        Picasso.with(this).load(url5).into(img5);

        //String ur1 ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_0xg5_Nd3GwRc6Og3gKsikEjqvDXbL2vKF4OmRa8YtiEjayCfqg";
        String ur1 ="https://5.imimg.com/data5/PB/MW/MY-7343282/tap-repair-500x500.jpg";
        //String url11 = "http://www.apprenticeshipguide.co.uk/wp-content/uploads/2016/04/Installation-and-maintenance-electrician-apprenticeships-800x500_c.jpg";
        String url11 = "https://5.imimg.com/data5/PB/MW/MY-7343282/tap-repair-500x500.jpg";
        String url111 = "https://5.imimg.com/data5/PB/MW/MY-7343282/tap-repair-500x500.jpg";
        String url2 = "https://media-cdn.tripadvisor.com/media/photo-s/0f/b7/fd/ad/special-mutton-thali.jpg";
        Picasso.with(this).load(ur1).into(image);
        Picasso.with(this).load(url11).into(image1);
        Picasso.with(this).load(url111).into(image2);
        Picasso.with(this).load(url2).into(image3);

        BottomNavigationView navigation = findViewById(R.id.main_nav);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Menu menu=navigation.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);
        firebaseAuth = FirebaseAuth.getInstance();
        LinearLayout maid1 = findViewById(R.id.Maid);
        LinearLayout electrician = findViewById(R.id.Electrician);
        LinearLayout plumber = findViewById(R.id.plumber);
        LinearLayout chef = findViewById(R.id.cheff);

        maid1.setOnClickListener(v -> {
            Intent maid2 = new Intent(Home_Page.this, MaidSelection.class);
            startActivity(maid2);
        });

        electrician.setOnClickListener(v -> startActivity(new Intent(Home_Page.this,ELectricianSelection.class)));
        plumber.setOnClickListener(v -> startActivity(new Intent(Home_Page.this,PlumberSelection.class)));
        chef.setOnClickListener(v -> startActivity(new Intent(Home_Page.this,ChefSelection.class)));

        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this::onBackPressedCallback);
    }
    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);
        //Animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void onBackPressedCallback() {
        if ((backpressedtime + 2000) > System.currentTimeMillis()) {
            backtoast.cancel();
            return;
        }

        backtoast = Toast.makeText(getBaseContext(),"Press Back Again to exit",Toast.LENGTH_SHORT);
        backtoast.show();
        backpressedtime=System.currentTimeMillis();
    }

}
