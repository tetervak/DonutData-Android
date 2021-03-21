package ca.tetervak.donutdata.ui.donutlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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

    // Users of this ViewModel will observe changes to its donuts list to know when
    // to redisplay those changes
    val donuts: LiveData<List<Donut>> = repository.getAll().asLiveData()

//    fun deleteAll() =
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAll()
//        }
}
