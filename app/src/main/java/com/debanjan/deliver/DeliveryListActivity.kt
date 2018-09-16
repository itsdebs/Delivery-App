package com.debanjan.deliver

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.debanjan.deliver.databinding.DeliveryItemBinding
import kotlinx.android.synthetic.main.activity_delivery_list.*
import android.view.animation.AnimationUtils.loadLayoutAnimation
import android.view.animation.LayoutAnimationController
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.debanjan.deliver.utils.dpToPx


class DeliveryListActivity : Activity() {

    private var deliveryAdapter : DeliveryAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Fade(Fade.IN)
            // set an exit transition
            exitTransition = Explode()
        }
        setContentView(R.layout.activity_delivery_list)
        deliver_list_recycler.layoutManager = LinearLayoutManager(this)
        deliveryAdapter = DeliveryAdapter(this)
        deliver_list_recycler.adapter = deliveryAdapter
        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fallback)

        deliver_list_recycler.layoutAnimation = controller
        deliveryAdapter?.submitList(generateMockDeliveries(28))
        deliver_list_recycler.scheduleLayoutAnimation()

    }


//    private fun runLayoutAnimation(recyclerView: RecyclerView) {
//        val context = recyclerView.context
//
//        recyclerView.adapter!!.notifyDataSetChanged()
//    }

    class DeliveryAdapter(val context: Context):
            ListAdapter<Delivery, DeliveryAdapter.DeliveryViewHolder>(DIFF_UTIL_CALLBACK){
        val options = RequestOptions().centerCrop().transform(RoundedCorners(dpToPx(15.0f, context)))

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
            return DeliveryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context?:context),
                    R.layout.delivery_item, parent, false))
        }

        override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.item = item
            Glide.with(context)
                    .load(item.imageUrl)
                    .apply(options)
                    .into(holder.binding.image)


        }

        companion object {
            private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Delivery>(){
                override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
                    return oldItem.equals(newItem)
                }

                override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
                    return oldItem.equals(newItem) && oldItem.location.address.equals(newItem.location.address)
                    && oldItem.description.equals(newItem.description)
                    && oldItem.imageUrl.equals(newItem.imageUrl)

                }

            }
        }
        class DeliveryViewHolder(val binding: DeliveryItemBinding):RecyclerView.ViewHolder(binding.root){

        }
    }
}
