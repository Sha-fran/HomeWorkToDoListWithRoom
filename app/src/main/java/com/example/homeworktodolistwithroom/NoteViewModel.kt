package com.example.homeworktodolistwithroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class NoteViewModel:ViewModel() {
    private val repo = MyApplication.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList)
    val listState:LiveData<ListState> = _listState
    private val observer = Observer<List<Note>> {
        _listState.postValue(ListState.UpdateList(list = it.toMutableList()))
    }

    init {
        repo.getAll().observeForever(observer)
    }

    fun add(note:String){
        repo.add(Note(note = note))
    }

    fun remove(note: Note) {
        repo.removeEmployee(note)
    }

    override fun onCleared() {
        super.onCleared()
        repo.getAll().removeObserver(observer)
    }


    sealed class ListState {
        data object EmptyList:ListState()
        class UpdateList(val list:MutableList<Note>):ListState()
    }
}
