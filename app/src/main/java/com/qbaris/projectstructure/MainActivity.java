package com.qbaris.projectstructure;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    Class aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setNavigationBarColor(Color.parseColor("#C46C00"));
        getWindow().setStatusBarColor(Color.parseColor("#C46C00"));

        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Ana Sayfa",R.drawable.anasayfa));
        items.add(new MenuItem("Yemekler",R.drawable.anasayfa));
        items.add(new MenuItem("Corbalar",R.drawable.anasayfa));
        items.add(new MenuItem("Tatlilar",R.drawable.anasayfa));

        sNavigationDrawer.setMenuItemList(items);
        sNavigationDrawer.setAppbarTitleTV("Ana Sayfa");

        aClass = Anasayfa.class;

        FragmentOlustur();

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        aClass = Anasayfa.class;
                        break;
                    case 1:
                        aClass = Yemekler.class;
                        break;
                    case 2:
                        aClass = Corbalar.class;
                        break;
                    case 3:
                        aClass = Tatlilar.class;
                        break;
                }
            }
        });

        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {
                FragmentOlustur();
            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });



    }

    private void FragmentOlustur() {
        try {
            Fragment fragment = (Fragment) aClass.newInstance();
            getSupportFragmentManager().
                    beginTransaction().
                    setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                    replace(R.id.frameLayout,fragment).
                    commit();
        }catch (IllegalAccessException | InstantiationException e){
            throw new RuntimeException(e);
        }
    }
}