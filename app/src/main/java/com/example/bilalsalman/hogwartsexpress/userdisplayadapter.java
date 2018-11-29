package com.example.bilalsalman.hogwartsexpress;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class userdisplayadapter extends ArrayAdapter<UserDataForFireBase> {

    private Activity context;
    List<UserDataForFireBase> obj;

    public userdisplayadapter(Activity context, List<UserDataForFireBase> artists) {
        super(context, R.layout.userdisplayadapter, artists);
        this.context = context;
        this.obj = artists;
        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.userdisplayadapter, null, true);

        TextView fName = (TextView) listViewItem.findViewById(R.id.textViewfname);
        TextView lName = (TextView) listViewItem.findViewById(R.id.textViewlname);
        TextView cnic = (TextView) listViewItem.findViewById(R.id.textViewcnic);
        TextView ccno = (TextView) listViewItem.findViewById(R.id.textViewccno);
        TextView email = (TextView) listViewItem.findViewById(R.id.textViewemail);
        TextView id = (TextView) listViewItem.findViewById(R.id.textViewid);
        TextView pno = (TextView) listViewItem.findViewById(R.id.textViewpno);



        UserDataForFireBase artist = obj.get(position);
        fName.setText("First Name: "+artist.getFirstName());
        lName.setText("Last Name: "+artist.getLastNAme());
        cnic.setText("CNIC: "+artist.getCnic());
        ccno.setText("Credit Card No: "+artist.getCreditCardNo());
        email.setText("Email: "+artist.getEmail());
        id.setText("Id: "+artist.getiD());
        pno.setText("PhoneNumber "+artist.getPhoneNumber());

        return listViewItem;
        }
}
