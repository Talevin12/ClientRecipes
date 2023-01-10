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
import com.example.foodizclient.boundaries.Ingredient;

import java.util.ArrayList;

public class Adapter_AddIngredients extends RecyclerView.Adapter<Adapter_AddIngredients.AddIngredientViewHolder>{

    private Context context;
    private ArrayList<Ingredient> ingredients;

    public Adapter_AddIngredients(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public AddIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ingredient, parent, false);
        Adapter_AddIngredients.AddIngredientViewHolder addIngredientViewHolder = new Adapter_AddIngredients.AddIngredientViewHolder(view);
        return addIngredientViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddIngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);

        holder.ingredient_name.setText(ingredient.getName());
        holder.ingredient_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                holder.ingredient_submit.setVisibility(View.VISIBLE);
                holder.ingredient_delete.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        holder.ingredient_unit.setText(ingredient.getUnit());
        holder.ingredient_unit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                holder.ingredient_submit.setVisibility(View.VISIBLE);
                holder.ingredient_delete.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        holder.ingredient_amount.setText(""+ ingredient.getAmount());
        holder.ingredient_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                holder.ingredient_submit.setVisibility(View.VISIBLE);
                holder.ingredient_delete.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        if(position == getItemCount()-1 || getItemCount() == 1) {
            holder.ingredient_submit.setVisibility(View.VISIBLE);
            holder.ingredient_delete.setVisibility(View.INVISIBLE);
        } else {
            holder.ingredient_submit.setVisibility(View.INVISIBLE);
            holder.ingredient_delete.setVisibility(View.VISIBLE);
        }

        holder.ingredient_submit.setOnClickListener(v -> {
            if(!holder.ingredient_name.getText().toString().equals("") &&
                    !holder.ingredient_unit.getText().toString().equals("") &&
                        Float.parseFloat(holder.ingredient_amount.getText().toString()) != 0) {

                setIngredient(holder.ingredient_name.getText().toString(),
                                holder.ingredient_unit.getText().toString(),
                                Float.valueOf(holder.ingredient_amount.getText().toString()), position);

                if(position == getItemCount()-1) {
                    addNewIngredient();
                }

                holder.ingredient_submit.setVisibility(View.INVISIBLE);
                holder.ingredient_delete.setVisibility(View.VISIBLE);
            }
        });

        holder.ingredient_delete.setOnClickListener(v -> removeIngredient(position));
    }

    @Override
    public int getItemCount() {
        return ingredients == null ? 0 : ingredients.size();
    }

    public void setIngredient(String name, String unit, Float amount, int position) {
        ingredients.get(position)
                .setName(name)
                .setUnit(unit)
                .setAmount(amount);

        notifyItemChanged(position);
    }

    public void addNewIngredient() {
        ingredients.add(new Ingredient());

        notifyDataSetChanged();
    }

    private void removeIngredient(int position) {
        ingredients.remove(position);
        notifyDataSetChanged();
    }

    public ArrayList<Ingredient> getIngredients() {
        ingredients.remove(getItemCount()-1);
        return ingredients;
    }

    class AddIngredientViewHolder extends RecyclerView.ViewHolder {

        private EditText ingredient_name;
        private EditText ingredient_unit;
        private EditText ingredient_amount;
        private AppCompatImageButton ingredient_submit;
        private AppCompatImageButton ingredient_delete;

        public AddIngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_unit = itemView.findViewById(R.id.ingredient_unit);
            ingredient_amount = itemView.findViewById(R.id.ingredient_amount);
            ingredient_submit = itemView.findViewById(R.id.ingredient_submit);
            ingredient_delete = itemView.findViewById(R.id.ingredient_delete);
        }
    }
}
