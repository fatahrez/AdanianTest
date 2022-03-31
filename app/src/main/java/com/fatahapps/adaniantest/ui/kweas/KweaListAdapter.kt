package com.fatahapps.adaniantest.ui.kweas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import coil.load
import com.fatahapps.adaniantest.databinding.KweaGridItemBinding
import com.fatahapps.domain.entities.KweaModels.KweaItemEntity
import com.fatahapps.presentation.model.kweaModels.KweaItem

class KweaListAdapter(
    private val context: Context,
    private val kweaList: List<KweaItemEntity>
) : BaseAdapter() {

    private var _binding: KweaGridItemBinding? = null
    private val binding get() = _binding!!
//    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return kweaList.size
    }

    override fun getItem(position: Int): Any {
        return kweaList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (parent != null) {
            _binding = KweaGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        convertView = binding.root

        binding.kweaItemImageView.load(kweaList[position].imageEntities?.get(0)?.image)
        binding.gridViewTextView.text = kweaList[position].itemName
        binding.gridViewPriceTextView.text = kweaList[position].itemCurrency
            .plus(" ${kweaList[position].itemPrice}")

        return convertView
    }
}