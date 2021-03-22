package ca.tetervak.donutdata.ui.donutlist

import androidx.lifecycle.*
import ca.tetervak.donutdata.domain.Donut
import ca.tetervak.donutdata.repositories.DonutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This ViewModel is used to access the underlying data and to observe changes to it.
 */
@HiltViewModel
class DonutListViewModel @Inject constructor(
    private val repository: DonutRepository
) : ViewModel() {

    private val userId = MutableLiveData<String?>()
    fun loadData(id: String) {
        userId.value = id
    }

    fun clearData() {
        userId.value = null
    }

    val donuts: LiveData<List<Donut>> =
        userId.switchMap { id ->
            if (id != null) {
                repository.getAll().asLiveData()
            } else {
                MutableLiveData()
            }
        }
}
