package com.bignerdranch.android.geomain.geoquiz

import androidx.annotation.StringRes

//data class Question(@StringRes val textResId: Int, val answer: Boolean)                                       // первоначально
data class Question(@StringRes val textResId: Int, val answer: Boolean, var answered: Boolean = false)