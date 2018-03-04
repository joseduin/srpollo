package com.patricia.srpollo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.patricia.srpollo.adaptador.PagerAdapter;
import com.patricia.srpollo.fragment.FaltantesProductos;
import com.patricia.srpollo.fragment.FaltantesResponsable;
import com.patricia.srpollo.interfaz.IBaseActivity;

import java.util.ArrayList;

public class ProductosFaltantesActivity extends AppCompatActivity implements IBaseActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_faltantes);

        enlazarVista();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle( "Productos Faltantes" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViewPager();
        OnClick();
        esconderTeclado();
    }
    private void esconderTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpViewPager() {

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), agregarFragment()) {
        };
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);
        setTabsText();
    }

    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new FaltantesProductos());
        fragments.add(new FaltantesResponsable());


        return fragments;
    }

    private void setTabsText() {
        tablayout.getTabAt(0).setText("Faltantes");
        tablayout.getTabAt(1).setText("Responsable");
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void enlazarVista() {

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tablayout = findViewById(R.id.tablayout);
        

    }
}
