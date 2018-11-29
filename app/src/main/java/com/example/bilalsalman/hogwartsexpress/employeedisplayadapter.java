package com.example.bilalsalman.hogwartsexpress;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class employeedisplayadapter extends ArrayAdapter<EmployeeDataForFireBase>{

    private Activity context;
    List<EmployeeDataForFireBase> obj;

    public employeedisplayadapter(Activity context, List<EmployeeDataForFireBase> artists) {
        super(context, R.layout.employeedisplayadapter, artists);
        this.context = context;
        this.obj = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.employeedisplayadapter, null, true);

        TextView fName = (TextView) listViewItem.findViewById(R.id.textViewEMPfname);
        TextView lName = (TextView) listViewItem.findViewById(R.id.textViewEMPlname);
        TextView cnic = (TextView) listViewItem.findViewById(R.id.textViewEMPcnic);
        TextView email = (TextView) listViewItem.findViewById(R.id.textViewEMPemail);
        TextView id = (TextView) listViewItem.findViewById(R.id.textViewEMPid);
        TextView pno = (TextView) listViewItem.findViewById(R.id.textViewEMPpno);
        TextView sal = (TextView) listViewItem.findViewById(R.id.textViewEMPsal);
        TextView dep = (TextView) listViewItem.findViewById(R.id.textViewEMPdep);


        EmployeeDataForFireBase artist = obj.get(position);
        fName.setText("First Name: "+artist.getFirstName());
        lName.setText("Last Name: "+artist.getLastNAme());
        cnic.setText("CNIC: "+artist.getCnic());
        sal.setText("Credit Card No: "+artist.getSalary());
        email.setText("Email: "+artist.getEmail());
        id.setText("Id: "+artist.getiD());
        pno.setText("PhoneNumber "+artist.getPhoneNumber());
        dep.setText("Department "+artist.getDepartment());
        sal.setText("Salary "+artist.getSalary());

        return listViewItem;
    }

}
