package com.example.nasiru_kun.skeydas;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Door extends Fragment implements View.OnClickListener{

    private TextView mStatusTv;
    private Button mActivateBtn;
    private Button mScanBtn;

    private ProgressDialog mProgressDlg;

    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();

    private BluetoothAdapter mBluetoothAdapter;

    // Insert your server's MAC address
    private static String address = "98:D3:31:20:43:EA";

    //ni yang asal
    public Door() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_door, container, false);

        mStatusTv 			= (TextView) view.findViewById(R.id.btStatus);
        mActivateBtn 		= (Button) view.findViewById(R.id.tv_status);
        mScanBtn 			= (Button) view.findViewById(R.id.btn_scan);

        mBluetoothAdapter	= BluetoothAdapter.getDefaultAdapter();

        mProgressDlg 		= new ProgressDialog(getContext());

        //get the password door EditText
        final EditText pwddoor = (EditText) view.findViewById(R.id.passworddoor);
        //get the show or hide password door Checkbox
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbshowpass);
        //forget password
        TextView text = (TextView) view.findViewById(R.id.forgetpassword);
        text.setOnClickListener(this);

        mProgressDlg.setMessage("Scanning...");
        mProgressDlg.setCancelable(false);
        mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();

                mBluetoothAdapter.cancelDiscovery();
            }
        });

        /** add onClickListener on Checkbox
         * when user clicks on this checkbox, this is the handler
         */
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //checkbox status is changed from uncheck to checked
                if (!isChecked) {
                    //show password of door
                    pwddoor.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //hide password of door
                    pwddoor.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


    if (mBluetoothAdapter == null)
    {
        showUnsupported();
    }
    else
    {

        mScanBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                mBluetoothAdapter.startDiscovery();
            }
        });

        mActivateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mBluetoothAdapter.isEnabled())
                {
                    mBluetoothAdapter.disable();

                    showDisabled();
                }
                else
                {
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                    startActivityForResult(intent, 1000);
                }
            }
        });

        if (mBluetoothAdapter.isEnabled())
        {
            showEnabled();
        }
        else
        {
            showDisabled();
        }
    }

    IntentFilter filter = new IntentFilter();

    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    filter.addAction(BluetoothDevice.ACTION_FOUND);
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

    getContext().registerReceiver(mReceiver, filter);
        return view;
    }
    @Override
    public void onPause()
    {
        if (mBluetoothAdapter != null)
        {
            if (mBluetoothAdapter.isDiscovering())
            {
                mBluetoothAdapter.cancelDiscovery();
            }
        }

        super.onPause();
    }

    @Override
    public void onDestroy()
    {
        getContext().unregisterReceiver(mReceiver);

        super.onDestroy();
    }

    private void showEnabled()
    {
        mStatusTv.setText("Bluetooth is On");
        mStatusTv.setTextColor(Color.BLUE);

        mActivateBtn.setText("Disable");
        mActivateBtn.setEnabled(true);

        mScanBtn.setEnabled(true);
    }

    private void showDisabled()

    {
        mStatusTv.setText("Bluetooth is Off");
        mStatusTv.setTextColor(Color.RED);

        mActivateBtn.setText("Enable");
        mActivateBtn.setEnabled(true);

        mScanBtn.setEnabled(false);
    }

    private void showUnsupported()
    {
        mStatusTv.setText("Bluetooth is unsupported by this device");

        mActivateBtn.setText("Enable");
        mActivateBtn.setEnabled(false);

        mScanBtn.setEnabled(false);
    }

    private void showToast(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action))
            {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                if (state == BluetoothAdapter.STATE_ON)
                {
                    showToast("Enabled");

                    showEnabled();
                }
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action))
            {
                mDeviceList = new ArrayList<BluetoothDevice>();

                mProgressDlg.show();
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                mProgressDlg.dismiss();

                Intent newIntent = new Intent(getContext(), DeviceListActivity.class);

                newIntent.putParcelableArrayListExtra("device.list", mDeviceList);

                startActivity(newIntent);
            }
            else if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                mDeviceList.add(device);

                showToast("Found device " + device.getName());
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgetpassword:
                Intent intent = new Intent(getActivity(), ForgetPassword.class);
                startActivity(intent);
                break;
        }
    }
}