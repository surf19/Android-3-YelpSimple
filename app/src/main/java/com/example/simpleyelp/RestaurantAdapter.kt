package com.example.simpleyelp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class RestaurantAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false)
        )
    }

    // moved to bottom
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant) {
//            var tvName = itemView.findViewById<TextView>(R.id.tvName)
//            tvName.text = restaurant.name

            itemView.findViewById<TextView>(R.id.tvName).text = restaurant.name
            itemView.findViewById<RatingBar>(R.id.ratingBar).rating = restaurant.rating.toFloat()
            itemView.findViewById<TextView>(R.id.tvNumReviews).text = "${restaurant.numReviews}"
            itemView.findViewById<TextView>(R.id.tvAddress).text = restaurant.location.address
            itemView.findViewById<TextView>(R.id.tvCategory).text = restaurant.categories[0].title
            itemView.findViewById<TextView>(R.id.tvDistance).text = restaurant.displayDistance()
            itemView.findViewById<TextView>(R.id.tvPrice).text = restaurant.price
            Glide.with(itemView.context).load(restaurant.imageUrl).apply(RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20) ))
                .into(itemView.findViewById<ImageView>(R.id.imageView))
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount() = restaurants.size
}

