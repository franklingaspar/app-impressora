package br.gov.sp.sgf.appimpressora.adapter;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.List;

import br.gov.sp.sgf.appimpressora.R;

public class BluetoothListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<BluetoothDevice> mListPrinter;
    private LayoutInflater mInflater;

    public BluetoothListViewAdapter(Context context, List<BluetoothDevice> listPrinter) {
        this.mContext = context;
        this.mListPrinter = listPrinter;
    }

    @Override
    public int getCount() {
        return mListPrinter.size();
    }

    @Override
    public Object getItem(int i) {
        return mListPrinter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            mInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_bluetooth_devices, null);
            TextView name = convertView.findViewById(R.id.tv_bluetooth_device_name);
            TextView address = convertView.findViewById(R.id.tv_bluetooth_address);
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mContext, "Permissão de conexão Bluetooth não concedida", Toast.LENGTH_SHORT).show();
                return null;
            }
            name.setText(mListPrinter.get(i).getName());
            address.setText(mListPrinter.get(i).getAddress());
        }
        return convertView;
    }
}
