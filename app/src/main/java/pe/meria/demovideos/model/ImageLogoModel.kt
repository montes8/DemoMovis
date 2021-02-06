package pe.meria.demovideos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import pe.meria.demovideos.utils.EMPTY

@Parcelize
class ImageLogoModel (
    var id :Int = 0,
    var nameIcon : String = EMPTY
):Parcelable