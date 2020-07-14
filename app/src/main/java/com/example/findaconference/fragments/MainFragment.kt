package com.example.findaconference.fragments

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.findaconference.R
import com.example.findaconference.adapters.*
import com.example.findaconference.databinding.MainFragmentBinding
import com.example.findaconference.models.ConferenceItem
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val conferenceListItem: MutableList<ConferenceItem> = ArrayList()

    private val  bankingClickListener by lazy {
        object : bankingItemClickListener {
            override fun invoke(it: ConferenceItem) {
                val  arg = MainFragmentDirections.actionMainFragmentToBankingDetailsFragment(it)
                findNavController().navigate(arg)
            }

        }
    }
    private lateinit var bankingRecycler: RecyclerView
    private val bankingAdapter = BankingAdapter(conferenceListItem, bankingClickListener)

    private val litigationClickListener by lazy {
        object : litigationItemClickListener {
            override fun invoke(it: ConferenceItem) {
                val arg = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                findNavController().navigate(arg)
            }

        }
    }
    private lateinit var litigationRecycler: RecyclerView
    private val litigationAdapter = LitigationAdapter(conferenceListItem, litigationClickListener)

    private val corporateClickListener by lazy {
        object : corporateItemClickListener {
            override fun invoke(it: ConferenceItem) {
                val arg = MainFragmentDirections.actionMainFragmentToCorporateDetailsFragment(it)
                findNavController().navigate(arg)
            }

        }
    }
    private lateinit var corporateRecycler: RecyclerView
    private val corporateAdapter = CorporateAdapter(conferenceListItem, corporateClickListener)

    private val familyClickListener by lazy {
        object : familyItemClickListener {
            override fun invoke(it: ConferenceItem) {
                val arg = MainFragmentDirections.actionMainFragmentToFamilyDetailsFragment(it)
                findNavController().navigate(arg)
            }

        }
    }
    private lateinit var familyRecycler: RecyclerView
    private val familyAdapter = FamilyAdapter(conferenceListItem, familyClickListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)

        binding.litigationBlock.visibility = View.VISIBLE
        onClick()

        // inflate banking list
        bankingRecycler = binding.bankingList.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridItemDeco(10, 2))
            adapter = bankingAdapter
        }
        addBankingItemsFromJson()

        // inflate litigation list
        litigationRecycler = binding.litigationList.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridItemDeco(10, 2))
            adapter = litigationAdapter
        }
        addLitigationItemsFromJson()

        // inflate corporate list
        corporateRecycler = binding.corporateList.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridItemDeco(10, 2))
            adapter = corporateAdapter
        }
        addCorporateItemsFromJson()

        // inflate family list
        familyRecycler = binding.familyList.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridItemDeco(10, 2))
            adapter = familyAdapter
        }
        addFamilyItemsFromJson()

        binding.toFavourite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favouritesFragment)
        }

        return binding.root
    }


    private fun onClick() {
        binding.litigationCardView.setOnClickListener {
            litigation_block.visibility = View.VISIBLE
            banking_block.visibility = View.GONE
            corporate_block.visibility = View.GONE
            family_block.visibility = View.GONE
        }

        binding.bankingCardView.setOnClickListener {
            litigation_block.visibility = View.GONE
            banking_block.visibility = View.VISIBLE
            corporate_block.visibility = View.GONE
            family_block.visibility = View.GONE
        }

        binding.corporateCardView.setOnClickListener {
            litigation_block.visibility = View.GONE
            banking_block.visibility = View.GONE
            corporate_block.visibility = View.VISIBLE
            family_block.visibility = View.GONE
        }

        binding.familyCardView.setOnClickListener {
            litigation_block.visibility = View.GONE
            banking_block.visibility = View.GONE
            corporate_block.visibility = View.GONE
            family_block.visibility = View.VISIBLE
        }
    }
//
//    favoriteDatabase= Room.databaseBuilder(getApplicationContext(),FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();


    @Throws(IOException::class)
    private fun readBankingDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String? = null
            inputStream = context?.assets?.open("json/Banking.json")
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    private fun addBankingItemsFromJson() {
        try {
            val jsonDataString = readBankingDataFromFile()
            val bankingItemsJsonArray = JSONArray(jsonDataString)
            for (i in 0 until bankingItemsJsonArray.length()) {
                val bankingItem = bankingItemsJsonArray.getJSONObject(i)
                val confCode = bankingItem.getString("conference_code")
                val date = bankingItem.getString("date")
                val desc = bankingItem.getString("description")
                val img = bankingItem.getString("image")
                val isFav = bankingItem.getBoolean("isFavourite")
                val name = bankingItem.getString("name")
                val reg = bankingItem.getString("registration")
                val venue = bankingItem.getString("venue")
                val banking = ConferenceItem(confCode, date, desc, img, isFav, name, reg, venue)
                conferenceListItem.add(banking)
            }
        } catch (exception: IOException) {
        Log.d("MainFragment", "Unable to parse JSON file.", exception)
    } catch (exception: JSONException) {
        Log.d("MainFragment", "Unable to parse JSON file.", exception)
    }
    }

    @Throws(IOException::class)
    private fun readLitigationDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String? = null
            inputStream = context?.assets?.open("json/Litigation.json")
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    private fun addLitigationItemsFromJson() {
        try {
            val jsonDataString = readLitigationDataFromFile()
            val litigationItemsJsonArray = JSONArray(jsonDataString)
            for (i in 0 until litigationItemsJsonArray.length()) {
                val litigationItem = litigationItemsJsonArray.getJSONObject(i)
                val confCode = litigationItem.getString("conference_code")
                val date = litigationItem.getString("date")
                val desc = litigationItem.getString("description")
                val img = litigationItem.getString("image")
                val isFav = litigationItem.getBoolean("isFavourite")
                val name = litigationItem.getString("name")
                val reg = litigationItem.getString("registration")
                val venue = litigationItem.getString("venue")
                val litigation = ConferenceItem(confCode, date, desc, img, isFav, name, reg, venue)
                conferenceListItem.add(litigation)
            }
        } catch (exception: IOException) {
            Log.d("MainFragment", "Unable to parse JSON file.", exception)
        } catch (exception: JSONException) {
            Log.d("MainFragment", "Unable to parse JSON file.", exception)
        }
    }

    @Throws(IOException::class)
    private fun readCorporateDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String? = null
            inputStream = context?.assets?.open("json/Corporate.json")
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    private fun addCorporateItemsFromJson() {
        try {
            val jsonDataString = readCorporateDataFromFile()
            val corporateItemsJsonArray = JSONArray(jsonDataString)
            for (i in 0 until corporateItemsJsonArray.length()) {
                val corporateItem = corporateItemsJsonArray.getJSONObject(i)
                val confCode = corporateItem.getString("conference_code")
                val date = corporateItem.getString("date")
                val desc = corporateItem.getString("description")
                val img = corporateItem.getString("image")
                val isFav = corporateItem.getBoolean("isFavourite")
                val name = corporateItem.getString("name")
                val reg = corporateItem.getString("registration")
                val venue = corporateItem.getString("venue")
                val corporate = ConferenceItem(confCode, date, desc, img, isFav, name, reg, venue)
                conferenceListItem.add(corporate)
            }
        } catch (exception: IOException) {
            Log.d("MainFragment", "Unable to parse JSON file.", exception)
        } catch (exception: JSONException) {
            Log.d("MainFragment", "Unable to parse JSON file.", exception)
        }
    }

    @Throws(IOException::class)
    private fun readFamilyDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonDataString: String? = null
            inputStream = context?.assets?.open("json/Family.json")
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonDataString = it } != null) {
                builder.append(jsonDataString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    private fun addFamilyItemsFromJson() {
        try {
            val jsonDataString = readFamilyDataFromFile()
            val familyItemsJsonArray = JSONArray(jsonDataString)
            for (i in 0 until familyItemsJsonArray.length()) {
                val familyItem = familyItemsJsonArray.getJSONObject(i)
                val confCode = familyItem.getString("conference_code")
                val date = familyItem.getString("date")
                val desc = familyItem.getString("description")
                val img = familyItem.getString("image")
                val isFav = familyItem.getBoolean("isFavourite")
                val name = familyItem.getString("name")
                val reg = familyItem.getString("registration")
                val venue = familyItem.getString("venue")
                val family = ConferenceItem(confCode, date, desc, img, isFav, name, reg, venue)
                conferenceListItem.add(family)
            }
        } catch (exception: IOException) {
            Log.d("MainFragment", "Unable to parse JSON file.", exception)
        } catch (exception: JSONException) {
            Log.d("MainFragment", "Unable to parse JSON file.", exception)
        }
    }

    class GridItemDeco(private var gridSpacing: Int, private var gridSize: Int): RecyclerView.ItemDecoration() {

        private var leftSpacing = false

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val frameWidth = ((parent.width - gridSpacing.toFloat() * (gridSize - 1)) / gridSize).toInt()
            val padding = parent.width / gridSize - frameWidth
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
            if (itemPosition < gridSize) {
                outRect.top = 0
            } else {
                outRect.top = gridSpacing
            }
            if (itemPosition % gridSize == 0) {
                outRect.left = 0
                outRect.right = padding
                leftSpacing = true
            } else if ((itemPosition + 1) % gridSize == 0) {
                leftSpacing = false
                outRect.right = 0
                outRect.left = padding
            } else if (leftSpacing) {
                leftSpacing = false
                outRect.left = gridSpacing - padding
                if ((itemPosition + 2) % gridSize == 0) {
                    outRect.right = gridSpacing - padding
                } else {
                    outRect.right = gridSpacing / 2
                }
            } else if ((itemPosition + 2) % gridSize == 0) {
                leftSpacing = false
                outRect.left = gridSpacing / 2
                outRect.right = gridSpacing - padding
            } else {
                leftSpacing = false
                outRect.left = gridSpacing / 2
                outRect.right = gridSpacing / 2
            }
            outRect.bottom = 0
        }
    }
}