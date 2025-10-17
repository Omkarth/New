package edu.adelaide.sse_project.serverside;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<List_requested_Feedback> mExampleList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {

        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mDeleteImage;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.fee);
            mTextView2 = itemView.findViewById(R.id.feed);
            mDeleteImage = itemView.findViewById(R.id.image_delete);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }

                    }
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<List_requested_Feedback> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_requested_feedback, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

        List_requested_Feedback currentItem = mExampleList.get(position);

        holder.mTextView2.setText(mExampleList.get(position).getFeeds());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}


