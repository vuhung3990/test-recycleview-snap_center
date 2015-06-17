package com.example.tuandv.snapcenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private RecyclerView recycle;
    private TextView status;
    private LinearLayoutManager layoutManager;
    private View snap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycle.setHasFixedSize(false);

        final List<Long> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add((long) i);
        }

        list.add(0, -1L);
        list.add(0, -1L);
        list.add(-1L);
        list.add(-1L);

        Adapter adapter = new Adapter(list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);


        status = (TextView) findViewById(R.id.status);
        snap = (View) findViewById(R.id.snap);
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fist = layoutManager.findFirstVisibleItemPosition();
                int last = layoutManager.findLastVisibleItemPosition();
                int current = (fist + last)/2;
                int base_minute = 5 * 60; // 5 in adapter

                View currentItem = layoutManager.findViewByPosition(current);
                int current_percent = ((snap.getLeft() - currentItem.getLeft())*100)/currentItem.getMeasuredWidth();

                status.setText(String.format("fist item=%d, last item=%d\n" +
                        "where=%d(%d)\n" +
                        "current left=%d, percent=%d\n" +
                        "time: %s", fist, last, current, list.get(current), currentItem.getLeft(), current_percent, convertSecondsToHMmSs(((list.get(current) * base_minute) + (base_minute * current_percent/100)) * 1000 ) ) );
            }
        });
    }

    /**
     * convert milisec to hh:MM:ss
     * @param millisUntilFinished
     * @return String time
     */
    public static String convertSecondsToHMmSs(long millisUntilFinished) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
    }

    @Override
    public void onBackPressed() {

        // scroll to position (center snap)
        int fistPosVisivble = layoutManager.findFirstVisibleItemPosition();
        int base_width = layoutManager.findViewByPosition(fistPosVisivble).getMeasuredWidth();

        // set scroll ide now
        recycle.stopScroll();

        // constance offset
        float OFFSET_AT_START_POINT = 1.999999f; // sai so 1/1000000
        float OFFSET_AT_MIDDLE_POINT = 1.5f;
        int OFFSET_AT_END_POINT = 1;

        // scroll to position
        layoutManager.scrollToPositionWithOffset(50, (int)(base_width * OFFSET_AT_START_POINT));
        /****************************************************************************************/


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
}
