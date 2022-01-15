package com.example.draggable.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.draggable.R
import com.example.draggable.databinding.FragmentHomeBinding
import com.example.draggable.ui.home.DraggableAdapters.*

class HomeFragment : Fragment(), OnStartDragListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    lateinit var adapter: DragDropRecyclerAdapter
    lateinit var adapter2: RecyclerListAdapter
    lateinit var touchHelper: ItemTouchHelper
    private lateinit var recyclerView: RecyclerView;
    private lateinit var reorder_btn: Button
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var dragHint: LinearLayout


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = DragDropRecyclerAdapter(this)
        adapter2 = RecyclerListAdapter()
        dragHint = binding.dragHintLayout
        recyclerView = binding.recyclerView

        inflateReorderBtn(binding.btnLayout)
        populateListItem()

        val callback: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter)

        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter2


        /* homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }

    private fun populateListItem() {
        adapter.setUsers(ListDataItems.getUsers())
        adapter2.setUsers(ListDataItems.getUsers())
    }

    private fun inflateReorderBtn(root: ViewGroup) {
        root.removeAllViews()
        layoutInflater.inflate(R.layout.reorder_layout, root, true)
        dragHint.visibility = View.GONE
        initReorderBtn(root)
    }

    private fun initReorderBtn(root: ViewGroup) {
        reorder_btn = root.findViewById(R.id.reorder_btn)
        reorder_btn.setOnClickListener {
            inflateDraggerBtn(root)
            adapter.setUsers(ListDataItems.getUsers())
            recyclerView.adapter = adapter
        }
    }

    private fun inflateDraggerBtn(root: ViewGroup) {
        root.removeAllViews()
        layoutInflater.inflate(R.layout.drag_btn_layout, root, true)
        initDraggerBtn(root)
    }

    private fun initDraggerBtn(root: ViewGroup) {
        dragHint.visibility = View.VISIBLE
        btnSave = root.findViewById(R.id.btn_save)
        btnCancel = root.findViewById(R.id.btn_cancel)

        btnSave.setOnClickListener {
            inflateReorderBtn(root)
            adapter.saveData()
            adapter2.setUsers(ListDataItems.getUsers())
            recyclerView.adapter = adapter2
        }

        btnCancel.setOnClickListener {
            inflateReorderBtn(root)
            adapter2.setUsers(ListDataItems.getUsers())
            recyclerView.adapter = adapter2

        }


    }
}