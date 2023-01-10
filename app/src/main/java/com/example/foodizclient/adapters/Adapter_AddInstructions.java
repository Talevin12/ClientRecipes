package com.example.foodizclient.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodizclient.R;

import java.util.ArrayList;

public class Adapter_AddInstructions extends RecyclerView.Adapter<Adapter_AddInstructions.AddInstructionViewHolder> {
    private Context context;
    private ArrayList<String> instructions;

    public Adapter_AddInstructions(Context context, ArrayList<String> instructions) {
        this.context = context;
        this.instructions = instructions;
    }

    @NonNull
    @Override
    public AddInstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_instruction, parent, false);
        Adapter_AddInstructions.AddInstructionViewHolder addInstructionViewHolder = new Adapter_AddInstructions.AddInstructionViewHolder(view);
        return addInstructionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddInstructionViewHolder holder, int position) {
        String instruction = instructions.get(position);

        holder.instruction_text.setText(instruction);
        holder.instruction_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                holder.instruction_submit.setVisibility(View.VISIBLE);
                holder.instruction_delete.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        if(position == getItemCount()-1 || getItemCount() == 1) {
            holder.instruction_submit.setVisibility(View.VISIBLE);
            holder.instruction_delete.setVisibility(View.INVISIBLE);
        } else {
            holder.instruction_submit.setVisibility(View.INVISIBLE);
            holder.instruction_delete.setVisibility(View.VISIBLE);
        }

        holder.instruction_submit.setOnClickListener(v -> {
            if(!holder.instruction_text.getText().toString().equals("")) {
                setInstruction(holder.instruction_text.getText().toString(), position);

                if(position == getItemCount()-1) {
                    addNewInstruction();
                }

                holder.instruction_submit.setVisibility(View.INVISIBLE);
                holder.instruction_delete.setVisibility(View.VISIBLE);
            }
        });

        holder.instruction_delete.setOnClickListener(v -> removeInstruction(position));
    }

    @Override
    public int getItemCount() {
        return instructions == null ? 0 : instructions.size();
    }

    public void setInstruction(String text, int position) {
        instructions.set(position, text);

        notifyItemChanged(position);
    }

    public void addNewInstruction() {
        instructions.add("");

        notifyDataSetChanged();
    }

    private void removeInstruction(int position) {
        instructions.remove(position);
        notifyDataSetChanged();
    }

    public ArrayList<String> getInstructions() {
        instructions.remove(getItemCount()-1);

        for(String ins : instructions) {
            ins = "-\t"+ ins;
        }

        return instructions;
    }

    class AddInstructionViewHolder extends RecyclerView.ViewHolder {

        private EditText instruction_text;
        private AppCompatImageButton instruction_submit;
        private AppCompatImageButton instruction_delete;

        public AddInstructionViewHolder(@NonNull View itemView) {
            super(itemView);

            instruction_text = itemView.findViewById(R.id.instruction_text);
            instruction_submit = itemView.findViewById(R.id.instruction_submit);
            instruction_delete = itemView.findViewById(R.id.instruction_delete);
        }
    }
}
