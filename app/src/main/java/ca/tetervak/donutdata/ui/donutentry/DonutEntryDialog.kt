package ca.tetervak.donutdata.ui.donutentry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ca.tetervak.donutdata.MainViewModel
import ca.tetervak.donutdata.databinding.DonutEntryDialogBinding
import ca.tetervak.donutdata.domain.Donut
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * This dialog allows the user to enter information about a donut, either creating a new
 * entry or updating an existing one.
 */
@AndroidEntryPoint
class DonutEntryDialog : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "DonutEntryDialog"
    }

    private val safeArgs: DonutEntryDialogArgs by navArgs()
    private val donutEntryViewModel: DonutEntryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DonutEntryDialogBinding.inflate(inflater, container, false)

        binding.viewModel = donutEntryViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.saveButton.setOnClickListener {
            mainViewModel.save(
                Donut(
                    id = safeArgs.donutId,
                    name = binding.name.text.toString(),
                    description =  binding.description.text.toString(),
                    rating = binding.ratingBar.rating
                )
            )
            dismiss()
        }

        // User clicked the Cancel button; just exit the dialog without saving the data
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
}
