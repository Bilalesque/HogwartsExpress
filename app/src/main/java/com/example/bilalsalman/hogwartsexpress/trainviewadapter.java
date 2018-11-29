package com.example.bilalsalman.hogwartsexpress;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class trainviewadapter extends ArrayAdapter<AddTrain> {

    private Activity context;
    List<AddTrain> obj;

    public trainviewadapter(Activity context, List<AddTrain> trains) {
        super(context, R.layout.trainviewadapter, trains);
        this.context = context;
        this.obj = trains;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.trainviewadapter, null, true);

        TextView trainnum = (TextView) listViewItem.findViewById(R.id.textViewMan);
        TextView manufacturer= (TextView) listViewItem.findViewById(R.id.textVieOp);
        TextView operator = (TextView) listViewItem.findViewById(R.id.textViewtrainNum);
        TextView numOfCar = (TextView) listViewItem.findViewById(R.id.textViewNumCar);
        TextView routeid = (TextView) listViewItem.findViewById(R.id.textViewRid);
        TextView businessCap= (TextView) listViewItem.findViewById(R.id.textViewBcap);
        TextView ecoCap = (TextView) listViewItem.findViewById(R.id.textViewEcap);



        AddTrain train = obj.get(position);
        trainnum.setText("Train Number: "+train.getTainNumber());
        manufacturer.setText("Manufacturer: "+train.getManufacturer());
        operator.setText("Operator: "+train.getOperator());
        numOfCar.setText("Number of Carriages: "+train.getNumberOfCarriages());
        routeid.setText("Route ID: "+train.getRouteID());
        businessCap.setText("Business Capacity: "+train.getBusinessCapacity());
        ecoCap.setText("Economy Capacity: "+train.getEconomyCapacity());
        return listViewItem;
    }
}
