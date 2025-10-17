package edu.adelaide.sse_project.serverside;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;

public class orderviewholder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnCreateContextMenuListener {

   // private ItemClicklistner itemclicklistner;

    public orderviewholder(View itemview) {
        super(itemview);
        itemview.setOnClickListener(this);
        itemview.setOnCreateContextMenuListener(this);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Select the action");
        menu.add(0,0,getAdapterPosition(),"Update");
        menu.add(0,1,getAdapterPosition(),"Delete");


    }
}
