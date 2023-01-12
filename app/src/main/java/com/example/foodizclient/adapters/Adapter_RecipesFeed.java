package com.example.foodizclient.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.foodizclient.R;
import com.example.foodizclient.RecipeData;
import com.example.foodizclient.boundaries.Recipe;
import com.example.foodizclient.boundaries.UserBoundary;
import com.example.foodizclient.callbacks.CallBack_OpenPageProtocol;

import java.time.ZoneId;
import java.util.ArrayList;

public class Adapter_RecipesFeed extends RecyclerView.Adapter<Adapter_RecipesFeed.RecipePostViewHolder> {
    private Context context;
    private ArrayList<Recipe> recipes;
    private ArrayList<Boolean> recipesIsLike;
    private UserBoundary user;

    RecipeData recipeData;

    CallBack_OpenPageProtocol callBack_openCommentsPageProtocol;
    CallBack_OpenPageProtocol callBack_openInfoPageProtocol;

    public void setCallBack_openCommentsPageProtocol(CallBack_OpenPageProtocol callBack_openCommentsPageProtocol) {
        this.callBack_openCommentsPageProtocol = callBack_openCommentsPageProtocol;
    }

    public void setCallBack_openInfoPageProtocol(CallBack_OpenPageProtocol callBack_openInfoPageProtocol) {
        this.callBack_openInfoPageProtocol = callBack_openInfoPageProtocol;
    }

    public Adapter_RecipesFeed(Context context, UserBoundary user, ArrayList<Recipe> recipes, ArrayList<Boolean> recipesIsLike, RecipeData recipeData) {
        this.context = context;
        this.user = user;
        this.recipes = recipes;
        this.recipesIsLike = recipesIsLike;

        this.recipeData = recipeData;
    }

    @NonNull
    @Override
    public RecipePostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipe, parent, false);
        RecipePostViewHolder recipePostViewHolder = new RecipePostViewHolder(view);

        return recipePostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipePostViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        holder.recipe_LBL_name.setText(recipe.getName());
        holder.recipe_LBL_username.setText(recipe.getUsername());
        holder.recipe_LBL_date.setText(recipe
                                        .getUploadDate()
                                        .toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                                        .toString());
        holder.recipe_LBL_description.setText(recipe.getDescription());

        Glide.with(context)
                .load(Uri.parse(recipe.getImage()))
                .centerCrop()
                .into(holder.recipe_IMG_image);

        holder.recipe_IMG_image.setOnClickListener(v -> callBack_openInfoPageProtocol.openPage(recipe, null));

        holder.recipe_ANIM_likeHeart.pauseAnimation();
        if(recipesIsLike.get(position)) {
            holder.recipe_ANIM_likeHeart.playAnimation();
        } else {
            holder.recipe_ANIM_likeHeart.setProgress(0);
        }

        holder.recipe_ANIM_likeHeart.setOnClickListener(v -> {
            if(recipesIsLike.get(position)) {
                ((LottieAnimationView)v).setProgress(0);

                recipes.get(position).setLikes(recipe.getLikes()-1);
                recipesIsLike.set(position, false);

                notifyDataSetChanged();

                recipeData.unlikeRecipe(recipe);
            } else {
                ((LottieAnimationView)v).playAnimation();

                recipes.get(position).setLikes(recipe.getLikes()+1);
                recipesIsLike.set(position, true);

                notifyDataSetChanged();

                recipeData.likeRecipe(recipe, user.getUserId());
            }
        });

        holder.recipe_LBL_likeNum.setText(""+ recipe.getLikes());

        holder.recipe_LBL_Comments.setOnClickListener(v -> callBack_openCommentsPageProtocol.openPage(recipe, user));
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    public class RecipePostViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView  recipe_IMG_image;
        private TextView            recipe_LBL_name;
        private TextView            recipe_LBL_username;
        private TextView            recipe_LBL_date;
        private TextView            recipe_LBL_description;
        private LottieAnimationView recipe_ANIM_likeHeart;
        private TextView            recipe_LBL_likeNum;
        private TextView            recipe_LBL_Comments;

        public RecipePostViewHolder(@NonNull View itemView) {
            super(itemView);

            recipe_IMG_image        = itemView.findViewById(R.id.recipe_IMG_image);

            recipe_LBL_name         = itemView.findViewById(R.id.recipe_LBL_name);

            recipe_LBL_username         = itemView.findViewById(R.id.recipe_LBL_username);

            recipe_LBL_date  = itemView.findViewById(R.id.recipe_LBL_date);

            recipe_LBL_description  = itemView.findViewById(R.id.recipe_LBL_description);

            recipe_ANIM_likeHeart   = itemView.findViewById(R.id.recipe_ANIM_likeHeart);

            recipe_LBL_likeNum      = itemView.findViewById(R.id.recipe_LBL_likeNum);

            recipe_LBL_Comments     = itemView.findViewById(R.id.recipe_LBL_Comments);
        }
    }
}
