package edu.adelaide.sse_project.serverside;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<List_Request_item>list_request_items;
    private Context context;
    public MyAdapter(List<List_Request_item> list_request_items, Context context) {
        this.list_request_items = list_request_items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.list_request_items,parent,false);
       return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        List_Request_item list_request_item=list_request_items.get(position);
        holder.text.setText(list_request_item.getPermanent());
        holder.textView.setText(list_request_item.getGender());
        holder.textView1.setText(list_request_item.getHours());
        holder.textView2.setText(list_request_item.getTime());
        holder.textView3.setText(list_request_item.getDate());
        holder.textView4.setText(list_request_item.getCity());
        holder.textView5.setText(list_request_item.getAddress());
    }

    @Override
    public int getItemCount() {
        return list_request_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView text;
        public TextView textView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;


        public ViewHolder(View itemView) {

            super(itemView);


            text=(TextView)itemView.findViewById(R.id.maid);
            textView=(TextView)itemView.findViewById(R.id.tvgender);
            textView1=(TextView)itemView.findViewById(R.id.tvhours);
            textView2=(TextView)itemView.findViewById(R.id.tvTime);
            textView3=(TextView)itemView.findViewById(R.id.tvdate);
            textView4=(TextView)itemView.findViewById(R.id.tvcity);
            textView5=(TextView)itemView.findViewById(R.id.tvaddress);



        }


    }
}
