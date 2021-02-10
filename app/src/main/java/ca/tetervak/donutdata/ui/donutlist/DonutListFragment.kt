package ca.tetervak.donutdata.ui.donutlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.donutdata.MainViewModel
import ca.tetervak.donutdata.R
import ca.tetervak.donutdata.databinding.DonutListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment containing the RecyclerView which shows the current list of donuts being tracked.
 */
@AndroidEntryPoint
class DonutListFragment : Fragment() {

    private val donutListViewModel: DonutListViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DonutListFragmentBinding.inflate(inflater, container, false)

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        val adapter = DonutListAdapter(
            onEdit = { donut ->
                findNavController().navigate(
                    DonutListFragmentDirections.actionListToEntry(donut.id)
                )
            },
            onDelete = { donut ->
                mainViewModel.delete(donut)
            }
        )
        binding.recyclerView.adapter = adapter

        binding.viewModel = donutListViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.fab.setOnClickListener {
            findNavController().navigate(
                DonutListFragmentDirections.actionListToEntry(null)
            )
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                    donutListViewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
