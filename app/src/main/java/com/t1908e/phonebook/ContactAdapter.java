package com.t1908e.phonebook;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private static final String TAG = "Contact adapter";
    private Activity activity;
    private List<Contact> contacts;
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void registerChildItemClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void unRegisterChildItemClick() {
        this.onItemClickListener = null;
    }

    public ContactAdapter(Activity activity, List<Contact> contacts) {
        this.activity = activity;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parentView) {
        if (view == null) {
            //init item view == contact_item when first item call this method
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_contact, parentView, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtName = (TextView) view.findViewById(R.id.contactName);
            viewHolder.txtPhone = (TextView) view.findViewById(R.id.contactPhone);
            viewHolder.imageAvatar = (ImageView) view.findViewById(R.id.contactAvatar);
            viewHolder.imagePhone = view.findViewById(R.id.btnPhone);
            view.setTag(viewHolder);
        }
        //set data for item view when it is initialized
        ViewHolder holder = (ViewHolder) view.getTag();
        Contact contact = contacts.get(position);
        holder.txtName.setText(contact.getName());
        holder.txtPhone.setText(contact.getPhone());
        holder.imageAvatar.setImageResource(contact.getAvatar());
        holder.imagePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall(position);
            }
        });
        return view;
    }

    private void onCall(int position) {
        Contact contact = contacts.get(position);
        Log.d(TAG, "onCall: " + contact.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contact.getPhone()));
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        activity.startActivity(intent);
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtPhone;
        ImageView imageAvatar;
        ImageView imagePhone;

    }

    public interface OnItemClickListener {
        void phoneBtnClick(int position);
    }
}
