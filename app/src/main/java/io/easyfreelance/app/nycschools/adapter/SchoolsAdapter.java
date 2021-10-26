package io.easyfreelance.app.nycschools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.easyfreelance.app.nycschools.R;
import io.easyfreelance.app.nycschools.model.School;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.SchoolsViewHolder> {

    Context context;
    ArrayList<School> schools;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(School school);
    }

    public SchoolsAdapter(Context context, ArrayList<School> schools, OnItemClickListener listener) {
        this.context = context;
        this.schools = schools;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SchoolsAdapter.SchoolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_item, parent, false);
        return new SchoolsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolsAdapter.SchoolsViewHolder holder, int position) {
        holder.tvName.setText(schools.get(position).schoolName);
        holder.tvDescription.setText(schools.get(position).description);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(schools.get(position)));
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public class SchoolsViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvDescription;

        public SchoolsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.school_name);
            tvDescription = itemView.findViewById(R.id.school_description);
        }
    }
}
