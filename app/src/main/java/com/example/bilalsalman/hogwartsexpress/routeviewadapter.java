package com.example.bilalsalman.hogwartsexpress;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class routeviewadapter extends ArrayAdapter<RouteObjForFireBase> {

    private Activity context;
    List<RouteObjForFireBase> obj;

    public routeviewadapter(Activity context, List<RouteObjForFireBase> routes) {
        super(context, R.layout.routeviewadapter, routes);
        this.context = context;
        this.obj = routes;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.routeviewadapter, null, true);

        TextView id = (TextView) listViewItem.findViewById(R.id.textViewrouteNum);
        TextView s= (TextView) listViewItem.findViewById(R.id.textViewSource);
        TextView s1 = (TextView) listViewItem.findViewById(R.id.textViewS1);
        TextView s2 = (TextView) listViewItem.findViewById(R.id.textViewS2);
        TextView s3 = (TextView) listViewItem.findViewById(R.id.textViewS3);
        TextView s4= (TextView) listViewItem.findViewById(R.id.textViewS4);
        TextView s5 = (TextView) listViewItem.findViewById(R.id.textViewS5);
        TextView tid = (TextView) listViewItem.findViewById(R.id.textViewTID);
        TextView des = (TextView) listViewItem.findViewById(R.id.textViewdes);


        RouteObjForFireBase route = obj.get(position);
        id.setText("Route ID: "+route.getId());
        s.setText("Start: "+route.getStart());
        s1.setText("Stop1: "+route.getStop1());
        s2.setText("Stop2: "+route.getStop2());
        s3.setText("Stop3: "+route.getStop3());
        s4.setText("Stop4: "+route.getStop4());
        s5.setText("Stop5: "+route.getStop5());
        tid.setText("Track ID: "+route.getTrackId());
        des.setText("Destination: "+route.getDest());
        return listViewItem;
    }
}
