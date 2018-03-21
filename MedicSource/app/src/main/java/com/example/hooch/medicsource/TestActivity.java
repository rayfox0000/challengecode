package com.example.hooch.medicsource;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Arrays;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TESTACT";
    private SlidingUpPanelLayout mLayout;
    TextView textView;
    ListView listview;
    List<String> array_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init();
        setListview();   // call setListview method
        panelListener();// call init method
    }

    /**
     * Initialization of the textview and SlidingUpPanelLayout
     */
    public void init() {

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        textView = (TextView) findViewById(R.id.list_main);
        listview = (ListView) findViewById(R.id.list);
    }

    public List<String> array_list() {
       array_list = Arrays.asList(
                "This",
                "Is",
                "An",
                "Example",
                "ListView",
                "That",
                "You",
                "Can",
                "Scroll",
                ".",
                "It",
                "Shows",
                "How",
                "Any",
                "Scrollable",
                "View",
                "Can",
                "Be",
                "Included",
                "As",
                "A",
                "Child",
                "Of",
                "SlidingUpPanelLayout"
        );
        return  array_list;
    }

    public void setListview(){

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                textView.setText(array_list.get(position));
                Toast.makeText(TestActivity.this, "onItemClick" , Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * This is array adapter, it takes context of the activity as a first parameter,
         * layout of the listview as a second parameter and array as a third parameter.
         */
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter <String>(
                this,
                android.R.layout.simple_list_item_1,
                array_list());

        listview.setAdapter(arrayAdapter);
    }

    public void panelListener(){

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

                                                  // During the transition of expand and collapse onPanelSlide function will be called.
                                                  @Override
                                                  public void onPanelSlide(View panel, float slideOffset) {

                                                      Log.e(TAG, "onPanelSlide, offset " + slideOffset);
                                                  }

                                                  @Override
                                                  public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

                                                  }

//                                                  // Called when secondary layout is dragged up by user
//                                                  @Override
//                                                  public void onPanelExpanded(View panel) {
//
//                                                      Log.e(TAG, "onPanelExpanded");
//                                                  }
//
//                                                  // Called when secondary layout is dragged down by user
//                                                  @Override
//                                                  public void onPanelCollapsed(View panel) {
//
//                                                      Log.e(TAG, "onPanelCollapsed");
//                                                  }
//
//                                                  @Override
//                                                  public void onPanelAnchored(View panel) {
//
//                                                      Log.e(TAG, "onPanelAnchored");
//                                                  }
//
//                                                  @Override
//                                                  public void onPanelHidden(View panel) {
//
//                                                      Log.e(TAG, "onPanelHidden");
//                                                  }
                                              });
    }

    @Override
    public void onBackPressed() {

        if ( mLayout != null &&
        (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED
                || mLayout.getPanelState() ==
                SlidingUpPanelLayout.PanelState.ANCHORED)){

            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

        } else{

            super.onBackPressed();
        }
    }
}