package com.example.mnrr.whattodolist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by rohan on 02-11-2016.
 */
class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MainActivity.tasksList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ScrollView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.display_task, container, false);

        TextView titleName = (TextView) itemView.findViewById(R.id.title);
        TextView detailsTask = (TextView) itemView.findViewById(R.id.details);

        String title = MainActivity.tasksList.get(position).getTitle();
        String details = MainActivity.tasksList.get(position).getDetails();

        titleName.setText(title);
        detailsTask.setText(details);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ScrollView) object);
    }
}
