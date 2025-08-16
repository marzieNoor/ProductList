package com.marziehnourmohamadi.productlist.utils

inline fun <T> RequestState<T>.onSuccess(block: (T) -> Unit): RequestState<T> {
    if (this is RequestState.Success) block(data)
    return this
}
inline fun <T> RequestState<T>.onError(block: (String) -> Unit): RequestState<T> {
    if (this is RequestState.Error) block(message)
    return this
}
inline fun <T> RequestState<T>.onLoading(block: () -> Unit): RequestState<T> {
    if (this is RequestState.Loading) block()
    return this
}
