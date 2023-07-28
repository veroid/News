package bek.droid.news.common

sealed class UiStateList<out T> {
    data class SUCCESS<out T>(val data: List<T> = emptyList(), var type: String = "") :
        UiStateList<T>()

    data class ERROR(val message: String, var fromServer: Boolean = false) : UiStateList<Nothing>()
    object LOADING : UiStateList<Nothing>()
    object PAGING_END : UiStateList<Nothing>()
    object EMPTY : UiStateList<Nothing>()
}