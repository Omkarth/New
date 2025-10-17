package edu.adelaide.sse_project.serverside;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<List_Request_items2>list_request_items;
    private Context context;

    public MyAdapter2(List<List_Request_items2> list_request_items, Context context) {
        this.list_request_items = list_request_items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.list_request_items2,parent,false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        List_Request_items2 list_request_item2=list_request_items.get(position);
        holder.text.setText(list_request_item2.getPlumber());
        holder.textView.setText(list_request_item2.getGender());
        holder.textView1.setText(list_request_item2.getHours());
        holder.textView2.setText(list_request_item2.getTime());
        holder.textView3.setText(list_request_item2.getDate());
        holder.textView4.setText(list_request_item2.getCity());
        holder.textView5.setText(list_request_item2.getAddress());

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull List<Object> payloads) {
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7039772608"));
                context.startActivity(intent);
            }
        });
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
        RelativeLayout parentlayout;


        public ViewHolder(View itemView) {

            super(itemView);


            text=(TextView)itemView.findViewById(R.id.tvplumber);
            textView=(TextView)itemView.findViewById(R.id.tvgender);
            textView1=(TextView)itemView.findViewById(R.id.tvhours);
            textView2=(TextView)itemView.findViewById(R.id.tvTime);
            textView3=(TextView)itemView.findViewById(R.id.tvdate);
            textView4=(TextView)itemView.findViewById(R.id.tvcity);
            textView5=(TextView)itemView.findViewById(R.id.tvaddress);
            parentlayout=itemView.findViewById(R.id.parent_layout);

        }


    }
}
