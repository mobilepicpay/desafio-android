package com.picpay.desafio.android.shared.extensions

import android.content.Context
import android.widget.Toast

/**
 * @author Vitor Herrmann on 12/07/2021
 */

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
