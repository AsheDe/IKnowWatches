package com.bellalogica.yosderelojes.game.model

sealed class ImageWrapper (open val resource: Any) {
    data class NetworkImage(override val resource: String): ImageWrapper(resource)

    data class ResourcesImage(override val resource: Int): ImageWrapper(resource)

}
