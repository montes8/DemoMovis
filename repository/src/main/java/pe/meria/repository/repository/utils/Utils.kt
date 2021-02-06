package pe.meria.repository.repository.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import pe.meria.repository.repository.exception.GenericException
import pe.meria.repository.repository.exception.UnAuthorizeException
import java.lang.Exception

fun getDensity(context: Context): Float {
    return context.resources.displayMetrics.density
}

fun getWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun getHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

enum class ErrorResponse(val code: Int) {
    UNAUTORIZED(440);

    companion object {
        fun returnException(code: Int?, message: String?, detail: String?) : Exception {
            return when (findError(code ?: 0)) {
                UNAUTORIZED -> UnAuthorizeException(message ?: String())
                else -> GenericException(
                    errorCode = code ?: generalErrorCode,
                    errorMessage = message ?: generalErrorMessage,
                    errorMessageDetail = detail ?: generalErrorMessage
                )
            }
        }

        private fun findError(code: Int) = values().find { it.code == code }
    }

}

