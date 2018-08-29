package com.rocket.teamazbow.customsearchview

import android.content.ActivityNotFoundException
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.graphics.drawable.ColorDrawable
import android.support.annotation.RawRes
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_search_dialog.*


class SearchDialogFragment : DialogFragment() {



    companion object {

        const val BACK_ICON_COLOR = "back_icon_color"
        const val SEARCH_ICON_COLOR = "search_icon_color"
        const val BOTTOM_DIVIDER_COLOR = "bottom_divider_color"
        const val SEARCH_TEXT_COLOR = "search_text_color"
        const val SEARCH_HINT = "search_hint"
        const val TARGET_ACTIVITY = "target_activity"

        fun newInstance(bundle: Bundle): SearchDialogFragment{

            val fragment = SearchDialogFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    var dimAmount: Float = 0f
    lateinit var v: View

    private val iconColorDef = Color.BLACK


    override fun onStart() {
       super.onStart()

       //Control background dim
       val window = dialog.window
       val windowParams = window!!.attributes
       windowParams.dimAmount = dimAmount
       windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
       window.attributes = windowParams
   }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = dialog.window
        //val attributes = window!!.attributes
        //must setBackgroundDrawable(TRANSPARENT) in onActivityCreated()
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_search_dialog, container, false)

        val btnBack = v.findViewById<ImageView>(R.id.btnBack)
        val btnSearch = v.findViewById<ImageView>(R.id.btnSearch)
        val bottomDivider = v.findViewById<View>(R.id.bottomDivider)
        val editTextSearch = v.findViewById<EditText>(R.id.editText)

        try {
            btnBack.setColorFilter(arguments?.getInt(BACK_ICON_COLOR)!!)
        } catch (e : NullPointerException){
            btnBack.setColorFilter(iconColorDef)
        }

        try {
            btnSearch.setColorFilter(arguments?.getInt(SEARCH_ICON_COLOR)!!)
        } catch (e : NullPointerException){
            btnSearch.setColorFilter(iconColorDef)
        }

        try {
            bottomDivider.setBackgroundColor(arguments?.getInt(BOTTOM_DIVIDER_COLOR)!!)
        } catch (e : NullPointerException){
            bottomDivider.setBackgroundColor(iconColorDef)
        }

        try {
            val textColor = arguments?.getInt(SEARCH_TEXT_COLOR)!!
            if (textColor != 0) {
                editTextSearch.setTextColor(textColor)
            }
        } catch (e : NullPointerException){
            editTextSearch.setTextColor(iconColorDef)
        }

        try {
            editTextSearch.hint = arguments?.getString(SEARCH_HINT)
        } catch (e : NullPointerException){
            e.printStackTrace()
            editTextSearch.hint = "Search"
        }

        btnBack.setOnClickListener {
            this.dismiss()
        }

        editTextSearch.setOnEditorActionListener { v, action, event ->
            if (action == EditorInfo.IME_ACTION_SEARCH){
                performSearch()
            }
            true
        }

        val manager = SearchDialogManager(context).searchHistory



        return v
    }

    private fun performSearch(){

        try {
            val c = Class.forName(arguments?.getString(TARGET_ACTIVITY))

            val intent = Intent(context, c)
            intent.action = Intent.ACTION_SEARCH
            intent.putExtra(SearchDialogManager.SEARCH , editText.text.toString())
            context?.startActivity(intent)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }





}
