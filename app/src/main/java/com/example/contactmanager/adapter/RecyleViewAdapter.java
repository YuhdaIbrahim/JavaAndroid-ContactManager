package com.example.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contactmanager.DetailsActivity;
import com.example.contactmanager.MainActivity;
import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RecyleViewAdapter extends RecyclerView.Adapter <RecyleViewAdapter.ViewHolder>{

  private Context context;
  private List<Contact> contactList;

  public RecyleViewAdapter(Context context, List<Contact> contactList) {
    this.context = context;
    this.contactList = contactList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_cell,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Contact contact = contactList.get(position);
    holder.contactName.setText(contact.getName());
    holder.phoneNumber.setText(contact.getPhoneNumber());

  }

  @Override
  public int getItemCount() {
    return contactList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView contactName;
    public TextView phoneNumber;
    public ImageView iconButton;

    public ViewHolder(View itemView) {
      super(itemView);


      contactName = itemView.findViewById(R.id.name);
      phoneNumber = itemView.findViewById(R.id.phoneNumber);
      iconButton = itemView.findViewById(R.id.icon_buton);

      iconButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int position = getAdapterPosition();
      Contact contact = contactList.get(position);

      Intent intent = new Intent(context, DetailsActivity.class);
      intent.putExtra("name",contact.getName());
      intent.putExtra("number", contact.getPhoneNumber());

      context.startActivity(intent);

//      Log.d("Click", "onClick: " + contact.getName());

    }
  }
}
