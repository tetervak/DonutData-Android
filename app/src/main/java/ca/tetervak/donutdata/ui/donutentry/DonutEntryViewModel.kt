package ca.tetervak.donutdata.ui.donutentry

import androidx.lifecycle.*
import ca.tetervak.donutdata.domain.Donut
import ca.tetervak.donutdata.repositories.DonutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DonutEntryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DonutRepository
) : ViewModel() {

    private val donutId: String? = savedStateHandle["donutId"]

    val donut: LiveData<Donut> =
                liveData {
                    if (donutId == null) {
                        emit(Donut(null, "", "", 3.0F))
                    } else {
                        val donut = repository.get(donutId)
                        if(donut != null){
                            emit(donut)
                        }else{
                            emit(Donut(null, "", "", 3F))
                        }
                    }
                }
}
